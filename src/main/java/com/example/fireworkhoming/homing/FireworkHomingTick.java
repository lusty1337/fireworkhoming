package com.example.fireworkhoming.homing;

import com.example.fireworkhoming.FireworkHomingMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FireworkHomingMod.MODID)
public class FireworkHomingTick {

    @SubscribeEvent
    public static void onLevelTick(TickEvent.LevelTickEvent event) {
        if (event.level.isClientSide()) return;

        // Forge 1.21.8: вместо deprecated 'phase' можно проверять START через метод isStart()
        if (event.phase() == TickEvent.Phase.START) return; // пропускаем старт

        // обработка END фазы
        HomingManager.tickAll();
    }
}
