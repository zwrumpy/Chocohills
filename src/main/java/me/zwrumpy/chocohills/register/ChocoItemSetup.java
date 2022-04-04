package me.zwrumpy.chocohills.register;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.generator.MaterialGenerator;
import me.zwrumpy.chocohills.machines.*;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ChocoItemSetup {
    ChocoHills addon;
    SlimefunItemStack tempStack;

    public ChocoItemSetup(ChocoHills addon) {
        this.addon = addon;
        setup();
    }

    void setup() {
        tools();
        items();
        generators();
        machines();
    }

    void tools() {
        registerTools(ChocoItems.BLASTXEL, new ItemStack[]{
                ChocoItems.CHOCO_CRYSTAL, new ItemStack(Material.NETHERITE_INGOT), ChocoItems.CHOCO_CRYSTAL,
                new ItemStack(Material.NETHERITE_SHOVEL), new ItemStack(Material.NETHERITE_AXE), new ItemStack(Material.NETHERITE_PICKAXE),
                ChocoItems.CHOCO_CRYSTAL, new ItemStack(Material.NETHERITE_INGOT), ChocoItems.CHOCO_CRYSTAL});

        registerTools(ChocoItems.BLASTXEL_2, new ItemStack[]{
                ChocoItems.COMPRESSED_CRYSTAL, ChocoItems.COMPRESSED_CRYSTAL, ChocoItems.COMPRESSED_CRYSTAL,
                ChocoItems.COMPRESSED_CRYSTAL, ChocoItems.BLASTXEL, ChocoItems.COMPRESSED_CRYSTAL,
                ChocoItems.COMPRESSED_CRYSTAL, ChocoItems.COMPRESSED_CRYSTAL, ChocoItems.COMPRESSED_CRYSTAL
        });

        registerTools(ChocoItems.BLASTXEL_3, new ItemStack[]{
                ChocoItems.HYPER_CRYSTAL, ChocoItems.HYPER_CRYSTAL, ChocoItems.HYPER_CRYSTAL,
                ChocoItems.HYPER_CRYSTAL, ChocoItems.BLASTXEL_2, ChocoItems.HYPER_CRYSTAL,
                ChocoItems.HYPER_CRYSTAL, ChocoItems.HYPER_CRYSTAL, ChocoItems.HYPER_CRYSTAL
        });

        registerTools(ChocoItems.TORCHTILLAS, new ItemStack[]{
                null, ChocoItems.CHOCO_CRYSTAL, null,
                null, new ItemStack(Material.TORCH), null,
                null, ChocoItems.CHOCO_CRYSTAL, null
        });
    }

    void items() {
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
    }

    void machines() {
        new MaterialExporterUp(ChocoItems.CH_MACHINES, ChocoItems.KEIMAEXPORTER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        ChocoItems.CHOCO_CRYSTAL, null, null,
                        SlimefunItems.SOLAR_GENERATOR_4, SlimefunItems.CARGO_OUTPUT_NODE, SlimefunItems.SOLAR_GENERATOR_4,
                        ChocoItems.COMPRESSED_CRYSTAL, ChocoItems.TORCHTILLAS, null
                }
        ).register(addon);
        new MaterialExporterDown(ChocoItems.CH_MACHINES, ChocoItems.KEIMAEXPORTER_DOWN, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        null, null, ChocoItems.CHOCO_CRYSTAL,
                        SlimefunItems.SOLAR_GENERATOR_4, SlimefunItems.CARGO_OUTPUT_NODE, SlimefunItems.SOLAR_GENERATOR_4,
                        ChocoItems.COMPRESSED_CRYSTAL, ChocoItems.TORCHTILLAS, null
                }
        ).register(addon);

        new MaterialImporterUp(ChocoItems.CH_MACHINES, ChocoItems.KEIMAIMPORTER_UP, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        ChocoItems.CHOCO_CRYSTAL, null, ChocoItems.CHOCO_CRYSTAL,
                        SlimefunItems.SOLAR_GENERATOR_4, SlimefunItems.CARGO_INPUT_NODE, SlimefunItems.SOLAR_GENERATOR_4,
                        ChocoItems.COMPRESSED_CRYSTAL, ChocoItems.TORCHTILLAS, null
                }
        ).register(addon);

        new MaterialImporterDown(ChocoItems.CH_MACHINES, ChocoItems.KEIMAIMPORTER, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        ChocoItems.CHOCO_CRYSTAL, null, ChocoItems.CHOCO_CRYSTAL,
                        SlimefunItems.SOLAR_GENERATOR_4, SlimefunItems.CARGO_INPUT_NODE, SlimefunItems.SOLAR_GENERATOR_4,
                        null, ChocoItems.TORCHTILLAS,  ChocoItems.COMPRESSED_CRYSTAL
                }
        ).register(addon);

        new VanillaTransferUp(ChocoItems.CH_MACHINES, ChocoItems.ARFYTRANSPORTER_UP, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        ChocoItems.CHOCO_CRYSTAL, SlimefunItems.CARGO_INPUT_NODE, ChocoItems.CHOCO_CRYSTAL,
                        SlimefunItems.CARGO_INPUT_NODE, SlimefunItems.SOLAR_GENERATOR_4, SlimefunItems.CARGO_INPUT_NODE,
                        ChocoItems.CHOCO_CRYSTAL, SlimefunItems.CARGO_INPUT_NODE, ChocoItems.CHOCO_CRYSTAL
                }
        ).register(addon);

        new VanillaTransferDown(ChocoItems.CH_MACHINES, ChocoItems.ARFYTRANSPORTER_DOWN, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        ChocoItems.CHOCO_CRYSTAL, SlimefunItems.CARGO_OUTPUT_NODE, ChocoItems.CHOCO_CRYSTAL,
                        SlimefunItems.CARGO_OUTPUT_NODE, SlimefunItems.SOLAR_GENERATOR_4, SlimefunItems.CARGO_OUTPUT_NODE,
                        ChocoItems.CHOCO_CRYSTAL, SlimefunItems.CARGO_OUTPUT_NODE, ChocoItems.CHOCO_CRYSTAL
                }
        ).register(addon);
    }

    void generators() {
        new MaterialGenerator(ChocoItems.CH_GENERATOR, ChocoItems.ARFWOOFERITE, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        null, ChocoItems.ENERGIZED_CRYSTAL, null,
                        SlimefunItems.SOLAR_GENERATOR_4, SlimefunItems.ENERGIZED_CAPACITOR, SlimefunItems.SOLAR_GENERATOR_4,
                        null, ChocoItems.TORCHTILLAS, null
                }
        )
                .setItem(Material.NETHERITE_INGOT)
                .setRate(1, 300)
                .register(addon);
    }

    void registerTools(SlimefunItemStack stack, ItemStack[] recipe) {
        new UnplaceableBlock(ChocoItems.CH_TOOLS, stack, RecipeType.ENHANCED_CRAFTING_TABLE, recipe).register(this.addon);
    }

    void registerResource(SlimefunItemStack stack, ItemStack[] recipe) {
        new UnplaceableBlock(ChocoItems.CH_RESOURCES, stack, RecipeType.ENHANCED_CRAFTING_TABLE, recipe).register(this.addon);
    }
}
