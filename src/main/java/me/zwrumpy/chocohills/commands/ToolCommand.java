package me.zwrumpy.chocohills.commands;

import me.zwrumpy.chocohills.util.PlayerHotbar;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ToolCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("toolblock");
            player.sendMessage(PlayerHotbar.getHotbarItems(player).toString());
        }
        return false;
    }
}
