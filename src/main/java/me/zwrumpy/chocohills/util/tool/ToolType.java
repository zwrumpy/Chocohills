package me.zwrumpy.chocohills.util.tool;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;

public class ToolType {
    public boolean isPickAxe(@Nonnull ItemStack item) {
        if (isEmpty(item)) return false;
        if (isType(item, Material.DIAMOND_PICKAXE)) return true;
        if (isType(item, Material.NETHERITE_PICKAXE)) return true;
        if (isType(item, Material.IRON_PICKAXE)) return true;
        if (isType(item, Material.STONE_PICKAXE)) return true;
        if (isType(item, Material.WOODEN_PICKAXE)) return true;
        if (isType(item, Material.GOLDEN_PICKAXE)) return true;
        return false;
    }

    public boolean isShovel(@Nonnull ItemStack item) {
        if (isEmpty(item)) return false;
        if (isType(item, Material.DIAMOND_SHOVEL)) return true;
        if (isType(item, Material.NETHERITE_SHOVEL)) return true;
        if (isType(item, Material.IRON_SHOVEL)) return true;
        if (isType(item, Material.STONE_SHOVEL)) return true;
        if (isType(item, Material.WOODEN_SHOVEL)) return true;
        if (isType(item, Material.GOLDEN_SHOVEL)) return true;
        return false;
    }

    public boolean isAxe(@Nonnull ItemStack item) {
        if (isEmpty(item)) return false;
        if (isType(item, Material.DIAMOND_AXE)) return true;
        if (isType(item, Material.NETHERITE_AXE)) return true;
        if (isType(item, Material.IRON_AXE)) return true;
        if (isType(item, Material.STONE_AXE)) return true;
        if (isType(item, Material.WOODEN_AXE)) return true;
        if (isType(item, Material.GOLDEN_AXE)) return true;
        return false;
    }

    public boolean isHoe(@Nonnull ItemStack item) {
        if (isEmpty(item)) return false;
        if (isType(item, Material.DIAMOND_HOE)) return true;
        if (isType(item, Material.NETHERITE_HOE)) return true;
        if (isType(item, Material.IRON_HOE)) return true;
        if (isType(item, Material.STONE_HOE)) return true;
        if (isType(item, Material.WOODEN_HOE)) return true;
        if (isType(item, Material.GOLDEN_HOE)) return true;
        return false;
    }

    public boolean isSword(@Nonnull ItemStack item) {
        if (isEmpty(item)) return false;
        if (isType(item, Material.DIAMOND_SWORD)) return true;
        if (isType(item, Material.NETHERITE_SWORD)) return true;
        if (isType(item, Material.IRON_SWORD)) return true;
        if (isType(item, Material.STONE_SWORD)) return true;
        if (isType(item, Material.WOODEN_SWORD)) return true;
        if (isType(item, Material.GOLDEN_SWORD)) return true;
        return false;
    }

    private boolean isType(ItemStack item, Material material){ return item.getType().equals(material);
    }
    public boolean isEmpty(ItemStack item) {
        return (item == null || item.getType() == Material.AIR);
    }
}
