package conversion;

import java.util.HashMap;
import java.util.Map;

public class TurkishNumberConversionServiceImpl /*implements NumberConversionService*/ {

	/*
	private static final Map<String, Integer> numberWords = new HashMap<>();
	private static final Map<Integer, String> wordNumbers = new HashMap<>();

	// constructor
	public TurkishNumberConversionServiceImpl() {
	}

	static {
		// Populate the map with Turkish number words and their numeric values
		numberWords.put("sıfır", 0);
		numberWords.put("bir", 1);
		//
		numberWords.put("iki", 2);
		numberWords.put("üç", 3);
		numberWords.put("dört", 4);
		numberWords.put("beş", 5);
		numberWords.put("altı", 6);
		numberWords.put("yedi", 7);
		numberWords.put("sekiz", 8);
		numberWords.put("dokuz", 9);
		//
		numberWords.put("on", 10);
		numberWords.put("yirmi", 20);
		numberWords.put("otuz", 30);
		numberWords.put("kırk", 40);
		numberWords.put("elli", 50);
		numberWords.put("altmış", 60);
		numberWords.put("yetmiş", 70);
		numberWords.put("seksen", 80);
		numberWords.put("doksan", 90);
		//
		numberWords.put("yüz", 100);
		numberWords.put("bin", 1000);
		numberWords.put("milyon", 1000000);
		numberWords.put("milyar", 1000000000);
		//
		//
		wordNumbers.put(0, "sıfır");
		wordNumbers.put(1, "bir");
		wordNumbers.put(2, "iki");
		wordNumbers.put(3, "üç");
		wordNumbers.put(4, "dört");
		wordNumbers.put(5, "beş");
		wordNumbers.put(6, "altı");
		wordNumbers.put(7, "yedi");
		wordNumbers.put(8, "sekiz");
		wordNumbers.put(9, "dokuz");
		//
		wordNumbers.put(10, "on");
		wordNumbers.put(20, "yirmi");
		wordNumbers.put(30, "otuz");
		wordNumbers.put(40, "kırk");
		wordNumbers.put(50, "elli");
		wordNumbers.put(60, "altmış");
		wordNumbers.put(70, "yetmiş");
		wordNumbers.put(80, "seksen");
		wordNumbers.put(90, "doksan");
		//
		wordNumbers.put(100, "yüz");
		wordNumbers.put(1000, "bin");
		wordNumbers.put(1000000, "milyon");
		wordNumbers.put(1000000000, "milyar");
	}

	public int toNumber(String text) throws NumberFormatException {
		String[] parts = text.split("\\s+");
		int number = 0;
		int lastValue = 0;

		for (String part : parts) {
			if (!numberWords.containsKey(part)) {
				throw new NumberFormatException("Invalid Turkish number: " + part);
			}
			int value = numberWords.get(part);

			if (value == 100) {
				if (lastValue == 0)
					lastValue = 1;
				number += lastValue * value;
				lastValue = 0;
			} else if (value < 100) {
				if (lastValue != 0)
					throw new NumberFormatException("Invalid sequence: " + lastValue + " " + value);
				lastValue = value;
			} else {
				number += value;
				lastValue = 0;
			}
		}
		number += lastValue;
		System.out.println("Number: " + number);
		return number;
	}

	private static final String[] ONES = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine" };

	private static final String[] TEENS = { "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen",
			"Seventeen", "Eighteen", "Nineteen" };

	private static final String[] TENS = { "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty",
			"Ninety" };

	private static final String[] PLACE = { "", "Thousand", "Million", "Billion", "Trillion" };

	public static String spellNumber(double number) {
		int dollars = (int) number;
		int cents = (int) ((number - dollars) * 100);

		String dollarsInWords = toText(dollars, 1);
		String centsInWords = toText(cents, 1);

		return dollarsInWords + " Dollars and " + centsInWords + " Cents";
	}

	public static String toText(int number, int languageIndex) {
		//System.out.println("Number: " + number);
		String words = "";

		String numStr = String.valueOf(number);
		int length = numStr.length();

		int count = 1;
		int index = 0;

		while (length > 0) {
			String temp = getHundreds(numStr.substring(length - 3 < 0 ? 0 : length - 3, length));
			if (!temp.isEmpty()) {
				words = temp + PLACE[count] + " " + words;
			}
			length -= 3;
			count++;
		}

		if (words.isEmpty()) {
			words = "No";
		}

		return words.trim();
	}
	
	private static String getHundreds(String number) {
		String result = "";

		if (!number.equals("000")) {
			int num = Integer.parseInt(number);
			int hundred = num / 100;
			int ten = num % 100;
			int one = num % 10;

			if (hundred != 0) {
				result += ONES[hundred] + " Hundred ";
			}

			if (ten != 0) {
				if (ten < 10) {
					result += ONES[ten] + " ";
				} else if (ten < 20) {
					result += TEENS[ten - 10] + " ";
				} else {
					result += TENS[ten / 10] + " ";
					if (one != 0) {
						result += ONES[one] + " ";
					}
				}
			}
		}

		return result;
	}
	
	
	 public static String toText(int number, int languageIndex) {
	    String words = "";

	    if (number == 0) {
	        return "Zero";
	    }

	    if (number < 0) {
	        words = "Negative ";
	        number = Math.abs(number);
	    }

	    int count = 0;

	    while (number > 0) {
	        if (number % 1000 != 0) {
	            String temp = convertLessThanOneThousand(number % 1000);
	            words = temp + PLACE[count] + " " + words;
	        }
	        number /= 1000;
	        count++;
	    }

	    return words.trim();
	}

	private static String convertLessThanOneThousand(int number) {
	    String current;

	    if (number % 100 < 20) {
	        current = ONES[number % 100];
	        number /= 100;
	    } else {
	        current = ONES[number % 10];
	        number /= 10;

	        current = TENS[number % 10] + current;
	        number /= 10;
	    }
	    if (number == 0) return current;
	    return ONES[number] + " Hundred" + current;
	}*/
}
