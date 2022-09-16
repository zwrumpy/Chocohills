package me.zwrumpy.chocohills.tools;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.register.ChocoItems;
import me.zwrumpy.chocohills.tools.listener.PickaxeListener;
import me.zwrumpy.chocohills.tools.listener.WrenchInteract;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Wrench {

    public Wrench(){
        register(ANTHERITE_WRENCH, recipe);
        registerListener();
    }

    void registerListener(){
        ChocoHills.getInstance().getServer().getPluginManager().registerEvents(new WrenchInteract(ChocoHills.getInstance()), ChocoHills.getInstance());
    }

    SlimefunItemStack ANTHERITE_WRENCH = new SlimefunItemStack(
            "ANTHERITE_WRENCH",
            Material.NETHERITE_HOE,
            "&6&lAntherite Wrench",
            "&7Usage:",
            "&fInstant Break ",
            "&fSlimefun Blocks",
            " ",
            "&7tap/click slimefun blocks"
    );

    ItemStack[] recipe = new ItemStack[]{
            ChocoItems.CHOCO_CARBON, ChocoItems.CHOCO_CRYSTAL, ChocoItems.CHOCO_CARBON,
            new ItemStack(Material.AMETHYST_BLOCK), ChocoItems.TORCHTILLAS, new ItemStack(Material.AMETHYST_BLOCK),
            null, ChocoItems.CHOCO_CRYSTAL, null
    };

    void register(SlimefunItemStack stack, ItemStack[] recipe) {
        new Tools(ChocoItems.CH_TOOLS, stack, RecipeType.ENHANCED_CRAFTING_TABLE, recipe).register(ChocoHills.getInstance());
    }
}
