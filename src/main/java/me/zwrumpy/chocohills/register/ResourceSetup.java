package me.zwrumpy.chocohills.register;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import me.zwrumpy.chocohills.ChocoHills;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ResourceSetup {
    ChocoHills addon;
    SlimefunItemStack tempStack;

    public ResourceSetup(ChocoHills addon) {
        this.addon = addon;
        setup();
    }

    void setup() {
        registerResource(ChocoItems.CHOCO_CRYSTAL, new ItemStack[]{
                new ItemStack(Material.NETHERITE_INGOT), null, new ItemStack(Material.NETHERITE_INGOT),
                null, new ItemStack(Material.NETHER_STAR), null,
                new ItemStack(Material.NETHERITE_INGOT), null, new ItemStack(Material.NETHERITE_INGOT)
        });

        registerResource(ChocoItems.COMPRESSED_CRYSTAL, new ItemStack[]{
                ChocoItems.CHOCO_CRYSTAL, ChocoItems.CHOCO_CRYSTAL, ChocoItems.CHOCO_CRYSTAL,
                ChocoItems.CHOCO_CRYSTAL, new ItemStack(Material.NETHER_STAR), ChocoItems.CHOCO_CRYSTAL,
                ChocoItems.CHOCO_CRYSTAL, ChocoItems.CHOCO_CRYSTAL, ChocoItems.CHOCO_CRYSTAL
        });

        registerResource(ChocoItems.HYPER_CRYSTAL, new ItemStack[]{
                ChocoItems.COMPRESSED_CRYSTAL, ChocoItems.COMPRESSED_CRYSTAL, ChocoItems.COMPRESSED_CRYSTAL,
                ChocoItems.COMPRESSED_CRYSTAL, new ItemStack(Material.NETHER_STAR), ChocoItems.COMPRESSED_CRYSTAL,
                ChocoItems.COMPRESSED_CRYSTAL, ChocoItems.COMPRESSED_CRYSTAL, ChocoItems.COMPRESSED_CRYSTAL
        });

        registerResource(ChocoItems.CHOCO_CRYSTAL_X2, new ItemStack[]{
                ChocoItems.HYPER_CRYSTAL, null, ChocoItems.HYPER_CRYSTAL,
                null, new ItemStack(Material.NETHER_STAR), null,
                ChocoItems.HYPER_CRYSTAL, null, ChocoItems.HYPER_CRYSTAL
        });

        registerResource(ChocoItems.ENERGIZED_CRYSTAL, new ItemStack[]{
                ChocoItems.CHOCO_CRYSTAL_X2, null, ChocoItems.CHOCO_CRYSTAL_X2,
                SlimefunItems.ENERGIZED_CAPACITOR, new ItemStack(Material.NETHER_STAR), SlimefunItems.ENERGIZED_CAPACITOR,
                ChocoItems.CHOCO_CRYSTAL_X2, null, ChocoItems.CHOCO_CRYSTAL_X2
        });

        registerResource(ChocoItems.CHOCO_CARBON, new ItemStack[]{
                ChocoItems.CHOCO_CRYSTAL, null, ChocoItems.CHOCO_CRYSTAL,
                SlimefunItems.CARBON, ChocoItems.EGGPENSIVE, SlimefunItems.CARBON,
                ChocoItems.CHOCO_CRYSTAL, null, ChocoItems.CHOCO_CRYSTAL
        });

        registerResource(ChocoItems.AYANSHARD, new ItemStack[]{
                ChocoItems.CHOCO_CRYSTAL, new ItemStack(Material.AMETHYST_SHARD), ChocoItems.CHOCO_CRYSTAL,
                new ItemStack(Material.AMETHYST_SHARD),  new ItemStack(Material.AMETHYST_SHARD),  new ItemStack(Material.AMETHYST_SHARD),
                ChocoItems.CHOCO_CRYSTAL, new ItemStack(Material.AMETHYST_SHARD), ChocoItems.CHOCO_CRYSTAL
        });

        registerResource(ChocoItems.GHOSTINGGOO, new ItemStack[]{
                ChocoItems.CHOCO_CRYSTAL, SlimefunItems.STRANGE_NETHER_GOO, null,
                SlimefunItems.STRANGE_NETHER_GOO, ChocoItems.AYANSHARD,  SlimefunItems.STRANGE_NETHER_GOO,
                null, SlimefunItems.STRANGE_NETHER_GOO, ChocoItems.CHOCO_CRYSTAL
        });
    }

    void registerResource(SlimefunItemStack stack, ItemStack[] recipe) {
        new UnplaceableBlock(ChocoItems.CH_RESOURCES, stack, RecipeType.ENHANCED_CRAFTING_TABLE, recipe).register(this.addon);
    }
}
