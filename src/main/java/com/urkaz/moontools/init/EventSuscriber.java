package com.urkaz.moontools.init;

import com.urkaz.moontools.MoonToolsMod;
import com.urkaz.moontools.block.MoonSensorBlock;
import com.urkaz.moontools.item.MoonClockItem;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(MoonToolsMod.MODID)
public class EventSuscriber {

	// ITEMS
	public static final Item MOONCLOCK = null;

	// BLOCKS
	public static final Block MOONSENSOR = null;

	@EventBusSubscriber(modid = MoonToolsMod.MODID)
	public static class RegistrationHandler {

		@SubscribeEvent
		public static void registerBlocks(Register<Block> event) {
			final Block[] blocks = { new MoonSensorBlock(false).setRegistryName(MoonToolsMod.MODID, "moonsensor")
					.setTranslationKey(MoonToolsMod.MODID + "." + "moonsensor") };

			event.getRegistry().registerAll(blocks);
		}

		@SubscribeEvent
		public static void registerItems(Register<Item> event) {
			final Item[] items = { new MoonClockItem().setRegistryName(MoonToolsMod.MODID, "moonclock")
					.setTranslationKey(MoonToolsMod.MODID + "." + "moonclock").setCreativeTab(CreativeTabs.TOOLS) };

			final Item[] itemBlocks = {
					new ItemBlock(ModBlocks.MOONSENSOR).setRegistryName(ModBlocks.MOONSENSOR.getRegistryName()), };

			event.getRegistry().registerAll(items);
			event.getRegistry().registerAll(itemBlocks);
		}

	}
}
