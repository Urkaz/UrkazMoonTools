package com.urkaz.moontools.common.lib;

import com.urkaz.moontools.UMTConstants;
import net.minecraft.resources.ResourceLocation;

public class ResourceLocationHelper {
    public static ResourceLocation prefix(String path) {
        return new ResourceLocation(UMTConstants.MOD_ID, path);
    }
}
