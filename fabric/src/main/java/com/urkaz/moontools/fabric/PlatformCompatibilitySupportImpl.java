package com.urkaz.moontools.fabric;

//import com.urkaz.moontools.UMTExpectPlatform;
//import draylar.crimsonmoon.CrimsonMoonClient;
import net.minecraft.world.level.Level;

public class PlatformCompatibilitySupportImpl {

    public static final String MOD_CRIMSON_MOON_ID = "crimsonmoon";

    public static boolean isLunarEventActive(Level world) {
        if (world == null) {
            return false;
        }

        boolean eventActive = false;

//        // Crimson Moon
//        if (UMTExpectPlatform.isModLoaded(MOD_CRIMSON_MOON_ID)) {
//            eventActive |= CrimsonMoonClient.crimsonMoonPresent;
//        }

        return eventActive;
    }

    public static int getLunarEventColor(Level world) {
        int color = 0xffffffff;

        if (world == null) {
            return color;
        }

//        // Crimson Moon
//        if (UMTExpectPlatform.isModLoaded(MOD_CRIMSON_MOON_ID)) {
//            if (CrimsonMoonClient.crimsonMoonPresent) {
//                color = 0xffff0000;
//            }
//        }

        return color;
    }
}
