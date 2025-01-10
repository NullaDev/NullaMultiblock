package cn.nulladev.nullamultiblock.content.blocks;

import cn.nulladev.nullamultiblock.content.blocks.entities.ReactionChamberEntity;
import cn.nulladev.nullamultiblock.init.registrate.NMBBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ReactionChamber extends HorizontalComponent implements EntityBlock {
    public ReactionChamber(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new ReactionChamberEntity(NMBBlocks.REACTION_CHAMBER_ENTITY.get(), blockPos, blockState);
    }
}
