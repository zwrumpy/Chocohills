package me.zwrumpy.chocohills.register;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineTier;
import io.github.thebusybiscuit.slimefun4.core.attributes.MachineType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.LoreBuilder;
import me.zwrumpy.chocohills.ChocoHills;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

public class ChocoItems {

    private ChocoItems() {}

    public static final NestedItemGroup CH_GENERAL = new NestedItemGroup(new NamespacedKey(ChocoHills.getInstance(), "CH_GENERAL"), new CustomItemStack(Material.SUNFLOWER, "&bChocoHills"));
    public static final SubItemGroup CH_RESOURCES = subGroup("CH_RESOURCES", "Resources", Material.LARGE_AMETHYST_BUD);
    public static final SubItemGroup CH_TOOLS = subGroup("CH_TOOLS", "Tools", Material.NETHERITE_PICKAXE);
    public static final SubItemGroup CH_GENERATOR = subGroup("CH_GENERATOR", "Generator", Material.ANCIENT_DEBRIS);
    public static final SubItemGroup CH_MACHINES = subGroup("CH_MACHINES", "Machines", Material.HONEY_BLOCK);
    //public static final ItemGroup CH_GROUP = new ItemGroup(new NamespacedKey(ChocoHills.getInstance(), "CH_GROUP"), new CustomItemStack(Material.SUNFLOWER, "&e&lChocoHills"));

    static SubItemGroup subGroup( String namespaceKey, String name, Material icon){
        return new SubItemGroup(new NamespacedKey(ChocoHills.getInstance(), namespaceKey), CH_GENERAL,new CustomItemStack(icon, name));
    }
    public static final SlimefunItemStack BLASTXEL = new SlimefunItemStack(
            "BLASTXEL",
            Material.NETHERITE_PICKAXE,
            "&7&lBlastxel 1x",
            "&7After grueling months of mining",
            "&7wooferite inside woof clan mountains",
            "&7Keima the blacksmith",
            "&7forged blastxel from ancient wooferite ore",
            "",
            "&7Ability:",
            "&fBlast Mine Blocks: 3x1x3"
    );

    public static final SlimefunItemStack BLASTXEL_2 = new SlimefunItemStack(
            "BLASTXEL_2",
            Material.NETHERITE_PICKAXE,
            "&7&lBlastxel 3x",
            "&7Use:",
            "&fFaster Breaking Blocks",
            "",
            "&7Ability:",
            "&fBlast Mine Blocks: 3x3x3"
    );

    public static final SlimefunItemStack BLASTXEL_3 = new SlimefunItemStack(
            "BLASTXEL_3",
            Material.NETHERITE_PICKAXE,
            "&7&lBlastxel 6x",
            "&7Use:",
            "&fFaster Breaking Blocks",
            "",
            "&7Ability:",
            "&fBlast Mine Blocks: 3x6x3"
    );

    public static final SlimefunItemStack CHOCO_CARBON = new SlimefunItemStack(
            "CHOCO_CARBON",
            Material.BASALT,
            "&e&lChoco Carbon",
            "&fAltered wooferite",
            ""
    );

    public static final SlimefunItemStack CHOCO_CRYSTAL = new SlimefunItemStack(
            "CHOCO_CRYSTAL",
            Material.SMALL_AMETHYST_BUD,
            "&e&lChoco Crystal",
            "&fBasic Choco Element",
            ""
    );

    public static final SlimefunItemStack COMPRESSED_CRYSTAL = new SlimefunItemStack(
            "COMPRESSED_CRYSTAL",
            Material.MEDIUM_AMETHYST_BUD,
            "&e&lCompressed Crystal",
            "&fRefined Choco Element",
            ""
    );

    public static final SlimefunItemStack HYPER_CRYSTAL = new SlimefunItemStack(
            "HYPER_CRYSTAL",
            Material.LARGE_AMETHYST_BUD,
            "&e&lHyper Crystal",
            "&fModified Choco Element",
            ""
    );

    public static final SlimefunItemStack CHOCO_CRYSTAL_X2 = new SlimefunItemStack(
            "CHOCO_CRYSTAL_X2",
            Material.AMETHYST_CLUSTER,
            "&e&lChoco Crystal X2",
            "&f2nd Generation Crystal",
            ""
    );

    public static final SlimefunItemStack ENERGIZED_CRYSTAL = new SlimefunItemStack(
            "ENERGIZED_CRYSTAL",
            Material.END_CRYSTAL,
            "&e&lEnergized Crystal",
            "&fInfused with energy",
            ""
    );

    public static final SlimefunItemStack AYANSHARD = new SlimefunItemStack(
            "AYANSHARD",
            Material.AMETHYST_SHARD,
            "&e&lAyan Shard",  ""
    );

    public static final SlimefunItemStack GHOSTINGGOO = new SlimefunItemStack(
            "GHOSTINGGOO",
            Material.MAGENTA_DYE,
            "&e&lGhosting Goo",
            "&fA woof spirit trapped inside ",
            "&fa gelatinous substance"
    );

    public static final SlimefunItemStack CHOCO_INGOT = new SlimefunItemStack(
            "CHOCO_INGOT",
            Material.GOLD_INGOT,
            "&e&lChoco Ingot",  ""
    );

    public static final SlimefunItemStack KEIMAEXPORTER = new SlimefunItemStack(
            "KEIMAEXPORTER",
            Material.SLIME_BLOCK,
            "&a&lKeima SF Exporter &8(&7Up&8)",
            "&7Use",
            " &fItem transfer ",
            " &7from &fslimefun machine output",
            " &7to &fvanilla storage",
            "",
            "&aSetup",
            " &fPlace above: Vanilla storage: &6Output",
            " &fPlace middle: Keima exporter: &7Connector",
            " &fPlace below: Slimefun machine: &3Input"
    );

    public static final SlimefunItemStack KEIMAEXPORTER_DOWN = new SlimefunItemStack(
            "KEIMAEXPORTER_DOWN",
            Material.SLIME_BLOCK,
            "&a&lKeima SF Exporter &8(&7Down&8)",
            "&7Use",
            " &fItem transfer ",
            " &7from &fslimefun machine output",
            " &7to &fvanilla storage",
            "",
            "&aSetup",
            " &fPlace above: Slimefun machine: &3Input",
            " &fPlace middle: Keima exporter: &7Connector",
            " &fPlace below: Vanilla storage: &6Output"
    );


    public static final SlimefunItemStack KEIMAIMPORTER = new SlimefunItemStack(
            "KEIMAIMPORTER",
            Material.HONEY_BLOCK,
            "&6&lKeima Vanilla Importer &8(&7Down&8)",
            "&7Use",
            " &fItem transfer ",
            " &7from &fvanilla storage",
            " &7to &fslimefun machine input",
            "",
            "&aSetup",
            " &fPlace above: Vanilla storage: &3Input",
            " &fPlace middle: Keima importer: &7Connector",
            " &fPlace below: Slimefun machine: &6Output"
    );

    public static final SlimefunItemStack KEIMAIMPORTER_UP = new SlimefunItemStack(
            "KEIMAIMPORTER_UP",
            Material.HONEY_BLOCK,
            "&6&lKeima Vanilla Importer &8(&7UP&8)",
            "&7Use",
            " &fItem transfer ",
            " &7from &fvanilla storage",
            " &7to &fslimefun machine input",
            "",
            "&aSetup",
            " &fPlace above: Slimefun machine: &6Output",
            " &fPlace middle: Keima importer: &7Connector",
            " &fPlace below: Vanilla storage: &3Input"
    );

    public static final SlimefunItemStack ARFYTRANSPORTER_UP = new SlimefunItemStack(
            "ARFYTRANSPORTER_UP",
            Material.RED_STAINED_GLASS_PANE,
            "&f&lArfy fetch &r&8(&7Up&8)",
            "&7Use",
            " &fTransfer item upwards",
            " &fvanilla storage only",
            "",
            "&aSetup",
            " &fPlace above: Vanilla Storage: &6Output",
            " &fPlace middle: Arfy Fetch: &7Connector",
            " &fPlace below: Vanilla Storage: &3Input"
    );

    public static final SlimefunItemStack ARFYTRANSPORTER_DOWN = new SlimefunItemStack(
            "ARFYTRANSPORTER_DOWN",
            Material.BLACK_STAINED_GLASS_PANE,
            "&f&lArfy Fetch &r&8(&7Down&8)",
            "&7Use",
            " &fTransfer item downwards",
            " &fvanilla storage only",
            "",
            "&aSetup",
            " &fPlace above: Vanilla storage: &3Input",
            " &fPlace middle: Arfy fetch: &7Connector",
            " &fPlace below: Vanilla storage: &6Output"
    );

    public static final SlimefunItemStack TORCHTILLAS = new SlimefunItemStack(
            "TORCHTILLAS",
            Material.TORCH,
            "&6&lTorchtillas",
            "&7Use: ",
            "&fGives 30 minutes",
            "&fnightvision",
            "",
            "&8Left click on air",
            "&8to activate"
    );

    public static final SlimefunItemStack EGGPENSIVE = new SlimefunItemStack(
            "EGGPENSIVE",
            Material.EGG,
            "&fEggPensive",
            ""
    );

    public static final SlimefunItemStack EGG_COOKER = new SlimefunItemStack(
            "EGG_COOKER",
            Material.SMOKER,
            "&eEgg Cooker",
            "&7Usage",
            "&fCarbon chunk &8+ &fTinapang itlog",
            "&f&8= &aEggpensive",
            LoreBuilder.machine(MachineTier.END_GAME, MachineType.MACHINE),
            LoreBuilder.speed(1),
            LoreBuilder.powerPerSecond(20)
    );

    public static final SlimefunItemStack MENARDZ_SMOKER = new SlimefunItemStack(
            "MENARDZ_SMOKER",
            Material.CAMPFIRE,
            "&eMenardz Smoker",
            "&7",
            "&7Usage:",
            "&fMakes chicken egg into",
            "&fTinapang itlog"


    );

    public static final SlimefunItemStack TINAPANG_ITLOG = new SlimefunItemStack(
            "TINAPANG_ITLOG",
            Material.EGG,
            "&fTinapang Itlog",
            ""
    );


    public static final SlimefunItemStack ARFWOOFERITE = new SlimefunItemStack(
            "ARFWOOFERITE",
            Material.ANCIENT_DEBRIS,
            "&4&lArf Wooferite Gen",
            "&fDr. Arf discovered wooferite",
            "&funder boneloo shrine",
            "&fof woof clan",
            " ",
            "&7Usage:",
            "&fNetherite Generator",
            "&7Rate: &f1 &7netherite ingot",
            "&7Time: &f5 &7minutes",
            " ",
            "&7Setup:",
            "&fPlace Chest on top of this block"
    );

    public static final SlimefunItemStack ARFBON = new SlimefunItemStack(
            "ARFBON",
            Material.BASALT,
            "&8&lArfbon Generator",
            "&fUpon discovery of wooferite",
            "&fDr Arf experimented and found more ways",
            "&fto use this material and developed new arfbon",
            " ",
            "&7Usage:",
            "&fCarbon Generator",
            "&7Rate: &f64 &7carbon",
            "&7Time: &f30 &7seconds",
            " ",
            "&7Setup:",
            "&fPlace Chest on top of this block"
    );

    public static final SlimefunItemStack AYANCLUSTER = new SlimefunItemStack(
            "AYANCLUSTER",
            Material.AMETHYST_BLOCK,
            "&5&lAyanCluster Generator",
            "&fDr Arf assistant Ayan stumble",
            "&fupon new wooferite maliability",
            "&fnow can generate Amethyst Cluster",
            " ",
            "&7Usage:",
            "&fCarbon Generator",
            "&7Rate: &f16 &7Amethyst Cluster",
            "&7Time: &f2 &7minutes",
            " ",
            "&7Setup:",
            "&fPlace Chest on top of this block"
    );

    public static final SlimefunItemStack DRTGOO = new SlimefunItemStack(
            "DRTGOO",
            Material.BUBBLE_CORAL_BLOCK,
            "&d&lDrt Goo",
            "&fNew Ayancluster liquid mix",
            "&faccelerates its material regeneration",
            " ",
            "&7Usage:",
            "&fStrange Nether Goo Generator",
            "&7Rate: &f8 &7Goo",
            "&7Time: &f3 &7minutes",
            " ",
            "&7Setup:",
            "&fPlace Chest on top of this block"
    );

    public static final SlimefunItemStack REMI_DIAS = new SlimefunItemStack(
            "REMI_DIAS",
            Material.DIAMOND_ORE,
            "&b&lRemi Dias",
            "&7Usage:",
            "&fDiamond Generator",
            "&7Rate: &f1 &7Diamond",
            "&7Time: &f15 &7minutes",
            " ",
            "&7Setup:",
            "&fPlace Chest on top of this block"
    );
}
