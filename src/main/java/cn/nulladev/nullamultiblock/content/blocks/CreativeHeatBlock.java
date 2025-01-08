package cn.nulladev.nullamultiblock.content.blocks;

import cn.nulladev.nullamultiblock.api.NMBProperties;
import cn.nulladev.nullamultiblock.api.heat.HeatLevel;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import javax.annotation.Nullable;

public class CreativeHeatBlock extends Block {
    private final HeatLevel heatLevel;

    public static final EnumProperty<HeatLevel> HEAT_LEVEL = NMBProperties.HEAT_LEVEL;

    public CreativeHeatBlock(Properties properties, HeatLevel heatLevel) {
        super(properties);
        this.heatLevel = heatLevel;
        switch (heatLevel) {
            case NONE -> registerDefaultState(getStateDefinition().any().setValue(HEAT_LEVEL, HeatLevel.NONE));
            case LOW -> registerDefaultState(getStateDefinition().any().setValue(HEAT_LEVEL, HeatLevel.LOW));
            case MEDIUM -> registerDefaultState(getStateDefinition().any().setValue(HEAT_LEVEL, HeatLevel.MEDIUM));
            case HIGH -> registerDefaultState(getStateDefinition().any().setValue(HEAT_LEVEL, HeatLevel.HIGH));
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        // this is where the properties are actually added to the state
        pBuilder.add(HEAT_LEVEL);
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return switch (heatLevel) {
            case NONE -> getStateDefinition().any().setValue(HEAT_LEVEL, HeatLevel.NONE);
            case LOW -> getStateDefinition().any().setValue(HEAT_LEVEL, HeatLevel.LOW);
            case MEDIUM -> getStateDefinition().any().setValue(HEAT_LEVEL, HeatLevel.MEDIUM);
            case HIGH -> getStateDefinition().any().setValue(HEAT_LEVEL, HeatLevel.HIGH);
        };
    }
}
