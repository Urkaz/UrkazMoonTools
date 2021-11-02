package com.urkaz.moontools;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;

public class ModSettings {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final SensorSettings SETTINGS = new SensorSettings(BUILDER);
    public static final ForgeConfigSpec spec = BUILDER.build();

    public static class SensorSettings
    {
        public final ForgeConfigSpec.ConfigValue<Boolean> SensorOnlyNight;
        public final ForgeConfigSpec.ConfigValue<Boolean> SensorPhasesShifted;
        public final ForgeConfigSpec.ConfigValue<Boolean> EmmitExtraRedstoneOnLunarEvent;

        public SensorSettings(ForgeConfigSpec.Builder builder)
        {
            builder.comment("Urkaz Moon Tools").push(MoonToolsMod.MODID);
            SensorOnlyNight = builder
                    .comment("true (default): The 'Lunar Sensor' only emits redstone at night.",
                            "false: The 'Lunar Sensor' emits redstone all day.")
                    .translation("urkazmoontools.config.sensoronlynight")
                    .define("onlyNight", true);
            SensorPhasesShifted = builder
                    .comment("The 'Lunar Sensor' emits Redstone in the following way:",
                            "false:",
                            "   Waxing Gibbous 8 | First Quarter 7 | Waxing Crescent 6 | New Moon 5 | Waning Crescent 4 | Third Quarter 3 | Waning Gibbous 2 | Full Moon 1 | Sensor",
                            "true (default):",
                            "   Full Moon 8 | Waxing Gibbous 7 | First Quarter 6 | Waxing Crescent 5 | New Moon 4 | Waning Crescent 3 | Third Quarter 2 | Waning Gibbous 1 | Sensor")
                    .translation("urkazmoontools.config.sensorshiftphases")
                    .define("phasesShifted", true);
            EmmitExtraRedstoneOnLunarEvent = builder
                    .comment("urkazmoontools.config.emmitextraonmoonevent",
                            "false (default): The 'Lunar Sensor' emmits the regular amount of redstone on BloodMoon.",
                            "true: The 'Lunar Sensor' emmits 9 units of Redstone during Bloodmoon, and 10 during Harvestmoon.")
                    .translation("urkazmoontools.config.sensorshiftphases")
                    .define("extraRedstoneOnLunearEvent", false);
            builder.pop();
        }
    }
}
