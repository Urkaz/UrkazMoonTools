package com.urkaz.moontools.client;

import com.urkaz.moontools.common.UrkazMoonToolsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ConfigGuiHandler;
import net.minecraftforge.fml.ModLoadingContext;

@OnlyIn(Dist.CLIENT)
public class UrkazMoonToolsConfigMenu {
    public static void init() {
        ModLoadingContext.get().registerExtensionPoint(ConfigGuiHandler.ConfigGuiFactory.class,
                () -> new ConfigGuiHandler.ConfigGuiFactory((mc, parent) -> AutoConfig.getConfigScreen(UrkazMoonToolsConfig.class, parent).get())
        );
    }
}
