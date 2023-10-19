package com.urkaz.moontools.forge;

import net.minecraft.world.level.Level;

public class PlatformCompatibilitySupportImpl {

    public static boolean isLunarEventActive(Level world) {
        return false;
    }

    public static int getLunarEventColor(Level world) {
        return 0xffffffff;
    }
}
