package com.sekwah.advancedportals;

import com.sekwah.advancedportals.compat.CraftBukkit;
import com.sekwah.advancedportals.metrics.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

/**
 * Advanced Portals plugin for minecraft. Specifically bukkit/spigot servers.
 *
 * The purpose of the new recode of this plugin is to make it efficient, more modular than the last
 * and also allow for developers to make addons to interact with plugins or server networks.
 *
 * Could make a sponge port but it is not a priority for now at least.
 *
 * @author sekwah41
 */
public class AdvancedPortalsPlugin extends JavaPlugin {

    // Class which handles calls to code which is located in craftbukkit.
    public CraftBukkit compat = null;

    public void onEnable() {

        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }

        String packageName = getServer().getClass().getPackage().getName();
        String[] packageSplit = packageName.split("\\.");
        String version = packageSplit[packageSplit.length - 1];

        this.compat = new CraftBukkit(this, version);

        this.getServer().getConsoleSender().sendMessage("\u00A7aAdvanced portals have been successfully enabled!");
    }


    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage("\u00A7cAdvanced portals are being disabled!");
    }


}
