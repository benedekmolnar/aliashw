package romannumeralconverter.inputhandler;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputHandler {
    private final List<Integer> menuNumbersValidationList;
    private final Integer lowestPossibleNumber;
    private final Integer highestPossibleNumber;
    private final Scanner scanner;

    public InputHandler() {
        this.menuNumbersValidationList = List.of(1, 2, 3);
        this.lowestPossibleNumber = 1;
        this.highestPossibleNumber = 3999;
        this.scanner = new Scanner(System.in);
    }

    public Integer getNumberInput(Boolean isMenu) {
        try {
            Integer input = scanner.nextInt();
            if (isMenu) {
                if (menuNumbersValidationList.contains(input)) {
                    return input;
                } else {
                    System.out.println("The input must be one of " + menuNumbersValidationList);
                    System.out.println("Let's try again...");
                    return null;
                }
            } else {
                if (lowestPossibleNumber <= input && input <= highestPossibleNumber) {
                    return input;
                } else {
                    System.out.println("The input must be between " + lowestPossibleNumber + " and " + highestPossibleNumber);
                    System.out.println("Let's try again...");
                    return null;
                }
            }
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("The input must be a whole number");
            System.out.println("Let's try again...");
        } catch (Exception exception) {
            System.out.println("Error occurred while receiving input");
        }
        return null;
    }

    public Boolean isTrueInput() {
        return scanner.next().equalsIgnoreCase("Y");
    }
}
