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

public interface IMoonToolsModCompat {
    default boolean isLunarEventActive(Level world) {
        return false;
    }

    default int getLunarEventColor(Level world) {
        return 0xffffffff;
    }

    default Component getLunarEventName(Level world) {
        return null;
    }
}
