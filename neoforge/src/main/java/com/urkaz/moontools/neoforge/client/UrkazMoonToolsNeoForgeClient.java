/*
 * This file is part of "AlphaSIN 55".
 * Copyright (C) 2024 Urkaz - Fran Sánchez
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
import com.urkaz.moontools.client.MoonClockColorHandler;
import com.urkaz.moontools.client.MoonPhaseResource;
import com.urkaz.moontools.common.UMTRegistry;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD, modid = UMTConstants.MOD_ID)
public class UrkazMoonToolsNeoForgeClient {

    public UrkazMoonToolsNeoForgeClient(IEventBus modBus) {
    }

    @SubscribeEvent
    public static void clientSetup(final FMLClientSetupEvent event) {
        //NeoForge.EVENT_BUS.addListener(UrkazMoonToolsNeoForgeClient::onHandleColors);
        event.enqueueWork(() -> {
            ItemProperties.register(UMTRegistry.ITEM_MOONCLOCK.get(), new ResourceLocation(UMTConstants.MOD_ID, "moonphase"), new MoonPhaseResource());
        });
    }

    @SubscribeEvent
    private static void onHandleColors(final RegisterColorHandlersEvent.Item event) {
        event.getItemColors().register(new MoonClockColorHandler(), UMTRegistry.ITEM_MOONCLOCK.get());
    }
}
