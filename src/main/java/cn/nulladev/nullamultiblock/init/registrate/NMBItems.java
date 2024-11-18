package cn.nulladev.nullamultiblock.init.registrate;

import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static cn.nulladev.nullamultiblock.init.NullaMultiblock.MODID;

public class NMBItems {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    // Creates a new food item with the id "nullamultiblock:example_id", nutrition 1 and saturation 2
    public static final DeferredItem<Item> EXAMPLE_ITEM = ITEMS.registerSimpleItem("example_item", new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible().nutrition(1).saturationModifier(2f).build()));
}
