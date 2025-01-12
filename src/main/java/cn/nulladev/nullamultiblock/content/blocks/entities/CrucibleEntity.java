package cn.nulladev.nullamultiblock.content.blocks.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.ItemStackHandler;

public class CrucibleEntity extends BlockEntity {

    public final FluidTank INPUT_TANK = new FluidTank(2000);
    public final ItemStackHandler OUTPUT_STACK = new ItemStackHandler(1);
    public final FluidTank OUTPUT_TANK = new FluidTank(2000);

    public CrucibleEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, CrucibleEntity t) {}
}
