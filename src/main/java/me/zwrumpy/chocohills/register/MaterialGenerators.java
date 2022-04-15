package me.zwrumpy.chocohills.register;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.generator.MaterialGenerator;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MaterialGenerators {
    ChocoHills addon;
    SlimefunItemStack tempStack;

    public MaterialGenerators(ChocoHills addon) {
        this.addon = addon;
        setup();
    }

    void setup() {
        new MaterialGenerator(ChocoItems.CH_GENERATOR, ChocoItems.ARFWOOFERITE, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        null, ChocoItems.ENERGIZED_CRYSTAL, null,
                        SlimefunItems.SOLAR_GENERATOR_4, SlimefunItems.ENERGIZED_CAPACITOR, SlimefunItems.SOLAR_GENERATOR_4,
                        null, ChocoItems.TORCHTILLAS, null
                })
                .setItem(Material.NETHERITE_INGOT)
                .setRate(2, 300)
                .register(addon);

        new MaterialGenerator(ChocoItems.CH_GENERATOR, ChocoItems.ARFBON, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        null,SlimefunItems.ENERGIZED_CAPACITOR, null,
                        SlimefunItems.CARBON, ChocoItems.ARFWOOFERITE,  SlimefunItems.CARBON,
                        ChocoItems.CHOCO_CARBON, ChocoItems.TORCHTILLAS, ChocoItems.CHOCO_CARBON
                })
                .setItem(SlimefunItems.CARBON,64)
                .setRate(2, 30)
                .register(addon);

        new MaterialGenerator(ChocoItems.CH_GENERATOR, ChocoItems.AYANCLUSTER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        ChocoItems.AYANCLUSTER,SlimefunItems.ENERGIZED_CAPACITOR, ChocoItems.AYANCLUSTER,
                        SlimefunItems.CARBON, ChocoItems.ARFWOOFERITE,  SlimefunItems.CARBON,
                        ChocoItems.AYANCLUSTER, ChocoItems.TORCHTILLAS, ChocoItems.AYANCLUSTER
                })
                .setItem(Material.AMETHYST_CLUSTER,16)
                .setRate(4, 120)
                .register(addon);

        new MaterialGenerator(ChocoItems.CH_GENERATOR, ChocoItems.DRTGOO, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        ChocoItems.AYANCLUSTER,SlimefunItems.ENERGIZED_CAPACITOR, ChocoItems.AYANCLUSTER,
                        SlimefunItems.CARBON, ChocoItems.ARFWOOFERITE,  SlimefunItems.CARBON,
                        ChocoItems.AYANCLUSTER, ChocoItems.TORCHTILLAS, ChocoItems.AYANCLUSTER
                })
                .setItem(SlimefunItems.STRANGE_NETHER_GOO,8)
                .setRate(6, 180)
                .register(addon);
    }
}
