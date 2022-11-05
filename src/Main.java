import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Integer> givenNumbers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
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
        printPairsOfGivenSum(givenNumbers, sum);
    }

    public static void printPairsOfGivenSum(List<Integer> numbers, int sum) {
        System.out.println("Result:");
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
