package cn.nulladev.nullamultiblock.api;

import cn.nulladev.nullamultiblock.api.heat.HeatLevel;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class NMBProperties {
    public static final EnumProperty<HeatLevel> HEAT_LEVEL = EnumProperty.create("heat_level", HeatLevel.class);
}
