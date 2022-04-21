package me.zwrumpy.chocohills.machine;

import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.paperlib.PaperLib;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import me.zwrumpy.chocohills.register.ChocoItems;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.*;
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
                new ItemStack(Material.DEEPSLATE), new ItemStack(Material.DEEPSLATE), new ItemStack(Material.DEEPSLATE),
                new ItemStack(Material.SMOKER), new ItemStack(Material.ACACIA_FENCE), new ItemStack(Material.SMOKER),
                new ItemStack(Material.CAMPFIRE), new CustomItemStack(Material.DISPENSER, "Dispenser (Facing up)"), new ItemStack(Material.CAMPFIRE)
        }, BlockFace.SELF);
    }

    @Override
    protected void registerDefaultRecipes(List<ItemStack> recipes) {
        recipes.add(new ItemStack(Material.EGG));
        recipes.add(new SlimefunItemStack(ChocoItems.TINAPANG_ITLOG, 1));
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

            for (ItemStack item : inv.getContents()) {
                for (ItemStack recipeInput : RecipeType.getRecipeInputs(this)) {
                    if (recipeInput != null && SlimefunUtils.isItemSimilar(item, recipeInput, true)) {
                        ItemStack output = RecipeType.getRecipeOutput(this, recipeInput);
                        Inventory outputInv = findOutputInventory(output, dispBlock, inv);

                        if (outputInv != null) {
                            ItemStack removing = item.clone();
                            removing.setAmount(recipeInput.getAmount());
                            inv.removeItem(removing);

                            craft(p, output, dispBlock, inv);
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

    @ParametersAreNonnullByDefault
    private void craft(Player p, ItemStack output, Block dispenser, Inventory dispInv) {
        for (int i = 0; i < 4; i++) {
            int j = i;

            Slimefun.runSync(() -> {
                if (j < 3) {
                    p.getWorld().playSound(p.getLocation(), j == 1 ? Sound.BLOCK_PISTON_CONTRACT : Sound.BLOCK_PISTON_EXTEND, 1F, j == 0 ? 1F : 2F);
                } else {
                    p.getWorld().playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1F, 1F);
                    handleCraftedItem(output, dispenser, dispInv);
                }
            }, i * 20L);
        }
    }
}
