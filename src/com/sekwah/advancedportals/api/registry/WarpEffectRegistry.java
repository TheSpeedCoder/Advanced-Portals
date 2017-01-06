package com.sekwah.advancedportals.api.registry;

import com.sekwah.advancedportals.AdvancedPortalsPlugin;
import com.sekwah.advancedportals.api.effect.WarpEffect;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sekwah41
 */
public class WarpEffectRegistry {


    private Map<String, WarpEffect> visualEffects = new HashMap();

    private Map<String, WarpEffect> soundEffects = new HashMap();

    private static final WarpEffectRegistry instance = new WarpEffectRegistry();

    /**
     * Register a new warp effect.
     *
     * @param name
     * @param effect
     * @return if the effect was registered
     */
    public static boolean registerEffect(String name, WarpEffect effect) {
        if(name == null){
            return false;
        }
        Map<String, WarpEffect> list = null;
        switch (effect.getType()){
            case SOUND:
                list = instance.soundEffects;
                break;
            case VISUAL:
                list = instance.visualEffects;
                break;
            default:
                AdvancedPortalsPlugin.logWarning(effect.getType().toString()
                        + " effect type not recognised");
                return false;
        }
        if(list.containsKey(name)){
            return false;
        }
        list.put(name, effect);
        return true;
    }

    public static WarpEffect getEffect(String name, WarpEffect.Type type){
        Map<String, WarpEffect> list = null;
        switch (type){
            case SOUND:
                list = instance.soundEffects;
                break;
            case VISUAL:
                list = instance.visualEffects;
                break;
            default:
                AdvancedPortalsPlugin.logWarning(type.toString()
                        + " effect type not recognised");
                return null;
        }
        if(list.containsKey(name)) {
            return list.get(name);
        }
        else{
            AdvancedPortalsPlugin.logWarning("No effect of type:"
                    + type.toString() + " was registered with the name: " + name);
            return null;
        }
    }

}
