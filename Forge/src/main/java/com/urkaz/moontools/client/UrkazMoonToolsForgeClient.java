package com.urkaz.moontools.client;

import com.urkaz.moontools.Constants;
import com.urkaz.moontools.common.MoonToolsRegistry;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class UrkazMoonToolsForgeClient {
    public UrkazMoonToolsForgeClient() {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::clientSetup);
        modBus.addListener(this::onHandleColors);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        UrkazMoonToolsConfigMenu.init();

        ItemProperties.register(MoonToolsRegistry.ITEM_MOONCLOCK, new ResourceLocation(Constants.MOD_ID, "moonphase"), new MoonPhaseResource());
    }

    private void onHandleColors(final ColorHandlerEvent.Item event) {
        event.getItemColors().register(new MoonClockColorHandler(), MoonToolsRegistry.ITEM_MOONCLOCK);
    }
}
