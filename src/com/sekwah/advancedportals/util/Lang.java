package com.sekwah.advancedportals.util;

import com.google.common.collect.Maps;
import com.sekwah.advancedportals.AdvancedPortalsPlugin;

import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

/**
 * @author sekwah41
 *
 * The language translation file for the game. Will always load english first
 * so that if the translations are missing any then they are still readable and can then be translated.
 * (Its better than a raw translate string)
 *
 * TODO add a loaddefault where it only loads from the plugins version of the data rather than paying attention to any
 * possible changed versions in the lang folder.
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
            //URL url = lang.getClass().getClassLoader().getResource("lang/" + fileName + ".lang");
            //System.out.println(url);
            //Map<String, String> newLangMap = lang.parseLang(url.openStream());
            InputStream stream = loadResource(lang, "lang/" + fileName + ".lang");
            if(stream != null){
                Map<String, String> newLangMap = lang.parseLang(stream);
                lang.languageMap.putAll(newLangMap);
            }
        }
        catch(NullPointerException e){
            e.printStackTrace();
            AdvancedPortalsPlugin.getInstance().getLogger().warning("Could not load " + fileName + ".lang The file does" +
                    "not exist or there has been an error reading the file. Canceled loading language file.");
        }
    }

    /**
     * A method to try to grab the files from the plugin and if its in the plugin folder load from there instead.
     *
     * TODO add loading from the plugin folder first rather than straight from the plugin.
     *
     * @param lang
     * @param location
     * @return
     */
    private static InputStream loadResource(Lang lang, String location) {
        try{
            return lang.getClass().getClassLoader().getResourceAsStream(location);
        }
        catch(NullPointerException e){
            e.printStackTrace();
            AdvancedPortalsPlugin.getInstance().getLogger().warning("Could not load " + location + ". The file does" +
                    "not exist or there has been an error reading the file.");
            return null;
        }
    }

    public static void loadLanguage(String fileName){
        injectTranslations(instance, fileName);
    }

    private Map<String, String> parseLang(InputStream inputStream){
        Scanner scanner = new Scanner(inputStream);
        String line = scanner.nextLine();
        while(line != null){
            System.out.println(line);
            line = scanner.nextLine();
            // TODO add split code at the first = and also conversion of strings/codes which are constants like colors.
        }
        return null;
    }


    public static String translate(String s) {
        if(instance.languageMap.containsKey(s)){
            return instance.languageMap.get(s);
        }
        else{
            return s;
        }
    }
}
