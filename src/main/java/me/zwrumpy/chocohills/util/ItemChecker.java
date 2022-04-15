package me.zwrumpy.chocohills.util;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class ItemChecker {
    public static boolean isSfItem(String SfItemId, @Nonnull ItemStack item){
        SlimefunItem sfItem = SlimefunItem.getByItem(item);
        if (sfItem == null) return false;
        if (sfItem.getId() == null) return false ;
        if (!sfItem.getId().contains(SfItemId)) return false;
        return true;
    }

    public static boolean isItemEmpty(@Nonnull ItemStack item){
        if (item == null) return true;
        if (item.getType() == Material.AIR) return true;
        return false;
    }
}
