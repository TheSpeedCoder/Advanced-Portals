package com.sekwah.advancedportals.commands;

import com.sekwah.advancedportals.AdvancedPortalsPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

/**
 * @author sekwah41
 */
public class PortalCommand implements CommandExecutor, TabCompleter {

    public PortalCommand(AdvancedPortalsPlugin plugin) {

    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        return null;
    }
}
