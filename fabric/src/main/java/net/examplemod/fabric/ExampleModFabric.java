package net.examplemod.fabric;

import com.urkaz.moontools.UrkazMoonTools;
import net.fabricmc.api.ModInitializer;

public class ExampleModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        UrkazMoonTools.init();
    }
}
