package com.urkaz.moontools.item;

import java.util.List;

import javax.annotation.Nullable;

import lumien.bloodmoon.server.BloodmoonHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MoonClockItem extends Item {

	public MoonClockItem() {
		this.addPropertyOverride(new ResourceLocation("moonphase"), new IItemPropertyGetter() {

			@SideOnly(Side.CLIENT)
			public float apply(ItemStack stack, @Nullable World worldIn, @Nullable EntityLivingBase entityIn) {
				boolean flag = entityIn != null;
				Entity entity = (Entity) (flag ? entityIn : stack.getItemFrame());

				if (worldIn == null && entity != null) {
					worldIn = entity.world;
				}
				
				int extras = 0;
				
				if(Loader.isModLoaded("bloodmoon")) {
					if(BloodmoonHandler.INSTANCE.isBloodmoonActive()) {
						extras += 10;
					}
				}

				if (worldIn == null) {
					return 0;
				} else {
					int moonFactor;
					if (worldIn.provider.isSurfaceWorld()) {
						moonFactor = worldIn.provider.getMoonPhase(worldIn.getWorldTime()) + extras;
					} else {
						double randomDouble = Math.random();
						randomDouble = randomDouble * 8;
						moonFactor = (int) randomDouble;
					}
					return moonFactor;
				}
			}

		});
	}

	@SideOnly(Side.CLIENT)
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {	
		if(worldIn.isRemote && handIn == EnumHand.MAIN_HAND) {
			playerIn.sendMessage(new TextComponentString(getTooltipText(worldIn)));
			playerIn.swingArm(handIn);
	        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		}
		else {
			return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
		}
    }

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (worldIn != null) {
			tooltip.add(getTooltipText(worldIn));
		}
	}

	@SideOnly(Side.CLIENT)
	public String getTooltipText (World worldIn) {
		return I18n.format("urkazmoontools.moonclock.phaseTooltip")
				+ I18n.format("urkazmoontools.moonclock.phase" + worldIn.provider.getMoonPhase(worldIn.getWorldTime()));
	}

}
