package com.urkaz.moontools.forge.client;

import com.urkaz.moontools.UMTConstants;
import com.urkaz.moontools.client.MoonClockColorHandler;
import com.urkaz.moontools.client.MoonPhaseResource;
import com.urkaz.moontools.common.UMTRegistry;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
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
        UMTConfigMenu.init();
        ItemProperties.register(UMTRegistry.ITEM_MOONCLOCK, new ResourceLocation(UMTConstants.MOD_ID, "moonphase"), new MoonPhaseResource());
    }

    private void onHandleColors(final RegisterColorHandlersEvent.Item event) {
        event.getItemColors().register(new MoonClockColorHandler(), UMTRegistry.ITEM_MOONCLOCK);
    }
}