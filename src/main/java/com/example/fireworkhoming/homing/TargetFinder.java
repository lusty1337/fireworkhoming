package com.example.fireworkhoming.homing;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class TargetFinder {

    private static final double MAX_DISTANCE = 30.0;
    private static final double MAX_ANGLE = Math.toRadians(20); // 20 градусов

    public static LivingEntity findTarget(Player player) {
        Vec3 eyePos = player.getEyePosition();
        Vec3 look = player.getLookAngle().normalize();

        AABB box = player.getBoundingBox()
                .expandTowards(look.scale(MAX_DISTANCE))
                .inflate(3);

        List<Entity> entities = player.level().getEntities(player, box);

        LivingEntity bestTarget = null;
        double bestAngle = MAX_ANGLE;

        for (Entity entity : entities) {
            if (!(entity instanceof LivingEntity living)) continue;
            if (living == player) continue;
            if (!living.isAlive()) continue;

            Vec3 toEntity = living.getEyePosition().subtract(eyePos).normalize();
            double angle = Math.acos(look.dot(toEntity));

            if (angle < bestAngle) {
                bestAngle = angle;
                bestTarget = living;
            }
        }

        return bestTarget;
    }
}
