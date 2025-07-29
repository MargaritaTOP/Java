package HomeWork_1;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CollectionProcessor {
    // числа, делящиеся на 3 или 5
    public static List<Integer> getDivisibleBy3Or5(List<Integer> collection) {
        List<Integer> result = new ArrayList<>(collection);
        result.removeIf(num -> !isDivisibleBy3Or5(num));
        return result;
    }
    // простые числа
    public static List<Integer> getPrimes(List<Integer> collection) {
        List<Integer> result = new ArrayList<>(collection);
        result.removeIf(num -> !isPrime(num));
        return result;
    }
    public static int getMax(List<Integer> collection) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Коллекция не может быть пустой");
        }
        return Collections.max(collection);
    }
    public static int getMin(List<Integer> collection) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Коллекция не может быть пустой");
        }
        return Collections.min(collection);
    }
    public static double getAverage(List<Integer> collection) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Коллекция не может быть пустой");
        }
        int sum = 0;
        for (int num : collection) {
            sum += num;
        }
        return (double) sum / collection.size();
    }
    private static boolean isDivisibleBy3Or5(int number) {
        return number % 3 == 0 || number % 5 == 0;
    }
    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        if (number % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}