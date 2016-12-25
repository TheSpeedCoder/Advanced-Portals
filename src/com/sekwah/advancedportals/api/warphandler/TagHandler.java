package com.sekwah.advancedportals.api.warphandler;

import com.sun.istack.internal.NotNull;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

/**
 * Created by on 30/07/2016.
 *
 * @author sekwah41
 */
public class TagHandler {

    public interface Creation {

        /**
         * Example if the player does not have access to use a tag on the portal.
         *
         * @param player if null the portal has been created by the server or a plugin
         * @param activeData
         * @param argData
         * @return if the portal can be created.
         */
        boolean portalCreated(@Nullable Player player, @NotNull ActivationData activeData, @NotNull String argData);

        /**
         * Example if the player does not have access to remove the portal.
         *
         * @param player if null the portal has been destroyed by the server or a plugin
         * @param activeData
         * @param argData
         * @return if the portal can be destroyed.
         */
        boolean portalDestroyed(@Nullable Player player, @NotNull ActivationData activeData, @NotNull String argData);

    }

    public interface Activation {

        /**
         * Activates before the main part of portal activation.
         *
         * @param player
         * @param activeData
         * @param argData
         */
        void portalPreActivated(@NotNull Player player, @NotNull ActivationData activeData, @NotNull String argData);

        /**
         * Activates after portal activation
         *
         * @param player
         * @param activeData
         * @param argData
         */
        void portalPostActivated(@NotNull Player player, @NotNull ActivationData activeData, @NotNull String argData);

        /**
         * Activates if the portal is allowed from pre
         *
         * @param player
         * @param activeData
         * @param argData
         */
        void portalActivated(@NotNull Player player, @NotNull ActivationData activeData, @NotNull String argData);

    }

    public interface TagStatus {

        /**
         * If the user has access to add the tag
         *
         * @param player
         * @param activeData
         * @param argData
         * @return if the tag will be added.
         */
        boolean tagAdded(@Nullable Player player, @NotNull ActivationData activeData, @NotNull String argData);

        /**
         * If the user has access to remove the tag
         *
         * @param player
         * @param activeData
         * @param argData
         * @return if the tag will be removed.
         */
        boolean ragRemoved(@Nullable Player player, @NotNull ActivationData activeData, @NotNull String argData);

    }

}
