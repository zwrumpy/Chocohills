package me.zwrumpy.chocohills.util;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class Util {
    @Nonnull
    public static Map<Enchantment, Integer> getEnchants(@Nonnull ConfigurationSection section) {
        Map<Enchantment, Integer> enchants = new HashMap<>();
        for (String path : section.getKeys(false)) {
            Enchantment e = enchantmentByPath(path);
            if (e != null) {
                int level = section.getInt(path);
                if (level > 0 && level <= Short.MAX_VALUE) {
                    enchants.put(e, level);
                } else if (level != 0) {
                    section.set(path, 0);
                }
            }
        }
        return enchants;
    }

    public static ItemStack getDisplayItem(@Nonnull ItemStack output) {
        ItemMeta meta = output.getItemMeta();
        List<String> lore;
        if (meta.hasLore()) {
            lore = meta.getLore();
        }
        else {
            lore = new ArrayList<>();
        }
        lore.add("");
        lore.add(ChatColor.GREEN + "-------------------");
        lore.add(ChatColor.GREEN + "\u21E8 Click to craft");
        lore.add(ChatColor.GREEN + "-------------------");
        output.setItemMeta(meta);
        return output;
    }

    @Nullable
    private static Enchantment enchantmentByPath(@Nonnull String path) {
        switch (path) {
            case "sharpness":
                return Enchantment.DAMAGE_ALL;
            case "smite":
                return Enchantment.DAMAGE_UNDEAD;
            case "bane-of-arthropods":
                return Enchantment.DAMAGE_ARTHROPODS;
            case "efficiency":
                return Enchantment.DIG_SPEED;
            case "protection":
                return Enchantment.PROTECTION_ENVIRONMENTAL;
            case "fire-aspect":
                return Enchantment.FIRE_ASPECT;
            case "fortune":
                return Enchantment.LOOT_BONUS_BLOCKS;
            case "looting":
                return Enchantment.LOOT_BONUS_MOBS;
            case "silk-touch":
                return Enchantment.SILK_TOUCH;
            case "thorns":
                return Enchantment.THORNS;
            case "aqua-affinity":
                return Enchantment.WATER_WORKER;
            case "power":
                return Enchantment.ARROW_DAMAGE;
            case "flame":
                return Enchantment.ARROW_FIRE;
            case "infinity":
                return Enchantment.ARROW_INFINITE;
            case "punch":
                return Enchantment.ARROW_KNOCKBACK;
            case "feather-falling":
                return Enchantment.PROTECTION_FALL;
            case "unbreaking":
                return Enchantment.DURABILITY;
            default:
                return null;
        }
    }
}

