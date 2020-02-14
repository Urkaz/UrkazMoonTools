package com.urkaz.moontools;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;

@Config(modid = MoonToolsMod.MODID)
public class ModSettings {

	@LangKey("urkazmoontools.config.sensoronlynight")
	@Comment({ "true: The 'Lunar Sensor' only emits redstone at night.",
			"false: The 'Lunar Sensor' emits redstone all day." })
	public static boolean SensorOnlyNight = true;
}
