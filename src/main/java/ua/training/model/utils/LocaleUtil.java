package ua.training.model.utils;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleUtil {

    private ResourceBundle bundle;

    public LocaleUtil(String locale){
        if (locale.equalsIgnoreCase("ua")){
            bundle = ResourceBundle.getBundle("res_uk",new Locale("uk","UA"));
        } else if (locale.equalsIgnoreCase("en")){
            bundle = ResourceBundle.getBundle("res_en", new Locale("en","US"));
        }else if (locale.equalsIgnoreCase("url")){
            bundle = ResourceBundle.getBundle("urls_en", new Locale("en","US"));
        }
    }

    public String getText(String context){
        try {
            return new String(bundle.getString(context).getBytes( "ISO-8859-1"),"utf-8");
        }catch (IOException e){
            e.printStackTrace();
        }
        return "Bundle error";
    }
}
