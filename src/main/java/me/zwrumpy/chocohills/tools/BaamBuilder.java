package me.zwrumpy.chocohills.tools;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.register.ChocoItems;
import net.md_5.bungee.api.chat.hover.content.Item;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import sun.jvm.hotspot.opto.Block;

import javax.annotation.Nonnull;

public class BaamBuilder implements Listener {

    public BaamBuilder(){
        register(BAAM_BUILDER, recipe);
        registerListener();
    }

    void registerListener(){
        ChocoHills.getInstance().getServer().getPluginManager().registerEvents(
                this, ChocoHills.getInstance()
        );
    }

    SlimefunItemStack BAAM_BUILDER = new SlimefunItemStack(
            "BAAM_BUILDER",
            Material.NETHERITE_HOE,
            "&6&lBaam Builder",
            "&7Usage:",
            "&fInstant Place 5 Blocks ",
            "&fin your hotbar",
            " ",
            "&7Right click blocks"
    );

    ItemStack[] recipe = new ItemStack[]{
            ChocoItems.CHOCO_CARBON, ChocoItems.CHOCO_CRYSTAL, ChocoItems.CHOCO_CARBON,
            new ItemStack(Material.AMETHYST_BLOCK), ChocoItems.TORCHTILLAS, new ItemStack(Material.AMETHYST_BLOCK),
            null, ChocoItems.CHOCO_CRYSTAL, null
    };

    void register(SlimefunItemStack stack, ItemStack[] recipe) {
        new Tools(ChocoItems.CH_TOOLS, stack, RecipeType.ENHANCED_CRAFTING_TABLE, recipe).register(ChocoHills.getInstance());
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onClick(PlayerInteractEvent e) {
        if (!(e.getAction() == Action.RIGHT_CLICK_BLOCK)) return;
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        if (!(isTool("BAAM_BUILDER", item))) return;
        e.setCancelled(true);

        ItemStack hotbarItem = getHotBarItem(p);
        placeItem(hotbarItem, p);
    }

    void placeItem(ItemStack hotbarItem, Player player){
        if (hotbarItem != null && hotbarItem.getAmount() >= 1){
            Location loc = player.getTargetBlockExact(5).getLocation();
            for (int i = 1; i <= 5; i++){
                loc.add(0, 1,0);
                if (!(hotbarItem.getAmount() >= 1 && loc.getBlock().getType() == Material.AIR)) continue;
                log(""+i+" "+loc.getBlockY());
                int amount = hotbarItem.getAmount() - 1;
                hotbarItem.setAmount(amount);
                loc.getBlock().setType(hotbarItem.getType());
                log(loc.getBlock().getType()+"");

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
            //if (playerItem instanceof InventoryHolder) continue;
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
}
