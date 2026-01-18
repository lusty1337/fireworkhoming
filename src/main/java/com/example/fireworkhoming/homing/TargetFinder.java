package com.example.fireworkhoming.homing;

import com.example.fireworkhoming.config.HomingConfig;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class TargetFinder {

    private static final double SEARCH_RADIUS = 16.0; // радиус поиска целей

    public static LivingEntity findTarget(FireworkRocketEntity firework) {
        Level world = firework.level();
        if (world == null) return null;

        // область поиска вокруг ракеты
        AABB searchBox = firework.getBoundingBox().inflate(SEARCH_RADIUS);

        List<LivingEntity> entities = world.getEntitiesOfClass(LivingEntity.class, searchBox, e -> e.isAlive());

        LivingEntity closest = null;
        double closestDistance = Double.MAX_VALUE;

        for (LivingEntity entity : entities) {
            if (entity == firework.getOwner()) continue; // не атаковать владельца

            if (!isValidTarget(entity)) continue; // проверка по конфигу

            double distance = firework.position().distanceToSqr(entity.position());
            if (distance < closestDistance) {
                closestDistance = distance;
                closest = entity;
            }
        }

        return closest;
    }

    // проверка типа цели по конфигу
    private static boolean isValidTarget(LivingEntity entity) {
        if (entity instanceof Player && HomingConfig.allowPlayers()) return true;
        if (entity instanceof Monster && HomingConfig.allowHostile()) return true;
        if (entity instanceof Animal && HomingConfig.allowPassive()) return true;

        return false;
    }
}
