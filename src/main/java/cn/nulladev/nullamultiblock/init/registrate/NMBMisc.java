package cn.nulladev.nullamultiblock.init.registrate;

import cn.nulladev.nullamultiblock.api.heat.HeatLevel;
import cn.nulladev.nullamultiblock.content.blocks.blockentities.CombustionChamberEntity;
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
