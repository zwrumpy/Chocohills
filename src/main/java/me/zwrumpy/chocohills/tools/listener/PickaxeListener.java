package me.zwrumpy.chocohills.tools.listener;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import me.zwrumpy.chocohills.tools.block.BlockEdit;
import me.zwrumpy.chocohills.tools.block.BlockSelection;
import me.zwrumpy.chocohills.tools.block.BlockType;
import me.zwrumpy.chocohills.util.tool.ToolType;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class PickaxeListener implements Listener {
    private JavaPlugin plugin;
    private HashMap<Player, BlockFace> blockfaceMap;
    private final BlockSelection selection;
    private ToolType tool;
    private BlockType blockType;
    private BlockEdit blockEdit;

    public PickaxeListener(JavaPlugin plugin) {
        this.plugin = plugin;
        selection = new BlockSelection();
        blockEdit = new BlockEdit(plugin);
        blockType = new BlockType();
        tool = new ToolType();
        blockfaceMap = new HashMap<>();
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlockClick(PlayerInteractEvent e) {
        if (e.getAction() != Action.LEFT_CLICK_BLOCK) return;
        if (e.getItem() == null) return;
        if (e.getItem().getType() == Material.AIR) return;
        if (e.getItem().getType() != Material.NETHERITE_PICKAXE) return;
        if (this.blockfaceMap.containsKey(e.getPlayer())) return;

        if (isBlastxelOnHand(e.getPlayer())) {
            this.blockfaceMap.put(e.getPlayer(), e.getBlockFace());
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlastBreak(BlockBreakEvent e) {
        if (e.isCancelled()) return;
        if (!this.blockfaceMap.containsKey(e.getPlayer())) return;

        Player player = e.getPlayer();
        if (!(isBlastxelOnHand(player))) {
            this.blockfaceMap.remove(player);
            return;
        }

        SlimefunItem sfItem = SlimefunItem.getByItem(player.getInventory().getItemInMainHand());
        assert sfItem != null;
        int level = getLevel(sfItem);
        processBlocks(e.getBlock(), player, level);
        this.blockfaceMap.remove(player);
    }

    boolean isBlastxelOnHand(@Nonnull Player player) {
        SlimefunItem sfItem = SlimefunItem.getByItem(player.getInventory().getItemInMainHand());
        if (sfItem == null) return false;
        return sfItem.getId().contains("BLASTXEL");
    }

    int getLevel(@Nonnull SlimefunItem item) {
        int level = 1;
        if(item.getId().contains("BLASTXEL_2")) level = 3;
        if(item.getId().contains("BLASTXEL_3")) level = 6;
        return level;
    }
    @ParametersAreNonnullByDefault
    void processBlocks(@Nonnull  Block block, Player player, int level){
        World world = block.getWorld();
        BlockFace face = this.blockfaceMap.get(player);

        CompletableFuture
                .supplyAsync(() -> selection.cuboid(block.getLocation(), face, level))
                .thenApply(blocks -> blockEdit.filterBlocks(blocks))
                .thenApply(filteredBlocks -> blockEdit.filterProtectedBlocks(filteredBlocks, player))
                .thenAccept(filteredProtectedBlocks -> processBlockDrops(filteredProtectedBlocks, world));
    }

    @ParametersAreNonnullByDefault
    void processBlockDrops(List<Block> blocks, final World world) {
        (new BukkitRunnable() {
            public void run() {
                blockEdit.spawnDrops(blocks, world);
                blockEdit.removeBlocks(blocks);
            }
        }).runTask(plugin);
    }
}
