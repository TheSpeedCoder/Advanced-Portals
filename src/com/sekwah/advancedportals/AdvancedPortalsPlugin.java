package com.sekwah.advancedportals;

import com.sekwah.advancedportals.DataCollector.DataCollector;
import com.sekwah.advancedportals.compat.CraftBukkit;
import com.sekwah.advancedportals.destinations.*;
import com.sekwah.advancedportals.effects.WarpEffects;
import com.sekwah.advancedportals.listeners.*;
import com.sekwah.advancedportals.metrics.Metrics;
import com.sekwah.advancedportals.portals.Portal;
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



        this.getServer().getConsoleSender().sendMessage("\u00A7aAdvanced portals have been successfully enabled!");
    }


    public void onDisable() {
        this.getServer().getConsoleSender().sendMessage("\u00A7cAdvanced portals are being disabled!");
    }


}
