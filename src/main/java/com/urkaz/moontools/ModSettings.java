package com.urkaz.moontools;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Comment;
import net.minecraftforge.common.config.Config.LangKey;

@Config(modid = MoonToolsMod.MODID)
public class ModSettings {

	@LangKey("urkazmoontools.config.sensoronlynight")
	@Comment({ 
		"true (default): The 'Lunar Sensor' only emits redstone at night.",
		"false: The 'Lunar Sensor' emits redstone all day." })
	public static boolean SensorOnlyNight = true;

	@LangKey("urkazmoontools.config.sensorshiftphases")
	@Comment({
		"The 'Lunar Sensor' emits Redstone in the following way:",
		"false:",
		"   Waxing Gibbous 8 | First Quarter 7 | Waxing Crescent 6 | New Moon 5 | Waning Crescent 4 | Third Quarter 3 | Waning Gibbous 2 | Full Moon 1 | Sensor",
		"true (default):",
		"   Full Moon 8 | Waxing Gibbous 7 | First Quarter 6 | Waxing Crescent 5 | New Moon 4 | Waning Crescent 3 | Third Quarter 2 | Waning Gibbous 1 | Sensor",
		})
	public static boolean SensorPhasesShifted = true;
}
