package com.urkaz.moontools.common.block;

import com.urkaz.moontools.Constants;
import com.urkaz.moontools.common.ModRegistry;
import com.urkaz.moontools.common.block.entity.MoonSensorBlockEntity;
import com.urkaz.moontools.common.thirdparty.EnhancedCelestialsSupport;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class MoonSensorBlock extends BaseEntityBlock {

    public static final IntegerProperty POWER = BlockStateProperties.POWER;
    protected static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 6.0D, 16.0D);

    public MoonSensorBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(POWER, 0));
    }

    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    public boolean useShapeForLightOcclusion(BlockState state) {
        return true;
    }

    public int getSignal(BlockState blockState, BlockGetter blockAccess, BlockPos pos, Direction side) {
        return blockState.getValue(POWER);
    }

    public void updateSignalStrength(BlockState state, Level world, BlockPos pos) {
        int current = state.getValue(POWER);
        int signal = signal(world, pos);

        if (current != signal) {
            this.registerDefaultState(this.stateDefinition.any().setValue(POWER, Integer.valueOf(0)));
            world.setBlock(pos, state.setValue(POWER, Integer.valueOf(signal)), 3);
        }
    }

    protected int signal(Level worldIn, BlockPos pos) {
        ResourceLocation worldResourceLocation = worldIn.dimension().location();
        ResourceLocation overworldResourceLocation = DimensionType.OVERWORLD_LOCATION.location();

        //null or not in OVERWORLD
        if (worldIn == null || !worldResourceLocation.equals(overworldResourceLocation))
            return 0;

        //Check if is night
        long worldTime = worldIn.getLevelData().getDayTime();
        boolean isNight = true;
        if (Constants.CONFIG.sensorOnlyNight) {
            isNight = worldTime % 24000L >= 12000L;
        }

        //If the EmitExtraRedstoneOnLunarEvent setting is enabled, return 9 directly
        if (Constants.CONFIG.emitExtraRedstoneOnLunarEvent) {
            if (worldIn.canSeeSky(pos) && isNight && EnhancedCelestialsSupport.isLunarEventActive(worldIn)) {
                return 9;
            }
        }

        //Get Redstone value
        int moonPhase = getMoonFactor(worldIn);
        if (Constants.CONFIG.sensorPhasesShifted) {
            if (worldTime - 24000 < 0) {
                moonPhase = 7;
            }
        }
        if (worldIn.canSeeSky(pos) && isNight) {
            return 1 + moonPhase;
        } else {
            return 0;
        }
    }

    protected int getMoonFactor(@Nullable Level worldIn) {
        if (worldIn == null) {
            return 0;
        } else {
            int moonFactor;
            ResourceLocation worldResourceLocation = worldIn.dimension().location();
            ResourceLocation overworldResourceLocation = DimensionType.OVERWORLD_LOCATION.location();

            //check if the dimension is the OVERWORLD
            if (worldResourceLocation.equals(overworldResourceLocation)) {
                moonFactor = worldIn.dimensionType().moonPhase(worldIn.getLevelData().getDayTime());
            } else {
                double randomDouble = Math.random() * 8;
                moonFactor = (int) randomDouble;
            }
            return moonFactor;
        }
    }

    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    public boolean isSignalSource(BlockState state) {
        return true;
    }

    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new MoonSensorBlockEntity(blockPos, blockState);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(POWER);
    }

    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return level.isClientSide() ? null : createTickerHelper(type, ModRegistry.BLOCKENTITY_MOONSENSOR, MoonSensorBlockEntity::serverTick);
    }
}
