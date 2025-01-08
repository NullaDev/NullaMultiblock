package cn.nulladev.nullamultiblock.content.datamaps;

import cn.nulladev.nullamultiblock.api.heat.HeatLevel;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

public record FuelData(HeatLevel heatLevel, int burningTime) {
    public static final Codec<FuelData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("heat_level").forGetter(data -> data.heatLevel.ordinal()),
            Codec.INT.fieldOf("burning_time").forGetter(FuelData::burningTime)
    ).apply(instance, (heatLevel, burningTime) -> new FuelData(HeatLevel.values()[heatLevel], burningTime)));
}
