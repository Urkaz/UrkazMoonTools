package com.urkaz.moontools.common.thirdparty;

import com.urkaz.moontools.Constants;
import com.urkaz.moontools.platform.Services;
import corgitaco.enhancedcelestials.EnhancedCelestialsWorldData;
import corgitaco.enhancedcelestials.LunarContext;
import corgitaco.enhancedcelestials.api.lunarevent.LunarEvent;
import net.minecraft.world.level.Level;

public class EnhancedCelestialsSupport {

    static public boolean isLunarEventActive(Level world) {
        if (world != null && Services.PLATFORM.isModLoaded(Constants.MOD_ENHANCED_CELESTIALS_ID)) {
            EnhancedCelestialsWorldData ecWorldData = ((EnhancedCelestialsWorldData) world);
            if (ecWorldData != null) {
                LunarContext lunarContext = ecWorldData.getLunarContext();
                if (lunarContext != null) {
                    LunarEvent lunarEvent = lunarContext.getCurrentEvent();
                    return lunarEvent != null;
                }
            }
        }
        return false;
    }

    static public int getLunarEventColor(Level world) {
        if (world != null && Services.PLATFORM.isModLoaded(Constants.MOD_ENHANCED_CELESTIALS_ID)) {
            EnhancedCelestialsWorldData ecWorldData = ((EnhancedCelestialsWorldData) world);
            if (ecWorldData != null) {
                LunarContext lunarContext = ecWorldData.getLunarContext();
                if (lunarContext != null) {
                    LunarEvent lunarEvent = lunarContext.getCurrentEvent();
                    if (lunarEvent != null) {
                        return lunarEvent.getClientSettings().getColorSettings().getMoonTextureColor();
                    }
                }
            }
        }
        return 0xffffffff;
    }
}
