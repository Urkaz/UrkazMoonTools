/*
 * This file is part of "Urkaz Mod Tools".
 * Copyright (C) 2023 Urkaz
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package com.urkaz.moontools.client;

import com.urkaz.moontools.common.item.MoonClockItem;
import com.urkaz.moontools.common.lib.ModCompatHandler;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import org.jetbrains.annotations.Nullable;

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
        int color = ModCompatHandler.getLunarEventColor(world);

        ((MoonClockItem) stack.getItem()).setColor(color);
        return moonFactor/10.f;
    }

    public float worldCall(@Nullable Level worldIn) {
        if (worldIn == null) {
            return 0;
        } else {
            int moonFactor;
            ResourceLocation worldResourceLocation = worldIn.dimension().location();
            ResourceLocation overworldResourceLocation = BuiltinDimensionTypes.OVERWORLD.location();

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
