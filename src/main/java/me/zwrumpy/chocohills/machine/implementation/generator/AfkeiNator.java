package me.zwrumpy.chocohills.machine.implementation.generator;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.machine.implementation.generator.AreaEnergyGenerator;
import me.zwrumpy.chocohills.register.ChocoItems;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;


public class AfkeiNator  {

    public AfkeiNator() {
        register();
    }

    void register(){
        new AreaEnergyGenerator(ChocoItems.CH_GENERATOR, AFKEINATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        null, SlimefunItems.SMALL_CAPACITOR, null,
                        SlimefunItems.CARBON, SlimefunItems.ADVANCED_CIRCUIT_BOARD,  SlimefunItems.CARBON,
                        null, new ItemStack(Material.MINECART), null
                })
                .setTime(10)
                .setEnergySource(EntityType.PLAYER)
                .setRadius(2)
                .setEnergyOutput(250)
                .setEnergyCapacity(1500)
                .register(ChocoHills.getInstance());
    }

    SlimefunItemStack AFKEINATOR = new SlimefunItemStack(
            "AFKEINATOR",
            Material.BOOKSHELF,
            "&3&lAFKeiNator",
            "&7Usage:",
            "&fGenerates and multiplies energy",
            "&ffrom nearby players",
            " ",
            "&7Range: &f2 Blocks ",
            "&7Capacity: &f1500",
            "&7Rate: &f250 joules x nearby players ",
            "&7Time: &f10 Seconds"
    );
}
