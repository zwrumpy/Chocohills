package me.zwrumpy.chocohills.register;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.tools.BaamBuilder;
import me.zwrumpy.chocohills.tools.Tools;
import me.zwrumpy.chocohills.tools.Wrench;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ToolSetup {
    ChocoHills addon;
    SlimefunItemStack tempStack;

    public ToolSetup(ChocoHills addon) {
        this.addon = addon;
        setup();
    }

    void setup() {
        new Wrench();
        new BaamBuilder();

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

    void registerTools(SlimefunItemStack stack, ItemStack[] recipe) {
        new Tools(ChocoItems.CH_TOOLS, stack, RecipeType.ENHANCED_CRAFTING_TABLE, recipe).register(this.addon);
    }
}
