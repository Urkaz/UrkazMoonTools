/*
 * This file is part of "Urkaz Moon Tools".
 * Copyright (C) 2025 Urkaz - Fran Sánchez
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

import com.urkaz.moontools.UMTConstants;
import com.urkaz.moontools.UMTExpectPlatform;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import org.jetbrains.annotations.Nullable;

public class UMTConfigWrapper {

    private static UMTConfig instance;

    public static @Nullable UMTConfig getConfig() {
        if (instance == null && UMTExpectPlatform.isClothConfigLoaded()) {
            instance = new UMTConfig();
        }
        return instance;
    }

    public static void setConfig(UMTConfig config) {
        instance = config;
    }

    @Config(name = UMTConstants.MOD_ID)
    public static class UMTConfig implements ConfigData {
        @ConfigEntry.Category("moonsensor")
        @ConfigEntry.Gui.Tooltip(count = 2)
        public boolean sensorOnlyNight = true;

        @ConfigEntry.Category("moonsensor")
        @ConfigEntry.Gui.Tooltip(count = 2)
        public boolean emitExtraRedstoneOnLunarEvent = false;

        @ConfigEntry.Category("moonsensor.redstone")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 15)
        public int waningGibbous = 1;

        @ConfigEntry.Category("moonsensor.redstone")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 15)
        public int thirdQuarter = 2;

        @ConfigEntry.Category("moonsensor.redstone")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 15)
        public int waningCrescent = 3;

        @ConfigEntry.Category("moonsensor.redstone")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 15)
        public int newMoon = 4;

        @ConfigEntry.Category("moonsensor.redstone")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 15)
        public int waxingCrescent = 5;

        @ConfigEntry.Category("moonsensor.redstone")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 15)
        public int firstQuarter = 6;

        @ConfigEntry.Category("moonsensor.redstone")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 15)
        public int waxingGibbous = 7;

        @ConfigEntry.Category("moonsensor.redstone")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 15)
        public int fullMoon = 8;

        @ConfigEntry.Category("moonsensor.redstone")
        @ConfigEntry.BoundedDiscrete(min = 0, max = 15)
        @ConfigEntry.Gui.Tooltip()
        public int duringEvents = 9;

        @ConfigEntry.Category("moonclock")
        @ConfigEntry.Gui.Tooltip(count = 2)
        public boolean disableRightClick = false;
    }
}