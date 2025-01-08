package cn.nulladev.nullamultiblock.init.registrate;

import cn.nulladev.nullamultiblock.api.NMBProperties;
import cn.nulladev.nullamultiblock.api.heat.HeatLevel;
import cn.nulladev.nullamultiblock.content.blocks.CombustionChamber;
import cn.nulladev.nullamultiblock.content.blocks.CreativeHeatBlock;
import cn.nulladev.nullamultiblock.content.blocks.HeatCounter;
import cn.nulladev.nullamultiblock.content.blocks.blockentities.CombustionChamberEntity;
import cn.nulladev.nullamultiblock.content.blocks.blockentities.HeatCounterEntity;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;

import static cn.nulladev.nullamultiblock.content.blocks.CombustionChamber.LIT;
import static cn.nulladev.nullamultiblock.init.NullaMultiblock.REGISTRATE;

public class NMBBlocks {

    public static final BlockEntry<CombustionChamber> COMBUSTION_CHAMBER = REGISTRATE.block("combustion_chamber", CombustionChamber::new)
            .defaultLang()
            .blockstate((ctx, pvd) -> {
                var unlit = pvd.models().cube("block/combustion_chamber",
                        pvd.modLoc("block/combustion_chamber_top"),
                        pvd.modLoc("block/combustion_chamber_top"),
                        pvd.modLoc("block/combustion_chamber_front"),
                        pvd.modLoc("block/combustion_chamber_side"),
                        pvd.modLoc("block/combustion_chamber_side"),
                        pvd.modLoc("block/combustion_chamber_side"));

                var lit = pvd.models().cube("block/lit_combustion_chamber",
                        pvd.modLoc("block/combustion_chamber_top"),
                        pvd.modLoc("block/combustion_chamber_top"),
                        pvd.modLoc("block/combustion_chamber_front_lit"),
                        pvd.modLoc("block/combustion_chamber_side"),
                        pvd.modLoc("block/combustion_chamber_side"),
                        pvd.modLoc("block/combustion_chamber_side"));

                pvd.horizontalBlock(ctx.get(), blockState -> blockState.getValue(LIT) ? lit : unlit);
            })
            .simpleItem()
            .register();
    public static final BlockEntityEntry<CombustionChamberEntity> COMBUSTION_CHAMBER_ENTITY = REGISTRATE.blockEntity("combustion_chamber_entity", CombustionChamberEntity::new)
            .validBlock(COMBUSTION_CHAMBER).register();

    public static final BlockEntry<HeatCounter> HEAT_COUNTER = REGISTRATE.block("heat_counter", HeatCounter::new)
            .defaultLang()
            .blockstate((ctx, pvd) -> {
                var none = pvd.models().cubeAll("block/heat_counter", pvd.modLoc("block/none_heat_counter"));
                var low = pvd.models().cubeAll("block/low_heat_counter", pvd.modLoc("block/low_heat_counter"));
                var medium = pvd.models().cubeAll("block/medium_heat_counter", pvd.modLoc("block/medium_heat_counter"));
                var high = pvd.models().cubeAll("block/high_heat_counter", pvd.modLoc("block/high_heat_counter"));

                pvd.getVariantBuilder(ctx.get()).forAllStates(blockState ->
                    switch (blockState.getValue(NMBProperties.HEAT_LEVEL)) {
                        case NONE -> ConfiguredModel.builder().modelFile(none).build();
                        case LOW -> ConfiguredModel.builder().modelFile(low).build();
                        case MEDIUM -> ConfiguredModel.builder().modelFile(medium).build();
                        case HIGH -> ConfiguredModel.builder().modelFile(high).build();
                    }
                );
            })
            .simpleItem()
            .register();
    public static final BlockEntityEntry<HeatCounterEntity> HEAT_COUNTER_ENTITY = REGISTRATE.blockEntity("heat_counter_entity", HeatCounterEntity::new)
            .validBlock(HEAT_COUNTER)
            .register();

    public static final BlockEntry<CreativeHeatBlock> NONE_HEAT_BLOCK =
            REGISTRATE.block("none_heat_block", properties -> new CreativeHeatBlock(properties, HeatLevel.NONE))
                    .defaultLang()
                    .defaultBlockstate()
                    .simpleItem()
                    .register();
    public static final BlockEntry<CreativeHeatBlock> LOW_HEAT_BLOCK =
            REGISTRATE.block("low_heat_block", properties -> new CreativeHeatBlock(properties, HeatLevel.LOW))
                    .defaultLang()
                    .defaultBlockstate()
                    .simpleItem()
                    .register();

    public static final BlockEntry<CreativeHeatBlock> MEDIUM_HEAT_BLOCK =
            REGISTRATE.block("medium_heat_block", properties -> new CreativeHeatBlock(properties, HeatLevel.MEDIUM))
                    .defaultLang()
                    .defaultBlockstate()
                    .simpleItem()
                    .register();

    public static final BlockEntry<CreativeHeatBlock> HIGH_HEAT_BLOCK =
            REGISTRATE.block("high_heat_block", properties -> new CreativeHeatBlock(properties, HeatLevel.HIGH))
                    .defaultLang()
                    .defaultBlockstate()
                    .simpleItem()
                    .register();

    public static final BlockEntry<Block> SULPHUR =
            REGISTRATE.block("sulphur_block", Block::new)
                    .defaultLang()
                    .defaultBlockstate()
                    .simpleItem()
                    .register();

    public static void register() {}
}
