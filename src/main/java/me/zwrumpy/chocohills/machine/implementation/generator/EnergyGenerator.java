package me.zwrumpy.chocohills.machine.implementation.generator;

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

public class EnergyGenerator extends SlimefunItem implements EnergyNetProvider {

    private static final Map<BlockPosition, Integer> generatorProgress = new HashMap<>();
    private int time = 20;
    private int capacity = 500;
    private Material energySource = Material.AIR;
    int energy = 50;
    int energyProduced = 0;


    @ParametersAreNonnullByDefault
    public EnergyGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {
            @Override
            @ParametersAreNonnullByDefault
            public void tick(Block b, SlimefunItem sf, Config data) {
                EnergyGenerator.this.tick(b);
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
        Block targetBlock = b.getRelative(BlockFace.DOWN);
        if (targetBlock.getType() != energySource) return;

        final BlockPosition pos = new BlockPosition(b);
        int progress = generatorProgress.getOrDefault(pos, 0);

        if (progress >= this.time) {
            progress = 0;
            energyProduced = energy;
            //Bukkit.getLogger().info("energy "+ energyProduced);
        } else {
            energyProduced = 0;
            progress++;
        }
        generatorProgress.put(pos, progress);
    }


    @ParametersAreNonnullByDefault
    public final EnergyGenerator setTime(int seconds) {
        this.time = seconds * 2;
        return this;
    }

    @Nonnull
    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetProvider.super.getEnergyComponentType();
    }


    @Override
    public int getCapacity() {
        return capacity;
    }

    public EnergyGenerator setEnergySource(Material energySource) {
        this.energySource = energySource;
        return this;
    }

    public EnergyGenerator setEnergyCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public EnergyGenerator setEnergyOutput(int energy){
        this.energy = energy;
        return this;
    }

    @Override
    public int getGeneratedOutput(@Nonnull Location l, @Nonnull Config data) {
        return energyProduced;
    }

    @Override
    public boolean willExplode(@Nonnull Location l, @Nonnull Config data) {
        return false;
    }
}

