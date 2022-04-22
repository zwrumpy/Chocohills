package me.zwrumpy.chocohills.util.item;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class ItemChecker {
    @ParametersAreNonnullByDefault
    public static boolean isSfItem(String SfItemId, ItemStack item){
        SlimefunItem sfItem = SlimefunItem.getByItem(item);
        if (sfItem == null) return false;
        return sfItem.getId().contains(SfItemId);
    }
    public static boolean isItemEmpty(@Nonnull ItemStack item){
        return item.getType() == Material.AIR;
    }
}
