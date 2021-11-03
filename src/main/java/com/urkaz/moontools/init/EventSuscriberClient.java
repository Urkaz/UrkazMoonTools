package com.urkaz.moontools.init;

import com.urkaz.moontools.MoonToolsMod;
import com.urkaz.moontools.client.MoonClockColorHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MoonToolsMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class EventSuscriberClient {

    @SubscribeEvent
    public static void onHandleColors(ColorHandlerEvent.Item event){
        event.getItemColors().register(new MoonClockColorHandler(), ModItems.MOONCLOCK);
    }
}
