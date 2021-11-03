package com.urkaz.moontools.resources;

import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

@OnlyIn(Dist.CLIENT)
public class MoonPhaseResource implements IItemPropertyGetter {

    @Override
    public float call(ItemStack stack, @Nullable ClientWorld worldIn, @Nullable LivingEntity entityIn) {
        boolean flag = entityIn != null;
        Entity entity = (Entity) (flag ? entityIn : stack.getFrame());

        World world = worldIn;
        if (worldIn == null && entity != null) {
            world = entity.level;
        }
        int value = (int)worldCall(world);

        /*if(world != null && ModList.get().isLoaded(EnhancedCelestialsRegistry.MOD_ID))
        {
            EnhancedCelestialsWorldData wd = ((EnhancedCelestialsWorldData)world);
            LunarContext lc = wd.getLunarContext();

            LunarEvent event = lc.getCurrentEvent();
            LunarForecast forecast = lc.getLunarForecast();
            if(event != null)
            {
                String key = event.getKey();
                value = value | 8;
            }
        }*/

        return value;
    }

    public float worldCall(@Nullable World worldIn) {
        if (worldIn == null) {
            return 0;
        } else {
            int moonFactor;
            ResourceLocation worldResourceLocation = worldIn.dimension().location();
            ResourceLocation overworldResourceLocation = DimensionType.OVERWORLD_LOCATION.location();

            //check if the dimension is the OVERWORLD
            if (worldResourceLocation.equals(overworldResourceLocation)) {
                moonFactor = worldIn.dimensionType().moonPhase(worldIn.getLevelData().getDayTime());
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
