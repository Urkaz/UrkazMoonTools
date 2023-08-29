package com.urkaz.moontools.client;

import com.urkaz.moontools.common.item.MoonClockItem;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;

public class MoonClockColorHandler implements ItemColor {
    @Override
    public int getColor(ItemStack stack, int layer) {
        if (layer == 1) {
            return ((MoonClockItem) stack.getItem()).getColor();
        }
        else
        {
            return 0xffffffff;
        }
    }
}
