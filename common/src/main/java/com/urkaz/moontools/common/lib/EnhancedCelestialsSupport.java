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

import com.urkaz.moontools.UMTExpectPlatform;
import corgitaco.enhancedcelestials.EnhancedCelestialsWorldData;
import corgitaco.enhancedcelestials.api.lunarevent.LunarEvent;
import corgitaco.enhancedcelestials.core.EnhancedCelestialsContext;
import corgitaco.enhancedcelestials.lunarevent.LunarForecast;
import net.minecraft.core.Holder;
import net.minecraft.world.level.Level;

public class EnhancedCelestialsSupport {

    public static final String MOD_ENHANCED_CELESTIALS_ID = "enhancedcelestials";

    static public boolean isLunarEventActive(Level world) {
        if (world != null && UMTExpectPlatform.isModLoaded(MOD_ENHANCED_CELESTIALS_ID)) {
            EnhancedCelestialsWorldData ecWorldData = ((EnhancedCelestialsWorldData) world);
            if (ecWorldData != null) {
                EnhancedCelestialsContext lunarContext = ecWorldData.getLunarContext();
                if (lunarContext != null) {
                    LunarForecast forecast = lunarContext.getLunarForecast();
                    if (forecast != null)
                    {
                        Holder<LunarEvent> lunarEvent = forecast.getCurrentEvent(true);
                        return lunarEvent.isBound();
                    }
                }
            }
        }
        return false;
    }

    static public int getLunarEventColor(Level world) {
        if (world != null && UMTExpectPlatform.isModLoaded(MOD_ENHANCED_CELESTIALS_ID)) {
            EnhancedCelestialsWorldData ecWorldData = ((EnhancedCelestialsWorldData) world);
            if (ecWorldData != null) {
                EnhancedCelestialsContext lunarContext = ecWorldData.getLunarContext();
                if (lunarContext != null) {
                    LunarForecast forecast = lunarContext.getLunarForecast();
                    if (forecast != null)
                    {
                        Holder<LunarEvent> lunarEvent = forecast.getCurrentEvent(true);
                        if (lunarEvent.isBound()) {
                            return lunarEvent.value().getClientSettings().colorSettings().getMoonTextureColor();
                        }
                    }
                }
            }
        }
        return 0xffffffff;
    }
}
