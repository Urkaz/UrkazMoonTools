/*
 * This file is part of "Urkaz Moon Tools".
 * Copyright (C) 2025 Urkaz - Fran Sánchez
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

package com.urkaz.moontools.common.item;

import com.urkaz.moontools.common.component.MoonClockPhaseComponent;
import com.urkaz.moontools.common.component.UMTDataComponents;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MoonClockItem extends Item {

    public MoonClockItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        if (worldIn.isClientSide && handIn == InteractionHand.MAIN_HAND) {
            playerIn.sendSystemMessage(Component.literal(getTooltipText(worldIn)));
            playerIn.swing(handIn);
        }
        return new InteractionResultHolder<>(InteractionResult.SUCCESS, playerIn.getItemInHand(handIn));
    }

    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean bl) {
        MoonClockPhaseComponent phaseComponent = itemStack.get(UMTDataComponents.MOON_CLOCK_PHASE.get());
        if (phaseComponent != null) {
            MoonClockPhaseComponent phaseComponent2 = phaseComponent.tick(level);
            if (phaseComponent2 != phaseComponent) {
                itemStack.set(UMTDataComponents.MOON_CLOCK_PHASE.get(), phaseComponent2);
            }
        } else {
            itemStack.set(UMTDataComponents.MOON_CLOCK_PHASE.get(), new MoonClockPhaseComponent(0, true, 0xffffffff));
        }
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> tooltip, TooltipFlag tooltipFlag) {
        super.appendHoverText(itemStack, tooltipContext, tooltip, tooltipFlag);

        MoonClockPhaseComponent phaseComponent = itemStack.get(UMTDataComponents.MOON_CLOCK_PHASE.get());
        tooltip.add(Component.literal(getTooltipText(phaseComponent)));
    }

    public String getTooltipText(Level level) {
        ResourceLocation worldResourceLocation = level.dimension().location();
        ResourceLocation overworldResourceLocation = BuiltinDimensionTypes.OVERWORLD.location();

        return getTooltipText(MoonClockPhaseComponent.getMoonPhaseInteger(level), worldResourceLocation.equals(overworldResourceLocation));
    }

    public String getTooltipText(@Nullable MoonClockPhaseComponent phaseComponent) {
        return getTooltipText(
                phaseComponent != null ? phaseComponent.phase() : 0,
                phaseComponent != null && phaseComponent.hasData());
    }

    public String getTooltipText(int phase, boolean hasData) {
        if (hasData) {
            return I18n.get("urkazmoontools.moonclock.phaseTooltip") + " " +
                    I18n.get("urkazmoontools.moonclock.phase" + phase);
        } else {
            return I18n.get("urkazmoontools.moonclock.phaseTooltip") + " "
                    + I18n.get("urkazmoontools.moonclock.nodata");
        }
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    public int getColor(ItemStack itemStack) {
        MoonClockPhaseComponent phaseComponent = itemStack.get(UMTDataComponents.MOON_CLOCK_PHASE.get());
        return phaseComponent != null ? phaseComponent.color() : 0xffffffff;
    }
}
