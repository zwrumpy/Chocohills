package me.zwrumpy.chocohills.block;

import java.util.logging.Level;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockType {
    public boolean isBlackListed(Block block) {
        if (block != null && block.getType() != null) {
            if (block.getType().isAir())
                return true;
            if (!block.getType().isSolid())
                return true;
            if (isIndestructible(block))
                return true;
            if (block.getType().equals(Material.SPAWNER))
                return true;
            if (isContainer(block))
                return true;
            if (isSfBlock(block))
                return true;
        }
        return false;
    }

    public boolean isProtected(Block block, Player player) {
        BlockBreakEvent event = new BlockBreakEvent(block, player);
        Bukkit.getPluginManager().callEvent(event);
        return event.isCancelled();
    }

    public boolean isSfBlock(Block block) {
        return (BlockStorage.check(block) != null);
    }

    public boolean isContainer(Block block) {
        if (block.getState() != null &&
                block.getState() instanceof org.bukkit.inventory.InventoryHolder)
            return true;
        return false;
    }

    public boolean isShovelable(Block block) {
        if (block.getType().equals(Material.SAND))
            return true;
        if (block.getType().equals(Material.CLAY))
            return true;
        if (block.getType().equals(Material.COARSE_DIRT))
            return true;
        if (block.getType().equals(Material.DIRT))
            return true;
        if (block.getType().equals(Material.FARMLAND))
            return true;
        if (block.getType().equals(Material.GRASS_BLOCK))
            return true;
        if (block.getType().equals(Material.DIRT_PATH))
            return true;
        if (block.getType().equals(Material.GRAVEL))
            return true;
        if (block.getType().equals(Material.MYCELIUM))
            return true;
        if (block.getType().equals(Material.PODZOL))
            return true;
        if (block.getType().equals(Material.RED_SAND))
            return true;
        if (block.getType().equals(Material.SOUL_SAND))
            return true;
        if (block.getType().equals(Material.SOUL_SOIL))
            return true;
        if (block.getType().equals(Material.BLACK_CONCRETE_POWDER))
            return true;
        if (block.getType().equals(Material.BLUE_CONCRETE_POWDER))
            return true;
        return false;
    }

    public boolean isSmeltable(Block block) {
        if (block.getType().equals(Material.COAL_ORE))
            return true;
        if (block.getType().equals(Material.DEEPSLATE_COAL_ORE))
            return true;
        if (block.getType().equals(Material.IRON_ORE))
            return true;
        if (block.getType().equals(Material.DEEPSLATE_IRON_ORE))
            return true;
        if (block.getType().equals(Material.COPPER_ORE))
            return true;
        if (block.getType().equals(Material.DEEPSLATE_COPPER_ORE))
            return true;
        if (block.getType().equals(Material.GOLD_ORE))
            return true;
        if (block.getType().equals(Material.NETHER_GOLD_ORE))
            return true;
        return false;
    }

    public boolean isIndestructible(Block block) {
        if (block.getType().equals(Material.BEDROCK))
            return true;
        if (block.getType().equals(Material.BARRIER))
            return true;
        if (block.getType().equals(Material.END_PORTAL_FRAME))
            return true;
        if (block.getType().equals(Material.COMMAND_BLOCK))
            return true;
        if (block.getType().equals(Material.STRUCTURE_BLOCK))
            return true;
        return false;
    }

    void log(String string) {
        Bukkit.getLogger().log(Level.INFO, string);
    }
}
