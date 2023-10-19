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

package com.urkaz.moontools;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.level.Level;

public class PlatformCompatibilitySupport {

    @ExpectPlatform
    static public boolean isLunarEventActive(Level world) {
        return false;
    }

    @ExpectPlatform
    static public int getLunarEventColor(Level world) {
        return 0xffffffff;
    }
}