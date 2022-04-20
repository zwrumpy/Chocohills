package me.zwrumpy.chocohills.register;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.machine.EggCooker;
import me.zwrumpy.chocohills.machine.MenardzSmoker;
import org.bukkit.inventory.ItemStack;

public class MachineSetup {
    ChocoHills addon;
    SlimefunItemStack tempStack;

    public MachineSetup(ChocoHills addon) {
        this.addon = addon;
        setup();
    }

    void setup() {
        new MenardzSmoker(ChocoItems.CH_MACHINES, ChocoItems.MENARDZ_SMOKER);

        new EggCooker(ChocoItems.CH_MACHINES, ChocoItems.EGGCOOKER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{SlimefunItems.CARBON, SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.CARBON, SlimefunItems.CARBON, SlimefunItems.HEATED_PRESSURE_CHAMBER, SlimefunItems.CARBON, SlimefunItems.HEATING_COIL, SlimefunItems.CARBONADO, SlimefunItems.HEATING_COIL})
                .setCapacity(256)
                .setEnergyConsumption(10)
                .setProcessingSpeed(1)
                .register(addon);
    }
}
