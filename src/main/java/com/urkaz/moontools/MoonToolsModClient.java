package com.urkaz.moontools;

import com.urkaz.moontools.client.ConfigScreen;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class MoonToolsModClient {

    public MoonToolsModClient() {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        modBus.register(ConfigScreen.class);
    }
}
