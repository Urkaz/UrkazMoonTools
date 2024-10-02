/*
 * This file is part of "UrkazMoonTools".
 * Copyright (C) 2024 Urkaz - Fran Sánchez
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

package com.urkaz.moontools.common;

import com.urkaz.moontools.UMTConstants;
import com.urkaz.moontools.common.block.MoonSensorBlock;
import com.urkaz.moontools.common.block.entity.MoonSensorBlockEntity;
import com.urkaz.moontools.common.item.MoonClockItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class UMTRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(UMTConstants.MOD_ID, Registries.BLOCK);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(UMTConstants.MOD_ID, Registries.ITEM);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(UMTConstants.MOD_ID, Registries.BLOCK_ENTITY_TYPE);
    public static DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(UMTConstants.MOD_ID, Registries.CREATIVE_MODE_TAB);

    public static final RegistrySupplier<Block> BLOCK_MOONSENSOR = registerBlock("moonsensor_block", () -> new MoonSensorBlock(BlockBehaviour.Properties.of().strength(0.2F).sound(SoundType.WOOD).pushReaction(PushReaction.DESTROY)));

    public static final RegistrySupplier<Item> ITEM_MOONCLOCK = registerItem("moonclock_item", p -> new MoonClockItem(p.stacksTo(1)));

    public static final RegistrySupplier<BlockEntityType<MoonSensorBlockEntity>> BLOCKENTITY_MOONSENSOR = BLOCK_ENTITIES.register("moonsensor_entity", () ->
            BlockEntityType.Builder.of(MoonSensorBlockEntity::new, UMTRegistry.BLOCK_MOONSENSOR.get()).build(null));

    public static final RegistrySupplier<CreativeModeTab> ALPHA_SIN_55_CREATIVE_TAB = registerCreativeTab("urkazmoontools.creative_tab", UMTRegistry.ITEM_MOONCLOCK, UMTRegistry::createDefaultCreativeTab);

    public static void createDefaultCreativeTab(CreativeModeTab.Output output) {
        output.accept(UMTRegistry.ITEM_MOONCLOCK.get());
        output.accept(UMTRegistry.BLOCK_MOONSENSOR.get());
    }

    private static RegistrySupplier<Block> registerBlock(String name, Supplier<Block> block) {
        return registerBlockWithItem(name, block, name + "item", it -> new BlockItem(it, new Item.Properties()));
    }

    private static <B extends Block> RegistrySupplier<Block> registerBlockWithItem(String blockName, Supplier<B> block, String itemName, Function<Block, Item> blockItem) {
        RegistrySupplier<Block> blockSupplier = BLOCKS.register(blockName, block);
        ITEMS.register(itemName, () -> blockItem.apply(blockSupplier.get()));
        return blockSupplier;
    }

    private static RegistrySupplier<Item> registerItem(String name, Function<Item.Properties, Item> item) {
        return ITEMS.register(name, () -> item.apply(new Item.Properties()));
    }

    private static RegistrySupplier<CreativeModeTab> registerCreativeTab(String name, RegistrySupplier<Item> iconSupplier, Consumer<CreativeModeTab.Output> consumer) {
        return CREATIVE_MODE_TABS.register(name, () -> CreativeModeTab.builder(null, -1)
                .title(Component.translatable(name))
                .icon(() -> new ItemStack(iconSupplier.get()))
                .displayItems((params, output) -> {
                    consumer.accept(output);

                })
                .build()
        );
    }

    public static ResourceLocation prefixedModLocation(String path) {
        return ResourceLocation.fromNamespaceAndPath(UMTConstants.MOD_ID, path);
    }
}