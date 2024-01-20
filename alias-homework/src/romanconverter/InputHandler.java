package romanconverter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    protected Integer getNumberInput() {
        try {
            Integer result = scanner.nextInt();
            if (0 < result && result < 4000){
                return result;
            } else {
                System.out.println("The input must be between 0 and 4000");
                System.out.println("Let's try again...");
                return null;
            }
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("The input must be a whole number");
            System.out.println("Let's try again...");
        } catch (Exception exception) {
            System.out.println("Error occurred while receiving input");
        }
        return null;
    }

    protected Boolean isNextNumber() {
        String result = scanner.next();
        return result.equalsIgnoreCase("Y");
    }
}
