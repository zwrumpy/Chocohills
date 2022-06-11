package me.zwrumpy.chocohills.machine;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.NotHopperable;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.zwrumpy.chocohills.machine.abstracts.AnvilAbstract;
import me.zwrumpy.chocohills.register.ChocoItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class AdvanceAnvil extends AnvilAbstract implements RecipeDisplayItem, NotHopperable {


    @ParametersAreNonnullByDefault
    public AdvanceAnvil(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public int[] getInputSlots() {
        return new int[]{19, 21};
    }

    @Override
    public int[] getOutputSlots() {
        return new int[]{25};
    }

    @Nonnull
    @Override
    public String getMachineIdentifier() {
        return "CHOCO_ADVANCE_ANVIL";
    }
}