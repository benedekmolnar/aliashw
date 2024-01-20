package romanconverter;

public class Converter {
    public static void main(String[] args) {
        Converter converter = new Converter();
        converter.convert();
    }

    private void convert() {
        InputHandler inputHandler = new InputHandler();
        boolean converterRunning = true;
        while (converterRunning) {
            System.out.println("Give me an arabic number to be turned into a roman numeral");
            Integer userInput = inputHandler.getUserInput();
            System.out.println(userInput);
            converterRunning = false;
        }
    }
}