package me.zwrumpy.chocohills.machine.implementation.generator;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

public class MaterialGenerator extends SlimefunItem {

    private static final Map<BlockPosition, Integer> generatorProgress = new HashMap<>();
    private int time = 20;
    private ItemStack item;
    private int itemAmount = 1;
    @ParametersAreNonnullByDefault
    public MaterialGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {
            @Override
            @ParametersAreNonnullByDefault
            public void tick(Block b, SlimefunItem sf, Config data) {
                MaterialGenerator.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return true;
            }
        });
    }

    @Override
    public boolean useVanillaBlockBreaking() {
        return super.useVanillaBlockBreaking();
    }

    public void tick(@Nonnull Block b) {
        Block targetBlock = b.getRelative(BlockFace.UP);
        if (targetBlock.getType() != Material.CHEST) return;

        BlockState state = PaperLib.getBlockState(targetBlock, false).getState();
        if (!(state instanceof InventoryHolder)) return;

        Inventory inv = ((InventoryHolder) state).getInventory();

        if (inv.firstEmpty() != -1) {
            final BlockPosition pos = new BlockPosition(b);
            int progress = generatorProgress.getOrDefault(pos, 0);

            if (progress >= this.time) {
                progress = 0;
                this.item.setAmount(itemAmount);
                inv.addItem(this.item);
            } else {
                progress++;
            }
            generatorProgress.put(pos, progress);
        }
    }

    @ParametersAreNonnullByDefault
    public final MaterialGenerator setItem(Material material) {
        this.item = new ItemStack(material);
        return this;
    }

    @ParametersAreNonnullByDefault
    public final MaterialGenerator setItem(SlimefunItemStack material) {
        ItemStack item = material.clone();
        this.item = item;
        return this;
    }
    @ParametersAreNonnullByDefault
    public final MaterialGenerator setItemAmount(int itemAmount){
        this.itemAmount = itemAmount;
        return this;
    }

    @ParametersAreNonnullByDefault
    public final MaterialGenerator setTime(int seconds) {
        this.time =  seconds * 2;
        return this;
    }
}

