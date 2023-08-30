package com.urkaz.moontools.common.lib;

import com.urkaz.moontools.UMTConstants;
import com.urkaz.moontools.UMTExpectPlatform;
import corgitaco.enhancedcelestials.EnhancedCelestialsWorldData;
import corgitaco.enhancedcelestials.api.lunarevent.LunarEvent;
import corgitaco.enhancedcelestials.lunarevent.LunarContext;
import corgitaco.enhancedcelestials.lunarevent.LunarForecast;
import net.minecraft.core.Holder;
import net.minecraft.world.level.Level;

public class EnhancedCelestialsSupport {

    static public boolean isLunarEventActive(Level world) {
        if (world != null && UMTExpectPlatform.isModLoaded(UMTConstants.MOD_ENHANCED_CELESTIALS_ID)) {
            EnhancedCelestialsWorldData ecWorldData = ((EnhancedCelestialsWorldData) world);
            if (ecWorldData != null) {
                LunarContext lunarContext = ecWorldData.getLunarContext();
                if (lunarContext != null) {
                    LunarForecast forecast = lunarContext.getLunarForecast();
                    if (forecast != null)
                    {
                        Holder<LunarEvent> lunarEvent = forecast.getCurrentEvent();
                        return lunarEvent.isBound();
                    }
                }
            }
        }
        return false;
    }

    static public int getLunarEventColor(Level world) {
        if (world != null && UMTExpectPlatform.isModLoaded(UMTConstants.MOD_ENHANCED_CELESTIALS_ID)) {
            EnhancedCelestialsWorldData ecWorldData = ((EnhancedCelestialsWorldData) world);
            if (ecWorldData != null) {
                LunarContext lunarContext = ecWorldData.getLunarContext();
                if (lunarContext != null) {
                    LunarForecast forecast = lunarContext.getLunarForecast();
                    if (forecast != null)
                    {
                        Holder<LunarEvent> lunarEvent = forecast.getCurrentEvent();
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
