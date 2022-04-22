package me.zwrumpy.chocohills;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.zwrumpy.chocohills.commands.ToolCommand;
import me.zwrumpy.chocohills.listener.PickaxeListener;
import me.zwrumpy.chocohills.listener.TorchInteract;
import me.zwrumpy.chocohills.register.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class ChocoHills extends JavaPlugin implements SlimefunAddon {
    private static ChocoHills instance;

    public void onEnable() {
        this.saveDefaultConfig();

        registerCommands();
        registerListeners();

        instance = this;

        new ToolSetup(this);
        new TransporterSetup(this);
        new MaterialGeneratorSetup(this);
        new MachineSetup(this);
        new ResourceSetup(this);
    }

    void registerCommands(){
        Objects.requireNonNull(this.getCommand("chtool")).setExecutor(new ToolCommand());
    }

    void registerListeners(){
        getServer().getPluginManager().registerEvents(new PickaxeListener(this), this);
        getServer().getPluginManager().registerEvents(new TorchInteract(this), this);
    }

    public static ChocoHills getInstance() {return instance;}
    public String getBugTrackerURL() {return null;}
    public JavaPlugin getJavaPlugin() {return this;}
}