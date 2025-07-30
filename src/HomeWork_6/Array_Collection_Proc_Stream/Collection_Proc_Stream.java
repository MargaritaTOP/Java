package HomeWork_6.Array_Collection_Proc_Stream;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Collection_Proc_Stream {
    // Максимальный предел для решета Эратосфена
    private static final int MAX_PRECOMPUTED = 1_000_000;
    // Массив для хранения простых чисел (true = простое, false = не простое)
    private static final boolean[] isPrimePrecomputed = new boolean[MAX_PRECOMPUTED + 1];

    // Инициализация решета Эратосфена
    static {
        Arrays.fill(isPrimePrecomputed, true);
        isPrimePrecomputed[0] = isPrimePrecomputed[1] = false;
        for (int i = 2; i * i <= MAX_PRECOMPUTED; i++) {
            if (isPrimePrecomputed[i]) {
                for (int j = i * i; j <= MAX_PRECOMPUTED; j += i) {
                    isPrimePrecomputed[j] = false;
                }
            }
        }
    }

    // Числа, делящиеся на 3 или 5
    public static List<Integer> getDivisibleBy3Or5(List<Integer> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("Коллекция не может быть null или пустой");
        }
        return collection.stream()
                .filter(Collection_Proc_Stream::isDivisibleBy3Or5)
                .collect(Collectors.toList());
    }

    // Простые числа
    public static List<Integer> getPrimes(List<Integer> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("Коллекция не может быть null или пустой");
        }
        return collection.stream()
                .filter(Collection_Proc_Stream::isPrime)
                .collect(Collectors.toList());
    }

    // Максимальный элемент
    public static int getMax(List<Integer> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("Коллекция не может быть null или пустой");
        }
        return collection.stream()
                .mapToInt(Integer::intValue)
                .max()
                .orElseThrow(() -> new IllegalStateException("Не удалось найти максимум в коллекции"));
    }

    // Минимальный элемент
    public static int getMin(List<Integer> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("Коллекция не может быть null или пустой");
        }
        return collection.stream()
                .mapToInt(Integer::intValue)
                .min()
                .orElseThrow(() -> new IllegalStateException("Не удалось найти минимум в коллекции"));
    }

    // Среднее арифметическое
    public static double getAverage(List<Integer> collection) {
        if (collection == null || collection.isEmpty()) {
            throw new IllegalArgumentException("Коллекция не может быть null или пустой");
        }
        return collection.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElseThrow(() -> new IllegalStateException("Не удалось вычислить среднее для коллекции"));
    }

    // Проверка делимости на 3 или 5
    private static boolean isDivisibleBy3Or5(int number) {
        return number % 3 == 0 || number % 5 == 0;
    }

    // Проверка на простоту числа с использованием решета Эратосфена
    private static boolean isPrime(int number) {
        if (number <= MAX_PRECOMPUTED) {
            return number >= 0 && isPrimePrecomputed[number];
        }
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

    // Пример использования
    public static void main(String[] args) {
        // Генерация коллекции для тестирования
        List<Integer> numbers = new ArrayList<>();
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < 100; i++) {
            numbers.add(random.nextInt(1000));
        }

        // Тестирование методов
        System.out.println("Исходная коллекция (первые 10 элементов): " + numbers.subList(0, Math.min(10, numbers.size())));

        List<Integer> divisibleBy3Or5 = getDivisibleBy3Or5(numbers);
        System.out.println("Числа, делящиеся на 3 или 5 (первые 10): " + divisibleBy3Or5.subList(0, Math.min(10, divisibleBy3Or5.size())));

        List<Integer> primes = getPrimes(numbers);
        System.out.println("Простые числа (первые 10): " + primes.subList(0, Math.min(10, primes.size())));

        int max = getMax(numbers);
        System.out.println("Максимальный элемент: " + max);

        int min = getMin(numbers);
        System.out.println("Минимальный элемент: " + min);

        double average = getAverage(numbers);
        System.out.println("Среднее арифметическое: " + average);
    }
}