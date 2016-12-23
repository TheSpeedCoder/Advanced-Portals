package com.sekwah.advancedportals;

import com.sekwah.advancedportals.compat.CraftBukkit;
import com.sekwah.advancedportals.metrics.Metrics;
import com.sekwah.advancedportals.util.DefaultLoader;
import com.sekwah.advancedportals.util.Lang;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

/**
 * Advanced Portals plugin for minecraft. Specifically bukkit/spigot servers.
 *
 * Try to keep bukkit references generally in the same files. All the registry stuff should try to not
 * use direct bukkit references unless needed for the data. This will make it easier for updating and also
 * cross branches/forks such as spigot versions.
 *
 * The purpose of the new recode of this plugin is to make it efficient, more modular than the last
 * and also allow for developers to make addons to interact with plugins or server networks.
 *
 * Could make a sponge port but it is not a priority for now at least.
 *
 * Looking over the license but it seems the GNU is best for now. MIT seemed too vague and not specific enough.
 *
 * @author sekwah41
 */
public class AdvancedPortalsPlugin extends JavaPlugin {

    // Class which handles calls to code which is located in craftbukkit.
    public CraftBukkit compat = null;

    private static AdvancedPortalsPlugin INSTANCE;

    public void onEnable() {

        this.INSTANCE = this;

        // TODO add the config loaders from default spigot and use those to save default if easier.

        /**
         * If local files don't exist then included ones are copied into their locations.
         */
        DefaultLoader.copyDefaultFiles(false, "config.yml", "portals.yml", "destinations.yml");

        // Test for now to initialise the classes before its first use.
        System.out.println("Translation: " + Lang.instance.translate("test.string"));

        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }

        String packageName = getServer().getClass().getPackage().getName();
        String[] packageSplit = packageName.split("\\.");
        String version = packageSplit[packageSplit.length - 1];

        try {
            this.compat = new CraftBukkit(this, version);
        } catch (ClassNotFoundException | NoSuchMethodException | NoSuchFieldException e) {
            e.printStackTrace();
            this.getLogger().warning("This version of craftbukkit is not yet supported or something went wrong, please" +
                    " post this message with the version number and the above stacktrace in an issue on GitHub v:" + version);
            this.getLogger().warning("https://github.com/sekwah41/Advanced-Portals/issues");
            this.setEnabled(false);
        }

        this.getServer().getConsoleSender().sendMessage("\u00A7aAdvanced portals have been successfully enabled!");
    }

    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage("\u00A7cAdvanced portals are being disabled!");
    }

    public static AdvancedPortalsPlugin getInstance(){
        return INSTANCE;
    }


}
