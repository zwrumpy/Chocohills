package me.zwrumpy.chocohills.util.item;

import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.item_transport.ItemTransportFlow;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class ItemTransfer {

    public static void SlimefunToVanillaUp(@Nonnull Block b) {
        Block inputBlock = b.getRelative(BlockFace.DOWN);
        Block outputBlock = b.getRelative(BlockFace.UP);
        SlimefunToVanilla(inputBlock, outputBlock);
    }

    public static void SlimefunToVanillaDown(@Nonnull Block b) {
        Block inputBlock = b.getRelative(BlockFace.UP);
        Block outputBlock = b.getRelative(BlockFace.DOWN);
        SlimefunToVanilla(inputBlock, outputBlock);
    }

    public static void vanillaTransferItemUp(@Nonnull Block b) {
        Block inputBlock = b.getRelative(BlockFace.DOWN);
        Block outputBlock = b.getRelative(BlockFace.UP);
        vanillaToVanilla(inputBlock, outputBlock);
    }

    public static void vanillaTransferItemDown(@Nonnull Block b) {
        Block inputBlock = b.getRelative(BlockFace.UP);
        Block outputBlock = b.getRelative(BlockFace.DOWN);
        vanillaToVanilla(inputBlock, outputBlock);
    }

    public boolean isVanillaStorage(@Nonnull BlockState block) {
        if (!(block instanceof InventoryHolder)) return false;
        Inventory blockInv = ((InventoryHolder) block).getInventory();
        if (blockInv == null) return false;
        return true;
    }

    public boolean isSlimefunStorage(@Nonnull BlockState block) {
        if (BlockStorage.check((Block) block) == null) return false;
        BlockMenu blockInv = BlockStorage.getInventory((Block) block);
        if (blockInv == null) return false;
        return true;
    }

    public static void vanillaToSlimefunDown(@Nonnull Block b) {
        Block inputBlock = b.getRelative(BlockFace.UP);
        Block outputBlock = b.getRelative(BlockFace.DOWN);
        vanillaToSlimefun(inputBlock, outputBlock);
    }

    public static void vanillaToSlimefunUp(@Nonnull Block b) {
        Block inputBlock = b.getRelative(BlockFace.DOWN);
        Block outputBlock = b.getRelative(BlockFace.UP);
        vanillaToSlimefun(inputBlock, outputBlock);
    }
    @ParametersAreNonnullByDefault
    static void vanillaToVanilla(Block inputBlock, Block outputBlock) {
        BlockState input = PaperLib.getBlockState(inputBlock, false).getState();
        BlockState output = PaperLib.getBlockState(outputBlock, false).getState();

        if (!(output instanceof InventoryHolder)) return;
        if (!(input instanceof InventoryHolder)) return;

        Inventory outputInv = ((InventoryHolder) output).getInventory();
        Inventory inputInv = ((InventoryHolder) input).getInventory();

        if (outputInv == null) return;
        if (inputInv == null) return;

        if (inputInv.isEmpty()) {return;}
        if (outputInv.firstEmpty() == -1) {return;}

        for (ItemStack i : inputInv.getContents()) {
            if (inputInv.isEmpty()) break;
            if (i == null) continue;
            if (i.getType() == null) continue;
            if (i.getType() == Material.AIR) continue;
            if (outputInv.firstEmpty() == -1) return;
            outputInv.addItem(i);
            i.setAmount(0);
        }
    }
    @ParametersAreNonnullByDefault
    public static void SlimefunToVanilla(Block inputBlock, Block outputBlock) {
        BlockState output = PaperLib.getBlockState(outputBlock, false).getState();

        if (BlockStorage.check(inputBlock) == null) return;
        if (!(output instanceof InventoryHolder)) return;

        Inventory outputInv = ((InventoryHolder) output).getInventory();
        if (outputInv.firstEmpty() == -1) return;

        BlockMenu inputInv = BlockStorage.getInventory(inputBlock);
        if (inputInv == null) return;

        int[] slots = inputInv.getPreset().getSlotsAccessedByItemTransport(inputInv, ItemTransportFlow.WITHDRAW, null);
        if (slots == null) return;

        for (int slot : slots) {
            ItemStack itemStack = inputInv.getItemInSlot(slot);
            if (itemStack == null) continue;
            if (itemStack.getType() == null) continue;
            if (itemStack.getType() == Material.AIR) continue;
            if (inputInv.getItemInSlot(slot) == null) continue;

            outputInv.addItem(inputInv.getItemInSlot(slot));
            inputInv.replaceExistingItem(slot, new ItemStack(Material.AIR));
        }
    }
    @ParametersAreNonnullByDefault
    public static void vanillaToSlimefun(Block inputBlock, Block outputBlock) {
        BlockState input = PaperLib.getBlockState(inputBlock, false).getState();

        if (!(input instanceof InventoryHolder)) {return;}
        if (BlockStorage.check(outputBlock) == null) {return;}

        Inventory inputInv = ((InventoryHolder) input).getInventory();
        BlockMenu outputInv = BlockStorage.getInventory(outputBlock);

        if (inputInv == null) {return;}
        if (outputInv == null) {return;}
        if (inputInv.isEmpty()) {return;}

        int[] slots = outputInv.getPreset().getSlotsAccessedByItemTransport(outputInv, ItemTransportFlow.INSERT, null);
        if (slots == null) {return;}

        for (ItemStack item : inputInv.getContents()) {
            if (item == null) continue;
            if (item.getType() == null) continue;
            if (item.getType() == Material.AIR) continue;
            if (!outputInv.fits(item, slots)) continue;
            outputInv.pushItem(item, slots);
            item.setAmount(0);
        }
    }
}
