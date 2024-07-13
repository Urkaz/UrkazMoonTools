package com.urkaz.moontools.common.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;

public record MoonClockPhaseComponent(int phase, boolean hasData) {
    public static final Codec<MoonClockPhaseComponent> CODEC = RecordCodecBuilder.create(builder ->
            builder.group(
                    Codec.INT.optionalFieldOf("phase", 0).forGetter(MoonClockPhaseComponent::phase),
                    Codec.BOOL.optionalFieldOf("no_data", true).forGetter(MoonClockPhaseComponent::hasData)
            ).apply(builder, MoonClockPhaseComponent::new)
    );

    public MoonClockPhaseComponent tick(Level level) {
        ResourceLocation worldResourceLocation = level.dimension().location();
        ResourceLocation overworldResourceLocation = BuiltinDimensionTypes.OVERWORLD.location();

        // Check if the dimension is the OVERWORLD
        if (worldResourceLocation.equals(overworldResourceLocation)) {
            int currentPhase = getMoonPhaseInteger(level);
            if (currentPhase != phase)
                return new MoonClockPhaseComponent(currentPhase, true);
            else
                return this;
        }
        else {
            return new MoonClockPhaseComponent(0, true);
        }
    }

    static public int getMoonPhaseInteger(Level level) {
        return level.dimensionType().moonPhase(level.getLevelData().getDayTime());
    }

    public static final StreamCodec<RegistryFriendlyByteBuf, MoonClockPhaseComponent> STREAM_CODEC;

    static {
        STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.INT,
                MoonClockPhaseComponent::phase,
                ByteBufCodecs.BOOL,
                MoonClockPhaseComponent::hasData,
                MoonClockPhaseComponent::new);
    }
}