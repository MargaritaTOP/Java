package HomeWork_1;

import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        try {
            int n = readPositiveInt(scanner);
            int[] array = fillRandomArray(n, random);
            System.out.println("Исходный массив: " + Arrays.toString(array));
            processAndPrintResults(array);
        } catch (Exception e) {
            System.out.println("Произошла непредвиденная ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static int readPositiveInt(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Введите размер массива (n > 0): ");
                String input = scanner.nextLine();
                int number = Integer.parseInt(input);
                if (number <= 0) {
                    throw new IllegalArgumentException("Размер массива должен быть положительным.");
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: Введено некорректное число. Пожалуйста, введите целое число.");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }

    private static int[] fillRandomArray(int size, Random random) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100) + 1;
        }
        return array;
    }

    private static void processAndPrintResults(int[] array) {
        try {
               NumberProcessor.Result results = NumberProcessor.processArray(array);

            System.out.println("Числа, делящиеся на 3 или 5: " + Arrays.toString(results.divisibleBy3Or5));
            System.out.println("Простые числа: " + Arrays.toString(results.primes));
            System.out.println("Максимальное значение: " + results.max);
            System.out.println("Минимальное значение: " + results.min);
            System.out.printf("Среднее значение: %.2f\n", results.average);

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка обработки массива: " + e.getMessage());
        }
    }
}
