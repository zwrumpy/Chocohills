package me.zwrumpy.chocohills.register;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.machine.transporter.*;
import org.bukkit.inventory.ItemStack;

public class TransporterSetup {
    ChocoHills addon;
    SlimefunItemStack tempStack;

    public TransporterSetup(ChocoHills addon) {
        this.addon = addon;
        setup();
    }

    void setup() {
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
}
