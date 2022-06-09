package me.zwrumpy.chocohills.tools.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class WalkListener implements Listener {
    private JavaPlugin plugin;
    private final HashMap<Player, Boolean> walking;

    public WalkListener(JavaPlugin plugin) {
        this.plugin = plugin;
        walking = new HashMap<>();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onWalk(PlayerMoveEvent e) {
        if (walking.get(e.getPlayer()) != null) {
            int X = e.getFrom().getBlockX();
            int x = e.getTo().getBlockX();
            int Z = e.getFrom().getBlockZ();
            int z = e.getTo().getBlockZ();
            if (x != X || z != Z) {

            }
        }
    }
}