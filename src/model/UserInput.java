package model;

import java.util.List;

public class UserInput {
    private List<Integer> numbers;
    private int sum;

    public UserInput(List<Integer> numbers, int sum) {
        this.numbers = numbers;
        this.sum = sum;
    }

    public UserInput(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public UserInput() {
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}
