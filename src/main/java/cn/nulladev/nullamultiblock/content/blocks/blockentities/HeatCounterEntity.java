package cn.nulladev.nullamultiblock.content.blocks.blockentities;

import cn.nulladev.nullamultiblock.api.NMBProperties;
import cn.nulladev.nullamultiblock.api.heat.HeatLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class HeatCounterEntity extends BlockEntity {
    public HeatCounterEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, HeatCounterEntity t) {
        if (level.getBlockState(blockPos.below()).hasProperty(NMBProperties.HEAT_LEVEL)) {
            var heat = level.getBlockState(blockPos.below()).getValue(NMBProperties.HEAT_LEVEL);
            switch (heat) {
                case LOW -> {
                    if (blockState.getValue(NMBProperties.HEAT_LEVEL) != HeatLevel.LOW)
                        level.setBlockAndUpdate(blockPos, blockState.setValue(NMBProperties.HEAT_LEVEL, HeatLevel.LOW));
                }
                case MEDIUM -> {
                    if (blockState.getValue(NMBProperties.HEAT_LEVEL) != HeatLevel.MEDIUM)
                        level.setBlockAndUpdate(blockPos, blockState.setValue(NMBProperties.HEAT_LEVEL, HeatLevel.MEDIUM));
                }
                case HIGH -> {
                    if (blockState.getValue(NMBProperties.HEAT_LEVEL) != HeatLevel.HIGH)
                        level.setBlockAndUpdate(blockPos, blockState.setValue(NMBProperties.HEAT_LEVEL, HeatLevel.HIGH));
                }
                default -> {
                    if (blockState.getValue(NMBProperties.HEAT_LEVEL) != HeatLevel.NONE)
                        level.setBlockAndUpdate(blockPos, blockState.setValue(NMBProperties.HEAT_LEVEL, HeatLevel.NONE));
                }
            }
        } else {
            if (blockState.getValue(NMBProperties.HEAT_LEVEL) != HeatLevel.NONE)
                level.setBlockAndUpdate(blockPos, blockState.setValue(NMBProperties.HEAT_LEVEL, HeatLevel.NONE));
        }
    }
}
