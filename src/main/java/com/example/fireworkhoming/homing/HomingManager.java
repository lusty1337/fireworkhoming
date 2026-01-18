package com.example.fireworkhoming.homing;

import com.example.fireworkhoming.homing.TargetFinder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.phys.Vec3;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class HomingManager {

    private static final Map<UUID, FireworkRocketEntity> FIREWORKS = new ConcurrentHashMap<>();
    private static final Map<UUID, LivingEntity> TARGETS = new ConcurrentHashMap<>();

    public static void register(FireworkRocketEntity firework) {
        FIREWORKS.put(firework.getUUID(), firework);

        LivingEntity target = TargetFinder.findTarget(firework);
        if (target != null) {
            TARGETS.put(firework.getUUID(), target);
        }
    }

    public static void tickAll() {
        Iterator<Map.Entry<UUID, FireworkRocketEntity>> it = FIREWORKS.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<UUID, FireworkRocketEntity> entry = it.next();
            FireworkRocketEntity firework = entry.getValue();

            if (!firework.isAlive()) {
                TARGETS.remove(entry.getKey());
                it.remove();
                continue;
            }

            LivingEntity target = TARGETS.get(entry.getKey());
            if (target == null || !target.isAlive()) continue;

            Vec3 direction = target.position().subtract(firework.position());
            if (direction.lengthSqr() < 0.0001) continue;

            Vec3 velocity = direction.normalize().scale(0.9);
            firework.setDeltaMovement(velocity);
        }
    }
}
