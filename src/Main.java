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
        Set<Integer> distinctNumbers = new LinkedHashSet<>(numbers);
        int max = distinctNumbers
                .stream()
                .mapToInt(n -> n)
                .max().orElseThrow(NoSuchElementException::new);
        int min = distinctNumbers
                .stream()
                .mapToInt(n -> n)
                .min().orElseThrow(NoSuchElementException::new);
        distinctNumbers
                .stream()
                .sorted()
                .forEach(n -> System.out.print(n + " "));
        System.out.println("\ncount: " + numbers.size());
        System.out.println("distinct: " + distinctNumbers.size());
        System.out.println("min: " + min);
        System.out.println("max: " + max);
    }
}
