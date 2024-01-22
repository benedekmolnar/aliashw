package romannumeralconverter.converter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.stream.IntStream;

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

    public String convertTest(Integer input) throws IOException, InterruptedException {
        String appResult = convert(input);
        String apiResult = convertUsingApi(input);
        String mainResult = appResult + " (APP) - " + apiResult + " (API)\n";
        return appResult.equals(apiResult) ? mainResult + "The results are identical" : mainResult + "The results are not identical";
    }

    public String convertUsingApi(Integer input) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.funtranslations.com/translate/roman-numerals.json"))
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .method("POST", HttpRequest.BodyPublishers.ofString("text=" + input.toString()))
                .build();
        HttpResponse<?> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            List<String> responseBodyList = List.of(response.body().toString().split(" "));
            OptionalInt translatedIndex = IntStream.range(0, responseBodyList.size()).filter(element -> responseBodyList.get(element).contains("translated")).findFirst();
            String result = responseBodyList.get(translatedIndex.getAsInt() + 1);
            return result.substring(0, result.length() - 2).replace("\"", "");
        } else {
            System.out.println("Error occurred during API call");
            return null;
        }
    }

    public String convert(Integer input) {
        String result = "";
        List<String> digitsList = List.of(input.toString().split(""));
        int digitsListSize = digitsList.size();
        for (int i = 0; i < digitsListSize; i++) {
            String digitToConvert = digitsList.get(i);
            result += convertDigits(Integer.parseInt(digitToConvert), digitsListSize - i);
        }
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
            if (arabicNumber == 4) {
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
