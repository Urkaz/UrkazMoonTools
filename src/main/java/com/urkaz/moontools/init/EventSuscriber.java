package com.urkaz.moontools.init;

import com.urkaz.moontools.MoonToolsMod;
import com.urkaz.moontools.tileentity.MoonSensorTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventSuscriber {

    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> event)
    {
        TileEntityType type = TileEntityType.Builder.of(MoonSensorTileEntity::new, ModBlocks.MOONSENSOR).build(null);
        type.setRegistryName(MoonToolsMod.MODID, "moonsensor");
        ForgeRegistries.TILE_ENTITIES.register(type);

        ModBlocks.MOONSENSOR_TILEENTITY = type;
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        BlockItem BlockMOONSENSOR = new BlockItem(ModBlocks.MOONSENSOR, new Item.Properties().tab(ItemGroup.TAB_REDSTONE));
        BlockMOONSENSOR.setRegistryName(MoonToolsMod.MODID, "moonsensor");

        ForgeRegistries.BLOCKS.register(ModBlocks.MOONSENSOR);
        ForgeRegistries.ITEMS.register(BlockMOONSENSOR);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ForgeRegistries.ITEMS.register(ModItems.MOONCLOCK);
    }

}
