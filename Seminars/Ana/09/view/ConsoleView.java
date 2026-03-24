package view;

import java.util.Scanner;

public class ConsoleView extends AbstractView {
    @Override
    public void show() {
        System.out.println("Console Mode On");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.isEmpty())
                break;
            fireInputChanged(input);
        }
    }

    @Override
    public void displayChanged(String newDisplay) {
        System.out.println(newDisplay);
    }
}