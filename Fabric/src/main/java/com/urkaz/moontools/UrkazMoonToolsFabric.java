package com.urkaz.moontools;

import com.urkaz.moontools.common.MoonToolsRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

public class UrkazMoonToolsFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        coreInit();
        registryInit();
    }

    private void coreInit() {
        UrkazMoonToolsCommon.init();
    }

    private void registryInit() {
        MoonToolsRegistry.registerBlocks(bind(Registry.BLOCK));
        MoonToolsRegistry.registerItems(bind(Registry.ITEM));
        MoonToolsRegistry.registerTiles(bind(Registry.BLOCK_ENTITY_TYPE));
    }

    private static <T> BiConsumer<T, ResourceLocation> bind(Registry<? super T> registry) {
        return (t, id) -> Registry.register(registry, id, t);
    }
}
