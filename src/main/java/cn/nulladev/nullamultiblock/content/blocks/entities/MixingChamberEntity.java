package cn.nulladev.nullamultiblock.content.blocks.entities;

import cn.nulladev.nullamultiblock.content.blocks.MixingChamber;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.fluids.capability.IFluidHandler;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nullable;

public class MixingChamberEntity extends BlockEntity {
    public final FluidTank TANK_1 = new FluidTank(1000);
    public final FluidTank TANK_2 = new FluidTank(1000);
    public final FluidTank TANK_3 = new FluidTank(1000);

    public MixingChamberEntity(BlockEntityType<?> type, BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    public @Nullable IFluidHandler getTankPerFace(Direction direction) {
        var facing = getBlockState().getValue(MixingChamber.FACING);
        return switch (facing) {
            case NORTH -> switch (direction) {
                case UP -> TANK_3;
                case WEST -> TANK_2;
                case EAST -> TANK_1;
                default -> null;
            };
            case SOUTH -> switch (direction) {
                case UP -> TANK_3;
                case WEST -> TANK_1;
                case EAST -> TANK_2;
                default -> null;
            };
            case WEST -> switch (direction) {
                case UP -> TANK_3;
                case NORTH -> TANK_1;
                case SOUTH -> TANK_2;
                default -> null;
            };
            case EAST -> switch (direction) {
                case UP -> TANK_3;
                case NORTH -> TANK_2;
                case SOUTH -> TANK_1;
                default -> null;
            };
            default -> null;
        };
    }

    public ItemStack insertPerFace(Direction direction, ItemStack stack) {
        return ItemStack.EMPTY;
    }

    public static void tick(Level level, BlockPos blockPos, BlockState blockState, MixingChamberEntity t) {}
}
