package me.zwrumpy.chocohills.tools.listener;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.handlers.SimpleBlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.annotation.Nonnull;

public class WrenchInteract implements Listener {
    private JavaPlugin plugin;

    public WrenchInteract(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onWrenchClick(PlayerInteractEvent e) {
        if (!(e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();

        if (!(isWrench(item))) return;
        if ((isWrench(item))) {
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                e.setCancelled(true);
                return;
            }
        }

        Block b = e.getClickedBlock();
        if (!isSfBlock(b)) return;
        if (!canBreak(p, b)) return;

        BlockBreakEvent event = new BlockBreakEvent(b, p);
        Bukkit.getPluginManager().callEvent(event);
        if (event.isCancelled()) return;

        b.setType(Material.AIR);
        BlockStorage.clearBlockInfo(b);
    }

    boolean isWrench(@Nonnull ItemStack item) {
        SlimefunItem sfItem = SlimefunItem.getByItem(item);
        if (sfItem == null) return false;
        if (item.getType() == Material.AIR) return false;
        if (sfItem.getId().contains("ANTHERITE_WRENCH")) return true;
        return false;
    }

    void log(String message) {
        Bukkit.getLogger().info(message);
    }

    public boolean isSfBlock(@Nonnull Block block) {
        return (BlockStorage.check(block) != null);
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