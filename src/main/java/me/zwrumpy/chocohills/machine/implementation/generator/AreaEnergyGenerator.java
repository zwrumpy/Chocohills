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
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

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
        if (progress >= this.rate) {
            progress = 0;
            int i = 0;
            for (Entity e: getEntitiesAroundPoint(b.getLocation(), radius)){
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

    public static List<Entity> getEntitiesAroundPoint(Location location, double radius) {
        List<Entity> entities = new ArrayList<Entity>();
        World world = location.getWorld();

        // To find chunks we use chunk coordinates (not block coordinates!)
        int smallX = (int) Math.floor((location.getX() - radius) / 16.0D);
        int bigX = (int) Math.floor((location.getX() + radius) / 16.0D);
        int smallZ = (int) Math.floor((location.getZ() - radius) / 16.0D);
        int bigZ = (int) Math.floor((location.getZ() + radius) / 16.0D);

        for (int x = smallX; x <= bigX; x++) {
            for (int z = smallZ; z <= bigZ; z++) {
                if (world.isChunkLoaded(x, z)) {
                    // Add all entities from this chunk to the list
                    entities.addAll(Arrays.asList(world.getChunkAt(x, z).getEntities()));
                }
            }
        }
        // Create an iterator so we can loop through the list while removing entries
        Iterator<Entity> entityIterator = entities.iterator();
        while (entityIterator.hasNext()) {
            // If the entity is outside of the sphere...
            if (entityIterator.next().getLocation().distanceSquared(location) > radius * radius) {
                entityIterator.remove();
            }
        }
        return entities;
    }
}

