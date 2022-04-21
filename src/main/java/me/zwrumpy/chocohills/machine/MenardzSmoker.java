package me.zwrumpy.chocohills.machine;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.zwrumpy.chocohills.register.ChocoItems;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.stream.Collectors;

public class MenardzSmoker extends MultiBlockMachine {
    @ParametersAreNonnullByDefault
    public MenardzSmoker(ItemGroup itemGroup, SlimefunItemStack item) {
        super(itemGroup, item, new ItemStack[] {
                null, new ItemStack(Material.OAK_FENCE), null,
                null, new CustomItemStack(Material.DISPENSER, "Dispenser (Facing up)"), null,
                new ItemStack(Material.DEEPSLATE), new ItemStack(Material.CAMPFIRE), new ItemStack(Material.DEEPSLATE)
        }, BlockFace.SELF);
    }

    @Override
    protected void registerDefaultRecipes(@Nonnull List<ItemStack> recipes) {
        recipes.add(new ItemStack(Material.EGG));
        recipes.add(new ItemStack(ChocoItems.TINAPANG_ITLOG));
    }

    @Override
    public List<ItemStack> getDisplayRecipes() {
        return recipes.stream().map(items -> items[0]).collect(Collectors.toList());
    }

    @Override
    public void onInteract(Player p, Block b) {
        Block dispBlock = b.getRelative(BlockFace.DOWN);
        BlockState state = PaperLib.getBlockState(dispBlock, false).getState();

        if (state instanceof Dispenser) {
            Dispenser disp = (Dispenser) state;
            Inventory inv = disp.getInventory();

            for (ItemStack current : inv.getContents()) {
                for (ItemStack convert : RecipeType.getRecipeInputs(this)) {
                    if (convert != null && SlimefunUtils.isItemSimilar(current, convert, true)) {
                        ItemStack output = RecipeType.getRecipeOutput(this, convert);
                        Inventory outputInv = findOutputInventory(output, dispBlock, inv);

                        if (outputInv != null) {
                            ItemStack removing = current.clone();
                            removing.setAmount(1);
                            inv.removeItem(removing);
                            outputInv.addItem(output);
                            p.getWorld().playSound(p.getLocation(), Sound.BLOCK_WOODEN_BUTTON_CLICK_ON, 1, 1);
                        } else {
                            Slimefun.getLocalization().sendMessage(p, "machines.full-inventory", true);
                        }

                        return;
                    }
                }
            }
            Slimefun.getLocalization().sendMessage(p, "machines.unknown-material", true);
        }
    }
}
