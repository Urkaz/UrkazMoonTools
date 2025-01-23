/*
 * This file is part of "Urkaz Moon Tools".
 * Copyright (C) 2025 Urkaz - Fran Sánchez
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package com.urkaz.moontools.neoforge.client;

import com.urkaz.moontools.UMTConstants;
import com.urkaz.moontools.UMTExpectPlatform;
import com.urkaz.moontools.client.MoonClockColorHandler;
import com.urkaz.moontools.client.MoonPhaseResource;
import com.urkaz.moontools.common.UMTConfigWrapper;
import com.urkaz.moontools.common.UMTRegistry;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;


@Mod(value = UMTConstants.MOD_ID, dist = Dist.CLIENT)
@EventBusSubscriber(value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD, modid = UMTConstants.MOD_ID)
public class UrkazMoonToolsNeoForgeClient {

    public UrkazMoonToolsNeoForgeClient(IEventBus modBus) {
    }

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemProperties.register(UMTRegistry.ITEM_MOONCLOCK.get(), ResourceLocation.fromNamespaceAndPath(UMTConstants.MOD_ID, "moonphase"), new MoonPhaseResource());
        });

        if (UMTExpectPlatform.isClothConfigLoaded()) {
            ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (container, parent) -> {
                return AutoConfig.getConfigScreen(UMTConfigWrapper.UMTConfig.class, parent).get();
            });
        }
    }

    @SubscribeEvent
    private static void onHandleColors(final RegisterColorHandlersEvent.Item event) {
        event.getItemColors().register(new MoonClockColorHandler(), UMTRegistry.ITEM_MOONCLOCK.get());
    }
}


