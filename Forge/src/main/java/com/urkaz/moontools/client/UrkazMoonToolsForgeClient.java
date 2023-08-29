package com.urkaz.moontools.client;

import com.urkaz.moontools.Constants;
import com.urkaz.moontools.common.ModRegistry;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class UrkazMoonToolsForgeClient {
    public UrkazMoonToolsForgeClient() {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::clientSetup);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        UrkazMoonToolsConfigMenu.init();

        ItemProperties.register(ModRegistry.ITEM_MOONCLOCK, new ResourceLocation(Constants.MOD_ID, "moonphase"), new MoonPhaseResource());
    }
}
