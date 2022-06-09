package me.zwrumpy.chocohills.tools.listener;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class TorchInteract implements Listener{
    private JavaPlugin plugin;
    private int minutes30 = 30 * (60 * 20);

    public TorchInteract(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onTorchClick(PlayerInteractEvent e) {
        if (!(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK)) return;
        ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
        if (item.getType() == Material.AIR) return;
        if (!(isTorchtillas(item))) return;
        if (e.getPlayer().getPotionEffect(PotionEffectType.NIGHT_VISION) != null) return;
        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, minutes30, 0));
    }

    boolean isTorchtillas(ItemStack item){
        SlimefunItem sfItem = SlimefunItem.getByItem(item);
        if (sfItem == null) return false;
        if (sfItem.getId().contains("TORCHTILLAS")) return true;
        return false;
    }

    void log(String message){
        Bukkit.getLogger().info(message);
    }
}