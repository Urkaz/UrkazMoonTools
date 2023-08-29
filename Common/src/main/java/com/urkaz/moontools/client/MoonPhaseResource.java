package com.urkaz.moontools.client;

import com.urkaz.moontools.Constants;
import com.urkaz.moontools.common.item.MoonClockItem;
import com.urkaz.moontools.common.thirdparty.EnhancedCelestialsSupport;
import com.urkaz.moontools.platform.Services;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

import javax.annotation.Nullable;

public class MoonPhaseResource implements ClampedItemPropertyFunction {

    @Override
    public float unclampedCall(ItemStack stack, @Nullable ClientLevel worldIn, @Nullable LivingEntity entityIn, int holderID) {
        boolean flag = entityIn != null;
        Entity entity = flag ? entityIn : stack.getFrame();

        Level world = worldIn;
        if (worldIn == null && entity != null) {
            world = entity.level;
        }
        int moonFactor = (int) worldCall(world);
        int color = 0xffffffff;

        if (world != null && Services.PLATFORM.isModLoaded(Constants.MOD_ENHANCED_CELESTIALS_ID))
        {
            color = EnhancedCelestialsSupport.getLunarEventColor(world);
        }

        ((MoonClockItem) stack.getItem()).setColor(color);
        return moonFactor;
    }

    public float worldCall(@Nullable Level worldIn) {
        if (worldIn == null) {
            return 0;
        } else {
            int moonFactor;
            ResourceLocation worldResourceLocation = worldIn.dimension().location();
            ResourceLocation overworldResourceLocation = DimensionType.OVERWORLD_LOCATION.location();

            //check if the dimension is the OVERWORLD
            if (worldResourceLocation.equals(overworldResourceLocation)) {
                moonFactor = worldIn.dimensionType().moonPhase(worldIn.getLevelData().getDayTime());
            } else {
                double randomDouble = Math.random();
                randomDouble = randomDouble * 8;
                moonFactor = (int) randomDouble;
            }
            return moonFactor;
        }
    }


}
