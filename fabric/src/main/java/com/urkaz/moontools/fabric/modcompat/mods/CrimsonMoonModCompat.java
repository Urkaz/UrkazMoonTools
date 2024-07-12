package com.urkaz.moontools.fabric.modcompat.mods;

import com.urkaz.moontools.UMTExpectPlatform;
import com.urkaz.moontools.common.modcompat.handler.IMoonToolsModCompat;
import draylar.crimsonmoon.CrimsonMoonClient;
import net.minecraft.world.level.Level;

public class CrimsonMoonModCompat implements IMoonToolsModCompat {

    public static final String MOD_CRIMSON_MOON_ID = "crimsonmoon";

    @Override
    public boolean isLunarEventActive(Level world) {
        if (UMTExpectPlatform.isModLoaded(MOD_CRIMSON_MOON_ID)) {
            return CrimsonMoonClient.crimsonMoonPresent;
        }
        return false;
    }

    @Override
    public int getLunarEventColor(Level world) {
        if (UMTExpectPlatform.isModLoaded(MOD_CRIMSON_MOON_ID)) {
            if (CrimsonMoonClient.crimsonMoonPresent) {
                return 0xffff0000;
            }
        }
        return 0xffffffff;
    }
}
