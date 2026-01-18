package com.example.fireworkhoming.events;

import com.example.fireworkhoming.FireworkHomingMod;
import com.example.fireworkhoming.homing.HomingManager;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FireworkHomingMod.MODID)
public class FireworkEvents {

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof FireworkRocketEntity firework)) return;
        if (event.getLevel().isClientSide()) return;

        HomingManager.register(firework);
    }
}
