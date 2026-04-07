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

package com.urkaz.moontools.common;

import com.urkaz.moontools.UMTConstants;
import com.urkaz.moontools.UMTExpectPlatform;
import com.urkaz.moontools.UrkazMoonTools;
import com.urkaz.moontools.common.block.MoonSensorBlock;
import com.urkaz.moontools.common.block.entity.MoonSensorBlockEntity;
import com.urkaz.moontools.common.item.MoonClockItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public class UMTRegistry {

    public static final Block BLOCK_MOONSENSOR = new MoonSensorBlock(BlockBehaviour.Properties.of().strength(0.2F).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY));
    public static final Item ITEM_MOONCLOCK = new MoonClockItem(new Item.Properties().stacksTo(1));
    public static final Item ITEM_BLOCK_MOONSENSOR = new BlockItem(BLOCK_MOONSENSOR, new Item.Properties());

    public static final ResourceKey<CreativeModeTab> UMC_CREATIVE_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB,
            new ResourceLocation(UMTConstants.MOD_ID, "urkazmoontools"));

    public static final BlockEntityType<MoonSensorBlockEntity> BLOCKENTITY_MOONSENSOR = createBEType(
            MoonSensorBlockEntity::new, UMTRegistry.BLOCK_MOONSENSOR);

    public static void createDefaultCreativeTab(CreativeModeTab.Output output) {
        output.accept(UMTRegistry.ITEM_MOONCLOCK);
        output.accept(UMTRegistry.BLOCK_MOONSENSOR);
    }

    public static void registerBlocks(BiConsumer<Block, ResourceLocation> r) {
        r.accept(BLOCK_MOONSENSOR, prefixedModLocation("moonsensor_block"));
    }

    public static void registerItems(BiConsumer<Item, ResourceLocation> r) {
        r.accept(ITEM_MOONCLOCK, prefixedModLocation("moonclock_item"));
        r.accept(ITEM_BLOCK_MOONSENSOR, prefixedModLocation("moonsensor_blockitem"));
    }

    public static <T extends BlockEntity> BlockEntityType<T> createBEType(BiFunction<BlockPos, BlockState, T> func, Block... blocks) {
        return UMTExpectPlatform.createBlockEntityType(func, blocks);
    }

    public static void registerBlockEntities(BiConsumer<BlockEntityType<?>, ResourceLocation> r) {
        r.accept(BLOCKENTITY_MOONSENSOR, prefixedModLocation("moonsensor_entity"));
    }

    public static ResourceLocation prefixedModLocation(String path) {
        return new ResourceLocation(UMTConstants.MOD_ID, path);
    }
}