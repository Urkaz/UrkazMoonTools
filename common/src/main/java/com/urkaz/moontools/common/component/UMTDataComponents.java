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

import com.urkaz.moontools.UMTConstants;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;

import java.util.function.UnaryOperator;

public class UMTDataComponents {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.create(UMTConstants.MOD_ID, Registries.DATA_COMPONENT_TYPE);

    public static final RegistrySupplier<DataComponentType<MoonClockPhaseComponent>> MOON_CLOCK_PHASE = register("lodestone_tracker",
            builder -> builder
                    .persistent(MoonClockPhaseComponent.CODEC)
                    .networkSynchronized(MoonClockPhaseComponent.STREAM_CODEC)
                    .cacheEncoding()
    );

    private static <T> RegistrySupplier<DataComponentType<T>> register(final String name, final UnaryOperator<DataComponentType.Builder<T>> builder) {
        return DATA_COMPONENT_TYPES.register(name, () -> builder.apply(DataComponentType.builder()).build());
    }
}
