package cn.nulladev.nullamultiblock.content.blocks;

import cn.nulladev.nullamultiblock.content.blocks.entities.CrucibleEntity;
import cn.nulladev.nullamultiblock.init.registrate.NMBBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class Crucible extends Block implements EntityBlock {
    public Crucible(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CrucibleEntity(NMBBlocks.CRUCIBLE_ENTITY.get(), blockPos, blockState);
    }
}
