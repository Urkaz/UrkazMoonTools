package com.urkaz.moontools.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.urkaz.moontools.ModSettings;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.list.OptionsRowList;
import net.minecraft.client.settings.BooleanOption;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;

public class ConfigScreen extends Screen {

    private static final int TITLE_MARGIN = 8;
    private static final int OPTIONS_LIST_MARGIN = 24;
    private static final int OPTIONS_LIST_BOTTOM_MARGIN = 32;
    private static final int OPTION_HEIGHT = 25;

    private static final int BUTTON_WIDTH = 200;
    private static final int BUTTON_HEIGHT = 20;
    private static final int DONE_BUTTON_BOTTOM_MARGIN = 6;

    protected Screen lastScreen;
    protected OptionsRowList optionsList;
    protected Button doneButton;

    private Boolean emitExtraRedstoneOnLunarEvent;
    private Boolean sensorOnlyNight;
    private Boolean sensorPhasesShifted;

    @SubscribeEvent
    public static void onConstructModEvent(FMLConstructModEvent event) {
        final ModLoadingContext context = ModLoadingContext.get();
        context.registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY,
                () -> (mc, screen) -> new ConfigScreen(screen));
    }

    public ConfigScreen(Screen lastScreen) {
        super(new TranslationTextComponent("urkazmoontools.configUI.title"));
        this.lastScreen = lastScreen;
    }

    @Override
    protected void init() {
        fetchSettings();

        optionsList = new OptionsRowList(minecraft, width, height, OPTIONS_LIST_MARGIN,
                height - OPTIONS_LIST_BOTTOM_MARGIN, OPTION_HEIGHT);

        optionsList.addBig(new BooleanOption("urkazmoontools.configUI.sensoronlynight",
                settings -> sensorOnlyNight,
                (settings, value) -> sensorOnlyNight = value));
        //optionsList.addOption(new BooleanOption("urkazmoontools.configUI.emmitextraonmoonevent",
                //settings -> emitExtraRedstoneOnLunarEvent,
                //(settings, value) -> emitExtraRedstoneOnLunarEvent = value));
        optionsList.addBig(new BooleanOption("urkazmoontools.configUI.sensorshiftphases",
                settings -> sensorPhasesShifted,
                (settings, value) -> sensorPhasesShifted = value));

        addWidget(optionsList);

        int doneX = (width - BUTTON_WIDTH) / 2;
        int doneY = height - BUTTON_HEIGHT - DONE_BUTTON_BOTTOM_MARGIN;
        doneButton = new Button(doneX, doneY, BUTTON_WIDTH, BUTTON_HEIGHT,  new TranslationTextComponent("urkazmoontools.configUI.done"),
                button -> onClose());

        addButton(doneButton);
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(stack);
        optionsList.render(stack, mouseX, mouseY, partialTicks);
        drawCenteredString(stack, font, title.getString(), width / 2, TITLE_MARGIN, 0xFFFFFF);
        super.render(stack, mouseX, mouseY, partialTicks);
    }

    @Override
    public void onClose() {
        saveSettings();
        minecraft.setScreen(lastScreen);
    }

    private void saveSettings() {
        ModSettings.SETTINGS.EmmitExtraRedstoneOnLunarEvent.set(emitExtraRedstoneOnLunarEvent);
        ModSettings.SETTINGS.SensorOnlyNight.set(sensorOnlyNight);
        ModSettings.SETTINGS.SensorPhasesShifted.set(sensorPhasesShifted);
    }

    private void fetchSettings() {
        emitExtraRedstoneOnLunarEvent = ModSettings.SETTINGS.EmmitExtraRedstoneOnLunarEvent.get();
        sensorOnlyNight = ModSettings.SETTINGS.SensorOnlyNight.get();
        sensorPhasesShifted = ModSettings.SETTINGS.SensorPhasesShifted.get();
    }
}
