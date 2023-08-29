package com.urkaz.moontools;

import com.urkaz.moontools.client.UrkazMoonToolsForgeClient;
import com.urkaz.moontools.common.MoonToolsRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod(Constants.MOD_ID)
public class UrkazMoonToolsForge {
    
    public UrkazMoonToolsForge() {
        coreInit();
        registryInit();
        clientInit();
    }

    private void coreInit() {
        UrkazMoonToolsCommon.init();
    }

    private void registryInit() {
        bind(ForgeRegistries.BLOCKS, MoonToolsRegistry::registerBlocks);
        bind(ForgeRegistries.ITEMS, MoonToolsRegistry::registerItems);
        bind(ForgeRegistries.BLOCK_ENTITIES, MoonToolsRegistry::registerTiles);
    }

    private void clientInit() {
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> UrkazMoonToolsForgeClient::new);
    }

    private static <T extends IForgeRegistryEntry<T>> void bind(IForgeRegistry<T> registry, Consumer<BiConsumer<T, ResourceLocation>> source) {
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(registry.getRegistrySuperType(),
                (RegistryEvent.Register<T> event) -> {
                    IForgeRegistry<T> forgeRegistry = event.getRegistry();
                    source.accept((t, rl) -> {
                        t.setRegistryName(rl);
                        forgeRegistry.register(t);
                    });
                });
    }
}