package me.zwrumpy.chocohills.register;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.tools.*;
import me.zwrumpy.chocohills.tools.implementation.Tools;
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
        new AntheriteWrench();
        new BaamBuilder();
        //new DumBear();
        new Blastxel();
        new Torchtillas();
    }
}
