package romanconverter;

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
            System.out.println("Type 'Y' if you wish to use API");
            Boolean useApi = inputHandler.isTrueInput();
            System.out.println("Give me an arabic number to be turned into a roman numeral");
            Integer userInput = inputHandler.getNumberInput();
            if (userInput != null) {
                String convertedResult = "";
                if (useApi) {
                    convertedResult = converter.convertUsingApi(userInput);
                } else {
                    convertedResult = converter.convert(userInput);
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