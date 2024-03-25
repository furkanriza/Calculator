package conversion;

import java.util.HashMap;
import java.util.Map;

public class EnglishNumberConversionServiceImpl {
	
	// for text to number
	private static HashMap<String, Integer[]> numWords = new HashMap<>();
    
    // for number to text
    private static final String[] units = { "", "one", "two", "three", "four",
            "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
            "thirteen", "fourteen", "fifteen", "sixteen", "seventeen",
            "eighteen", "nineteen" };
    
    private static final String[] tens = { 
            "",         // 0
            "",         // 1
            "twenty",   // 2
            "thirty",   // 3
            "forty",    // 4
            "fifty",    // 5
            "sixty",    // 6
            "seventy",  // 7
            "eighty",   // 8
            "ninety"    // 9
    };

    public EnglishNumberConversionServiceImpl() {

    }   

    public static long toNumber(String textnum) throws IllegalArgumentException {
    	if (numWords.isEmpty()) {
			String[] units = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
					"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen",
					"nineteen" };

			String[] tens = { "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety" };

			String[] scales = { "hundred", "thousand", "million", "billion", "trillion" };

			numWords.put("and", new Integer[] { 1, 0 });
			for (int idx = 0; idx < units.length; idx++)
				numWords.put(units[idx], new Integer[] { 1, idx });
			for (int idx = 0; idx < tens.length; idx++)
				numWords.put(tens[idx], new Integer[] { 1, idx * 10 });
			for (int idx = 0; idx < scales.length; idx++)
				numWords.put(scales[idx], new Integer[] { (int) Math.pow(10, (idx * 3) == 0 ? 2 : idx * 3), 0 });
		}

		long current = 0;
		long result = 0;

		for (String word : textnum.split(" ")) {
			if (!numWords.containsKey(word)) {
				throw new IllegalArgumentException("Illegal word: " + word);
			}

			Integer[] scaleAndIncrement = numWords.get(word);
			long scale = scaleAndIncrement[0];
			long increment = scaleAndIncrement[1];

			current = current * scale + increment;
			if (scale > 100) {
				result += current;
				current = 0;
			}
		}
		
		long finalResult = result + current;
		System.out.println("toNumber: " + finalResult);
		return finalResult;
    }

	public static String toText(long num) {
		//System.out.println("toText: " + num);
		if (num < 0) {
            return "minus " + toText(-num);
        }

        if (num < 20) {
            return units[(int) num];
        }

        if (num < 100) {
            return tens[(int) (num / 10)] + ((num % 10 != 0) ? " " : "") + units[(int) (num % 10)];
        }

        if (num < 1000) {
            return units[(int) (num / 100)] + " hundred" + ((num % 100 != 0) ? " " : "") + toText(num % 100);
        }

        if (num < 1000000) {
            return toText(num / 1000) + " thousand" + ((num % 1000 != 0) ? " " : "") + toText(num % 1000);
        }

        if (num < 1000000000) {
            return toText(num / 1000000) + " million" + ((num % 1000000 != 0) ? " " : "") + toText(num % 1000000);
        }
        //System.out.println("toTextres: " + num);
        return toText(num / 1000000000) + " billion" + ((num % 1000000000 != 0) ? " " : "") + toText(num % 1000000000);
		
	}
}
