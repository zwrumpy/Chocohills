package me.zwrumpy.chocohills.machine.area;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.machine.abstracts.AreaEnergyGenerator;
import me.zwrumpy.chocohills.machine.abstracts.EnergyGenerator;
import me.zwrumpy.chocohills.register.ChocoItems;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;


public class AfkNator  {

    public AfkNator() {
        register();
    }

    void register(){
        new AreaEnergyGenerator(ChocoItems.CH_GENERATOR, AFKEINATOR, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        null, SlimefunItems.LARGE_CAPACITOR, null,
                        SlimefunItems.CARBON, SlimefunItems.SOLAR_GENERATOR,  SlimefunItems.CARBON,
                        null, SlimefunItems.LARGE_CAPACITOR, null
                })
                .setTime(5)
                .setEnergySource(EntityType.PLAYER)
                .setRadius(5)
                .setEnergyOutput(250)
                .setEnergyCapacity(2500)
                .register(ChocoHills.getInstance());
    }

    SlimefunItemStack AFKEINATOR = new SlimefunItemStack(
            "AFKEINATOR",
            Material.BOOKSHELF,
            "&3&lAFKeiNator",
            "&7Usage:",
            "&fGenerates energy from ",
            "&fnearby players",
            " ",
            "&7Range: &f2 Blocks ",
            "&7Capacity: &f1000",
            "&7Rate: &f250 joules x nearby players ",
            "&7Time: &f5 Seconds"
    );
}
