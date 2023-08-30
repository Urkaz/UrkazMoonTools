package com.urkaz.moontools;

import com.urkaz.moontools.common.UMTConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public class UrkazMoonTools {

    public static void init() {
        AutoConfig.register(UMTConfig.class, GsonConfigSerializer::new);
        UMTConstants.CONFIG = AutoConfig.getConfigHolder(UMTConfig.class).getConfig();
    }
}
