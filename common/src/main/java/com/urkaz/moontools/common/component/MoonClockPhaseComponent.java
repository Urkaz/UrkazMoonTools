/*
 * This file is part of "Urkaz Moon Tools".
 * Copyright (C) 2025 Urkaz - Fran Sánchez
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
import com.urkaz.moontools.common.modcompat.handler.ModCompatHandler;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;

public record MoonClockPhaseComponent(int phase, boolean hasData, int color) {
    public static final Codec<MoonClockPhaseComponent> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    Codec.INT.optionalFieldOf("phase", 0).forGetter(MoonClockPhaseComponent::phase),
                    Codec.BOOL.optionalFieldOf("no_data", true).forGetter(MoonClockPhaseComponent::hasData),
                    Codec.INT.optionalFieldOf("color", 0xffffffff).forGetter(MoonClockPhaseComponent::color)
            ).apply(builder, MoonClockPhaseComponent::new)
    );

    public MoonClockPhaseComponent tick(Level level) {
        ResourceLocation worldResourceLocation = level.dimension().location();
        ResourceLocation overworldResourceLocation = BuiltinDimensionTypes.OVERWORLD.location();

        int newColor = 0xffffffff;
        boolean eventActive = ModCompatHandler.getInstance().isLunarEventActive(level);
        if (eventActive) {
            newColor = ModCompatHandler.getInstance().getLunarEventColor(level);
        }

        // Check if the dimension is the OVERWORLD
        if (worldResourceLocation.equals(overworldResourceLocation)) {
            int currentPhase = getMoonPhaseInteger(level);
            if (currentPhase != phase || newColor != color)
                return new MoonClockPhaseComponent(currentPhase, true, newColor);
            else
                return this;
        } else {
            return new MoonClockPhaseComponent(0, false, 0xffffffff);
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
                ByteBufCodecs.INT,
                MoonClockPhaseComponent::color,
                MoonClockPhaseComponent::new);
    }
}