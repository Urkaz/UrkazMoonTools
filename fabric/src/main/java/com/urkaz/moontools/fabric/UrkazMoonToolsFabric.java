/*
 * This file is part of "Urkaz Mod Tools".
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

package com.urkaz.moontools.fabric;

import com.urkaz.moontools.UrkazMoonTools;
import com.urkaz.moontools.common.UMTRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class UrkazMoonToolsFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        coreInit();
        registryInit();
    }

    private void coreInit() {
        UrkazMoonTools.init();
    }

    private void registryInit() {
        UMTRegistry.registerBlocks(bind(BuiltInRegistries.BLOCK));
        UMTRegistry.registerItems(bind(BuiltInRegistries.ITEM));
        UMTRegistry.registerTiles(bind(BuiltInRegistries.BLOCK_ENTITY_TYPE));

        UMTRegistry.registerCreativeTabs(bindCreativeTab());
    }

    private static <T> BiConsumer<T, ResourceLocation> bind(Registry<? super T> registry) {
        return (t, id) -> Registry.register(registry, id, t);
    }

    private static BiConsumer<Consumer<CreativeModeTab.Builder>, ResourceLocation> bindCreativeTab() {
        return (b, rl) ->
        {
            CreativeModeTab.Builder builder = FabricItemGroup.builder(rl);
            b.accept(builder);
            builder.build();
        };
    }
}
