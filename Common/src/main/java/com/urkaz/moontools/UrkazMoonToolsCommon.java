package com.urkaz.moontools;

import com.urkaz.moontools.common.UrkazMoonToolsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public class UrkazMoonToolsCommon {
    public static void init() {
        AutoConfig.register(UrkazMoonToolsConfig.class, GsonConfigSerializer::new);
        Constants.CONFIG = AutoConfig.getConfigHolder(UrkazMoonToolsConfig.class).getConfig();
    }
}