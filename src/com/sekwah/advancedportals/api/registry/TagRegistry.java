package com.sekwah.advancedportals.api.registry;

import com.sekwah.advancedportals.AdvancedPortalsPlugin;
import com.sekwah.advancedportals.api.warphandler.TagHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by on 25/07/2016.
 *
 * @author sekwah41
 */
public class TagRegistry {

    private ArrayList<String> tags = new ArrayList();

    private Map<String, TagHandler.Activation> tagActivation = new HashMap();

    private Map<String, TagHandler.Creation> tagCreation = new HashMap();

    private Map<String, TagHandler.TagStatus> tagStatus = new HashMap();

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
            return false;
        }

        tags.add(tag);

        if (tagHandler != null && !(tagHandler instanceof TagHandler.Activation) && !(tagHandler instanceof TagHandler.TagStatus) &&
                !(tagHandler instanceof TagHandler.Creation)) {
            AdvancedPortalsPlugin.getInstance().getLogger().warning("Error with tag: " + tag + ". A tag handler must implement one of the handlers. Not just extend.");
            if (tagHandler instanceof TagHandler.Activation) {
                instance.tagActivation.put(tag, (TagHandler.Activation) tagHandler);
            }
            if (tagHandler instanceof TagHandler.TagStatus) {
                instance.tagStatus.put(tag, (TagHandler.TagStatus) tagHandler);
            }
            if (tagHandler instanceof TagHandler.Creation) {
                instance.tagCreation.put(tag, (TagHandler.Creation) tagHandler);
            }
        }
        return true;
    }


}
