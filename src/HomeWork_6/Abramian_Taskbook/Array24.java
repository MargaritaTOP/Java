package HomeWork_6.Abramian_Taskbook;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

 /* УСЛОВИЕ ЗАДАЧИ
 Дан целочисленный массив размера N, не содержащий одинаковых
 чисел. Проверить, образуют ли его элементы арифметическую прогрес
 сию. Если образуют, то вывести разность прогрессии,
 если нет — вывести 0.
 */

public class Array24 {
    public static int checkArithmeticProgression(int[] array) {
        if (array == null || array.length < 2) {
            throw new IllegalArgumentException("Массив должен быть не null и содержать как минимум 2 элемента");
        }
        // Проверяем уникальность элементов
        Set<Integer> set = new HashSet<>();
        for (int num : array) {
            if (!set.add(num)) {
                throw new IllegalArgumentException("Массив содержит одинаковые элементы");
            }
        }
        // Сортируем массив для проверки арифметической прогрессии
        int[] sortedArray = Arrays.copyOf(array, array.length);
        Arrays.sort(sortedArray);
        // Вычисляем разность между первыми двумя элементами
        int difference = sortedArray[1] - sortedArray[0];
        // Проверяем, что разность сохраняется для всех последовательных элементов
        boolean isArithmetic = IntStream.range(1, sortedArray.length - 1)
                .allMatch(i -> sortedArray[i + 1] - sortedArray[i] == difference);
        return isArithmetic ? difference : 0;
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
            Set<Integer> usedNumbers = new HashSet<>();
            System.out.println("\nВведите " + n + " различных ненулевых целых чисел:");
            for (int i = 0; i < n; i++) {
                while (true) {
                    System.out.print("Элемент " + (i + 1) + ": ");
                    if (scanner.hasNextInt()) {
                        int num = scanner.nextInt();
                        if (num != 0 && usedNumbers.add(num)) {
                            array[i] = num;
                            break;
                        } else {
                            System.out.println("Ошибка: число должно быть ненулевым и не повторяться");
                        }
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
            // Проверка, что диапазон достаточно велик для N уникальных чисел
            if (upperBound - lowerBound + 1 < n) {
                System.out.println("Ошибка: диапазон слишком мал для " + n + " различных чисел");
                scanner.close();
                return;
            }
            // Генерация случайного массива с уникальными ненулевыми числами
            Set<Integer> uniqueNumbers = new HashSet<>();
            array = new int[n];
            int index = 0;
            while (index < n) {
                int num = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                if (num != 0 && uniqueNumbers.add(num)) {
                    array[index++] = num;
                }
            }
        }
        // Вывод массива
        System.out.println("\nМассив: " + Arrays.toString(array));
        // Проверка арифметической прогрессии и вывод результата
        try {
            int result = checkArithmeticProgression(array);
            System.out.println("Разность арифметической прогрессии: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        scanner.close();
    }
}
