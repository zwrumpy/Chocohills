package me.zwrumpy.chocohills.tools;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.register.ChocoItems;
import me.zwrumpy.chocohills.tools.implementation.Tools;
import me.zwrumpy.chocohills.tools.listener.BlastxelListener;
import me.zwrumpy.chocohills.tools.listener.WrenchInteract;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Blastxel {
    public Blastxel(){
        registerItems();
        registerListener();
    }

    void registerItems(){
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
    }

    void registerListener(){
        ChocoHills.getInstance().getServer().getPluginManager().registerEvents(new BlastxelListener(ChocoHills.getInstance()), ChocoHills.getInstance());
    }

    void registerTools(SlimefunItemStack stack, ItemStack[] recipe) {
        new Tools(ChocoItems.CH_TOOLS, stack, RecipeType.ENHANCED_CRAFTING_TABLE, recipe).register(ChocoHills.getInstance());
    }
}
