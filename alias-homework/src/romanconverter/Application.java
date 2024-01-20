package romanconverter;

public class Application {
    public static void main(String[] args) {
        Application application = new Application();
        application.convert();
    }

    private void convert() {
        boolean applicationRunning = true;
        while (applicationRunning) {
            InputHandler inputHandler = new InputHandler();
            System.out.println("Give me an arabic number to be turned into a roman numeral");
            Integer userInput = inputHandler.getNumberInput();
            System.out.println(userInput);
            if (userInput != null){
                System.out.println("Type 'Y' if you want to convert another number");
                if (!inputHandler.isNextNumber()) {
                    System.out.println("Shutting down application...");
                    applicationRunning = false;
                }
            }
        }
    }
}