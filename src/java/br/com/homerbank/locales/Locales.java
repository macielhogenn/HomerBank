package br.com.homerbank.locales;

import java.util.Locale;
import java.util.ResourceBundle;

public class Locales {
    
    public static String get(String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("br.com.homerbank.locales.messages", Locale.getDefault());
        
        return resourceBundle.getString(key);
    }
}
