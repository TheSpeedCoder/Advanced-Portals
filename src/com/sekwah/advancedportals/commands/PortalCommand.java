package com.sekwah.advancedportals.commands;

import com.sekwah.advancedportals.AdvancedPortalsPlugin;
import com.sekwah.advancedportals.api.commands.SubCommand;
import com.sekwah.advancedportals.api.registry.PortalSubCommandRegistry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sekwah41
 */
public class PortalCommand implements CommandExecutor, TabCompleter {

    private final List<String> BLANK_LIST = new ArrayList<>();

    public PortalCommand(AdvancedPortalsPlugin plugin) {


    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String command, String[] args) {
        if (sender.hasPermission("advancedportals.portal")) {
            if (args.length > 0) {
                String subComArg = args[0].toLowerCase();
                if(PortalSubCommandRegistry.isArgRegistered(subComArg)) {
                    SubCommand subCommand = PortalSubCommandRegistry.getSubCommand(subComArg);
                    if(subCommand != null){
                        if(!subCommand.onCommand(sender, args)){
                            // TODO add mesasge for something being wrong with command.
                        }
                    }

                }
                else{
                    // TODO add message for invalud argument
                }
            }
        }
        // TODO add error message no permission
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String command, String[] args) {
        //LinkedList<String> autoComplete = new LinkedList<String>();
        if (sender.hasPermission("advancedportals.portal")) {
            if (args.length == 1) {
                return PortalSubCommandRegistry.getSubCommands();
            }
            else if(args.length > 1){
                String subComArg = args[0].toLowerCase();
                if(PortalSubCommandRegistry.isArgRegistered(subComArg)) {
                    SubCommand subCommand = PortalSubCommandRegistry.getSubCommand(subComArg);
                    if(subCommand != null){
                        return subCommand.onTabComplete(sender, args);
                    }
                }
            }
        }
        return BLANK_LIST;
    }
}
