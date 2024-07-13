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
