/*
 * This file is part of "UrkazMoonTools".
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

package com.urkaz.moontools.neoforge;

import com.urkaz.moontools.UMTConstants;
import com.urkaz.moontools.UrkazMoonTools;
import com.urkaz.moontools.common.UMTRegistry;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod(value = UMTConstants.MOD_ID)
public final class UrkazMoonToolsNeoForge {
    public UrkazMoonToolsNeoForge() {
        coreInit();
        registryInit();
    }

    private void coreInit() {
        UrkazMoonTools.init();
    }

    private void registryInit() {
        bind(Registries.BLOCK, UMTRegistry::registerBlocks);
        bind(Registries.ITEM, UMTRegistry::registerItems);
        bind(Registries.BLOCK_ENTITY_TYPE, UMTRegistry::registerBlockEntities);
        bind(Registries.CREATIVE_MODE_TAB, (consumer -> {
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

    private static <T> void bind(ResourceKey<Registry<T>> registry, Consumer<BiConsumer<T, ResourceLocation>> source) {
        FMLJavaModLoadingContext.get().getModEventBus().addListener((RegisterEvent event) -> {
            if (registry.equals(event.getRegistryKey())) {
                source.accept((t, rl) -> event.register(registry, rl, () -> t));
            }
        });
    }
}
