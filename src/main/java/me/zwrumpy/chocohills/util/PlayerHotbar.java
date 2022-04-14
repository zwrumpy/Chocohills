package me.zwrumpy.chocohills.util;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class PlayerHotbar {
    public void removeItem(@Nonnull Player player){ }

    public static List getHotbarItems(@Nonnull Player player){
        List<ItemStack> items = new ArrayList<ItemStack>();
        if (player.getInventory() == null) return items;

        for (int i = 0; i < 9; i++) {
            if (player.getInventory().getItem(i) == null) continue;
            if (player.getInventory().getItem(i).equals(Material.AIR)) continue;
            items.add(player.getInventory().getItem(i));
        }

        return items;
    }
}
