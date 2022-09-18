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
import me.zwrumpy.chocohills.util.entities.EntityScan;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashMap;
import java.util.Map;

public class AreaEnergyGenerator extends SlimefunItem implements EnergyNetProvider {

    private static final Map<BlockPosition, Integer> generatorProgress = new HashMap<>();
    private int rate = 20;
    private int capacity = 500;
    int energy = 50;
    int radius = 0;
    int energyProduced = 0;
    EntityType energySource;

    @ParametersAreNonnullByDefault
    public AreaEnergyGenerator(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {
            @Override
            @ParametersAreNonnullByDefault
            public void tick(Block b, SlimefunItem sf, Config data) {
                AreaEnergyGenerator.this.tick(b);
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
        Bukkit.getLogger().info(""+progress);
        if (progress >= this.rate) {
            progress = 0;
            int i = 0;
            for (Entity e: EntityScan.getEntitiesAroundPoint(b.getLocation(), radius)){
                if (e.getType() == energySource){
                    i++;
                }
            }
            energyProduced = energy * i;
        } else {
            energyProduced = 0;
            progress++;
        }
        generatorProgress.put(pos, progress);
    }


    @ParametersAreNonnullByDefault
    public final AreaEnergyGenerator setTime(int seconds) {
        this.rate = seconds * 2;
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

    public AreaEnergyGenerator setEnergySource(EntityType entitySource) {
        this.energySource = entitySource;
        return this;
    }

    public AreaEnergyGenerator setEnergyCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public AreaEnergyGenerator setEnergyOutput(int energy){
        this.energy = energy;
        return this;
    }

    public AreaEnergyGenerator setRadius(int radius){
        this.radius = radius;
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

