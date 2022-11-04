import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Integer> givenNumbers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        boolean end = false;

        System.out.println("Input a list of numbers separated by a space");
        System.out.println("Confirm by pressing enter");
        while (!end) {
            System.out.println("Input numbers:");
            String input = scanner.nextLine();
            String[] tab = input.split(" ");

            boolean hasSigns = false;
            for (String s : tab) {
                if (!s.matches("[0-9]+")) {
                    System.out.println("Provide only numbers");
                    hasSigns = true;
                    break;
                } else givenNumbers.add(Integer.parseInt(s));
            }
            if (!hasSigns) end = true;
            else givenNumbers.clear();
        }
        scanner.close();
        printInfo(givenNumbers);
    }

    public static void printInfo(List<Integer> numbers) {
        List<Integer> distinctNumbers = getDistinctNumbers(numbers);
        printSortedNumbers(distinctNumbers);
        System.out.println("\ncount: " + numbers.size());
        System.out.println("distinct: " + distinctNumbers.size());
        printMinMaxNumber(numbers);
    }

    public static List<Integer> getDistinctNumbers(List<Integer> numbers) {
        List<Integer> tempList = new ArrayList<>();
        for (Integer number : numbers) {
            if (!tempList.contains(number)) {
                tempList.add(number);
            }
        }
        return tempList;
    }

    public static void printMinMaxNumber(List<Integer> numbers) {
        int min = numbers.get(0);
        int max = min;
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) < min) min = numbers.get(i);
            if (numbers.get(i) > max) max = numbers.get(i);
        }
        System.out.println("min = " + min);
        System.out.println("max = " + max);
    }

    public static void printSortedNumbers(List<Integer> numbers) {
        int[] tab = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            tab[i] = numbers.get(i);
        }
        int temp;
        for (int i = 0; i < tab.length; i++) {
            for (int j = i + 1; j < tab.length; j++) {
                if (tab[i] > tab[j]) {
                    temp = tab[i];
                    tab[i] = tab[j];
                    tab[j] = temp;
                }
            }
        }
        for (int number : tab) System.out.print(number + " ");
    }
}
