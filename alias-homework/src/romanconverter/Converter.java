package romanconverter;

import java.util.List;
import java.util.Map;

public class Converter {
    private Map<Integer, String> romanDictionary;

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
        Integer digitsListSize = digitsList.size();
        for (int i = 0; i < digitsListSize; i++) {
            String digitToConvert = digitsList.get(i);
            result += convertDigits(Integer.parseInt(digitToConvert), digitsListSize - i);
        }
        System.out.println(result);
        return result;
    }

    private String convertDigits(Integer number, Integer decimalPlace) {
        String result = "";
        switch (decimalPlace) {
            case 4 -> result += romanDictionary.get(1000).repeat(number);
            case 3 -> {
                if (number >= 5 && number < 9) {
                    result += romanDictionary.get(500);
                    result += romanDictionary.get(100).repeat(number - 5);
                } else if (number == 9) {
                    result += romanDictionary.get(100);
                    result += romanDictionary.get(1000);
                } else {
                    result += romanDictionary.get(100).repeat(number);
                }
            }
            case 2 -> {
                if (number >= 5 && number < 9) {
                    result += romanDictionary.get(50);
                    result += romanDictionary.get(10).repeat(number - 5);
                } else if (number == 9) {
                    result += romanDictionary.get(10);
                    result += romanDictionary.get(100);
                } else {
                    result += romanDictionary.get(10).repeat(number);
                }
            }
            default -> {
                if (number >= 5 && number < 9) {
                    result += romanDictionary.get(5);
                    result += romanDictionary.get(1).repeat(number - 5);
                } else if (number == 9) {
                    result += romanDictionary.get(1);
                    result += romanDictionary.get(10);
                } else {
                    result += romanDictionary.get(10).repeat(number);
                }
            }
        }
        return result;
    }
}
