package com.sekwah.advancedportals.util;

import com.google.common.collect.Maps;
import com.sekwah.advancedportals.AdvancedPortalsPlugin;

import java.io.InputStream;
import java.util.Map;

/**
 * @author sekwah41
 *
 * The language translation file for the game. Will always load english first
 * so that if the translations are missing any then they are still readable and can then be translated.
 * (Its better than a raw translate string)
 */
public class Lang {

    public static final String DEFAULT_LANG = "en_GB";

    public final Map<String, String> languageMap = Maps.<String, String>newHashMap();

    public static final Lang instance = new Lang();

    public Lang(){
        injectTranslations(this, DEFAULT_LANG);
    }

    private static void injectTranslations(Lang lang, String fileName) {
        try{
            InputStream inputStream = lang.getClass().getClassLoader().getResourceAsStream(fileName + ".lang");
            Map<String, String> newLangMap = lang.parseLang(inputStream);

        }
        catch(NullPointerException e){
            e.printStackTrace();
            AdvancedPortalsPlugin.getInstance().getLogger().warning("Could not load " + fileName + ". The file does" +
                    "not exist or there has been an error reading the file. Canceled loading language file.");
        }
    }

    public static void loadLanguage(String fileName){
        injectTranslations(instance, fileName);
    }

    private Map<String, String> parseLang(InputStream inputStream) {
        return null;
    }


}
