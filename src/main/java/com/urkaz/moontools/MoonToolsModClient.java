package com.urkaz.moontools;

import com.urkaz.moontools.client.ConfigScreen;
import com.urkaz.moontools.init.ModItems;
import com.urkaz.moontools.resources.MoonPhaseResource;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static com.urkaz.moontools.MoonToolsMod.MODID;

public class MoonToolsModClient {

    public MoonToolsModClient() {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        modBus.addListener(this::clientSetup);

        modBus.register(ConfigScreen.class);
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        ItemModelsProperties.register(ModItems.MOONCLOCK, new ResourceLocation(MODID, "moonphase"), new MoonPhaseResource());
    }
}
