import model.UserInput;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class UserInputServiceImpl implements UserInputService {
    @Override
    public UserInput getInputFromFile() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        List<Integer> numbers = new ArrayList<>();
        try (BufferedReader fileReader = new BufferedReader(new FileReader("src/resources/input"))) {
            while ((line = fileReader.readLine()) != null) {
                Scanner sc = new Scanner(line);
                stringBuilder.append(sc.nextLine().trim());
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
    public UserInput getInputFromKeyboard() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> givenNumbers = new ArrayList<>();
        boolean isEnd = false;
        int sum = 0;

        System.out.println("Input a list of numbers separated by a space.");
        System.out.println("Then input the sum of the pairs which you want to find.");
        System.out.println("Confirm by pressing enter.\n");
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
                System.out.println("Provide sum:");
                String abc = scanner.nextLine();
                if (abc.matches("[0-9]+")) {
                    sum = Integer.parseInt(abc);
                    isEnd = true;
                } else System.out.println("Provide only one number!");
            }
        }
        scanner.close();
        return new UserInput(givenNumbers, sum);
    }

    @Override
    public void printPairsOfGivenSum(int choice) throws IOException {
        UserInput userInput = new UserInput();
        switch (choice) {
            case 1:
                userInput = getInputFromKeyboard();
                break;
            case 2:
                userInput = getInputFromFile();
                break;
        }

        System.out.println("Result:");
        List<Integer> numbers = userInput.getNumbers();
        int sum = userInput.getSum();
        int numberOfPairs = 0;
        numbers.sort(Comparator.comparingInt(a -> a));
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(i) + numbers.get(j) == sum) {
                    numberOfPairs++;
                    System.out.println(numbers.get(i) + " " + numbers.get(j));
                }
            }
        }
        if (numberOfPairs == 0) System.out.println("There are no matching pairs.");
    }
}
