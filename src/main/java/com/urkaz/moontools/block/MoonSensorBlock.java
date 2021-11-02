package com.urkaz.moontools.block;

import com.urkaz.moontools.ModSettings;
import com.urkaz.moontools.resources.MoonPhaseResource;
import com.urkaz.moontools.tileentity.MoonSensorTileEntity;
import net.minecraft.block.*;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.DimensionType;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class MoonSensorBlock extends ContainerBlock {

    public static final IntegerProperty POWER = BlockStateProperties.POWER_0_15;
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);
    protected MoonPhaseResource moonResource = null;

    public MoonSensorBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(POWER, Integer.valueOf(0)));
        this.moonResource = new MoonPhaseResource();
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    public boolean isTransparent(BlockState state) {
        return true;
    }

    public int getWeakPower(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return blockState.get(POWER);
    }

    public void updatePower(BlockState state, World world, BlockPos pos)
    {
        int current = state.get(POWER);
        int signal = signal(world, pos);

        if(current != signal) {
            world.setBlockState(pos, state.with(POWER, Integer.valueOf(signal)), 3);
        }
    }

    protected int signal(World worldIn, BlockPos pos)
    {
        ResourceLocation worldResourceLocation = worldIn.getDimensionKey().getLocation();
        ResourceLocation overworldResourceLocation = DimensionType.OVERWORLD.getLocation();

        //null or not OVERWORLD
        if(worldIn == null || !worldResourceLocation.equals(overworldResourceLocation))
            return 0;

        //TODO: Check events and mod compatibility
        boolean isBloodMoon = false;
        boolean isHarvestMoon = false;
        if (ModSettings.MOD_SETTINGS.EmmitExtraRedstoneOnLunarEvent.get()) {
        }

        //Check if is night
        long worldTime = worldIn.getWorldInfo().getDayTime();
        boolean isNight = true;
        if (ModSettings.MOD_SETTINGS.SensorOnlyNight.get()) {
            isNight = worldTime % 24000L >= 12000L;
        }

        //Get Redstone value
        if (ModSettings.MOD_SETTINGS.SensorPhasesShifted.get()) {
            int moonPhase = (int)moonResource.worldCall(worldIn);
            if (worldTime - 24000 < 0)
            {
                moonPhase = 7;
            }
            if(worldIn.canBlockSeeSky(pos) && isNight){
                if(isBloodMoon) {
                    return 9;
                }
                else if(isHarvestMoon) {
                    return 10;
                }
                else
                    return 1 + moonPhase;
            }
            else {
                return 0;
            }
        }
        else {
            if(worldIn.canBlockSeeSky(pos) && isNight){
                if(isBloodMoon) {
                    return 9;
                }
                else if(isHarvestMoon) {
                    return 10;
                }
                else
                    return 1 + (int)moonResource.worldCall(worldIn);
            }
            else {
                return 0;
            }
        }
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public boolean canProvidePower(BlockState state) {
        return true;
    }

    public TileEntity createNewTileEntity(IBlockReader reader)
    {
        return new MoonSensorTileEntity();
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(POWER);
    }
}
