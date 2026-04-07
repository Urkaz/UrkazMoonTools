package com.urkaz.moontools.common.component;

import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

import static com.urkaz.moontools.common.UMTRegistry.prefixedModLocation;

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
