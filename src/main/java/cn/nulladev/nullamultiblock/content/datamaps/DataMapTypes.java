package cn.nulladev.nullamultiblock.content.datamaps;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.datamaps.DataMapType;

public class DataMapTypes {
    public static final DataMapType<Item, FuelData> FUEL_DATA = DataMapType.builder(
            ResourceLocation.fromNamespaceAndPath("nullamultiblock", "fuel_data"),
            // The registry to register the data map for.
            Registries.ITEM,
            // The codec of the data map entries.
            FuelData.CODEC
    ).build();
}
