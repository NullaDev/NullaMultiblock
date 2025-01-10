package cn.nulladev.nullamultiblock.content.blocks;

import cn.nulladev.nullamultiblock.api.NMBProperties;
import cn.nulladev.nullamultiblock.api.heat.HeatLevel;
import cn.nulladev.nullamultiblock.content.blocks.entities.CombustionChamberEntity;
import cn.nulladev.nullamultiblock.content.datamaps.DataMapTypes;
import cn.nulladev.nullamultiblock.init.registrate.NMBBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class CombustionChamber extends HorizontalComponent implements EntityBlock {

    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final EnumProperty<HeatLevel> HEAT_LEVEL = NMBProperties.HEAT_LEVEL;

    public CombustionChamber(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any()
                .setValue(FACING, Direction.NORTH)
                .setValue(LIT, false)
                .setValue(HEAT_LEVEL, HeatLevel.NONE));
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new CombustionChamberEntity(NMBBlocks.COMBUSTION_CHAMBER_ENTITY.get(), blockPos, blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        // this is where the properties are actually added to the state
        pBuilder.add(FACING, LIT, HEAT_LEVEL);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return getStateDefinition().any()
                .setValue(FACING, pContext.getHorizontalDirection().getOpposite())
                .setValue(LIT, false)
                .setValue(HEAT_LEVEL, HeatLevel.NONE);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        var entity = level.getBlockEntity(pos);
        if (entity instanceof CombustionChamberEntity combustionChamber) {
            if (stack.isEmpty()) {
                player.setItemInHand(hand, combustionChamber.extractFuel());
            } else {
                var data = stack.getItemHolder().getData(DataMapTypes.FUEL_DATA);
                if (data != null) {
                    var r = combustionChamber.insertFuel(stack);
                    player.setItemInHand(hand, r);
                    return ItemInteractionResult.SUCCESS;
                }
            }
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @SuppressWarnings("unchecked")
    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return blockEntityType == NMBBlocks.COMBUSTION_CHAMBER_ENTITY.get() ?
                (BlockEntityTicker<T>) (BlockEntityTicker<CombustionChamberEntity>) CombustionChamberEntity::tick :
                null;
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock())) {
            BlockEntity blockentity = level.getBlockEntity(pos);
            if (blockentity instanceof CombustionChamberEntity combustionChamberEntity) {
                if (level instanceof ServerLevel) {
                    for (int i = 0; i < combustionChamberEntity.FUEL.getSlots(); i++) {
                        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), combustionChamberEntity.FUEL.getStackInSlot(i));
                    }
                }
                super.onRemove(state, level, pos, newState, movedByPiston);
                level.updateNeighbourForOutputSignal(pos, this);
            } else {
                super.onRemove(state, level, pos, newState, movedByPiston);
            }
        }
    }
}
