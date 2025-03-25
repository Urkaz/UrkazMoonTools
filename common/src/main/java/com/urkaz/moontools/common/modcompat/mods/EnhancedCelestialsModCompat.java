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

package com.urkaz.moontools.common.modcompat.mods;

import com.urkaz.moontools.UMTExpectPlatform;
import com.urkaz.moontools.common.modcompat.handler.IMoonToolsModCompat;
import dev.corgitaco.enhancedcelestials.EnhancedCelestials;
import dev.corgitaco.enhancedcelestials.api.lunarevent.DefaultLunarEvents;
import dev.corgitaco.enhancedcelestials.api.lunarevent.LunarEvent;
import dev.corgitaco.enhancedcelestials.lunarevent.EnhancedCelestialsLunarForecastWorldData;
import dev.corgitaco.enhancedcelestials.util.CustomTranslationTextComponent;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.level.Level;

import java.util.Optional;

public class EnhancedCelestialsModCompat implements IMoonToolsModCompat {

    public static final String MOD_ENHANCED_CELESTIALS_ID = "enhancedcelestials";

    @Override
    public boolean isLunarEventActive(Level world) {
        if (world == null || !UMTExpectPlatform.isModLoaded(MOD_ENHANCED_CELESTIALS_ID))
            return false;

        Optional<EnhancedCelestialsLunarForecastWorldData> lunarForecastWorldData = EnhancedCelestials.lunarForecastWorldData(world);
        if (lunarForecastWorldData.isPresent()) {
            EnhancedCelestialsLunarForecastWorldData data = lunarForecastWorldData.orElseThrow();
            Holder<LunarEvent> currentEvent = data.currentLunarEventHolder();
            if (currentEvent.isBound()) {
                return !currentEvent.is(DefaultLunarEvents.DEFAULT);
            }
        }

        return false;
    }

    @Override
    public int getLunarEventColor(Level world) {
        if (world == null || !UMTExpectPlatform.isModLoaded(MOD_ENHANCED_CELESTIALS_ID))
            return 0xffffffff;

        Optional<EnhancedCelestialsLunarForecastWorldData> lunarForecastWorldData = EnhancedCelestials.lunarForecastWorldData(world);
        if (lunarForecastWorldData.isPresent()) {
            EnhancedCelestialsLunarForecastWorldData data = lunarForecastWorldData.orElseThrow();
            Holder<LunarEvent> currentEvent = data.currentLunarEventHolder();
            if (currentEvent.isBound()) {
                int color = currentEvent.value().getClientSettings().colorSettings().getMoonTextureColor();
                color |= 0xff000000; // Add opaque alpha channel
                return color;
            }
        }

        return 0xffffffff;
    }

    @Override
    public Component getLunarEventName(Level world) {
        if (world == null || !UMTExpectPlatform.isModLoaded(MOD_ENHANCED_CELESTIALS_ID))
            return null;

        if (world.isDay()) return null;

        Optional<EnhancedCelestialsLunarForecastWorldData> lunarForecastWorldData = EnhancedCelestials.lunarForecastWorldData(world);
        if (lunarForecastWorldData.isPresent()) {
            EnhancedCelestialsLunarForecastWorldData data = lunarForecastWorldData.orElseThrow();
            Holder<LunarEvent> currentEvent = data.currentLunarEventHolder();
            if (currentEvent.isBound()) {
                CustomTranslationTextComponent eventName = currentEvent.value().getTextComponents().name();
                TextColor color = eventName.getStyle().getColor();
                return Component.translatable(eventName.getKey()).withStyle(Style.EMPTY.withColor(color));
            }
        }

        return null;
    }
}
