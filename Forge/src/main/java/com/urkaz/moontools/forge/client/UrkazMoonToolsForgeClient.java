/*
 * This file is part of "Urkaz Mod Tools"".
 * Copyright (C) 2023 Urkaz
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