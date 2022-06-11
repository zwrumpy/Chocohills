package me.zwrumpy.chocohills.machine.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class AnvilInventoryListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onClickMenu(InventoryClickEvent e) {
        if (!e.getView().getTitle().equalsIgnoreCase("CHOCO ADVANCE ANVIL")) return;
        Inventory invBottom = e.getView().getBottomInventory();
        if (e.getInventory().equals(invBottom)) return;

        Inventory invTop = e.getView().getTopInventory();

        ItemStack itemOutput = invTop.getItem(25);
        ItemStack itemInput1 = invTop.getItem(19);
        ItemStack itemInput2 = invTop.getItem(21);

        if (itemInput1 != null && itemInput2 != null && !isBarrierOutput(itemOutput)) {
            if (e.getInventory().equals(invBottom)) return;
            if (e.getRawSlot() != 25) return;
            invTop.setItem(19, new ItemStack(Material.AIR));
            invTop.setItem(21, new ItemStack(Material.AIR));
            e.setCancelled(true);
            return;
        }

        if (isBarrierOutput(itemOutput)) {
            if (!e.getInventory().equals(invTop)) return;
            checkBarrier(itemOutput, invTop);
            e.setCancelled(true);
            return;
        }
    }

    public void checkBarrier(ItemStack item, Inventory inv) {
        if (item == null) return;
        if (isBarrierOutput(item)) {
            inv.setItem(25, new ItemStack(Material.AIR));
        }
    }

    @Nonnull
    public boolean isBarrierOutput(ItemStack item){
        if (item == null) return false;
        return item.getType() == Material.BARRIER;
    }
}

