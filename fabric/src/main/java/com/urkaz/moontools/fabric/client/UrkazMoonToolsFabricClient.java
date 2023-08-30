package com.urkaz.moontools.fabric.client;

import com.urkaz.moontools.UMTConstants;
import com.urkaz.moontools.client.MoonClockColorHandler;
import com.urkaz.moontools.client.MoonPhaseResource;
import com.urkaz.moontools.common.UMTRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.resources.ResourceLocation;

public class UrkazMoonToolsFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FabricModelPredicateProviderRegistry.register(UMTRegistry.ITEM_MOONCLOCK, new ResourceLocation(UMTConstants.MOD_ID, "moonphase"), new MoonPhaseResource());
        ColorProviderRegistry.ITEM.register(new MoonClockColorHandler(), UMTRegistry.ITEM_MOONCLOCK);
    }
}
