package GUI;
import conversion.EnglishNumberConversionServiceImpl;
import conversion.TurkishNumberConversionServiceImpl;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {
    private static ResourceBundle messages = ResourceBundle.getBundle("Messages", Locale.getDefault());

    public static void setLocale(Locale locale) {
        messages = ResourceBundle.getBundle("Messages", locale);
    }

    public static String getString(String key) {
        return messages.getString(key);
    }
    
    public static long toNumber(String text, int languageIndex) { // 0 for English, 1 for Turkish
    	
        /*if (languageIndex == 1) {
            TurkishNumberConversionServiceImpl converter = new TurkishNumberConversionServiceImpl();
            return converter.toNumber(text);
        } else if (languageIndex == 0) {
            return Integer.parseInt(text);
        }*/
    	//System.out.println("toNumber: " + text);
    	EnglishNumberConversionServiceImpl converter = new EnglishNumberConversionServiceImpl();
        long result = converter.toNumber(text);
        
        return result;
    }


	public static String toText(long number, int languageIndex) {
		/*if (languageIndex == 1) {
			TurkishNumberConversionServiceImpl converter = new TurkishNumberConversionServiceImpl();
			return converter.toText(number, 0);
		} else if (languageIndex == 0) {
			return Integer.toString(number);
		}*/
		
		EnglishNumberConversionServiceImpl converter = new EnglishNumberConversionServiceImpl();
		String result = converter.toText(number);

		return result;
        
    }
}
