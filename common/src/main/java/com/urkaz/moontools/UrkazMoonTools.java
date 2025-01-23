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

package com.urkaz.moontools;

import com.urkaz.moontools.common.UMTConfigWrapper;
import com.urkaz.moontools.common.UMTRegistry;
import com.urkaz.moontools.common.component.UMTDataComponents;
import com.urkaz.moontools.common.modcompat.handler.ModCompatHandler;
import com.urkaz.moontools.common.modcompat.mods.EnhancedCelestialsModCompat;
import com.urkaz.moontools.common.modcompat.mods.LunarModCompat;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public class UrkazMoonTools {

    public static void init() {
        if (UMTExpectPlatform.isClothConfigLoaded()) {
            AutoConfig.register(UMTConfigWrapper.UMTConfig.class, GsonConfigSerializer::new);
            UMTConfigWrapper.setConfig(AutoConfig.getConfigHolder(UMTConfigWrapper.UMTConfig.class).getConfig());
        }

        if (UMTExpectPlatform.isModLoaded(EnhancedCelestialsModCompat.MOD_ENHANCED_CELESTIALS_ID)) {
            ModCompatHandler.getInstance().registerModCompat(new EnhancedCelestialsModCompat());
        }
        if (UMTExpectPlatform.isModLoaded(LunarModCompat.MOD_LUNAR_ID)) {
            ModCompatHandler.getInstance().registerModCompat(new LunarModCompat());
        }
    }

    public static void registryInit() {
        UMTRegistry.BLOCKS.register();
        UMTRegistry.ITEMS.register();
        UMTRegistry.BLOCK_ENTITIES.register();
        UMTRegistry.CREATIVE_MODE_TABS.register();
        UMTDataComponents.DATA_COMPONENT_TYPES.register();
    }
}
