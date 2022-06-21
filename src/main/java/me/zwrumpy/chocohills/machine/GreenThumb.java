package me.zwrumpy.chocohills.machine;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotHopperable;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.zwrumpy.chocohills.register.ChocoItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class GreenThumb extends AContainer implements RecipeDisplayItem, NotHopperable {

    @ParametersAreNonnullByDefault
    public GreenThumb(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(5, new ItemStack[]{new ItemStack(Material.DIRT, 32)}, new ItemStack[]{new ItemStack(Material.GRASS_BLOCK, 32)});
    }

    @Nonnull
    @Override
    public String getMachineIdentifier() {
        return "GREEN_THUMB";
    }

    @Override
    public ItemStack getProgressBar() {
        return new ItemStack(Material.GOLDEN_HOE);
    }
}