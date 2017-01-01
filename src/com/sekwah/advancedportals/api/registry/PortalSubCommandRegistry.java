package com.sekwah.advancedportals.api.registry;

import com.sekwah.advancedportals.api.commands.SubCommand;

/**
 * Designed to let addons add new command sections to access, edit or add new functonality.
 *
 * @author sekwah41
 */
public class PortalSubCommandRegistry extends SubCommandRegistry {

    /**
     * @param subCommand
     * @return if the subcommand is registered or not
     */
    public static boolean registerSubCommand(SubCommand subCommand){
        return false;
    }

}
