package cn.nulladev.nullamultiblock.content.blocks;

import cn.nulladev.nullamultiblock.content.blocks.entities.MixingChamberEntity;
import cn.nulladev.nullamultiblock.init.registrate.NMBBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class MixingChamber extends HorizontalComponent implements EntityBlock {

    public MixingChamber(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new MixingChamberEntity(NMBBlocks.MIXING_CHAMBER_ENTITY.get(), blockPos, blockState);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        var entity = level.getBlockEntity(pos);
        if (entity instanceof MixingChamberEntity mixingChamber) {
            if (player.getItemInHand(hand).is(Items.WATER_BUCKET)) {
                var ret = mixingChamber.insertPerFace(player.getDirection(), player.getItemInHand(hand));
                player.setItemInHand(hand, ret);
                return ItemInteractionResult.SUCCESS;
            } else {
                return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
}
