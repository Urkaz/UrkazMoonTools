/*
 * This file is part of "Urkaz Moon Tools".
 * Copyright (C) 2026 Urkaz - Fran Sánchez
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

package com.urkaz.moontools.neoforge;

import com.urkaz.moontools.UMTConstants;
import com.urkaz.moontools.UrkazMoonTools;
import com.urkaz.moontools.common.UMTRegistry;
import com.urkaz.moontools.common.component.UMTDataComponents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod(value = UMTConstants.MOD_ID)
public final class UrkazMoonToolsNeoForge {
    public UrkazMoonToolsNeoForge(IEventBus modBus, ModContainer modContainer) {
        modBus.register(this);
        coreInit();
    }

    private void coreInit() {
        UrkazMoonTools.init();
    }

    @SubscribeEvent
    private void registryInit(RegisterEvent event) {
        bind(event, Registries.BLOCK, UMTRegistry::registerBlocks);
        bind(event, Registries.ITEM, UMTRegistry::registerItems);
        bind(event, Registries.BLOCK_ENTITY_TYPE, UMTRegistry::registerBlockEntities);
        bind(event, Registries.DATA_COMPONENT_TYPE, UMTDataComponents::registerComponents);
        bind(event, Registries.CREATIVE_MODE_TAB, (consumer -> {
            consumer.accept(
                    CreativeModeTab.builder()
                            .title(Component.translatable("urkazmoontools.creative_tab").withStyle((style -> style.withColor(ChatFormatting.WHITE))))
                            .icon(() -> new ItemStack(UMTRegistry.ITEM_MOONCLOCK))
                            .displayItems((params, output) -> {
                                UMTRegistry.createDefaultCreativeTab(output);
                            })
                            .build(),
                    UMTRegistry.UMC_CREATIVE_KEY.location()
            );
        }));
    }

    private static <T> void bind(RegisterEvent event, ResourceKey<Registry<T>> registryKey, Consumer<BiConsumer<T, ResourceLocation>> source) {
        Registry<T> registry = event.getRegistry(registryKey);
        if (registry != null) {
            source.accept((t, rl) -> Registry.register(registry, rl, t));
        }
    }
}
