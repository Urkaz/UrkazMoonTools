package com.urkaz.moontools.common.lib;

import com.urkaz.moontools.PlatformCompatibilitySupport;
import net.minecraft.world.level.Level;

public class ModCompatHandler {
    static public boolean isLunarEventActive(Level world) {
        boolean eventActive = false;

        eventActive |= EnhancedCelestialsSupport.isLunarEventActive(world);
        eventActive |= PlatformCompatibilitySupport.isLunarEventActive(world);

        return eventActive;
    }

    static public int getLunarEventColor(Level world) {
        int color;

        // Enhanced Celestials
        color = EnhancedCelestialsSupport.getLunarEventColor(world);

        // Platform specific mods
        if (color == 0xffffffff)
            color = PlatformCompatibilitySupport.getLunarEventColor(world);

        return color;
    }
}
