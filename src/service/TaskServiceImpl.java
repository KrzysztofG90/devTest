package service;

import model.UserInput;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
        int[][] pairsOfVertices = new int[inputNumbers.get(0)][];
        inputNumbers.remove(0);

        for (int i = 0; i < inputNumbers.size() / 2 ; i++) {
            for (int j = 0; j < inputNumbers.size(); j += 2) {
                pairsOfVertices[i] = new int[]{inputNumbers.get(j), inputNumbers.get(j + 1)};
                i++;
            }
        }

        List<int[]> graphs = Arrays.stream(findPairs(pairsOfVertices)).collect(Collectors.toList());
        System.out.println("There are `" + graphs.size() + "` separate graphs at the input");
        System.out.println("And they are:");
        for (int i = 0; i < graphs.size(); i++) {
            System.out.print((i + 1) + ". ");
            System.out.println(Arrays.toString(graphs.get(i)));
        }
        System.out.println("--------------------------------------");
    }


    private int[][] findPairs(int[][] pairs) {
        boolean foundMore = true;
        while (foundMore) {
            foundMore = false;
            for (int i = 0; i < pairs.length; i++) {
                for (int j = 0; j < pairs.length; j++) {
                    for (int k = 0; k < pairs[i].length; k++) {
                        if (i != j) {
                            if (pairs[i][k] == pairs[j][0]) {
                                Set<Integer> distinctVertices = new HashSet<>();
                                for (int m = 0; m < pairs[i].length; m++) {
                                    distinctVertices.add(pairs[i][m]);
                                }
                                for (int m = 0; m < pairs[j].length; m++) {
                                    distinctVertices.add(pairs[j][m]);
                                }
                                pairs[i] = distinctVertices.stream().mapToInt(v -> v).toArray();
                                List<int[]> listOfPairs = Arrays.stream(pairs).collect(Collectors.toList());
                                listOfPairs.remove(pairs[j]);
                                pairs = listOfPairs.toArray(new int[0][]);
                                foundMore = true;
                                if (j >= pairs.length) break;
                            }
                        }
                    }
                }
            }
        }
        return pairs;
    }
}