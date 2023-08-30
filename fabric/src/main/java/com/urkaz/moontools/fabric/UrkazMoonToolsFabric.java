package com.urkaz.moontools.fabric;

import com.urkaz.moontools.UMTConstants;
import com.urkaz.moontools.UrkazMoonTools;
import com.urkaz.moontools.common.UMTRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.impl.itemgroup.FabricItemGroupBuilderImpl;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

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

        UMTRegistry.registerCreativeTabs(bindCreativeTab());
    }

    private static <T> BiConsumer<T, ResourceLocation> bind(Registry<? super T> registry) {
        return (t, id) -> Registry.register(registry, id, t);
    }

    private static BiConsumer<Consumer<CreativeModeTab.Builder>, ResourceLocation> bindCreativeTab() {
        return (b, rl) ->
        {
            CreativeModeTab.Builder builder = FabricItemGroup.builder(rl);
            b.accept(builder);
            builder.build();
        };
    }
}
