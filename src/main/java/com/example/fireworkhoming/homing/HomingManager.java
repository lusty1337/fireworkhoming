package com.example.fireworkhoming.homing;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class HomingManager {

    private static final Map<UUID, LivingEntity> TARGETS = new ConcurrentHashMap<>();

    public static void register(FireworkRocketEntity rocket, LivingEntity target) {
        TARGETS.put(rocket.getUUID(), target);
    }

    public static LivingEntity getTarget(UUID rocketId) {
        return TARGETS.get(rocketId);
    }

    public static void remove(UUID rocketId) {
        TARGETS.remove(rocketId);
    }

    public static Map<UUID, LivingEntity> getAll() {
        return TARGETS;
    }
}
