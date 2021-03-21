package com.urkaz.moontools.block;

import java.util.Random;

import com.urkaz.moontools.ModSettings;

import de.ellpeck.nyx.capabilities.NyxWorld;
import de.ellpeck.nyx.lunarevents.BloodMoon;
import de.ellpeck.nyx.lunarevents.HarvestMoon;
import lumien.bloodmoon.client.ClientBloodmoonHandler;
import lumien.bloodmoon.server.BloodmoonHandler;
import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;

public class MoonSensorBlock extends BlockDaylightDetector {

	public MoonSensorBlock(boolean inverted) {
		super(false);
	}

	@Override
	public boolean canDropFromExplosion(Explosion ex) {
		return true;
	}

	@Override
	public void updatePower(World worldIn, BlockPos pos) {
		IBlockState iblockstate = worldIn.getBlockState(pos);
		int current = (int) iblockstate.getValue(POWER);
		int s = this.signal(worldIn, pos);

		if (s != current) {
			worldIn.setBlockState(pos, iblockstate.withProperty(POWER, s), 3);
		}
	}

	protected int signal(World worldIn, BlockPos pos) {
		if (worldIn.provider.isSurfaceWorld()) {
			return 0;
		}

		long wt = worldIn.provider.getWorldTime();
		boolean isNight = true;
		boolean isBloodMoon = false;
		boolean isHarvestMoon = false;
		if (ModSettings.SensorOnlyNight) {
			isNight = wt % 24000L >= 12000L;
		}

		if(ModSettings.EmmitExtraRedstoneOnLunarEvent) {
			if (Loader.isModLoaded("bloodmoon")) {
				if(worldIn.isRemote)
				{
					if (ClientBloodmoonHandler.INSTANCE != null && ClientBloodmoonHandler.INSTANCE.isBloodmoonActive()) {
						isBloodMoon = true;
					}
				}
				else
				{
					if (BloodmoonHandler.INSTANCE != null && BloodmoonHandler.INSTANCE.isBloodmoonActive()) {
						isBloodMoon = true;
					}
				}
			}
			if (Loader.isModLoaded("nyx")) {
				NyxWorld nyx = NyxWorld.get(worldIn);
				if (nyx != null && nyx.currentEvent instanceof HarvestMoon) {
					isHarvestMoon = true;
				}
				if (nyx != null && nyx.currentEvent instanceof BloodMoon) {
					isBloodMoon = true;
				}
			}
		}
		
		if (ModSettings.SensorPhasesShifted)
		{
			int moonPhase = worldIn.provider.getMoonPhase(wt - 24000);
			if (wt - 24000 < 0)
			{
				moonPhase = 7;
			}
			if(worldIn.canBlockSeeSky(pos) && isNight)
			{
				if(isBloodMoon)
				{
					return 9;
				}
				else if(isHarvestMoon)
				{
					return 10;
				}
				else
					return 1 + moonPhase;
			}
			else
				return 0;
		}
		else
		{
			if(worldIn.canBlockSeeSky(pos) && isNight)
			{
				if(isBloodMoon)
				{
					return 9;
				}
				else if(isHarvestMoon)
				{
					return 10;
				}
				else
					return 1 + worldIn.provider.getMoonPhase(wt);
			}
			else
				return 0;
		}

	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn,
			EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		return false;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return Item.getItemFromBlock(this);
	}

	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		Item item = Item.getItemFromBlock(this);
		return item == null ? null : new ItemStack(item, 1, this.damageDropped(state));
	}

}
