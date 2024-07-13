/*
 * This file is part of "UrkazMoonTools".
 * Copyright (C) 2024 Urkaz - Fran Sánchez
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

import com.urkaz.moontools.common.component.MoonClockPhaseComponent;
import com.urkaz.moontools.common.component.UMTDataComponents;
import com.urkaz.moontools.common.item.MoonClockItem;
import com.urkaz.moontools.common.modcompat.handler.ModCompatHandler;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class MoonPhaseResource implements ClampedItemPropertyFunction {

    @Override
    public float unclampedCall(ItemStack itemStack, @Nullable ClientLevel level, @Nullable LivingEntity entityIn, int holderID) {
        boolean flag = entityIn != null;
        Entity entity = flag ? entityIn : itemStack.getFrame();

        MoonClockPhaseComponent phaseComponent = itemStack.get(UMTDataComponents.MOON_CLOCK_PHASE.get());
        if(phaseComponent != null) {
            phaseComponent = phaseComponent.tick(level);
        }

        Level world = level;
        if (level == null && entity != null) {
            world = entity.level();
        }
        int moonFactor = (int) getMoonFactor(phaseComponent);
        int color = ModCompatHandler.getInstance().getLunarEventColor(world);

        ((MoonClockItem) itemStack.getItem()).setColor(color);
        return moonFactor / 10.f;
    }

    public float getMoonFactor(@Nullable MoonClockPhaseComponent phaseComponent) {
        int moonFactor = 0;
        boolean hasData = true;

        if (phaseComponent != null) {
            moonFactor = phaseComponent.phase();
            hasData = phaseComponent.hasData();
        }

        if (!hasData) {
            double randomDouble = Math.random();
            randomDouble = randomDouble * 8;
            moonFactor = (int) randomDouble;
        }

        return moonFactor;
    }


}
