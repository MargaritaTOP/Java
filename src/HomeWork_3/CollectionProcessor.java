package HomeWork_3;

import java.util.ArrayList;
import java.util.Collections;

public class CollectionProcessor {
    public static void processCollection(ArrayList<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Коллекция не может быть пустой");
        }

        ArrayList<Integer> divisibleNumbers = new ArrayList<>();
        ArrayList<Integer> primeNumbers = new ArrayList<>();

        for (Integer num : numbers) {
            // Числа, делящиеся на 3 или 5
            if (num % 3 == 0 || num % 5 == 0) {
                divisibleNumbers.add(num);
            }
            // Простые числа
            if (isPrime(num)) {
                primeNumbers.add(num);
            }
        }

        int max = Collections.max(numbers);
        int min = Collections.min(numbers);
        double average = calculateAverage(numbers);

        System.out.println("Числа, делящиеся на 3 или 5: " + divisibleNumbers);
        System.out.println("Простые числа: " + primeNumbers);
        System.out.println("Максимальное значение: " + max);
        System.out.println("Минимальное значение: " + min);
        System.out.printf("Среднее значение: %.2f\n", average);
    }

    private static boolean isPrime(int number) {
        if (number <= 1) return false;
        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) return false;
        }
        return true;
    }

    private static double calculateAverage(ArrayList<Integer> numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return (double) sum / numbers.size();
    }
}