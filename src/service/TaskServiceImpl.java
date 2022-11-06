package service;

import model.UserInput;

import java.io.IOException;
import java.util.*;

public class TaskServiceImpl implements TaskService {
    private UserInputService userInputService = new UserInputServiceImpl();

    @Override
    public void printSpecificInfoTask1() throws IOException {
        UserInput userInput = userInputService.getInputFromUser(false);
        List<Integer> numbers = userInput.getNumbers();
        Set<Integer> distinctNumbers = new LinkedHashSet<>(numbers);
        int max = distinctNumbers
                .stream()
                .mapToInt(n -> n)
                .max().orElseThrow(NoSuchElementException::new);
        int min = distinctNumbers
                .stream()
                .mapToInt(n -> n)
                .min().orElseThrow(NoSuchElementException::new);
        System.out.println("--------------------------------------");
        System.out.println("Result:");
        distinctNumbers
                .stream()
                .sorted()
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\ncount: " + numbers.size());
        System.out.println("distinct: " + distinctNumbers.size());
        System.out.println("min: " + min);
        System.out.println("max: " + max);
        System.out.println("--------------------------------------");
    }

    @Override
    public void printPairsOfGivenSumTask2() throws IOException {
        UserInput userInput = userInputService.getInputFromUser(true);
        List<Integer> numbers = userInput.getNumbers();
        int sum = userInput.getSum();
        int numberOfPairs = 0;
        numbers.sort(Comparator.comparingInt(a -> a));
        System.out.println("--------------------------------------");
        System.out.println("Result:");
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                if (numbers.get(i) + numbers.get(j) == sum) {
                    numberOfPairs++;
                    System.out.println(numbers.get(i) + " " + numbers.get(j));
                }
            }
        }
        if (numberOfPairs == 0) System.out.println("There are no matching pairs.");
        System.out.println("--------------------------------------");
    }
}
