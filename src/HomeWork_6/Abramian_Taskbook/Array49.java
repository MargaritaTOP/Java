package HomeWork_6.Abramian_Taskbook;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

 /* УСЛОВИЕ ЗАДАЧИ
   Дан целочисленный массив размера N. Если он является перестановкой, то есть содержит все числа от 1 до N, то вывести 0; в противном
 случае вывести номер первого недопустимого элемента.
 */

public class Array49 {
    public static int checkPermutation(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Массив должен быть не null и содержать как минимум 1 элемент");
        }
        int n = array.length;
        Set<Integer> seen = new HashSet<>();
        // Проверяем каждый элемент
        for (int i = 0; i < n; i++) {
            int num = array[i];
            // Если элемент вне диапазона [1, N] или уже встречался, возвращаем его номер (1-based)
            if (num < 1 || num > n || !seen.add(num)) {
                return i + 1;
            }
        }
        // Если все элементы от 1 до N присутствуют ровно один раз, возвращаем 0
        return 0;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SecureRandom random = new SecureRandom();
        int n;
        // 1. Ввод размера массива
        while (true) {
            System.out.print("Введите размер массива (N > 0): ");
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
        // 2. Выбор способа ввода массива
        int choice;
        System.out.println("\nВыберите способ ввода массива:");
        System.out.println("1. Ввести массив вручную");
        System.out.println("2. Сгенерировать случайный массив");
        while (true) {
            System.out.print("Введите 1 или 2: ");
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice == 1 || choice == 2) {
                    break;
                } else {
                    System.out.println("Ошибка: выберите 1 или 2");
                }
            } else {
                System.out.println("Ошибка: введите целое число");
                scanner.next();
            }
        }
        int[] array;
        if (choice == 1) {
            // 3. Ручной ввод массива
            array = new int[n];
            System.out.println("\nВведите " + n + " целых чисел:");
            for (int i = 0; i < n; i++) {
                while (true) {
                    System.out.print("Элемент " + (i + 1) + ": ");
                    if (scanner.hasNextInt()) {
                        array[i] = scanner.nextInt();
                        break;
                    } else {
                        System.out.println("Ошибка: введите целое число");
                        scanner.next();
                    }
                }
            }
        } else {
            // 4. Ввод диапазона случайных чисел
            int lowerBound, upperBound;
            while (true) {
                System.out.print("\nВведите нижнюю границу диапазона чисел: ");
                if (scanner.hasNextInt()) {
                    lowerBound = scanner.nextInt();
                    break;
                } else {
                    System.out.println("Ошибка: введите целое число");
                    scanner.next();
                }
            }
            while (true) {
                System.out.print("Введите верхнюю границу диапазона чисел (больше или равно нижней): ");
                if (scanner.hasNextInt()) {
                    upperBound = scanner.nextInt();
                    if (upperBound >= lowerBound) {
                        break;
                    } else {
                        System.out.println("Ошибка: верхняя граница должна быть больше или равна нижней");
                    }
                } else {
                    System.out.println("Ошибка: введите целое число");
                    scanner.next();
                }
            }
            // Генерация случайного массива
            if (lowerBound <= 1 && upperBound >= n) {
                // Если диапазон позволяет создать перестановку, генерируем перестановку с 50% вероятностью
                if (random.nextBoolean()) {
                    array = IntStream.rangeClosed(1, n)
                            .toArray();
                    // Перемешиваем массив
                    for (int i = n - 1; i > 0; i--) {
                        int j = random.nextInt(i + 1);
                        int temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                    }
                } else {
                    // Иначе генерируем случайный массив
                    array = random.ints(n, lowerBound, upperBound + 1).toArray();
                }
            } else {
                // Генерируем случайный массив в заданном диапазоне
                array = random.ints(n, lowerBound, upperBound + 1).toArray();
            }
        }
        // Вывод массива
        System.out.println("\nМассив: " + Arrays.toString(array));
        // Проверка, является ли массив перестановкой, и вывод результата
        try {
            int result = checkPermutation(array);
            if (result == 0) {
                System.out.println("Массив является перестановкой: 0");
            } else {
                System.out.println("Номер первого недопустимого элемента: " + result);
                System.out.println("Значение недопустимого элемента: " + array[result - 1]);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        scanner.close();
    }
}
