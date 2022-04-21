package me.zwrumpy.chocohills.listener;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
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
    int minutes30 = 30 * (60 * 20);

    public TorchInteract(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onTorchClick(PlayerInteractEvent e) {
        if (!(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK)) return;
        if (isItemEmpty(e.getItem())) return;
        ItemStack item = e.getPlayer().getInventory().getItemInMainHand();
        if (!isSfItem("TORCHTILLAS", item)) return;
        if (e.getPlayer().getPotionEffect(PotionEffectType.NIGHT_VISION) != null) return;

        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, minutes30, 0));
    }

    boolean isSfItem(String SfItemId, ItemStack item){
        SlimefunItem sfItem = SlimefunItem.getByItem(item);
        if (sfItem == null) return false;
        if (sfItem.getId() == null) return false;
        if (!sfItem.getId().contains(SfItemId)) return false;
        return true;
    }

    boolean isItemEmpty(ItemStack item){
        if (item == null) return true;
        if (item.getType() == null) return true;
        if (item.getType() == Material.AIR) return true;
        return false;
    }
}