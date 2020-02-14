package com.urkaz.moontools.handlers;

import com.urkaz.moontools.MoonToolsMod;

import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigurationHandler {

	@SubscribeEvent
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
		if (event.getModID().equals(MoonToolsMod.MODID)) {
			ConfigManager.sync(MoonToolsMod.MODID, Type.INSTANCE);
		}
	}

}