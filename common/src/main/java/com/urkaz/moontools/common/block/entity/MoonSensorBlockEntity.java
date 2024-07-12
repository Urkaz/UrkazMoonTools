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

package com.urkaz.moontools.common.block.entity;

import com.urkaz.moontools.common.UMTRegistry;
import com.urkaz.moontools.common.block.MoonSensorBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MoonSensorBlockEntity extends BlockEntity {

    public MoonSensorBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(UMTRegistry.BLOCKENTITY_MOONSENSOR.get(), blockPos, blockState);
    }

    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, MoonSensorBlockEntity thisEntity) {
        BlockState blockstate = thisEntity.getBlockState();
        Block block = blockstate.getBlock();
        if (block instanceof MoonSensorBlock) {
            ((MoonSensorBlock) block).updateSignalStrength(blockstate, thisEntity.level, thisEntity.worldPosition);
        }
    }
}
