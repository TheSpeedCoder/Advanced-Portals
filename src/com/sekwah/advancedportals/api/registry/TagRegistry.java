package com.sekwah.advancedportals.api.registry;

import com.sekwah.advancedportals.AdvancedPortalsPlugin;
import com.sekwah.advancedportals.api.warphandler.TagHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * Allows a portal to register a tag and add a handler. If a plugin wants to add functionality
 * to someone elses tag then they should use the events.
 *
 * TODO take a look and rewrite parts because this was written a fair bit before the rest.
 *
 * @author sekwah41
 */
public class TagRegistry {

    // May not be needed
    private ArrayList<String> tags = new ArrayList();

    private Map<String, TagHandler.Activation> activationHandlers = new HashMap();

    private Map<String, TagHandler.Creation> creationHandlers = new HashMap();

    private Map<String, TagHandler.TagStatus> statusHandlers = new HashMap();

    // TODO the event can be used for general data detection and management, but use a TagHandler to make it so they can register
    // the individual class to handle.
    private static TagRegistry instance = new TagRegistry();

    /**
     * @return if the tag has been registered or if it already exists.
     */
    public boolean registerTag(String tag, TagHandler tagHandler) {

        if (tag == null) {
            AdvancedPortalsPlugin.getInstance().getLogger().warning("A tag can not be null.");
            return false;
        }

        if (instance.tags.contains(tag)) {
            AdvancedPortalsPlugin.getInstance().getLogger().warning("There can only be one handler per tag.");
            return false;
        }

        tags.add(tag);

        if (tagHandler != null && !(tagHandler instanceof TagHandler.Activation) && !(tagHandler instanceof TagHandler.TagStatus) &&
                !(tagHandler instanceof TagHandler.Creation)) {
            AdvancedPortalsPlugin.getInstance().getLogger().warning("Error with tag: " + tag + ". A tag handler must implement one of the handlers. Not just extend.");
            if (tagHandler instanceof TagHandler.Activation) {
                instance.activationHandlers.put(tag, (TagHandler.Activation) tagHandler);
            }
            if (tagHandler instanceof TagHandler.TagStatus) {
                instance.statusHandlers.put(tag, (TagHandler.TagStatus) tagHandler);
            }
            if (tagHandler instanceof TagHandler.Creation) {
                instance.creationHandlers.put(tag, (TagHandler.Creation) tagHandler);
            }
        }
        return true;
    }


}
