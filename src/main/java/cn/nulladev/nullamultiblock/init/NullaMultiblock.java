package cn.nulladev.nullamultiblock.init;

import cn.nulladev.nullamultiblock.init.registrate.NMBBlocks;
import cn.nulladev.nullamultiblock.init.registrate.NMBItems;
import cn.nulladev.nullamultiblock.init.registrate.NMBMisc;
import com.tterrag.registrate.Registrate;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(NullaMultiblock.MODID)
public class NullaMultiblock
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "nullamultiblock";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final Registrate REGISTRATE = Registrate.create(MODID);


    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public NullaMultiblock(IEventBus modEventBus, ModContainer modContainer)
    {
        NMBItems.register();
        NMBBlocks.register();

        // Register the commonSetup method for mod loading
        modEventBus.addListener(this::commonSetup);
        NMBMisc.register(modEventBus);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
    }
}
