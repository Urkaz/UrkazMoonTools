package com.urkaz.moontools;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.level.Level;

public class PlatformCompatibilitySupport {

    @ExpectPlatform
    static public boolean isLunarEventActive(Level world) {
        return false;
    }

    @ExpectPlatform
    static public int getLunarEventColor(Level world) {
        return 0xffffffff;
    }
}
