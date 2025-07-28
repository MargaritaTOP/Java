package HomeWork_1;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите размер массива (n > 0): ");
        int n = scanner.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = random.nextInt(100) + 1;
        }
        // Преобразуем массив в список для CollectionProcessor
        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        System.out.println("\nИсходный массив: " + Arrays.toString(array));
        // Обработка через ArrayProcessor
        System.out.println("\nРезультаты ArrayProcessor:");
        processWithArrayProcessor(array);
        // Обработка через CollectionProcessor
        System.out.println("\nРезультаты CollectionProcessor:");
        processWithCollectionProcessor(list);
        scanner.close();
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