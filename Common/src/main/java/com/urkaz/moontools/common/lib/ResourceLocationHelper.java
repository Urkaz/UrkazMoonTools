package com.urkaz.moontools.common.lib;

import com.urkaz.moontools.Constants;
import net.minecraft.resources.ResourceLocation;

public class ResourceLocationHelper {
    public static ResourceLocation prefix(String path) {
        return new ResourceLocation(Constants.MOD_ID, path);
    }
}
