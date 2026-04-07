/*
 * This file is part of "Urkaz Moon Tools".
 * Copyright (C) 2026 Urkaz - Fran Sánchez
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

package com.urkaz.moontools.common.block;

import com.urkaz.moontools.common.UMTConfigWrapper;
import com.urkaz.moontools.common.UMTRegistry;
import com.urkaz.moontools.common.block.entity.MoonSensorBlockEntity;
import com.urkaz.moontools.common.modcompat.handler.ModCompatHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MoonSensorBlock extends Block implements EntityBlock {

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
        ResourceLocation overworldResourceLocation = BuiltinDimensionTypes.OVERWORLD.location();

        //null or not in OVERWORLD
        if (worldIn == null || !worldResourceLocation.equals(overworldResourceLocation))
            return 0;

        //Check if is night
        long worldTime = worldIn.getLevelData().getDayTime();
        boolean isNight = true;

        UMTConfigWrapper.UMTConfig config = UMTConfigWrapper.getConfig();
        if (config != null && config.sensorOnlyNight) {
            isNight = worldTime % 24000L >= 12000L;
        }

        // If the EmitExtraRedstoneOnLunarEvent setting is enabled
        if (config != null && config.emitExtraRedstoneOnLunarEvent) {
            if (isNight && worldIn.canSeeSky(pos) && ModCompatHandler.getInstance().isLunarEventActive(worldIn)) {
                return config.duringEvents;
            }
        }

        //Get current Phase
        int moonPhase = getMoonFactor(worldIn);

        int restoneOutput = 0;
        if (config != null && isNight && worldIn.canSeeSky(pos)) {
            if (moonPhase == 0) restoneOutput = config.fullMoon;
            else if (moonPhase == 1) restoneOutput = config.waningGibbous;
            else if (moonPhase == 2) restoneOutput = config.thirdQuarter;
            else if (moonPhase == 3) restoneOutput = config.waningCrescent;
            else if (moonPhase == 4) restoneOutput = config.newMoon;
            else if (moonPhase == 5) restoneOutput = config.waxingCrescent;
            else if (moonPhase == 6) restoneOutput = config.firstQuarter;
            else if (moonPhase == 7) restoneOutput = config.waxingGibbous;
        }

        //Get final value
        return restoneOutput;
    }

    /**
     * @param worldIn
     * @return Waxing Gibbous 7
     * First Quarter 6
     * Waxing Crescent 5
     * New Moon 4
     * Waning Crescent 3
     * Third Quarter 2
     * Waning Gibbous 1
     * Full Moon 0
     */
    protected int getMoonFactor(@Nullable Level worldIn) {
        if (worldIn == null) {
            return 0;
        } else {
            int moonFactor;
            ResourceLocation worldResourceLocation = worldIn.dimension().location();
            ResourceLocation overworldResourceLocation = BuiltinDimensionTypes.OVERWORLD.location();

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

    public @NotNull RenderShape getRenderShape(BlockState state) {
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
        return level.isClientSide() ? null : createTicker(type, UMTRegistry.BLOCKENTITY_MOONSENSOR, MoonSensorBlockEntity::serverTick);
    }

    @Nullable
    @SuppressWarnings("unchecked")
    public static <E extends BlockEntity, A extends BlockEntity> BlockEntityTicker<A> createTicker(BlockEntityType<A> candidate, BlockEntityType<E> desired, BlockEntityTicker<? super E> ticker) {
        return desired == candidate ? (BlockEntityTicker<A>) ticker : null;
    }
}
