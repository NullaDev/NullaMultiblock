package cn.nulladev.nullamultiblock.content.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

public class CrucibleEntity extends BlockEntity {

    public final FluidTank TANK = new FluidTank(3000);

    public CrucibleEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, CrucibleEntity t) {}
}
