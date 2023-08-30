package com.urkaz.moontools.forge;

import com.urkaz.moontools.UMTConstants;
import com.urkaz.moontools.UMTExpectPlatform;
import com.urkaz.moontools.UrkazMoonTools;
import com.urkaz.moontools.client.MoonPhaseResource;
import com.urkaz.moontools.common.UMTRegistry;
import com.urkaz.moontools.forge.client.UMTConfigMenu;
import com.urkaz.moontools.forge.client.UrkazMoonToolsForgeClient;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
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

        bindCreativeTab(UMTRegistry::registerCreativeTabs);
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

    private static <T> void bindCreativeTab(Consumer<BiConsumer<Consumer<CreativeModeTab.Builder>, ResourceLocation>> source) {
        FMLJavaModLoadingContext.get().getModEventBus().addListener((CreativeModeTabEvent.Register event) -> {
            if (CreativeModeTabEvent.Register.class.equals(event.getClass())) {
                source.accept((t, rl) -> event.registerCreativeModeTab(rl, t));
            }
        });
    }
}
