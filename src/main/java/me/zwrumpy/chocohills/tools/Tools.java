package me.zwrumpy.chocohills.tools;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.zwrumpy.chocohills.tools.abstracts.AbstractTools;
import org.bukkit.inventory.ItemStack;

public class Tools extends AbstractTools {
    public Tools(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }
}
