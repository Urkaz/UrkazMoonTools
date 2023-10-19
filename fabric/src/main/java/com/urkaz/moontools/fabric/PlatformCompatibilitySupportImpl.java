/*
 * This file is part of "Urkaz Mod Tools".
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

package com.urkaz.moontools.fabric;

import com.urkaz.moontools.UMTExpectPlatform;
import draylar.crimsonmoon.CrimsonMoonClient;
import net.minecraft.world.level.Level;

public class PlatformCompatibilitySupportImpl {

    public static final String MOD_CRIMSON_MOON_ID = "crimsonmoon";

    public static boolean isLunarEventActive(Level world) {
        if (world == null) {
            return false;
        }

        boolean eventActive = false;

        // Crimson Moon
        if (UMTExpectPlatform.isModLoaded(MOD_CRIMSON_MOON_ID)) {
            eventActive |= CrimsonMoonClient.crimsonMoonPresent;
        }

        return eventActive;
    }

    public static int getLunarEventColor(Level world) {
        int color = 0xffffffff;

        if (world == null) {
            return color;
        }

        // Crimson Moon
        if (UMTExpectPlatform.isModLoaded(MOD_CRIMSON_MOON_ID)) {
            if (CrimsonMoonClient.crimsonMoonPresent) {
                color = 0xffff0000;
            }
        }

        return color;
    }
}
