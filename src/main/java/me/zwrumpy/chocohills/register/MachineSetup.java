package me.zwrumpy.chocohills.register;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.machine.EggCooker;
import me.zwrumpy.chocohills.machine.GreenThumb;
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
        new MenardzSmoker(ChocoItems.CH_MACHINES, ChocoItems.MENARDZ_SMOKER)
                .register(addon);

        new EggCooker(ChocoItems.CH_MACHINES, ChocoItems.EGG_COOKER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        ChocoItems.TINAPANG_ITLOG, SlimefunItems.ELECTRIC_MOTOR, ChocoItems.TINAPANG_ITLOG,
                        SlimefunItems.CARBON, ChocoItems.AYANSHARD, SlimefunItems.CARBON,
                        SlimefunItems.HEATING_COIL, SlimefunItems.CARBONADO, SlimefunItems.HEATING_COIL})
                .setCapacity(256)
                .setEnergyConsumption(10)
                .setProcessingSpeed(1)
                .register(addon);

        new GreenThumb(ChocoItems.CH_MACHINES, ChocoItems.GREEN_THUMB, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        null, SlimefunItems.ELECTRIC_MOTOR, null,
                        ChocoItems.CHOCO_CARBON, ChocoItems.AYANSHARD, ChocoItems.CHOCO_CARBON,
                        null, ChocoItems.EGGPENSIVE, null})
                .setCapacity(256)
                .setEnergyConsumption(10)
                .setProcessingSpeed(1)
                .register(addon);
    }
}
