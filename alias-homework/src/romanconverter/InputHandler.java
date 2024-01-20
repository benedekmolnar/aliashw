package romanconverter;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {
    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    protected Integer getUserInput(){
        try {
            return scanner.nextInt();
        } catch (InputMismatchException inputMismatchException){
            System.out.println("The input must be a whole number");
        } catch (Exception exception){
            System.out.println("Error occurred while receiving input");
        }
        return null;
    }
}
