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
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class DumBearListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onClick(PlayerInteractEvent e) {
        if (!(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();

        if (!(isTool("DUMBEAR", item))) return;
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            e.setCancelled(true);
            return;
        }


        for (Block b : getNearbyBlocks(p, e.getClickedBlock().getLocation(), 4)){
            if (canBreak(p, b)){
                b.setType(Material.AIR);
            }
        }

        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockFromTo(BlockFromToEvent event) {
        if(!event.getBlock().isLiquid()) return;
        if(event.getBlock().getType() == Material.WATER)
            event.setCancelled(true);
    }

    public static List<Block> getNearbyBlocks(Player player, Location location, int radius) {
        List<Block> blocks = new ArrayList<Block>();
        for(int x = location.getBlockX() - radius; x <= location.getBlockX() + radius; x++) {
            for(int y = location.getBlockY() - radius; y <= location.getBlockY() + radius; y++) {
                for(int z = location.getBlockZ() - radius; z <= location.getBlockZ() + radius; z++) {
                    blocks.add(location.getWorld().getBlockAt(x, y, z));
                }
            }
        }
        return blocks;
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
        if (b.isEmpty() || !b.isLiquid()) {
            return false;
        } else if (SlimefunTag.UNBREAKABLE_MATERIALS.isTagged(b.getType())) {
            return false;
        } else if (!b.getWorld().getWorldBorder().isInside(b.getLocation())) {
            return false;
        } else if (Slimefun.getIntegrations().isCustomBlock(b)) {
            return false;
        } else if (Slimefun.getProtectionManager().hasPermission(p, b.getLocation(), Interaction.BREAK_BLOCK)) {
            return true;
        } else if (b.getType() == Material.WATER) {
            return true;
        } else if (b.isLiquid()) {
            return false;
        } else {
            return true;
        }
    }
}
