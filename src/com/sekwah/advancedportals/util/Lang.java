package com.sekwah.advancedportals.util;

import com.google.common.collect.Maps;
import com.sekwah.advancedportals.AdvancedPortalsPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Scanner;

/**
 * @author sekwah41
 *         <p>
 *         The language translation file for the game. Will always load english first
 *         so that if the translations are missing any then they are still readable and can then be translated.
 *         (Its better than a raw translate string)
 *         <p>
 *         TODO add a loaddefault where it only loads from the plugins version of the data rather than paying attention to any
 *         possible changed versions in the lang folder.
 */
public class Lang {

    public static final Lang instance = new Lang();
    public final Map<String, String> languageMap = Maps.newHashMap();
    private final String DEFAULT_LANG = "en_GB";

    public Lang() {
        injectTranslations(this, DEFAULT_LANG);
    }

    private void injectTranslations(Lang lang, String fileName) {
        try {
            //URL url = lang.getClass().getClassLoader().getResource("lang/" + fileName + ".lang");
            //System.out.println(url);
            //Map<String, String> newLangMap = lang.parseLang(url.openStream());
            InputStream stream = DefaultLoader.loadResource(lang, "lang/" + fileName + ".lang");
            if (stream != null) {
                Map<String, String> newLangMap = lang.parseLang(stream);
                if (newLangMap != null) {
                    lang.languageMap.putAll(newLangMap);
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            AdvancedPortalsPlugin.getInstance().getLogger().warning("Could not load " + fileName + ".lang The file does" +
                    "not exist or there has been an error reading the file. Canceled loading language file.");
        }
    }

    public void loadLanguage(String fileName) {
        injectTranslations(instance, fileName);
    }

    private Map<String, String> parseLang(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        String line = getNextLine(scanner);
        Map<String, String> newMap = Maps.newHashMap();
        while (scanner != null && line != null) {
            //System.out.println(line);
            if (!line.startsWith("#") && line.indexOf('=') > -1) {
                int split = line.indexOf('=');
                String key = line.substring(0, split);
                String value = line.substring(split + 1, line.length());
                newMap.put(key, value);
            }
            line = getNextLine(scanner);
            // TODO add split code at the first = and also conversion of strings/codes which are constants like colors.
        }
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return newMap;
    }

    private String getNextLine(Scanner scanner) {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return null;
    }


    public String translate(String s) {
        if (instance.languageMap.containsKey(s)) {
            return instance.languageMap.get(s);
        } else {
            return s;
        }
    }

    public String translateInsertVariables(String s, String... args) {
        String translation = translate(s);
        for (int i = 0; i < args.length; i++) {
            translation = translation.replaceAll("%" + i + "$s", args[i]);
        }
        return translation;
    }

    public String translateInsertVariablesColor(String s, String... args) {
        String translation = translateColor(s);
        for (int i = 0; i < args.length; i++) {
            translation = translation.replaceAll("%" + i + "$s", args[i]);
        }
        return translation;
    }

    public String translateColor(String s) {
        String translation = translate(s);
        translation = translation.replaceAll("\\\\u00A7", "\u00A7");
        return translation;
    }
}
