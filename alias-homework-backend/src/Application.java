import converter.Converter;
import inputhandler.InputHandler;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException, InterruptedException {
        Application application = new Application();
        application.convert();
    }

    private void convert() throws IOException, InterruptedException {
        Converter converter = new Converter();
        boolean applicationRunning = true;
        while (applicationRunning) {
            InputHandler inputHandler = new InputHandler();
            System.out.println("Give me input :\n1. To use the application's converter\n2. To use API to convert\n3. To test application with API");
            Integer selectedMenuPoint = inputHandler.getNumberInput(true);
            if (selectedMenuPoint != null) {
                System.out.println("Give me an arabic number to be turned into a roman numeral");
                Integer userInput = inputHandler.getNumberInput(false);
                if (userInput != null) {
                    String convertedResult;
                    switch (selectedMenuPoint) {
                        case 1 -> convertedResult = converter.convert(userInput);
                        case 2 -> convertedResult = converter.convertUsingApi(userInput);
                        default -> convertedResult = converter.convertTest(userInput);
                    }
                    System.out.println("The converted result is " + convertedResult);
                    System.out.println("Type 'Y' if you want to convert another number");
                    if (!inputHandler.isTrueInput()) {
                        System.out.println("Shutting down application...");
                        applicationRunning = false;
                    }
                }
            }
        }
    }
}