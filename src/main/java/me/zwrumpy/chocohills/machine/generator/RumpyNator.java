package me.zwrumpy.chocohills.machine.generator;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.machine.abstracts.EnergyGenerator;
import me.zwrumpy.chocohills.register.ChocoItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RumpyNator {
    public RumpyNator() {
        register();
    }
    void register(){
         new EnergyGenerator(ChocoItems.CH_GENERATOR, RUMPYNATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        SlimefunItems.LAVA_CRYSTAL, SlimefunItems.STAFF_FIRE, SlimefunItems.LAVA_CRYSTAL,
                        SlimefunItems.CARBON, SlimefunItems.COAL_GENERATOR,  SlimefunItems.CARBON,
                        null, SlimefunItems.SMALL_CAPACITOR, null
                })
                 .setTime(2)
                 .setEnergyOutput(75)
                 .setEnergySource(Material.LAVA)
                 .setEnergyCapacity(1000)
                 .register(ChocoHills.getInstance());
    }

    SlimefunItemStack RUMPYNATOR = new SlimefunItemStack(
            "RUMPYNATOR",
            Material.MAGMA_BLOCK,
            "&c&lRumpyNator",
            "&7Usage:",
            "&fThermal Energy Generator",
            " ",
            "&7Capacity: &f1000",
            "&7Rate: &f75 joules",
            "&7Time: &f2 Seconds",
            " ",
            "&7Setup",
            "&fPut Lava below this block"
    );
}
