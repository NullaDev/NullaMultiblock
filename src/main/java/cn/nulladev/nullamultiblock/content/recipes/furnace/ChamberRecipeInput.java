package cn.nulladev.nullamultiblock.content.recipes.furnace;

import cn.nulladev.nullamultiblock.api.heat.HeatLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeInput;

public record ChamberRecipeInput(HeatLevel heatLevel, Ingredient ingredient) implements RecipeInput {
    @Override
    public ItemStack getItem(int i) {
        if (i < 0 || i > 2) throw new IllegalArgumentException("No item for index " + i);
        return this.ingredient.getItems()[i];
    }

    @Override
    public int size() {
        return 3;
    }
}
