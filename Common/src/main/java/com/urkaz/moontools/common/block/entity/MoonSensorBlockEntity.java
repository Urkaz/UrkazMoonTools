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
        super(UMTRegistry.BLOCKENTITY_MOONSENSOR, blockPos, blockState);
    }

    public static void serverTick(Level level, BlockPos blockPos, BlockState blockState, MoonSensorBlockEntity thisEntity) {
        BlockState blockstate = thisEntity.getBlockState();
        Block block = blockstate.getBlock();
        if (block instanceof MoonSensorBlock) {
            ((MoonSensorBlock) block).updateSignalStrength(blockstate, thisEntity.level, thisEntity.worldPosition);
        }
    }
}
