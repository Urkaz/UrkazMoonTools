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

package com.urkaz.moontools.common.modcompat.handler;

import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class ModCompatHandler {

    private static ModCompatHandler instance;

    List<IMoonToolsModCompat> ModCompatList = new ArrayList<>();

    private ModCompatHandler() {
    }

    public static ModCompatHandler getInstance() {
        if (instance == null) {
            instance = new ModCompatHandler();
        }
        return instance;
    }

    public void registerModCompat(IMoonToolsModCompat ModCompat) {
        ModCompatList.add(ModCompat);
    }

    public boolean isLunarEventActive(Level world) {
        for (IMoonToolsModCompat iMoonToolsModCompat : ModCompatList) {
            boolean eventActive = iMoonToolsModCompat.isLunarEventActive(world);
            if (eventActive)
                return true;
        }

        return false;
    }

    public int getLunarEventColor(Level world) {
        for (IMoonToolsModCompat iMoonToolsModCompat : ModCompatList) {
            int color = iMoonToolsModCompat.getLunarEventColor(world);
            if (color != 0xffffffff)
                return color;
        }
        return 0xffffffff;
    }

    public Component getLunarEventName(Level world) {
        for (IMoonToolsModCompat iMoonToolsModCompat : ModCompatList) {
            Component eventName = iMoonToolsModCompat.getLunarEventName(world);
            if (eventName != null)
                return eventName;
        }
        return null;
    }
}
