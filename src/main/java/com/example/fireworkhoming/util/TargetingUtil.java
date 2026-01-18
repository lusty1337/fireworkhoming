package com.example.fireworkhoming.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class TargetingUtil {

    public static LivingEntity findTarget(LivingEntity shooter, double range) {
        Level level = shooter.level();
        Vec3 eyePos = shooter.getEyePosition();
        Vec3 lookVec = shooter.getLookAngle().normalize();

        AABB searchBox = shooter.getBoundingBox()
                .expandTowards(lookVec.scale(range))
                .inflate(2.0);

        List<Entity> entities = level.getEntities(shooter, searchBox);

        LivingEntity bestTarget = null;
        double bestDot = 0.95; // чем ближе к 1 — тем точнее по прицелу

        for (Entity entity : entities) {
            if (!(entity instanceof LivingEntity living)) continue;
            if (living == shooter) continue;

            Vec3 toTarget = living.getBoundingBox().getCenter().subtract(eyePos).normalize();
            double dot = lookVec.dot(toTarget);

            if (dot > bestDot) {
                bestDot = dot;
                bestTarget = living;
            }
        }

        return bestTarget;
    }
}
