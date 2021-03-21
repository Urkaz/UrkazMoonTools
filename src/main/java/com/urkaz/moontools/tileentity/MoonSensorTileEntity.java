package com.urkaz.moontools.tileentity;

import com.urkaz.moontools.block.MoonSensorBlock;
import com.urkaz.moontools.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class MoonSensorTileEntity extends TileEntity implements ITickableTileEntity {

    public MoonSensorTileEntity() {
        super(ModBlocks.MOONSENSOR_TILEENTITY);
    }

    @Override
    public void tick() {
        BlockState blockstate = this.getBlockState();
        Block block = blockstate.getBlock();
        if (block instanceof MoonSensorBlock)
        {
            ((MoonSensorBlock)block).updatePower(this.world, this.pos);
        }
    }
}
