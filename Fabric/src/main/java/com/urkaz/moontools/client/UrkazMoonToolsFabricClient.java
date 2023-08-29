package com.urkaz.moontools.client;

import com.urkaz.moontools.Constants;
import com.urkaz.moontools.common.MoonToolsRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.resources.ResourceLocation;

public class UrkazMoonToolsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FabricModelPredicateProviderRegistry.register(MoonToolsRegistry.ITEM_MOONCLOCK, new ResourceLocation(Constants.MOD_ID, "moonphase"), new MoonPhaseResource());
        ColorProviderRegistry.ITEM.register(new MoonClockColorHandler(), MoonToolsRegistry.ITEM_MOONCLOCK);
    }
}
