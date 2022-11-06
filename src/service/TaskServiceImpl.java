package service;

import model.Graph;
import model.UserInput;

import java.io.IOException;
import java.util.*;

public class TaskServiceImpl implements TaskService {
    private UserInputService userInputService = new UserInputServiceImpl();

    @Override
    public void printSpecificInfoTask1() throws IOException {
        UserInput userInput = userInputService.getInputFromUser(false, "src/resources/input");
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
        UserInput userInput = userInputService.getInputFromUser(true, "src/resources/input");
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

    public void printNumberOfGraphsTask3() throws IOException {
        UserInput userInput = userInputService.getInputFromFile("src/resources/input2");
        List<Integer> inputNumbers = userInput.getNumbers();
        List<Integer> listOfVertices = getListOfUniqueVertices(inputNumbers);
        List<Graph> listOfGraphs = getListOfGraphs(listOfVertices);

        System.out.println("There are `" + listOfGraphs.size() + "` separate graphs at the input");
        System.out.println("And the are:");
        for (int i = 0; i < listOfGraphs.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(listOfGraphs.get(i));
        }
        System.out.println("--------------------------------------");
    }

    private List<Integer> getListOfUniqueVertices(List<Integer> numbers) {
        Set<Integer> vertices = new HashSet<>();
        for (int i = 0; i < numbers.size(); i++) {
            for (Integer number : numbers) {
                if (number == numbers.get(i) + 1) {
                    vertices.add(numbers.get(i));
                    vertices.add(number);
                }
            }
        }
        return new ArrayList<>(vertices);
    }

    private List<Graph> getListOfGraphs(List<Integer> listOfVertices) {
        List<Graph> listOfGraphs = new ArrayList<>();
        int countOfVertices = 0;

        for (int i = 0; i < listOfVertices.size(); i++) {
            if (i + 1 < listOfVertices.size()) {
                if (listOfVertices.get(i + 1) == listOfVertices.get(i) + 1) countOfVertices++;
                else {
                    listOfGraphs.add(new Graph(new ArrayList<>(listOfVertices.subList(i - countOfVertices, i + 1))));
                    countOfVertices = 0;
                }
            } else listOfGraphs.add(new Graph(new ArrayList<>(listOfVertices.subList(i - countOfVertices, i + 1))));
        }
        return listOfGraphs;
    }
}