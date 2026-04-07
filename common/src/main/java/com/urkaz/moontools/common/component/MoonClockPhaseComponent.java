/*
 * This file is part of "Urkaz Moon Tools".
 * Copyright (C) 2026 Urkaz - Fran Sánchez
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package com.urkaz.moontools.common.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;

public record MoonClockPhaseComponent(int phase, boolean hasData) {
    public static final Codec<MoonClockPhaseComponent> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    Codec.INT.optionalFieldOf("phase", 0).forGetter(MoonClockPhaseComponent::phase),
                    Codec.BOOL.optionalFieldOf("no_data", true).forGetter(MoonClockPhaseComponent::hasData)
            ).apply(builder, MoonClockPhaseComponent::new)
    );

    public MoonClockPhaseComponent tick(Level level) {
        ResourceLocation worldResourceLocation = level.dimension().location();
        ResourceLocation overworldResourceLocation = BuiltinDimensionTypes.OVERWORLD.location();

        // Check if the dimension is the OVERWORLD
        if (worldResourceLocation.equals(overworldResourceLocation)) {
            int currentPhase = getMoonPhaseInteger(level);
            if (currentPhase != phase)
                return new MoonClockPhaseComponent(currentPhase, true);
            else
                return this;
        }
        else {
            return new MoonClockPhaseComponent(0, true);
        }
    }

    static public int getMoonPhaseInteger(Level level) {
        return level.dimensionType().moonPhase(level.getLevelData().getDayTime());
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, MoonClockPhaseComponent> STREAM_CODEC;

    static {
        STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.INT,
                MoonClockPhaseComponent::phase,
                ByteBufCodecs.BOOL,
                MoonClockPhaseComponent::hasData,
                MoonClockPhaseComponent::new);
    }
}