package com.sekwah.advancedportals.api.registry;

import com.sekwah.advancedportals.AdvancedPortalsPlugin;
import com.sekwah.advancedportals.api.warphandler.TagHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Allows a portal to register a tag and add a handler. If a plugin wants to add functionality
 * to someone elses tag then they should use the events.
 *
 * @author sekwah41
 */
public class TagRegistry {

    // TODO the event can be used for general data detection and management, but use a TagHandler to make it so they can register
    // the individual class to handle.
    private static TagRegistry instance = new TagRegistry();
    // May not be needed
    private ArrayList<String> tags = new ArrayList();
    /**
     * Description of tags for help commands
     */
    private Map<String, String> tagDesc = new HashMap();
    private Map<String, TagHandler.Activation> activationHandlers = new HashMap();
    private Map<String, TagHandler.Creation> creationHandlers = new HashMap();
    private Map<String, TagHandler.TagStatus> statusHandlers = new HashMap();

    public static boolean registerTag(String tag, String desc, TagHandler tagHandler) {
        if (registerTag(tag, tagHandler)) {
            instance.tagDesc.put(tag, desc);
        }
        return false;
    }


    /**
     * It is reccomended that you use the taghandlers to add tag functionality. However
     * if needed such as extra data for a tag then this is here.
     *
     * @param tag
     * @return
     */
    public static boolean registerTag(String tag) {
        if (tag.contains(" ")) {
            AdvancedPortalsPlugin.getInstance().getLogger().warning("The tag '" + tag + "' is invalid as it contains spaces.");
            return false;
        }
        if (instance.tags.contains(tag)) {
            AdvancedPortalsPlugin.getInstance().getLogger().warning("The tag " + tag + " has already been registered.");
            return false;
        }
        instance.tags.add(tag);
        return true;
    }

    /**
     * Same as registerTag(String tag) but allows a description to be added.
     *
     * @param tag  Tag to be used on command line
     * @param desc
     * @return
     */
    public static boolean registerTag(String tag, String desc) {
        if (registerTag(tag)) {
            instance.tagDesc.put(tag, desc);
            return true;
        }
        return false;
    }

    /**
     * @return if the tag has been registered or if it already exists.
     */
    public static boolean registerTag(String tag, TagHandler tagHandler) {

        if (tag == null) {
            AdvancedPortalsPlugin.getInstance().getLogger().warning("A tag cannot be null.");
            return false;
        }

        if (!registerTag(tag)) {
            return false;
        }

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
