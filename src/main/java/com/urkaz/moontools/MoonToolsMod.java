package com.urkaz.moontools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.urkaz.moontools.handlers.ConfigurationHandler;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = MoonToolsMod.MODID, name = MoonToolsMod.NAME, version = MoonToolsMod.VERSION, acceptedMinecraftVersions = MoonToolsMod.MC_VERSION)
public class MoonToolsMod {
	public static final String MODID = "urkazmoontools";
	public static final String NAME = "Urkaz Moon Tools";
	public static final String VERSION = "1.2";
	public static final String MC_VERSION = "[1.12.2]";

	public static final Logger LOGGER = LogManager.getLogger(MoonToolsMod.MODID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(new ConfigurationHandler());
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}
}
