package me.zwrumpy.chocohills.tools;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.register.ChocoItems;
import me.zwrumpy.chocohills.tools.implementation.Tools;
import me.zwrumpy.chocohills.tools.listener.DumBearListener;
import me.zwrumpy.chocohills.tools.listener.WrenchInteract;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DumBear {
    public DumBear(){
        register(DUMBEAR, recipe);
        registerListener();
    }

    void registerListener(){
        ChocoHills.getInstance().getServer().getPluginManager().registerEvents(new DumBearListener(), ChocoHills.getInstance());
    }

    SlimefunItemStack DUMBEAR = new SlimefunItemStack(
            "DUMBEAR",
            Material.HONEY_BOTTLE,
            "&6&lDumbear",
            "&7Usage:",
            "&fAbsorbs Nearby ",
            "&fWater ",
            " ",
            "&7tap/click slimefun blocks"
    );

    ItemStack[] recipe = new ItemStack[]{
            ChocoItems.CHOCO_CARBON, ChocoItems.CHOCO_CRYSTAL, ChocoItems.CHOCO_CARBON,
            new ItemStack(Material.HONEY_BLOCK), ChocoItems.TORCHTILLAS, new ItemStack(Material.HONEY_BLOCK),
            null, ChocoItems.CHOCO_CRYSTAL, null
    };

    void register(SlimefunItemStack stack, ItemStack[] recipe) {
        new Tools(ChocoItems.CH_TOOLS, stack, RecipeType.ENHANCED_CRAFTING_TABLE, recipe).register(ChocoHills.getInstance());
    }
}
