package me.zwrumpy.chocohills.machine.abstracts;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetProvider;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.blocks.BlockPosition;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

public class Machine extends SlimefunItem {

    private static final Map<BlockPosition, Integer> generatorProgress = new HashMap<>();
    private int rate = 20;


    @ParametersAreNonnullByDefault
    public Machine(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {
            @Override
            @ParametersAreNonnullByDefault
            public void tick(Block b, SlimefunItem sf, Config data) {
                Machine.this.tick(b);
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
        final BlockPosition pos = new BlockPosition(b);
        int progress = generatorProgress.getOrDefault(pos, 0);

        if (progress >= this.rate) {
            progress = 0;
            update();
        } else {
            progress++;
        }
        generatorProgress.put(pos, progress);
    }


    @ParametersAreNonnullByDefault
    public final Machine setTime(int rateTicks, int seconds) {
        this.rate = Math.max(rateTicks, seconds * 2);
        return this;
    }

    void update(){

    }
}

