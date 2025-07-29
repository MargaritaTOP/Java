package HomeWork_1;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер массива (n > 0): ");
        int n = scanner.nextInt();
        // Генерация списка случайных чисел в одну строку с SecureRandom
        List<Integer> list = generateRandomList(n, 100);
        // Преобразуем список в массив для ArrayProcessor
        int[] array = list.stream().mapToInt(Integer::intValue).toArray();
        System.out.println("\nИсходный массив: " + Arrays.toString(array));
        // Обработка через ArrayProcessor
        System.out.println("\nРезультаты ArrayProcessor:");
        processWithArrayProcessor(array);
        // Обработка через CollectionProcessor
        System.out.println("\nРезультаты CollectionProcessor:");
        processWithCollectionProcessor(list);
        scanner.close();
    }
    public static List<Integer> generateRandomList(int size, int bound) throws NoSuchAlgorithmException {
        return java.security.SecureRandom.getInstanceStrong()
                .ints(size, 1, bound)
                .boxed()
                .collect(java.util.stream.Collectors.toCollection(ArrayList::new));
    }
    private static void processWithArrayProcessor(int[] array) {
        int[] divisibleBy3Or5 = ArrayProcessor.getDivisibleBy3Or5(array);
        int[] primes = ArrayProcessor.getPrimes(array);
        int max = ArrayProcessor.getMax(array);
        int min = ArrayProcessor.getMin(array);
        double average = ArrayProcessor.getAverage(array);
        System.out.println("Числа, делящиеся на 3 или 5: " + Arrays.toString(divisibleBy3Or5));
        System.out.println("Простые числа: " + Arrays.toString(primes));
        System.out.println("Максимальное значение: " + max);
        System.out.println("Минимальное значение: " + min);
        System.out.printf("Среднее значение: %.2f\n", average);
    }
    private static void processWithCollectionProcessor(List<Integer> list) {
        List<Integer> divisibleBy3Or5 = CollectionProcessor.getDivisibleBy3Or5(list);
        List<Integer> primes = CollectionProcessor.getPrimes(list);
        int max = CollectionProcessor.getMax(list);
        int min = CollectionProcessor.getMin(list);
        double average = CollectionProcessor.getAverage(list);
        System.out.println("Числа, делящиеся на 3 или 5: " + divisibleBy3Or5);
        System.out.println("Простые числа: " + primes);
        System.out.println("Максимальное значение: " + max);
        System.out.println("Минимальное значение: " + min);
        System.out.printf("Среднее значение: %.2f\n", average);
    }
}