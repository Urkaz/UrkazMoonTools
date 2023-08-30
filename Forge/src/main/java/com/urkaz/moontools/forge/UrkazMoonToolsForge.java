package com.urkaz.moontools.forge;

import com.urkaz.moontools.UMTConstants;
import com.urkaz.moontools.UrkazMoonTools;
import com.urkaz.moontools.common.UMTRegistry;
import com.urkaz.moontools.forge.client.UrkazMoonToolsForgeClient;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod(UMTConstants.MOD_ID)
public class UrkazMoonToolsForge {
    public UrkazMoonToolsForge() {
        coreInit();
        registryInit();
        clientInit();
    }

    private void coreInit() {
        UrkazMoonTools.init();
    }

    private void registryInit() {
        bind(Registries.BLOCK, UMTRegistry::registerBlocks);
        bind(Registries.ITEM, UMTRegistry::registerItems);
        bind(Registries.BLOCK_ENTITY_TYPE, UMTRegistry::registerTiles);
        bind(Registries.CREATIVE_MODE_TAB, UMTRegistry::registerCreativeTabs);
    }

    private void clientInit() {
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> UrkazMoonToolsForgeClient::new);
    }

    private static <T> void bind(ResourceKey<Registry<T>> registry, Consumer<BiConsumer<T, ResourceLocation>> source) {
        FMLJavaModLoadingContext.get().getModEventBus().addListener((RegisterEvent event) -> {
            if (registry.equals(event.getRegistryKey())) {
                source.accept((t, rl) -> event.register(registry, rl, () -> t));
            }
        });
    }
}
