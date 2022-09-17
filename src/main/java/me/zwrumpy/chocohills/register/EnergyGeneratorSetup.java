package me.zwrumpy.chocohills.register;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.machine.abstracts.MaterialGenerator;
import me.zwrumpy.chocohills.machine.generator.RumpyNator;
import me.zwrumpy.chocohills.machine.generator.WaterNator;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class EnergyGeneratorSetup {
    ChocoHills addon;
    SlimefunItemStack tempStack;

    public EnergyGeneratorSetup(ChocoHills addon) {
        this.addon = addon;
        setup();
    }

    void setup() {
        new RumpyNator();
        new WaterNator();
    }
}
