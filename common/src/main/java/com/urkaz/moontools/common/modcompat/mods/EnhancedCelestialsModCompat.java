/*
 * This file is part of "Urkaz Moon Tools".
 * Copyright (C) 2026 Urkaz - Fran Sánchez
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
import net.minecraft.core.Holder;
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
                return 0xff000000 | currentEvent.value().getClientSettings().colorSettings().getMoonTextureColor();
            }
        }

        return 0xffffffff;
    }
}
