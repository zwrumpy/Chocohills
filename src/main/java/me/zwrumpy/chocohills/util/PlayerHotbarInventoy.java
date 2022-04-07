package me.zwrumpy.chocohills.util;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class PlayerHotbarInventoy {
    Inventory inv;
    List<ItemStack> items = new ArrayList<ItemStack>();

    public void removeItem(ItemStack item){

    }

    public List getItemList(){
        return items;
    }

}
