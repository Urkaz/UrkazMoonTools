package com.urkaz.moontools.client;

import com.urkaz.moontools.item.MoonClockItem;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class MoonClockColorHandler implements IItemColor {
    @Override
    public int getColor(ItemStack stack, int layer) {
        if(layer == 1) {
            return ((MoonClockItem) stack.getItem()).getColor();
        }
        else
        {
            return 0xffffffff;
        }
    }
}