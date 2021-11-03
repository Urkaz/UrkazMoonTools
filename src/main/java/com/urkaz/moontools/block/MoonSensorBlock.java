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

import javax.annotation.Nullable;

public class MoonSensorBlock extends ContainerBlock {

    public static final IntegerProperty POWER = BlockStateProperties.POWER;
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);

    public MoonSensorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWER, 0));
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    public int getSignal(BlockState blockState, IBlockReader blockAccess, BlockPos pos, Direction side) {
        return blockState.getValue(POWER);
    }

    public void updateSignalStrength(BlockState state, World world, BlockPos pos)
    {
        int current = state.getValue(POWER);
        int signal = signal(world, pos);

        if(current != signal) {
            this.registerDefaultState(this.stateDefinition.any().setValue(POWER, Integer.valueOf(0)));
            world.setBlock(pos, state.setValue(POWER, Integer.valueOf(signal)), 3);
        }
    }

    protected int signal(World worldIn, BlockPos pos)
    {
        ResourceLocation worldResourceLocation = worldIn.dimension().location();
        ResourceLocation overworldResourceLocation = DimensionType.OVERWORLD_LOCATION.location();

        //null or not OVERWORLD
        if(worldIn == null || !worldResourceLocation.equals(overworldResourceLocation))
            return 0;

        //Check if is night
        long worldTime = worldIn.getLevelData().getDayTime();
        boolean isNight = true;
        if (ModSettings.SETTINGS.SensorOnlyNight.get()) {
            isNight = worldTime % 24000L >= 12000L;
        }

        //Get Redstone value
        int moonPhase = getMoonFactor(worldIn);
        if (ModSettings.SETTINGS.SensorPhasesShifted.get()) {
            if (worldTime - 24000 < 0)
            {
                moonPhase = 7;
            }
            if(worldIn.canSeeSky(pos) && isNight){
                return 1 + moonPhase;
            }
            else {
                return 0;
            }
        }
        else {
            if(worldIn.canSeeSky(pos) && isNight){
                return 1 + moonPhase;
            }
            else {
                return 0;
            }
        }
    }

    protected int getMoonFactor(@Nullable World worldIn)
    {
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

    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public boolean isSignalSource(BlockState state) {
        return true;
    }

    public TileEntity newBlockEntity(IBlockReader reader)
    {
        return new MoonSensorTileEntity();
    }

    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
        builder.add(POWER);
    }
}
