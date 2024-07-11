package com.urkaz.moontools.common.modcompat.handler;

import net.minecraft.world.level.Level;

public interface IMoonToolsModCompat {
    default boolean isLunarEventActive(Level world) {
        return false;
    }

    default int getLunarEventColor(Level world) {
        return 0xffffffff;
    }
}
