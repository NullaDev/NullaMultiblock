package cn.nulladev.nullamultiblock.content.blocks.entities;

import cn.nulladev.nullamultiblock.api.NMBProperties;
import cn.nulladev.nullamultiblock.content.datamaps.DataMapTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.items.ItemStackHandler;

public class CombustionChamberEntity extends BlockEntity {
    public CombustionChamberEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public final ItemStackHandler FUEL = new ItemStackHandler();

    private int burningTime  = 0;

    public ItemStack insertFuel(ItemStack stack) {
        return FUEL.insertItem(0, stack, false);
    }

    public ItemStack extractFuel() {
        return FUEL.extractItem(0, FUEL.getStackInSlot(0).getCount(), false);
    }

    private void consumeFuel() {
        FUEL.getStackInSlot(0).setCount(FUEL.getStackInSlot(0).getCount() - 1);
    }

    @Override
    public void setChanged() {
        super.setChanged();
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        this.FUEL.deserializeNBT(registries, tag);
        this.burningTime = tag.getInt("burning_time");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.merge(this.FUEL.serializeNBT(registries));
        tag.putInt("burning_time", this.burningTime);
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, CombustionChamberEntity t) {
        if (t.burningTime > 0) {
            t.burningTime--;
        } else {
            var fuel = t.FUEL.getStackInSlot(0);
            if (fuel.isEmpty()) {
                level.setBlockAndUpdate(blockPos, blockState.setValue(BlockStateProperties.LIT, false));
            } else {
                var data = fuel.getItemHolder().getData(DataMapTypes.FUEL_DATA);
                if (data != null) {
                    t.burningTime = data.burningTime();
                    level.setBlockAndUpdate(blockPos,
                            blockState.setValue(BlockStateProperties.LIT, true)
                            .setValue(NMBProperties.HEAT_LEVEL, data.heatLevel()));
                    t.consumeFuel();
                }
            }
        }

    }
}
