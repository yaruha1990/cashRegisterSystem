package ua.training.model.services;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp {

    public static final String LETTERS_DIGITS_REGEXP = "([a-zA-Zа-яА-ЯіІїЇєЄ0-9]){1,50}";
    public static final String VENDOR_CODE_REGEXP = "([0-9]){1,20}";

    public boolean isValidInput(String patternString, String checkedString){
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(checkedString);
        return matcher.matches();
    }

}
