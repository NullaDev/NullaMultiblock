package cn.nulladev.nullamultiblock.content.blocks.blockentities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

public class FurnaceChamberEntity extends BlockEntity {


    public final ItemStackHandler CONTENT = new ItemStackHandler(3);

    public FurnaceChamberEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, CombustionChamberEntity t) {}
}
