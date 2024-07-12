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

package com.urkaz.moontools;

import com.mrbysco.lunar.Lunar;
import com.urkaz.moontools.common.UMTConfig;
import com.urkaz.moontools.common.modcompat.handler.ModCompatHandler;
import com.urkaz.moontools.common.modcompat.mods.EnhancedCelestialsModCompat;
import com.urkaz.moontools.common.modcompat.mods.LunarModCompat;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public class UrkazMoonTools {

    public static void init() {
        AutoConfig.register(UMTConfig.class, GsonConfigSerializer::new);
        UMTConstants.CONFIG = AutoConfig.getConfigHolder(UMTConfig.class).getConfig();

        ModCompatHandler.getInstance().registerModCompat(new EnhancedCelestialsModCompat());
        ModCompatHandler.getInstance().registerModCompat(new LunarModCompat());
    }
}
