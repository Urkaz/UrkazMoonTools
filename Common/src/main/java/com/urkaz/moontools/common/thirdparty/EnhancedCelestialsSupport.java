package com.urkaz.moontools.common.thirdparty;

import corgitaco.enhancedcelestials.EnhancedCelestialsWorldData;
import corgitaco.enhancedcelestials.LunarContext;
import corgitaco.enhancedcelestials.api.lunarevent.LunarEvent;
import net.minecraft.world.level.Level;

public class EnhancedCelestialsSupport {

    static public boolean isLunarEventActive(Level world) {
        EnhancedCelestialsWorldData ecWorldData = ((EnhancedCelestialsWorldData)world);
        if(ecWorldData != null)
        {
            LunarContext lunarContext = ecWorldData.getLunarContext();
            if(lunarContext != null)
            {
                LunarEvent lunarEvent = lunarContext.getCurrentEvent();
                return lunarEvent != null;
            }
        }
        return false;
    }

    static public int getLunarEventColor(Level world) {
        EnhancedCelestialsWorldData ecWorldData = ((EnhancedCelestialsWorldData)world);
        if(ecWorldData != null)
        {
            LunarContext lunarContext = ecWorldData.getLunarContext();
            if(lunarContext != null)
            {
                LunarEvent lunarEvent = lunarContext.getCurrentEvent();
                if(lunarEvent != null)
                {
                    return lunarEvent.getClientSettings().getColorSettings().getMoonTextureColor();
                }
            }
        }
        return 0xffffffff;
    }
}
