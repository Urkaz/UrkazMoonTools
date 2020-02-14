package com.urkaz.moontools.client;

import com.urkaz.moontools.init.ModBlocks;
import com.urkaz.moontools.init.ModItems;
import com.urkaz.moontools.MoonToolsMod;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@EventBusSubscriber(value = Side.CLIENT, modid = MoonToolsMod.MODID)
public class ModelRegistrationHandler {

	@SubscribeEvent
	public static void onRegisterModels(ModelRegistryEvent event) {
		registerModel(ModItems.MOONCLOCK, 0);
		registerModel(Item.getItemFromBlock(ModBlocks.MOONSENSOR), 0);
	}

	private static void registerModel(Item item, int meta) {
		ModelLoader.setCustomModelResourceLocation(item, meta,
				new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

}