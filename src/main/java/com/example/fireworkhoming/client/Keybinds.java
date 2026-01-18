package com.example.fireworkhoming.client;

import com.example.fireworkhoming.client.gui.HomingConfigScreen;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

import static com.example.fireworkhoming.FireworkHomingMod.MODID;

@Mod.EventBusSubscriber(
        modid = MODID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class Keybinds {

    public static KeyMapping OPEN_GUI;

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        OPEN_GUI = new KeyMapping(
                "key.fireworkhoming.open_gui",
                GLFW.GLFW_KEY_H,
                "key.categories.fireworkhoming"
        );
        event.register(OPEN_GUI);
    }

    @Mod.EventBusSubscriber(modid = MODID, value = Dist.CLIENT)
    public static class ClientTick {

        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            // пропускаем Start фазу, чтобы работать только в End
            if (event.phase() == TickEvent.Phase.START) return;

            if (OPEN_GUI != null && OPEN_GUI.consumeClick()) {
                Minecraft.getInstance().setScreen(new HomingConfigScreen());
            }
        }
    }
}
