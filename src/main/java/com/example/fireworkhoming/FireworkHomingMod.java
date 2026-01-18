package com.example.fireworkhoming;

import com.mojang.logging.LogUtils;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

@Mod(FireworkHomingMod.MODID)
public class FireworkHomingMod {
    public static final String MODID = "fireworkhoming";
    public static final Logger LOGGER = LogUtils.getLogger();

    public FireworkHomingMod() {
        LOGGER.info(">>> Firework Homing mod initialized <<<");
    }
}
