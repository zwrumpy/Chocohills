package me.zwrumpy.chocohills.tools;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ToolType {
    public boolean isPickAxe(ItemStack item) {
        if (isEmpty(item))
            return false;
        if (item.getType().equals(Material.DIAMOND_PICKAXE))
            return true;
        if (item.getType().equals(Material.NETHERITE_PICKAXE))
            return true;
        if (item.getType().equals(Material.IRON_PICKAXE))
            return true;
        if (item.getType().equals(Material.STONE_PICKAXE))
            return true;
        if (item.getType().equals(Material.WOODEN_PICKAXE))
            return true;
        if (item.getType().equals(Material.GOLDEN_PICKAXE))
            return true;
        return false;
    }

    public boolean isShovel(ItemStack item) {
        if (isEmpty(item))
            return false;
        if (item.getType().equals(Material.DIAMOND_SHOVEL))
            return true;
        if (item.getType().equals(Material.NETHERITE_SHOVEL))
            return true;
        if (item.getType().equals(Material.IRON_SHOVEL))
            return true;
        if (item.getType().equals(Material.STONE_SHOVEL))
            return true;
        if (item.getType().equals(Material.WOODEN_SHOVEL))
            return true;
        if (item.getType().equals(Material.GOLDEN_SHOVEL))
            return true;
        return false;
    }

    public boolean isAxe(ItemStack item) {
        if (isEmpty(item))
            return false;
        if (item.getType().equals(Material.DIAMOND_AXE))
            return true;
        if (item.getType().equals(Material.NETHERITE_AXE))
            return true;
        if (item.getType().equals(Material.IRON_AXE))
            return true;
        if (item.getType().equals(Material.STONE_AXE))
            return true;
        if (item.getType().equals(Material.WOODEN_AXE))
            return true;
        if (item.getType().equals(Material.GOLDEN_AXE))
            return true;
        return false;
    }

    public boolean isHoe(ItemStack item) {
        if (isEmpty(item))
            return false;
        if (item.getType().equals(Material.DIAMOND_HOE))
            return true;
        if (item.getType().equals(Material.NETHERITE_HOE))
            return true;
        if (item.getType().equals(Material.IRON_HOE))
            return true;
        if (item.getType().equals(Material.STONE_HOE))
            return true;
        if (item.getType().equals(Material.WOODEN_HOE))
            return true;
        if (item.getType().equals(Material.GOLDEN_HOE))
            return true;
        return false;
    }

    public boolean isSword(ItemStack item) {
        if (isEmpty(item))
            return false;
        if (item.getType().equals(Material.DIAMOND_SWORD))
            return true;
        if (item.getType().equals(Material.NETHERITE_SWORD))
            return true;
        if (item.getType().equals(Material.IRON_SWORD))
            return true;
        if (item.getType().equals(Material.STONE_SWORD))
            return true;
        if (item.getType().equals(Material.WOODEN_SWORD))
            return true;
        if (item.getType().equals(Material.GOLDEN_SWORD))
            return true;
        return false;
    }

    public boolean isEmpty(ItemStack item) {
        return (item == null || item.getType() == Material.AIR);
    }
}
