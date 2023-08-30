package com.urkaz.moontools.fabric;

import com.urkaz.moontools.UrkazMoonTools;
import com.urkaz.moontools.common.UMTRegistry;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

public class UrkazMoonToolsFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        coreInit();
        registryInit();
    }

    private void coreInit() {
        UrkazMoonTools.init();
    }

    private void registryInit() {
        UMTRegistry.registerBlocks(bind(BuiltInRegistries.BLOCK));
        UMTRegistry.registerItems(bind(BuiltInRegistries.ITEM));
        UMTRegistry.registerTiles(bind(BuiltInRegistries.BLOCK_ENTITY_TYPE));
        UMTRegistry.registerCreativeTabs(bind(BuiltInRegistries.CREATIVE_MODE_TAB));
    }

    private static <T> BiConsumer<T, ResourceLocation> bind(Registry<? super T> registry) {
        return (t, id) -> Registry.register(registry, id, t);
    }
}
