package com.urkaz.moontools.common;

import com.urkaz.moontools.common.block.MoonSensorBlock;
import com.urkaz.moontools.common.item.MoonClockItem;
import com.urkaz.moontools.platform.Services;
import com.urkaz.moontools.common.block.entity.MoonSensorBlockEntity;
import net.minecraft.core.BlockPos;
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
import net.minecraft.world.level.material.Material;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

import static com.urkaz.moontools.common.lib.ResourceLocationHelper.prefix;

public class ModRegistry {

    public static final Block BLOCK_MOONSENSOR = new MoonSensorBlock(BlockBehaviour.Properties.of(Material.WOOD).strength(0.2F).sound(SoundType.WOOD));
    public static final BlockItem ITEM_BLOCK_MOONSENSOR = new BlockItem(BLOCK_MOONSENSOR, new Item.Properties().tab(CreativeModeTab.TAB_REDSTONE));
    public static final Item ITEM_MOONCLOCK = new MoonClockItem(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1));
    public static final BlockEntityType<MoonSensorBlockEntity> BLOCKENTITY_MOONSENSOR = CreateEntity(MoonSensorBlockEntity::new, BLOCK_MOONSENSOR);

    private static <T extends BlockEntity> BlockEntityType<T> CreateEntity(BiFunction<BlockPos, BlockState, T> func, Block... blocks) {
        return Services.PLATFORM.createBlockEntityType(func, blocks);
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
}