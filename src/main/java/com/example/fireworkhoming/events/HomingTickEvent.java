package com.example.fireworkhoming.events;

import com.example.fireworkhoming.FireworkHomingMod;
import com.example.fireworkhoming.homing.HomingManager;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;

@Mod.EventBusSubscriber(modid = FireworkHomingMod.MODID)
public class HomingTickEvent {

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        if (!(event.level instanceof ServerLevel level)) return;

        for (UUID id : HomingManager.getAll().keySet()) {
            LivingEntity target = HomingManager.getTarget(id);
            if (target == null || !target.isAlive()) {
                HomingManager.remove(id);
                continue;
            }

            if (!(level.getEntity(id) instanceof FireworkRocketEntity rocket)) {
                HomingManager.remove(id);
                continue;
            }

            Vec3 dir = target.getEyePosition()
                    .subtract(rocket.position())
                    .normalize();

            double speed = rocket.getDeltaMovement().length();

            rocket.setDeltaMovement(
                    rocket.getDeltaMovement().scale(0.85)
                            .add(dir.scale(speed * 0.15))
            );
        }
    }
}
