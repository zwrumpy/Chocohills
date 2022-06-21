package me.zwrumpy.chocohills.machine.abstracts;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.ItemState;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.EnergyNetComponent;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineProcessHolder;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.core.machines.MachineProcessor;
import io.github.thebusybiscuit.slimefun4.core.networks.energy.EnergyNetComponentType;
import io.github.thebusybiscuit.slimefun4.implementation.handlers.SimpleBlockBreakHandler;
import io.github.thebusybiscuit.slimefun4.implementation.operations.CraftingOperation;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import me.mrCookieSlime.CSCoreLibPlugin.Configuration.Config;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.AdvancedMenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.interfaces.InventoryBlock;
import me.mrCookieSlime.Slimefun.Objects.handlers.BlockTicker;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenu;
import me.mrCookieSlime.Slimefun.api.inventory.BlockMenuPreset;
import me.zwrumpy.chocohills.ChocoHills;
import me.zwrumpy.chocohills.util.Util;
import org.apache.commons.lang.Validate;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.*;

public abstract class AdvanceAnvilAbstract extends SlimefunItem implements InventoryBlock, EnergyNetComponent, MachineProcessHolder<CraftingOperation> {

    protected static final int[] BORDER = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 14, 32, 36, 37, 38, 39, 40, 41, 42, 43, 44 };
    protected  static final int[] BORDER_IN = { 9, 10, 11, 12, 13, 18, 20, 22, 31, 27, 28, 29, 30 };
    protected  static final int[] BORDER_OUT = {15, 16, 17, 24, 26, 33, 34, 35 };
    protected  static final int[] INPUT = { 19, 21 };
    protected  static final int[] OUTPUT = { 25 };
    protected  static final int[] PREVIEW = { 23 };

    protected final List<MachineRecipe> recipes = new ArrayList<>();
    private final MachineProcessor<CraftingOperation> processor = new MachineProcessor<>(this);

    private int energyConsumedPerTick = -1;
    private int energyCapacity = -1;
    private int processingSpeed = -1;


    private static final Map<Enchantment, Integer> MAX_LEVELS = Util.getEnchants(Objects.requireNonNull(
            ChocoHills.getInstance().getConfig().getConfigurationSection("advanced-anvil-max-levels")
    ));

    @ParametersAreNonnullByDefault
    protected AdvanceAnvilAbstract(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        createPreset(this, getInventoryTitle(), this::constructMenu);
        addItemHandler(onBlockBreak());
    }

    @Nonnull
    protected BlockBreakHandler onBlockBreak() {
        return new SimpleBlockBreakHandler() {

            @Override
            public void onBlockBreak(Block b) {
                BlockMenu inv = BlockStorage.getInventory(b);

                if (inv != null) {
                    inv.dropItems(b.getLocation(), getInputSlots());
                    inv.dropItems(b.getLocation(), getOutputSlots());
                }
                processor.endOperation(b);
            }

        };
    }

    @Override
    public MachineProcessor<CraftingOperation> getMachineProcessor() {
        return processor;
    }

    protected void constructMenu(BlockMenuPreset preset) {
        for (int i : BORDER) {
            preset.addItem(i, ChestMenuUtils.getBackground(), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i : BORDER_IN) {
            preset.addItem(i, ChestMenuUtils.getInputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
        }

        for (int i : BORDER_OUT) {
            preset.addItem(i, ChestMenuUtils.getOutputSlotTexture(), ChestMenuUtils.getEmptyClickHandler());
        }

        preset.addItem(23, new CustomItemStack(Material.BLACK_STAINED_GLASS_PANE, " "), ChestMenuUtils.getEmptyClickHandler());

        for (int i : getOutputSlots()) {
            preset.addMenuClickHandler(i, new AdvancedMenuClickHandler() {

                @Override
                public boolean onClick(Player p, int slot, ItemStack cursor, ClickAction action) {
                    return false;
                }

                @Override
                public boolean onClick(InventoryClickEvent e, Player p, int slot, ItemStack cursor, ClickAction action) {
                    return cursor == null || cursor.getType() == null || cursor.getType() == Material.AIR;
                }
            });
        }
    }

    @Nonnull
    public String getInventoryTitle() {
        return "Choco Advance Anvil";
    }

    @Override
    public int getCapacity() {
        return energyCapacity;
    }
    public int getEnergyConsumption() {
        return energyConsumedPerTick;
    }
    public int getSpeed() {
        return processingSpeed;
    }
    public final AdvanceAnvilAbstract setCapacity(int capacity) {
        Validate.isTrue(capacity > 0, "The capacity must be greater than zero!");

        if (getState() == ItemState.UNREGISTERED) {
            this.energyCapacity = capacity;
            return this;
        } else {
            throw new IllegalStateException("You cannot modify the capacity after the Item was registered.");
        }
    }
    public final AdvanceAnvilAbstract setProcessingSpeed(int speed) {
        Validate.isTrue(speed > 0, "The speed must be greater than zero!");
        this.processingSpeed = speed;
        return this;
    }
    public final AdvanceAnvilAbstract setEnergyConsumption(int energyConsumption) {
        Validate.isTrue(energyConsumption > 0, "The energy consumption must be greater than zero!");
        Validate.isTrue(energyCapacity > 0, "You must specify the capacity before you can set the consumption amount.");
        Validate.isTrue(energyConsumption <= energyCapacity, "The energy consumption cannot be higher than the capacity (" + energyCapacity + ')');
        this.energyConsumedPerTick = energyConsumption;
        return this;
    }

    @Override
    public void register(@Nonnull SlimefunAddon addon) {
        this.addon = addon;

        if (getCapacity() <= 0) {
            warn("The capacity has not been configured correctly. The Item was disabled.");
            warn("Make sure to call '" + getClass().getSimpleName() + "#setEnergyCapacity(...)' before registering!");
        }

        if (getEnergyConsumption() <= 0) {
            warn("The energy consumption has not been configured correctly. The Item was disabled.");
            warn("Make sure to call '" + getClass().getSimpleName() + "#setEnergyConsumption(...)' before registering!");
        }

        if (getSpeed() <= 0) {
            warn("The processing speed has not been configured correctly. The Item was disabled.");
            warn("Make sure to call '" + getClass().getSimpleName() + "#setProcessingSpeed(...)' before registering!");
        }

        if (getCapacity() > 0 && getEnergyConsumption() > 0 && getSpeed() > 0) {
            super.register(addon);
        }

        // Fixes #3429 - Initialize Item Settings before recipes
        registerDefaultRecipes();
    }

    @Nonnull
    public abstract String getMachineIdentifier();

    protected void registerDefaultRecipes() {
        // Override this method to register your machine recipes
    }

    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes = new ArrayList<>(recipes.size() * 2);

        for (MachineRecipe recipe : recipes) {
            if (recipe.getInput().length != 1) {
                continue;
            }
            displayRecipes.add(recipe.getInput()[0]);
            displayRecipes.add(recipe.getOutput()[0]);
        }

        return displayRecipes;
    }


    @Override
    public EnergyNetComponentType getEnergyComponentType() {
        return EnergyNetComponentType.CONSUMER;
    }

    public void registerRecipe(MachineRecipe recipe) {
        recipe.setTicks(recipe.getTicks() / getSpeed());
        recipes.add(recipe);
    }

    public void registerRecipe(int seconds, ItemStack[] input, ItemStack[] output) {
        registerRecipe(new MachineRecipe(seconds, input, output));
    }

    @Override
    public void preRegister() {
        addItemHandler(new BlockTicker() {

            @Override
            public void tick(Block b, SlimefunItem sf, Config data) {
                AdvanceAnvilAbstract.this.tick(b);
            }

            @Override
            public boolean isSynchronized() {
                return false;
            }
        });
    }
    @Override
    public int[] getInputSlots() {
        return INPUT;
    }

    @Override
    public int[] getOutputSlots() {
        return OUTPUT;
    }

    public int[] getPreviewSlots() {
        return PREVIEW;
    }

    protected void tick(Block b) {
        BlockMenu inv = BlockStorage.getInventory(b);
        ItemStack item1 = inv.getItemInSlot(getInputSlots()[0]);
        ItemStack item2 = inv.getItemInSlot(getInputSlots()[1]);

        if (item1 == null || item2 == null || (item2.getType() != Material.ENCHANTED_BOOK && item1.getType() != item2.getType())) {
            inv.replaceExistingItem(getPreviewSlots()[0], new CustomItemStack(Material.BARRIER, "&cInvalid items!"));
            return;
        }

        ItemStack output = getOutput(item1, item2);

        if (output == null) {
            inv.replaceExistingItem(getPreviewSlots()[0], new CustomItemStack(Material.BARRIER, "&cNo upgrades!"));
            return;
        }

        if (!item1.getType().isAir() && !item1.getType().isAir()){
            inv.replaceExistingItem(getPreviewSlots()[0], Util.getDisplayItem(output));
            BlockStorage.getInventory(b).getItemInSlot(getInputSlots()[0]).setType(Material.AIR);
            BlockStorage.getInventory(b).getItemInSlot(getInputSlots()[1]).setType(Material.AIR);
        }
    }

    @Nullable
    private static ItemStack getOutput(@Nonnull ItemStack item1, @Nonnull ItemStack item2) {
        Map<Enchantment, Integer> enchants1 = getEnchants(item1.getItemMeta());
        Map<Enchantment, Integer> enchants2 = getEnchants(item2.getItemMeta());
        if (enchants1.size() == 0 && enchants2.size() == 0) return null;
        return combineEnchants(Maps.difference(enchants1, enchants2), item1, item2);
    }

    protected boolean takeCharge(@Nonnull Location l) {
        Validate.notNull(l, "Can't attempt to take charge from a null location!");

        if (isChargeable()) {
            int charge = getCharge(l);

            if (charge < getEnergyConsumption()) {
                return false;
            }

            setCharge(l, charge - getEnergyConsumption());
            return true;
        } else {
            return true;
        }
    }

    private static void setEnchants(@Nonnull ItemStack item, @Nonnull ItemMeta meta, @Nonnull Map<Enchantment, Integer> enchants) {
        if (meta instanceof EnchantmentStorageMeta) {
            EnchantmentStorageMeta book = (EnchantmentStorageMeta) meta;
            for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet()) {
                book.addStoredEnchant(entry.getKey(), entry.getValue(), true);
            }
            item.setItemMeta(book);
        }
        else {
            for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet()) {
                item.addUnsafeEnchantment(entry.getKey(), entry.getValue());
            }
        }
    }

    @Nonnull
    private static Map<Enchantment, Integer> getEnchants(@Nonnull ItemMeta meta) {
        if (meta instanceof EnchantmentStorageMeta) {
            EnchantmentStorageMeta book = (EnchantmentStorageMeta) meta;
            if (book.hasStoredEnchants()) {
                return book.getStoredEnchants();
            }
        }
        else if (meta.hasEnchants()) {
            return meta.getEnchants();
        }

        return new HashMap<>();
    }

    @Nullable
    private static ItemStack combineEnchants(@Nonnull MapDifference<Enchantment, Integer> dif, @Nonnull ItemStack item1, @Nonnull ItemStack item2) {
        ItemStack item = item1.clone();
        item.setAmount(1);
        ItemMeta meta = item.getItemMeta();

        Map<Enchantment, Integer> enchants = new HashMap<>();

        boolean changed = false;

        //upgrades (same enchant and level)
        for (Map.Entry<Enchantment, Integer> e : dif.entriesInCommon().entrySet()) {
            if (MAX_LEVELS.containsKey(e.getKey()) && e.getValue() < MAX_LEVELS.get(e.getKey())) {
                enchants.put(e.getKey(), e.getValue() + 1);
                changed = true;
            }
        }

        //override (same enchant different level)
        for (Map.Entry<Enchantment, MapDifference.ValueDifference<Integer>> e : dif.entriesDiffering().entrySet()) {
            if (e.getValue().rightValue() > e.getValue().leftValue()) {
                enchants.put(e.getKey(), e.getValue().rightValue());
                changed = true;
            }
        }

        boolean bookOntoTool = item2.getType() == Material.ENCHANTED_BOOK && item1.getType() != Material.ENCHANTED_BOOK;

        //unique (different enchants from 2nd item)
        for (Map.Entry<Enchantment, Integer> e : dif.entriesOnlyOnRight().entrySet()) {
            if (bookOntoTool) {
                if (!e.getKey().canEnchantItem(item)) {
                    continue;
                }
            }
            enchants.put(e.getKey(), e.getValue());
            changed = true;
        }

        if (changed) {
            setEnchants(item, meta, enchants);
            return item;
        }
        else {
            return null;
        }
    }
}
