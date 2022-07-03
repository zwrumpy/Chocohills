package me.zwrumpy.chocohills.register;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.machine.abstracts.ArfBon;
import me.zwrumpy.chocohills.machine.abstracts.MaterialGenerator;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class MaterialGeneratorSetup {
    ChocoHills addon;
    SlimefunItemStack tempStack;

    public MaterialGeneratorSetup(ChocoHills addon) {
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
                .setRate(4, 300)
                .register(addon);

        new ArfBon(ChocoItems.CH_GENERATOR, ChocoItems.ARFBON, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        null,SlimefunItems.ENERGIZED_CAPACITOR, null,
                        SlimefunItems.CARBON, ChocoItems.ARFWOOFERITE,  SlimefunItems.CARBON,
                        ChocoItems.CHOCO_CARBON, ChocoItems.TORCHTILLAS, ChocoItems.CHOCO_CARBON
                })
                .setItem(SlimefunItems.CARBON,64)
                .setRate(8, 30)
                .register(addon);

        new MaterialGenerator(ChocoItems.CH_GENERATOR, ChocoItems.AYANCLUSTER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        ChocoItems.AYANSHARD,SlimefunItems.ENERGIZED_CAPACITOR, ChocoItems.AYANSHARD,
                        SlimefunItems.CARBON, ChocoItems.ARFWOOFERITE,  SlimefunItems.CARBON,
                        ChocoItems.AYANSHARD, ChocoItems.TORCHTILLAS, ChocoItems.AYANSHARD
                })
                .setItem(Material.AMETHYST_CLUSTER,16)
                .setRate(10, 120)
                .register(addon);

        new MaterialGenerator(ChocoItems.CH_GENERATOR, ChocoItems.DRTGOO, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        null, SlimefunItems.ENERGIZED_CAPACITOR, null,
                        SlimefunItems.CARBON, ChocoItems.ARFWOOFERITE,  SlimefunItems.CARBON,
                        ChocoItems.AYANSHARD, ChocoItems.TORCHTILLAS, ChocoItems.AYANSHARD
                })
                .setItem(SlimefunItems.STRANGE_NETHER_GOO,8)
                .setRate(12,180)
                .register(addon);

        new MaterialGenerator(ChocoItems.CH_GENERATOR, ChocoItems.REMI_DIAS, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        null, SlimefunItems.ENERGIZED_CAPACITOR, null,
                        ChocoItems.CHOCO_CARBON, ChocoItems.HYPER_CRYSTAL,  ChocoItems.CHOCO_CARBON,
                        ChocoItems.AYANSHARD, ChocoItems.TORCHTILLAS, ChocoItems.AYANSHARD
                })
                .setItem(Material.DIAMOND,2)
                .setRate(14,00)
                .register(addon);

        new MaterialGenerator(ChocoItems.CH_GENERATOR, ChocoItems.DEAN_TAMBAK, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        ChocoItems.CHOCO_CARBON, new ItemStack(Material.DIRT),ChocoItems.CHOCO_CARBON,
                        new ItemStack(Material.DIRT), ChocoItems.ARFWOOFERITE, new ItemStack(Material.DIRT),
                        ChocoItems.AYANSHARD, new ItemStack(Material.DIRT), ChocoItems.AYANSHARD
                })
                .setItem(Material.DIRT, 64)
                .setRate(16,180)
                .register(addon);
    }
}
