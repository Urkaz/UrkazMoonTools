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

package com.urkaz.moontools.common.lib;

import com.mrbysco.lunar.client.MoonHandler;
import com.urkaz.moontools.mixin.Lunar_MoonHandler_Accessor;
import net.minecraft.world.level.Level;

public class LunarSupport {

    static public boolean isLunarEventActive(Level world) {
        return MoonHandler.isEventActive();
    }

    static public int getLunarEventColor(Level world) {
        float[] color = Lunar_MoonHandler_Accessor.getMoonColor();
        if (color != null) {
            int r = (int) (color[0] * 255);
            int g = (int) (color[1] * 255);
            int b = (int) (color[2] * 255);
            return 0xff000000 | r << 16 | g << 8 | b;
        }
        return 0xffffffff;
    }
}
