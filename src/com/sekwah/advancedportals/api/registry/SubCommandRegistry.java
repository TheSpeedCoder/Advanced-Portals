package com.sekwah.advancedportals.api.registry;

import com.sekwah.advancedportals.AdvancedPortalsPlugin;
import com.sekwah.advancedportals.api.commands.SubCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Do not register to here. Register to the sprcific subcommand registry classes.
 * <p>
 * Designed to let addons add new command sections to access, edit or add new functonality.
 *
 * @author sekwah41
 */
public class SubCommandRegistry {

    private Map<String, SubCommand> subCommands = new HashMap();

    private static final SubCommandRegistry instance = new SubCommandRegistry();

    /**
     * @param arg argument needed to activate
     * @param subCommand
     * @return if the subcommand is registered or not
     */
    public static boolean registerSubCommand(String arg, SubCommand subCommand) {

        if (subCommand == null) {
            AdvancedPortalsPlugin.logWarning("The subcommand '" + arg + "' cannot be null.");
            return false;
        }

        if(!instance.subCommands.containsKey(arg)){
            AdvancedPortalsPlugin.logWarning("The subcommand '" + arg + "' already exists.");
            return false;
        }

        return false;
    }

    public static SubCommand getSubCommand(String name){
        if(instance.subCommands.containsKey(name)){
            return instance.subCommands.get(name);
        }
        return null;
    }
}
