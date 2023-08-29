package com.urkaz.moontools.common.item;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;

import javax.annotation.Nullable;
import java.util.List;

public class MoonClockItem extends Item {

    private int color;

    public MoonClockItem(Properties properties) {
        super(properties);
    }

    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        if (worldIn.isClientSide && handIn == InteractionHand.MAIN_HAND) {
            playerIn.sendMessage(new TextComponent(getTooltipText(worldIn)), playerIn.getUUID());
            playerIn.swing(handIn);
        }
        return new InteractionResultHolder<ItemStack>(InteractionResult.SUCCESS, playerIn.getItemInHand(handIn));
    }

    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        if (worldIn != null) {
            tooltip.add(new TextComponent(getTooltipText(worldIn)));
        }
    }

    public String getTooltipText(Level worldIn) {
        ResourceLocation worldResourceLocation = worldIn.dimension().location();
        ResourceLocation overworldResourceLocation = DimensionType.OVERWORLD_LOCATION.location();

        //check if the dimension is the OVERWORLD
        if (worldResourceLocation.equals(overworldResourceLocation)) {
            return I18n.get("urkazmoontools.moonclock.phaseTooltip") + " "
                    + I18n.get("urkazmoontools.moonclock.phase" + worldIn.dimensionType().moonPhase(worldIn.getLevelData().getDayTime()));
        } else {
            return I18n.get("urkazmoontools.moonclock.phaseTooltip") + " "
                    + I18n.get("urkazmoontools.moonclock.nodata");
        }
    }

    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }
}
