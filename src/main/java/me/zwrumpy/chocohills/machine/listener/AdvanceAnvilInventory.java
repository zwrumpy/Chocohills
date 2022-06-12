package me.zwrumpy.chocohills.machine.listener;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class AdvanceAnvilInventory implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void onClickMenu(InventoryClickEvent e) {
        if (!isChocoAnvil(e)) return;

        Inventory invBottom = e.getView().getBottomInventory();
        Inventory invTop = e.getView().getTopInventory();
        ItemStack itemPreview = invTop.getItem(23);

        if (e.getInventory().equals(invBottom)) return;
        if (invTop.getItem(25) != null) return;

        if (!isEmpty(19, invTop) && !isEmpty(21, invTop) && !isBarrierOutput(itemPreview)){
            if (e.getRawSlot() == 23){
                invTop.setItem(25, itemPreview);
                invTop.setItem(19, new ItemStack(Material.AIR));
                invTop.setItem(21, new ItemStack(Material.AIR));
                invTop.setItem(23, new ItemStack(Material.AIR));
                return;
            }
        }
    }

    public boolean isChocoAnvil(InventoryClickEvent event){
        return event.getView().getTitle().equalsIgnoreCase("CHOCO ADVANCE ANVIL");
    }

    @Nonnull
    public boolean isBarrierOutput(ItemStack item){
        if (item == null) return false;
        return item.getType() == Material.BARRIER;
    }

    public boolean isEmpty(int slot, Inventory inv){
        ItemStack itemCheck = inv.getItem(slot);
        if (itemCheck == null) return false;
        if (itemCheck.getType().isAir()) return true;
        return false;
    }
}

