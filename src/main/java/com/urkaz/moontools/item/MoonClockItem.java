package com.urkaz.moontools.item;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

public class MoonClockItem extends Item {

    private int color;

    public MoonClockItem(Properties properties) {
        super(properties);
    }

    public ActionResult<ItemStack> use(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (worldIn.isClientSide && handIn == Hand.MAIN_HAND) {
            playerIn.sendMessage(new StringTextComponent(getTooltipText(worldIn)), playerIn.getUUID());
            playerIn.swing(handIn);
        }
        return new ActionResult<ItemStack>(ActionResultType.SUCCESS, playerIn.getItemInHand(handIn));
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        if (worldIn != null) {
            tooltip.add(new StringTextComponent(getTooltipText(worldIn)));
        }
    }

    @OnlyIn(Dist.CLIENT)
    public String getTooltipText(World worldIn) {
        ResourceLocation worldResourceLocation = worldIn.dimension().location();
        ResourceLocation overworldResourceLocation = DimensionType.OVERWORLD_LOCATION.location();

        //check if the dimension is the OVERWORLD
        if (worldResourceLocation.equals(overworldResourceLocation)) {
            return I18n.get("urkazmoontools.moonclock.phaseTooltip") + " "
                    + I18n.get("urkazmoontools.moonclock.phase" + worldIn.dimensionType().moonPhase(worldIn.getLevelData().getDayTime()));
        }
        else
        {
            return I18n.get("urkazmoontools.moonclock.phaseTooltip") + " "
                    + I18n.get("urkazmoontools.moonclock.nodata");
        }
    }

    public boolean isEnchantable(ItemStack stack) {
        return false;
    }

    public void setColor(int color)
    {
        this.color = color;
    }

    public int getColor()
    {
        return color;
    }
}
