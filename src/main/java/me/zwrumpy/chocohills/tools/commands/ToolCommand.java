package me.zwrumpy.chocohills.tools.commands;

import me.zwrumpy.chocohills.util.PlayerHotbar;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
