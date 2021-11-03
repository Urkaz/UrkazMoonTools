package com.urkaz.moontools;

import com.urkaz.moontools.block.MoonSensorBlock;
import com.urkaz.moontools.init.ModBlocks;
import com.urkaz.moontools.init.ModItems;
import com.urkaz.moontools.item.MoonClockItem;
import com.urkaz.moontools.resources.MoonPhaseResource;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MoonToolsMod.MODID)
public class MoonToolsMod
{
    public static final String MODID = "urkazmoontools";

    public static final Logger LOGGER = LogManager.getLogger(MoonToolsMod.MODID);

    public MoonToolsMod() {
        final IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        final IEventBus forgeBus = MinecraftForge.EVENT_BUS;

        forgeBus.register(this);

        //create blocks and item instances
        ModBlocks.MOONSENSOR = new MoonSensorBlock(AbstractBlock.Properties.of(Material.WOOD).strength(0.2F).sound(SoundType.WOOD)).setRegistryName(MoonToolsMod.MODID, "moonsensor");
        ModItems.MOONCLOCK = new MoonClockItem(new Item.Properties().tab(ItemGroup.TAB_TOOLS).setNoRepair().stacksTo(1)).setRegistryName(MoonToolsMod.MODID, "moonclock");

        //Load settings
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, com.urkaz.moontools.ModSettings.spec);

        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> MoonToolsModClient::new);
    }
}
