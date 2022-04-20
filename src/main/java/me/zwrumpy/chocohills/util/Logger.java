package me.zwrumpy.chocohills.util;

import org.bukkit.Bukkit;

import java.util.logging.Level;

public class Logger {
    public static void log(String string) {
        Bukkit.getLogger().log(Level.INFO, string);
    }
}
