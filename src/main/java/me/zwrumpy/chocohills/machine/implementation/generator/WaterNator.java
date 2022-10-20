package me.zwrumpy.chocohills.machine.implementation.generator;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.register.ChocoItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WaterNator {
    public WaterNator() {
        register();
    }
    void register(){
         new EnergyGenerator(ChocoItems.CH_GENERATOR, WATERNATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        SlimefunItems.WATER_RUNE, SlimefunItems.STAFF_WATER, SlimefunItems.WATER_RUNE,
                        SlimefunItems.CARBON, SlimefunItems.ELECTRIC_FURNACE,  SlimefunItems.CARBON,
                        null, SlimefunItems.SMALL_CAPACITOR, null
                })
                 .setTime(2)
                 .setEnergyOutput(75)
                 .setEnergySource(Material.WATER)
                 .setEnergyCapacity(1000)
                 .register(ChocoHills.getInstance());
    }

    SlimefunItemStack WATERNATOR = new SlimefunItemStack(
            "WATERNATOR",
            Material.BLUE_STAINED_GLASS,
            "&b&lWaterNator",
            "&7Usage:",
            "&fHydro Energy Generator",
            " ",
            "&7Capacity: &f1000",
            "&7Rate: &f75 joules",
            "&7Time: &f2 Seconds",
            " ",
            "&7Setup",
            "&fPut Water below this block"
    );

}
