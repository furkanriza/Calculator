package conversion;

import java.util.HashMap;
import java.util.Map;

public class TurkishNumberConversionServiceImpl{
	
	// for text to number
	private static final Map<String, Integer> turkishNumberMap = new HashMap<>();

	static {
		turkishNumberMap.put("sıfır", 0);
		turkishNumberMap.put("bir", 1);
		turkishNumberMap.put("iki", 2);
		turkishNumberMap.put("üç", 3);
		turkishNumberMap.put("dört", 4);
		turkishNumberMap.put("beş", 5);
		turkishNumberMap.put("altı", 6);
		turkishNumberMap.put("yedi", 7);
		turkishNumberMap.put("sekiz", 8);
		turkishNumberMap.put("dokuz", 9);
		turkishNumberMap.put("on", 10);
		turkishNumberMap.put("yirmi", 20);
		turkishNumberMap.put("otuz", 30);
		turkishNumberMap.put("kırk", 40);
		turkishNumberMap.put("elli", 50);
		turkishNumberMap.put("altmış", 60);
		turkishNumberMap.put("yetmiş", 70);
		turkishNumberMap.put("seksen", 80);
		turkishNumberMap.put("doksan", 90);
		turkishNumberMap.put("yüz", 100);
		turkishNumberMap.put("bin", 1000);
		turkishNumberMap.put("milyon", 1000000);
		turkishNumberMap.put("milyar", 1000000000);
	}
	
	// for number to text
    private static final String[] ONES = {"", "bir", "iki", "üç", "dört", "beş", "altı", "yedi", "sekiz", "dokuz"};
    private static final String[] TENS = {"", "on", "yirmi", "otuz", "kırk", "elli", "altmış", "yetmiş", "seksen", "doksan"};

	

	public static long toNumber(String text) throws NumberFormatException {
		String[] words = text.split("\\s+");
		int number = 0;
		int tempNumber = 0;
		for (String word : words) {
			int value = turkishNumberMap.get(word);
			if (value == 100) {
				tempNumber *= value;
			} else if (value > 100) {
				number += tempNumber * value;
				tempNumber = 0;
			} else {
				tempNumber += value;
			}
		}
		number += tempNumber;
		return number;
	}

	
	public static String toText(long number, int languageIndex) {
		if (number == 0) {
            return "sıfır";
        }
        if (number < 0) {
            return "negatif " + toText(-number, languageIndex);
        }
        if (number <= 999) {
            return convertLessThanOneThousand(number);
        }
        String result = "";
        String[] bigs = {"", "bin", "milyon", "milyar", "trilyon", "katrilyon"};
        int count = 0;
        while (number > 0) {
            if (number % 1000 != 0) {
                result = convertLessThanOneThousand(number % 1000) + " " + bigs[count] + " " + result;
            }
            number /= 1000;
            count++;
        }
        return result.trim();
	}
	
	 private static String convertLessThanOneThousand(long number) {
	        String result;
	        if (number % 100 < 10) {
	            result = ONES[(int) (number % 100)];
	            number /= 10;
	            if (number > 0) {
	                result = TENS[(int) (number % 10)] + " " + result;
	                number /= 10;
	            }
	        } else {
	            result = ONES[(int) (number % 10)];
	            number /= 10;
	            result = TENS[(int) (number % 10)] + " " + result;
	            number /= 10;
	        }
	        if (number > 0) {
	            result = ONES[(int) number] + " yüz " + result;
	        }
	        return result;
	    }
	
	
}
