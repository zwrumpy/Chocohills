package me.zwrumpy.chocohills.tools.listener;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class BaamBuilderListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onClick(PlayerInteractEvent e) {
        if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (!(isTool("BAAM_BUILDER", item))) return;
        e.setCancelled(true);
        placeItem(getHotBarItem(p), p);
    }

    void placeItem(ItemStack hotbarItem, Player player){
        if (hotbarItem != null && hotbarItem.getAmount() >= 1){
            Location loc = player.getTargetBlockExact(5).getLocation();
            if (!canBreak(player, loc.getBlock())) return;
            for (int i = 1; i <= 5; i++){
                loc.add(0, 1,0);
                if (!(hotbarItem.getAmount() >= 1 && loc.getBlock().getType() == Material.AIR)) continue;
                //log(""+i+" "+loc.getBlockY());
                int amount = hotbarItem.getAmount() - 1;
                loc.getBlock().setType(hotbarItem.getType());
                hotbarItem.setAmount(amount);
                //log(loc.getBlock().getType()+"");
            }
        }
    }

    ItemStack getHotBarItem(Player p){
        ItemStack item = null;
        for (int i = 0; i < 9; i++){
            if (p.getInventory().getItem(i) == null) continue;
            ItemStack playerItem = p.getInventory().getItem(i);
            if (playerItem.getType() == Material.AIR) continue;
            if (!playerItem.getType().isBlock()) continue;
            if (playerItem.getType().isInteractable()) continue;
            if (playerItem.getAmount() == 1) continue;
            if (isSlimefunItem(playerItem)) continue;
            item = p.getInventory().getItem(i);
            break;
        }
        return item;
    }

    private static boolean isSlimefunItem(@Nonnull ItemStack item) {
        SlimefunItem sfItem = SlimefunItem.getByItem(item);
        return sfItem != null;
    }

    boolean isTool(@Nonnull String slimefun_ID, ItemStack item) {
        SlimefunItem sfItem = SlimefunItem.getByItem(item);
        if (sfItem == null) return false;
        if (item.getType() == Material.AIR) return false;
        if (sfItem.getId().contains(slimefun_ID)) return true;
        return false;
    }

    void log(String message) {
        Bukkit.getLogger().info(message);
    }

    protected boolean canBreak(@Nonnull Player p, @Nonnull Block b) {
        if (b.isEmpty() || b.isLiquid()) {
            return false;
        } else if (SlimefunTag.UNBREAKABLE_MATERIALS.isTagged(b.getType())) {
            return false;
        } else if (!b.getWorld().getWorldBorder().isInside(b.getLocation())) {
            return false;
        } else if (Slimefun.getIntegrations().isCustomBlock(b)) {
            return false;
        } else {
            return Slimefun.getProtectionManager().hasPermission(p, b.getLocation(), Interaction.BREAK_BLOCK);
        }
    }
}
