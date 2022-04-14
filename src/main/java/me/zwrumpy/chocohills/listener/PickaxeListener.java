package me.zwrumpy.chocohills.listener;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import me.zwrumpy.chocohills.block.BlockEdit;
import me.zwrumpy.chocohills.block.BlockSelection;
import me.zwrumpy.chocohills.block.BlockType;
import me.zwrumpy.chocohills.tools.ToolType;
import org.bukkit.Bukkit;
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

public class PickaxeListener implements Listener {
    private JavaPlugin plugin;
    private final HashMap<Player, BlockFace> blockfaceMap;
    private BlockSelection selection;
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

        SlimefunItem sfItem = SlimefunItem.getByItem(e.getItem());
        if (sfItem == null) return;
        if (sfItem.getId() == null) return;

        if (sfItem.getId().contains("BLASTXEL")) {
            this.blockfaceMap.put(e.getPlayer(), e.getBlockFace());
            log("face 0");
        }
        log("face 1 test");
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onBlastBreak(BlockBreakEvent e) {
        if (e.isCancelled()) return;
        if (!this.blockfaceMap.containsKey(e.getPlayer())) return;
        log("break 0");

        SlimefunItem sfItem = SlimefunItem.getByItem(e.getPlayer().getInventory().getItemInMainHand());
        if (sfItem == null) return;
        if (!sfItem.getId().contains("BLASTXEL")) {
            this.blockfaceMap.remove(e.getPlayer());
            return;
        }
        log("break 1");

        int level = 1;
        if (sfItem.getId().contains("BLASTXEL_2")) level = 3;
        if (sfItem.getId().contains("BLASTXEL_3")) level = 6;

        Player player = e.getPlayer();
        Block block = e.getBlock();
        World world = e.getBlock().getWorld();
        BlockFace face = blockfaceMap.get(player);

        int finalLevel = level;

        CompletableFuture
                .supplyAsync(() -> selection.cuboid(block.getLocation(), face, finalLevel))
                .thenApply(blocks -> blockEdit.filterBlocks(blocks))
                .thenApply(filteredBlocks -> blockEdit.filterProtectedBlocks(filteredBlocks, player))
                .thenAccept(filteredProtectedBlocks -> processBlocks(filteredProtectedBlocks, world));

        this.blockfaceMap.remove(e.getPlayer());
        log("break 2");
    }

    void processBlocks(List<Block> blocks, final World world) {
        (new BukkitRunnable() {
            public void run() {
                blockEdit.spawnDrops(blocks, world);
                blockEdit.removeBlocks(blocks);
            }
        }).runTask(plugin);
    }

    void log(String string) {
        if (plugin.getConfig().getBoolean("debug") == true)
            Bukkit.getLogger().log(Level.INFO, string);
    }
}
