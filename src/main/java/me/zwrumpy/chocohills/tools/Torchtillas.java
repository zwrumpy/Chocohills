package me.zwrumpy.chocohills.tools;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.register.ChocoItems;
import me.zwrumpy.chocohills.tools.implementation.Tools;
import me.zwrumpy.chocohills.tools.listener.TorchtillasInteract;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Torchtillas {
    public Torchtillas(){
        registerItem();
        registerListener();
    }

    void registerItem() {
        registerTools(ChocoItems.TORCHTILLAS, new ItemStack[]{
                null, ChocoItems.CHOCO_CRYSTAL, null,
                null, new ItemStack(Material.TORCH), null,
                null, ChocoItems.CHOCO_CRYSTAL, null
        });
    }
    void registerListener(){
        ChocoHills.getInstance().getServer().getPluginManager().registerEvents(
                new TorchtillasInteract(ChocoHills.getInstance()), ChocoHills.getInstance()
        );
    }

    void registerTools(SlimefunItemStack stack, ItemStack[] recipe) {
        new Tools(ChocoItems.CH_TOOLS, stack, RecipeType.ENHANCED_CRAFTING_TABLE, recipe).register(ChocoHills.getInstance());
    }
}
