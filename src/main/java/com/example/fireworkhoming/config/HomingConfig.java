package com.example.fireworkhoming.config;

public class HomingConfig {

    public static boolean targetHostile = true;
    public static boolean targetPassive = false;
    public static boolean targetPlayers = false;

    public static boolean allowPlayers() {
        return targetPlayers;
    }

    public static boolean allowHostile() {
        return targetHostile;
    }

    public static boolean allowPassive() {
        return targetPassive;
    }

}
