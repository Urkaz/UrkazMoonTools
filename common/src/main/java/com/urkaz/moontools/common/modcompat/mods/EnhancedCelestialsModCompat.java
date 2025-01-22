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

package com.urkaz.moontools.common.modcompat.mods;

import com.urkaz.moontools.UMTExpectPlatform;
import com.urkaz.moontools.common.modcompat.handler.IMoonToolsModCompat;
import dev.corgitaco.enhancedcelestials.EnhancedCelestialsWorldData;
import dev.corgitaco.enhancedcelestials.api.lunarevent.LunarEvent;
import dev.corgitaco.enhancedcelestials.core.EnhancedCelestialsContext;
import dev.corgitaco.enhancedcelestials.lunarevent.LunarForecast;
import dev.corgitaco.enhancedcelestials.util.CustomTranslationTextComponent;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.level.Level;

public class EnhancedCelestialsModCompat implements IMoonToolsModCompat {

    public static final String MOD_ENHANCED_CELESTIALS_ID = "enhancedcelestials";

    @Override
    public boolean isLunarEventActive(Level world) {
        if (world == null || !UMTExpectPlatform.isModLoaded(MOD_ENHANCED_CELESTIALS_ID))
            return false;

        EnhancedCelestialsWorldData ecWorldData = ((EnhancedCelestialsWorldData) world);
        if (ecWorldData != null) {
            EnhancedCelestialsContext lunarContext = ecWorldData.getLunarContext();
            if (lunarContext != null) {
                LunarForecast forecast = lunarContext.getLunarForecast();
                if (forecast != null) {
                    Holder<LunarEvent> lunarEvent = forecast.currentLunarEvent();
                    return lunarEvent.isBound();
                }
            }
        }

        return false;
    }

    @Override
    public int getLunarEventColor(Level world) {
        if (world == null || !UMTExpectPlatform.isModLoaded(MOD_ENHANCED_CELESTIALS_ID))
            return 0xffffffff;

        EnhancedCelestialsWorldData ecWorldData = ((EnhancedCelestialsWorldData) world);
        if (ecWorldData != null) {
            EnhancedCelestialsContext lunarContext = ecWorldData.getLunarContext();
            if (lunarContext != null) {
                LunarForecast forecast = lunarContext.getLunarForecast();
                if (forecast != null) {
                    Holder<LunarEvent> lunarEvent = forecast.getLunarEventForDay(forecast.getCurrentDay());
                    if (lunarEvent.isBound()) {
                        int color = lunarEvent.value().getClientSettings().colorSettings().getMoonTextureColor();
                        color |= 0xff000000; // Add opaque alpha channel
                        return color;
                    }
                }
            }
        }
        return 0xffffffff;
    }

    @Override
    public Component getLunarEventName(Level world) {
        if (world == null || !UMTExpectPlatform.isModLoaded(MOD_ENHANCED_CELESTIALS_ID))
            return null;

        EnhancedCelestialsWorldData ecWorldData = ((EnhancedCelestialsWorldData) world);
        if (ecWorldData != null) {
            EnhancedCelestialsContext lunarContext = ecWorldData.getLunarContext();
            if (lunarContext != null) {
                LunarForecast forecast = lunarContext.getLunarForecast();
                if (forecast != null) {
                    Holder<LunarEvent> lunarEvent = forecast.getLunarEventForDay(forecast.getCurrentDay());
                    if (lunarEvent.isBound()) {
                        CustomTranslationTextComponent eventName = lunarEvent.value().getTextComponents().name();
                        TextColor color = eventName.getStyle().getColor();
                        return Component.translatable(eventName.getKey()).withStyle(Style.EMPTY.withColor(color));
                    }
                }
            }
        }
        return null;
    }
}
