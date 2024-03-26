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

	public static String getString(String key, int languageIndex) {
		ResourceBundle messages;
		if (languageIndex == 1) {
			messages = ResourceBundle.getBundle("Messages_tr");
		} else {
			messages = ResourceBundle.getBundle("Messages_en");
		}
		return messages.getString(key);
	}

	public static long toNumber(String text, int languageIndex) { // 0 for English, 1 for Turkish

		if (languageIndex == 1) {
			return TurkishNumberConversionServiceImpl.toNumber(text);
		} else if (languageIndex == 0) {
			return EnglishNumberConversionServiceImpl.toNumber(text);

		}
		return Long.MAX_VALUE;
	}

	public static String toText(long number, int languageIndex) {

		if (languageIndex == 1) {
			return TurkishNumberConversionServiceImpl.toText(number, languageIndex);
		} else if (languageIndex == 0) {
			return EnglishNumberConversionServiceImpl.toText(number);
		}
		return null;
	}
}
