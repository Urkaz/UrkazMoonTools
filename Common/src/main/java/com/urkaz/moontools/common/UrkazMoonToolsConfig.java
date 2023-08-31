/*
 * This file is part of "Urkaz Mod Tools"".
 * Copyright (C) 2023 Urkaz
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

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
