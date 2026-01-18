package com.example.fireworkhoming.events;

import com.example.fireworkhoming.FireworkHomingMod;
import com.example.fireworkhoming.homing.HomingManager;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.entity.player.Player;
import com.example.fireworkhoming.homing.TargetFinder;


@Mod.EventBusSubscriber(modid = FireworkHomingMod.MODID)
public class FireworkEvents {

    @SubscribeEvent
    public static void onEntitySpawn(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof FireworkRocketEntity rocket)) return;

        if (!(rocket.getOwner() instanceof LivingEntity shooter)) return;

        LivingEntity target = TargetFinder.findTarget((Player) shooter);
        if (target != null) {
            HomingManager.register(rocket, target);
        }

    }
}
