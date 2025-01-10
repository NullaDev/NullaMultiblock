package cn.nulladev.nullamultiblock.content.blocks;

import cn.nulladev.nullamultiblock.content.blocks.entities.FurnaceChamberEntity;
import cn.nulladev.nullamultiblock.init.registrate.NMBBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class FurnaceChamber extends Block implements EntityBlock {
    public FurnaceChamber(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new FurnaceChamberEntity(NMBBlocks.FURNACE_CHAMBER_ENTITY.get(), blockPos, blockState);
    }
}
