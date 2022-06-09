package me.zwrumpy.chocohills.tools.block;

import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.InventoryHolder;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class BlockType {
    public boolean isBlackListed(@Nonnull Block block) {
        if (block.getType() != null) {
            if (block.getType().isAir()) return true;
            if (!block.getType().isSolid()) return true;
            if (isIndestructible(block)) return true;
            if (isType(block, Material.SPAWNER)) return true;
            if (isContainer(block)) return true;
            if (isSfBlock(block)) return true;
        }
        return false;
    }
    @ParametersAreNonnullByDefault
    public boolean isProtected(Block block, Player player) {
        BlockBreakEvent event = new BlockBreakEvent(block, player);
        Bukkit.getPluginManager().callEvent(event);
        return event.isCancelled();
    }

    public boolean isSfBlock(@Nonnull Block block) {
        return (BlockStorage.check(block) != null);
    }

    public boolean isContainer(@Nonnull Block block) {
        return block.getState() != null && block.getState() instanceof InventoryHolder;
    }

    public boolean isShovelable(@Nonnull Block block) {
        if (isType(block, Material.SAND)) return true;
        if (isType(block, Material.CLAY)) return true;
        if (isType(block, Material.COARSE_DIRT)) return true;
        if (isType(block, Material.DIRT)) return true;
        if (isType(block, Material.FARMLAND)) return true;
        if (isType(block, Material.GRASS_BLOCK)) return true;
        if (isType(block, Material.DIRT_PATH)) return true;
        if (isType(block, Material.GRAVEL)) return true;
        if (isType(block, Material.MYCELIUM)) return true;
        if (isType(block, Material.PODZOL)) return true;
        if (isType(block, Material.RED_SAND)) return true;
        if (isType(block, Material.SOUL_SAND)) return true;
        if (isType(block, Material.SOUL_SOIL)) return true;
        if (isType(block, Material.BLACK_CONCRETE_POWDER)) return true;
        if (isType(block, Material.BLUE_CONCRETE_POWDER)) return true;
        return false;
    }

    public boolean isSmeltable(@Nonnull Block block) {
        if (isType(block, Material.COAL_ORE)) return true;
        if (isType(block, Material.DEEPSLATE_COAL_ORE)) return true;
        if (isType(block, Material.IRON_ORE)) return true;
        if (isType(block, Material.DEEPSLATE_IRON_ORE)) return true;
        if (isType(block, Material.COPPER_ORE)) return true;
        if (isType(block, Material.DEEPSLATE_COPPER_ORE)) return true;
        if (isType(block, Material.GOLD_ORE)) return true;
        if (isType(block, Material.NETHER_GOLD_ORE)) return true;
        return false;
    }

    public boolean isIndestructible(@Nonnull Block block) {
        if (isType(block, Material.BEDROCK)) return true;
        if (isType(block, Material.BARRIER)) return true;
        if (isType(block, Material.END_PORTAL_FRAME)) return true;
        if (isType(block, Material.COMMAND_BLOCK)) return true;
        if (isType(block, Material.STRUCTURE_BLOCK)) return true;
        return false;
    }


    private boolean isType(Block block, Material material){ return block.getType().equals(material);
    }
}
