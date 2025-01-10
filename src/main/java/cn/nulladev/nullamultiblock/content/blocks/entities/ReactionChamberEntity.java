package cn.nulladev.nullamultiblock.content.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public class ReactionChamberEntity extends BlockEntity {
    public final FluidTank FLUID_TANK = new FluidTank(1000);

    public ReactionChamberEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, MixingChamberEntity t) {
    }
}
