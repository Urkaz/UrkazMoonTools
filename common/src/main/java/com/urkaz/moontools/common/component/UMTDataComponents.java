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

import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

import static com.urkaz.moontools.common.lib.ResourceLocationHelper.prefixedModLocation;

public class UMTDataComponents {

    public static final DataComponentType<MoonClockPhaseComponent> MOON_CLOCK_PHASE = DataComponentType.<MoonClockPhaseComponent>builder()
                    .persistent(MoonClockPhaseComponent.CODEC)
                    .networkSynchronized(MoonClockPhaseComponent.STREAM_CODEC)
                    .cacheEncoding()
            .build();

    public static void registerComponents(BiConsumer<DataComponentType<?>, ResourceLocation> consumer) {
        consumer.accept(MOON_CLOCK_PHASE, prefixedModLocation("clock_phase"));
    }
}
