package cn.nulladev.nullamultiblock.init.registrate;

import cn.nulladev.nullamultiblock.api.heat.HeatLevel;
import cn.nulladev.nullamultiblock.content.blocks.entities.*;
import cn.nulladev.nullamultiblock.content.datamaps.DataMapTypes;
import cn.nulladev.nullamultiblock.content.datamaps.FuelData;
import com.tterrag.registrate.providers.ProviderType;
import net.minecraft.tags.ItemTags;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.registries.datamaps.RegisterDataMapTypesEvent;

import static cn.nulladev.nullamultiblock.init.NullaMultiblock.REGISTRATE;

public class NMBMisc {
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        // Register combustion chamber
        event.registerBlock(
                Capabilities.ItemHandler.BLOCK,
                (level, blockPos, blockState, blockEntity, direction) -> {
                    if (blockEntity instanceof CombustionChamberEntity combustionChamber) {
                        return combustionChamber.FUEL;
                    } else {
                        return null;
                    }
                },
                NMBBlocks.COMBUSTION_CHAMBER.get()
        );
        // Register furnace chamber
        event.registerBlock(
                Capabilities.ItemHandler.BLOCK,
                (level, blockPos, blockState, blockEntity, direction) -> {
                    if (blockEntity instanceof FurnaceChamberEntity furnaceChamber) {
                        return furnaceChamber.INPUT_STACK;
                    } else {
                        return null;
                    }
                },
                NMBBlocks.FURNACE_CHAMBER.get()
        );
        // Register crucible
        event.registerBlock(
                Capabilities.FluidHandler.BLOCK,
                (level, blockPos, blockState, blockEntity, direction) -> {
                    if (blockEntity instanceof CrucibleEntity crucible) {
                        return crucible.INPUT_TANK;
                    } else {
                        return null;
                    }
                },
                NMBBlocks.CRUCIBLE.get()
        );

    }

    public static void registerDataMapTypes(RegisterDataMapTypesEvent event) {
        event.register(DataMapTypes.FUEL_DATA);
    }

    public static void provideDataMaps() {
        REGISTRATE.addDataGenerator(ProviderType.DATA_MAP, pvd -> pvd.builder(DataMapTypes.FUEL_DATA)
                .add(ItemTags.LOGS, new FuelData(HeatLevel.LOW, 600), false)
                .add(ItemTags.PLANKS, new FuelData(HeatLevel.LOW, 300), false)
                .add(ItemTags.COALS, new FuelData(HeatLevel.MEDIUM, 1600), false));
    }

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(NMBMisc::registerCapabilities);
        modEventBus.addListener(NMBMisc::registerDataMapTypes);
        provideDataMaps();
    }
}
