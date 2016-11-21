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

public class AdvancedPortalsPlugin extends JavaPlugin {

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
