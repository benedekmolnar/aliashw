package romanconverter;

import java.util.List;
import java.util.Map;

public class Converter {
    private final Map<Integer, String> romanDictionary;

    public Converter() {
        this.romanDictionary = Map.of(
                1, "I",
                5, "V",
                10, "X",
                50, "L",
                100, "C",
                500, "D",
                1000, "M"
        );
    }

    protected String convert(Integer input) {
        String result = "";
        List<String> digitsList = List.of(input.toString().split(""));
        int digitsListSize = digitsList.size();
        for (int i = 0; i < digitsListSize; i++) {
            String digitToConvert = digitsList.get(i);
            result = convertDigits(Integer.parseInt(digitToConvert), digitsListSize - i);
        }
        System.out.println(result);
        return result;
    }

    private String convertDigits(Integer arabicNumber, Integer decimalPlace) {
        String result;
        switch (decimalPlace) {
            case 4 -> result = romanDictionary.get(1000).repeat(arabicNumber);
            case 3 -> result = processDigit(arabicNumber, 100);
            case 2 -> result = processDigit(arabicNumber, 10);
            default -> result = processDigit(arabicNumber, 1);
        }
        return result;
    }

    private String processDigit(Integer arabicNumber, Integer decimal) {
        String result = "";
        {
            if (arabicNumber == 4){
                result += romanDictionary.get(decimal);
                result += romanDictionary.get(decimal * 5);
            } else if (arabicNumber >= 5 && arabicNumber < 9) {
                result += romanDictionary.get(decimal * 5);
                result += romanDictionary.get(decimal).repeat(arabicNumber - 5);
            } else if (arabicNumber == 9) {
                result += romanDictionary.get(decimal);
                result += romanDictionary.get(decimal * 10);
            } else {
                result = romanDictionary.get(decimal).repeat(arabicNumber);
            }
        }
        return result;
    }
}
