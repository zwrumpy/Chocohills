package me.zwrumpy.chocohills;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import me.zwrumpy.chocohills.Commands.ToolCommand;
import me.zwrumpy.chocohills.listener.PickaxeListener;
import me.zwrumpy.chocohills.listener.TorchInteract;
import me.zwrumpy.chocohills.register.ChocoItemSetup;
import org.bukkit.plugin.java.JavaPlugin;

public class ChocoHills extends JavaPlugin implements SlimefunAddon {
    private static ChocoHills instance;

    public void onEnable() {
        this.saveDefaultConfig();

        registerCommands();
        registerListeners();

        instance = this;
        new ChocoItemSetup(this);
    }

    void registerCommands(){
        this.getCommand("chtool").setExecutor(new ToolCommand());
    }

    void registerListeners(){
        getServer().getPluginManager().registerEvents(new PickaxeListener(this), this);
        getServer().getPluginManager().registerEvents(new TorchInteract(this), this);
    }

    public static ChocoHills getInstance() {return instance;}
    public String getBugTrackerURL() {return null;}
    public JavaPlugin getJavaPlugin() {return this;}
}