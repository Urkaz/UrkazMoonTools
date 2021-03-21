package com.urkaz.moontools.init;

import com.urkaz.moontools.MoonToolsMod;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(MoonToolsMod.MODID)
public class ModBlocks {
    public static Block MOONSENSOR = null;

    public static TileEntityType<?> MOONSENSOR_TILEENTITY;
}
