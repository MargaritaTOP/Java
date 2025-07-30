package HomeWork_6.Array_Collection_Proc_Stream;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n;
        // Проверка корректности ввода
        while (true) {
            System.out.print("Введите размер массива (n > 0): ");
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n > 0) {
                    break;
                } else {
                    System.out.println("Ошибка: размер массива должен быть больше 0");
                }
            } else {
                System.out.println("Ошибка: введите целое число");
                scanner.next(); // Очистка некорректного ввода
            }
        }

        // Генерация списка случайных чисел
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

    public static List<Integer> generateRandomList(int size, int bound) {
        return new java.security.SecureRandom()
                .ints(size, 1, bound)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private static void processWithArrayProcessor(int[] array) {
        int[] divisibleBy3Or5 = Array_Proc_Stream.getDivisibleBy3Or5(array);
        int[] primes = Array_Proc_Stream.getPrimes(array);
        int max = Array_Proc_Stream.getMax(array);
        int min = Array_Proc_Stream.getMin(array);
        double average = Array_Proc_Stream.getAverage(array);
        System.out.println("Числа, делящиеся на 3 или 5: " + Arrays.toString(divisibleBy3Or5));
        System.out.println("Простые числа: " + Arrays.toString(primes));
        System.out.println("Максимальное значение: " + max);
        System.out.println("Минимальное значение: " + min);
        System.out.printf("Среднее значение: %.2f\n", average);
    }

    private static void processWithCollectionProcessor(List<Integer> list) {
        List<Integer> divisibleBy3Or5 = Collection_Proc_Stream.getDivisibleBy3Or5(list);
        List<Integer> primes = Collection_Proc_Stream.getPrimes(list);
        int max = Collection_Proc_Stream.getMax(list);
        int min = Collection_Proc_Stream.getMin(list);
        double average = Collection_Proc_Stream.getAverage(list);
        System.out.println("Числа, делящиеся на 3 или 5: " + divisibleBy3Or5);
        System.out.println("Простые числа: " + primes);
        System.out.println("Максимальное значение: " + max);
        System.out.println("Минимальное значение: " + min);
        System.out.printf("Среднее значение: %.2f\n", average);
    }
}
