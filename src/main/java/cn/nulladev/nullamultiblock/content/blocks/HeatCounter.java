package cn.nulladev.nullamultiblock.content.blocks;

import cn.nulladev.nullamultiblock.api.NMBProperties;
import cn.nulladev.nullamultiblock.api.heat.HeatLevel;
import cn.nulladev.nullamultiblock.content.blocks.entities.HeatCounterEntity;
import cn.nulladev.nullamultiblock.init.registrate.NMBBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import javax.annotation.Nullable;

public class HeatCounter extends Block implements EntityBlock {
    public static final EnumProperty<HeatLevel> HEAT_LEVEL = NMBProperties.HEAT_LEVEL;

    public HeatCounter(Properties properties) {
        super(properties);
        registerDefaultState(getStateDefinition().any().setValue(HEAT_LEVEL, HeatLevel.NONE));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        // this is where the properties are actually added to the state
        pBuilder.add(HEAT_LEVEL);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return getStateDefinition().any().setValue(HEAT_LEVEL, HeatLevel.NONE);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new HeatCounterEntity(NMBBlocks.HEAT_COUNTER_ENTITY.get(), blockPos, blockState);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return blockEntityType == NMBBlocks.HEAT_COUNTER_ENTITY.get() ? (BlockEntityTicker<T>) (BlockEntityTicker<HeatCounterEntity>) HeatCounterEntity::tick : null;
    }
}
