package cn.nulladev.nullamultiblock.api.heat;

import net.minecraft.util.StringRepresentable;

public enum HeatLevel implements StringRepresentable {
    NONE,
    LOW,
    MEDIUM,
    HIGH,
    ;

    @Override
    public String getSerializedName() {
        return switch (this) {
            case NONE -> "none_heat";
            case LOW -> "low_heat";
            case MEDIUM -> "medium_heat";
            case HIGH -> "high_heat";
        };
    }
}
