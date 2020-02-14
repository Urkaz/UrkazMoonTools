package com.urkaz.moontools.block;

import java.util.Random;

import com.urkaz.moontools.ModSettings;

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
		long wt = worldIn.provider.getWorldTime();

		boolean isNight = true;

		if (ModSettings.SensorOnlyNight) {
			isNight = wt % 24000L >= 12000L;
		}

		return worldIn.canBlockSeeSky(pos) && isNight ? 1 + worldIn.provider.getMoonPhase(wt) : 0;
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
