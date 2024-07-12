/*
 * This file is part of "UrkazMoonTools".
 * Copyright (C) 2024 Urkaz - Fran Sánchez
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
import com.urkaz.moontools.common.modcompat.handler.IMoonToolsModCompat;
import com.urkaz.moontools.common.modcompat.handler.ModCompatHandler;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UMTConfigWrapper {

    private static UMTConfig instance;
    public static final String CLOTH_CONFIG = "cloth-config";

    public static @Nullable UMTConfig getConfig() {
        if (instance == null && UMTExpectPlatform.isModLoaded(CLOTH_CONFIG)) {
            instance = new UMTConfig();
        }
        return instance;
    }

    public static void setConfig(UMTConfig config) {
        instance = config;
    }

    @Config(name = UMTConstants.MOD_ID)
    @Config.Gui.Background("urkazmoontools:textures/block/moon_sensor_side.png")
    public static class UMTConfig implements ConfigData {
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
}