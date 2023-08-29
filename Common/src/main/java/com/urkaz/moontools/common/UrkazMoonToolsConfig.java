package com.urkaz.moontools.common;

import com.urkaz.moontools.Constants;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = Constants.MOD_ID)
@Config.Gui.Background("urkazmoontools:textures/block/moon_sensor_side.png")
public class UrkazMoonToolsConfig implements ConfigData {
    @ConfigEntry.Category("moonsensor")
    @ConfigEntry.Gui.Tooltip(count = 2)
    public boolean sensorOnlyNight = true;

    @ConfigEntry.Category("moonsensor")
    @ConfigEntry.Gui.Tooltip(count = 5)
    public boolean sensorPhasesShifted = true;

    @ConfigEntry.Category("moonsensor")
    @ConfigEntry.Gui.Tooltip(count = 2)
    public boolean emitExtraRedstoneOnLunarEvent = false;
}
