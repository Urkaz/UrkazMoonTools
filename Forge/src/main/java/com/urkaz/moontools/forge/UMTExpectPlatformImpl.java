package com.urkaz.moontools.forge;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

import java.util.function.BiFunction;

public class UMTExpectPlatformImpl {

    public static String getPlatformName()
    {
        return "Forge";
    }

    public static boolean isModLoaded(String modId)
    {
        return ModList.get().isLoaded(modId);
    }

    public static boolean isDevelopmentEnvironment()
    {
        return !FMLLoader.isProduction();
    }

    static public <T extends BlockEntity> BlockEntityType<T> createBlockEntityType(BiFunction<BlockPos, BlockState, T> func, Block... blocks)
    {
        return BlockEntityType.Builder.of(func::apply, blocks).build(null);
    }
}
