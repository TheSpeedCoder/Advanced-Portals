package com.sekwah.advancedportals.api.effect;

import com.sekwah.advancedportals.api.portal.Portal;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * Effects to be registered to the list.
 *
 * Fires once at each end.
 *
 * Can be a Visual effect or a Sound. Just register to the correct one
 *
 * @author sekwah41
 */
public interface WarpEffect {

    enum Action{
        ENTER,
        EXIT;
    }

    void onWarp(Player player, Location loc, Action action, Portal portal);

}
