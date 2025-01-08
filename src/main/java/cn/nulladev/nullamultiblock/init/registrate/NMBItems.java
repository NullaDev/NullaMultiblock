package cn.nulladev.nullamultiblock.init.registrate;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;

import static cn.nulladev.nullamultiblock.init.NullaMultiblock.REGISTRATE;

public class NMBItems {

    static {
        REGISTRATE.defaultCreativeTab("nullamultiblock",
                builder ->  builder
                        .title(Component.translatable("itemGroup.nullamultiblock"))
                        .icon(Items.REDSTONE_BLOCK::getDefaultInstance)
                ).register();
    }

    public static void register() {}
}
