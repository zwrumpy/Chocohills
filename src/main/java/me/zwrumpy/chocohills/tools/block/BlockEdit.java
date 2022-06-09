package me.zwrumpy.chocohills.tools.block;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.ParametersAreNonnullByDefault;

public class BlockEdit {
    private final BlockType type = new BlockType();

    private final JavaPlugin plugin;

    public BlockEdit(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public List<Block> filterBlocks(List<Block> blocks) {
        List<Block> filterBlocks = new ArrayList<>();
        for (Block block : blocks) {
            if (this.type.isBlackListed(block))
                continue;
            filterBlocks.add(block);
        }
        return filterBlocks;
    }
    @ParametersAreNonnullByDefault
    public List<Block> filterProtectedBlocks(List<Block> blocks, final Player player) {
        final List<Block> filteredBlocks = new ArrayList<>();
        (new BukkitRunnable() {
            public void run() {
                for (Block block : blocks) {
                    if (BlockEdit.this.type.isProtected(block, player))
                        continue;
                    filteredBlocks.add(block);
                }
            }
        }).runTask((Plugin)this.plugin);
        return filteredBlocks;
    }

    public void removeBlocks(List<Block> blockList) {
        for (Block block : blockList) block.setType(Material.AIR);
    }
    @ParametersAreNonnullByDefault
    public void spawnDrops(List<Block> blockList, World world) {
        for (Block block : blockList) {
            for (ItemStack drop : block.getDrops())
                world.dropItem(block.getLocation(), drop);
        }
    }
}
