package com.example.fireworkhoming.client.gui;

import com.example.fireworkhoming.config.HomingConfig;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class HomingConfigScreen extends Screen {

    private Checkbox hostile;
    private Checkbox passive;
    private Checkbox players;

    public HomingConfigScreen() {
        super(Component.literal("Firework Homing Settings"));
    }

    @Override
    protected void init() {
        int x = this.width / 2 - 100;
        int y = this.height / 2 - 30;

        hostile = Checkbox.builder(
                Component.literal("Target hostile mobs"),
                this.font
        ).pos(x, y).selected(HomingConfig.targetHostile).build();

        passive = Checkbox.builder(
                Component.literal("Target passive mobs"),
                this.font
        ).pos(x, y + 25).selected(HomingConfig.targetPassive).build();

        players = Checkbox.builder(
                Component.literal("Target players"),
                this.font
        ).pos(x, y + 50).selected(HomingConfig.targetPlayers).build();

        addRenderableWidget(hostile);
        addRenderableWidget(passive);
        addRenderableWidget(players);

        addRenderableWidget(
                Button.builder(Component.literal("Done"), b -> {
                    HomingConfig.targetHostile = hostile.selected();
                    HomingConfig.targetPassive = passive.selected();
                    HomingConfig.targetPlayers = players.selected();
                    this.minecraft.setScreen(null);
                }).bounds(x + 50, y + 85, 100, 20).build()
        );
    }
}
