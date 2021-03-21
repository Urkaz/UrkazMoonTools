package com.urkaz.moontools.resources;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;

import javax.annotation.Nullable;

public class MoonPhaseResource implements IItemPropertyGetter {

    @Override
    public float call(ItemStack stack, @Nullable ClientWorld worldIn, @Nullable LivingEntity entityIn) {
        boolean flag = entityIn != null;
        Entity entity = (Entity) (flag ? entityIn : stack.getItemFrame());

        if (worldIn == null && entity != null) {
            worldIn = (ClientWorld)entity.world;
        }

        if (worldIn == null) {
            return 0;
        } else {
            int moonFactor;
            ResourceLocation worldResourceLocation = worldIn.getDimensionKey().getLocation();
            ResourceLocation overworldResourceLocation = DimensionType.OVERWORLD.getLocation();

            //check if the dimension is the OVERWORLD
            if (worldResourceLocation.equals(overworldResourceLocation)) {
                moonFactor = worldIn.getDimensionType().getMoonPhase(worldIn.getWorldInfo().getDayTime());
            }
            else
            {
                double randomDouble = Math.random();
                randomDouble = randomDouble * 8;
                moonFactor = (int) randomDouble;
            }
            return moonFactor;
        }
    }
}
