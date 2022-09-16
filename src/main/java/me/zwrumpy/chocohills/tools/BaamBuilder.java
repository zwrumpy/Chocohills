package me.zwrumpy.chocohills.tools;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.register.ChocoItems;
import me.zwrumpy.chocohills.tools.listener.BaamBuilderListener;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BaamBuilder {
    public BaamBuilder(){
        register(BAAM_BUILDER, recipe);
        registerListener();
    }

    SlimefunItemStack BAAM_BUILDER = new SlimefunItemStack(
            "BAAM_BUILDER",
            Material.NETHERITE_SHOVEL,
            "&6&lBaam Builder",
            "&7Usage:",
            "&fInstant Place 5 Blocks ",
            "&fin your hotbar inventory",
            " ",
            "&7tap/click blocks"
    );

    ItemStack[] recipe = new ItemStack[]{
            ChocoItems.CHOCO_CARBON, ChocoItems.BLASTXEL, ChocoItems.CHOCO_CARBON,
            null, ChocoItems.TORCHTILLAS, null,
            null, ChocoItems.CHOCO_CRYSTAL, null
    };

    void registerListener(){
        ChocoHills.getInstance().getServer().getPluginManager().registerEvents(
                new BaamBuilderListener(), ChocoHills.getInstance()
        );
    }

    void register(SlimefunItemStack stack, ItemStack[] recipe) {
        new Tools(ChocoItems.CH_TOOLS, stack, RecipeType.ENHANCED_CRAFTING_TABLE, recipe).register(ChocoHills.getInstance());
    }
}
