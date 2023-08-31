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

package com.urkaz.moontools.common;

import com.urkaz.moontools.common.block.MoonSensorBlock;
import com.urkaz.moontools.common.item.MoonClockItem;
import com.urkaz.moontools.common.block.entity.MoonSensorBlockEntity;
import com.urkaz.moontools.UMTExpectPlatform;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static com.urkaz.moontools.common.lib.ResourceLocationHelper.prefix;

public class UMTRegistry {

    public static final Block BLOCK_MOONSENSOR = new MoonSensorBlock(BlockBehaviour.Properties.of().strength(0.2F).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY));
    public static final BlockItem ITEM_BLOCK_MOONSENSOR = new BlockItem(BLOCK_MOONSENSOR, new Item.Properties());
    public static final Item ITEM_MOONCLOCK = new MoonClockItem(new Item.Properties().stacksTo(1));
    public static final BlockEntityType<MoonSensorBlockEntity> BLOCKENTITY_MOONSENSOR = CreateEntity(MoonSensorBlockEntity::new, BLOCK_MOONSENSOR);

    private static <T extends BlockEntity> BlockEntityType<T> CreateEntity(BiFunction<BlockPos, BlockState, T> func, Block... blocks) {
        return UMTExpectPlatform.createBlockEntityType(func, blocks);
    }

    public static void registerBlocks(BiConsumer<Block, ResourceLocation> r) {
        r.accept(BLOCK_MOONSENSOR, prefix("moonsensor_block"));
    }

    public static void registerItems(BiConsumer<Item, ResourceLocation> r) {
        r.accept(ITEM_BLOCK_MOONSENSOR, prefix("moonsensor_blockitem"));
        r.accept(ITEM_MOONCLOCK, prefix("moonclock_item"));
    }

    public static void registerTiles(BiConsumer<BlockEntityType<?>, ResourceLocation> r) {
        r.accept(BLOCKENTITY_MOONSENSOR, prefix("moonsensor_entity"));
    }

    public static void registerCreativeTabs(BiConsumer<CreativeModeTab, ResourceLocation>  r)
    {
        r.accept(CreativeModeTab.builder(null, -1)
                    .title(Component.translatable("urkazmoontools.creative_tab"))
                    .icon(() -> new ItemStack(ITEM_MOONCLOCK))
                    .displayItems((enabledFlags, populator) -> {
                        populator.accept(ITEM_MOONCLOCK);
                        populator.accept(BLOCK_MOONSENSOR);
                    })
                    .build()
        , prefix("creative_tab"));
    }
}