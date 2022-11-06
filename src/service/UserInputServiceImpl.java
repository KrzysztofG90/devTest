package service;

import model.UserInput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInputServiceImpl implements UserInputService {

    @Override
    public UserInput getInputFromFile(String fileSrc) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        List<Integer> numbers = new ArrayList<>();
        String line;
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileSrc))) {
            while ((line = fileReader.readLine()) != null) {
                Scanner sc = new Scanner(line);
                stringBuilder.append(sc.nextLine()).append(" ");
            }
        } catch (IOException e) {
            throw new IOException("Error processing the file.");
        }
        String[] lines = stringBuilder.toString().split(" ");
        for (String s : lines) {
            if (s.matches("[0-9]+")) numbers.add(Integer.parseInt(s));
        }
        return new UserInput(numbers, 13);
    }

    @Override
    public UserInput getInputFromKeyboard(boolean isSecondTask) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> givenNumbers = new ArrayList<>();
        boolean isEnd = false;
        int sum = 0;

        System.out.println("Input a list of numbers separated by a space.");
        if (isSecondTask) System.out.println("Then input the sum of the pairs which you want to find.");
        System.out.println("Confirm by pressing enter.");
        System.out.println("--------------------------------------");
        while (!isEnd) {
            if (givenNumbers.isEmpty()) {
                System.out.println("Input numbers:");
                String input = scanner.nextLine();
                String[] tab = input.split(" ");
                for (String s : tab) {
                    if (!s.matches("[0-9]+")) {
                        System.out.println("Provide only numbers!");
                        givenNumbers.clear();
                        break;
                    } else givenNumbers.add(Integer.parseInt(s));
                }
            } else {
                if (isSecondTask) {
                    System.out.println("Provide sum:");
                    String abc = scanner.nextLine();
                    if (abc.matches("[0-9]+")) {
                        sum = Integer.parseInt(abc);
                        isEnd = true;
                    } else System.out.println("Provide only one number!");
                } else isEnd = true;
            }
        }
        return new UserInput(givenNumbers, sum);
    }

    @Override
    public UserInput getInputFromUser(boolean isSecondTask, String fileSrc) throws IOException {
        Scanner scanner = new Scanner(System.in);
        UserInput userInput = new UserInput();
        boolean isChoosingType = true;
        while (isChoosingType) {
            System.out.println("Select an input type for numbers:");
            System.out.println("1. Enter '1' for input from keyboard");
            System.out.println("2. Enter '2' for input from file");
            System.out.println("--------------------------------------");
            String choice = scanner.nextLine();
            if (choice.matches("[0-9]+")) {
                switch (Integer.parseInt(choice)) {
                    case 1:
                        userInput = getInputFromKeyboard(isSecondTask);
                        isChoosingType = false;
                        break;
                    case 2:
                        userInput = getInputFromFile(fileSrc);
                        isChoosingType = false;
                        break;
                    default:
                        System.out.println("There is no such choice!");
                        break;
                }
            } else {
                System.out.println("Provide only digit!");
            }
        }
        return userInput;
    }
}
