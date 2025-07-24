package HomeWork_1;

import java.util.ArrayList;
import java.util.List;
public class NumberProcessor {
     public static class Result {
        public int[] divisibleBy3Or5;
        public int[] primes;
        public int max;
        public int min;
        public double average;
    }
    public static Result processArray(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Массив не может быть пустым");
        }
        Result result = new Result();
        List<Integer> divisibleList = new ArrayList<>();
        List<Integer> primesList = new ArrayList<>();
        int sum = 0;
        result.max = array[0];
        result.min = array[0];
        for (int num : array) {
            // Подсчет суммы для среднего
            sum += num;
            if (num > result.max) result.max = num;
            if (num < result.min) result.min = num;
            if (isDivisibleBy3Or5(num)) {
                divisibleList.add(num);
            }
            if (isPrime(num)) {
                primesList.add(num);
            }
        }
        result.divisibleBy3Or5 = divisibleList.stream().mapToInt(i -> i).toArray();
        result.primes = primesList.stream().mapToInt(i -> i).toArray();
        result.average = (double) sum / array.length;
        return result;
    }

    private static boolean isDivisibleBy3Or5(int number) {
        return number % 3 == 0 || number % 5 == 0;
    }

    private static boolean isPrime(int number) {
        if (number <= 1) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0) return false;
        }
        return true;
    }
}