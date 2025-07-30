package HomeWork_6.Abramian_Taskbook;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

 /* УСЛОВИЕ ЗАДАЧИ
  Дан целочисленный массив размера N. Найти количество различных элементов в данном массиве.
 */

public class Array47 {
    public static long countDistinctElements(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Массив должен быть не null и содержать как минимум 1 элемент");
        }

        // Используем Stream для подсчета различных элементов
        return Arrays.stream(array)
                .distinct()
                .count();
    }

    public static String getDistinctElementsList(int[] array) {
        // Возвращаем список различных элементов в виде строки
        return Arrays.stream(array)
                .distinct()
                .sorted() // Сортируем для упорядоченного вывода
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(", ", "[", "]"));
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
            System.out.println("\nВведите " + n + " ненулевых целых чисел:");
            for (int i = 0; i < n; i++) {
                while (true) {
                    System.out.print("Элемент " + (i + 1) + ": ");
                    if (scanner.hasNextInt()) {
                        int num = scanner.nextInt();
                        if (num != 0) {
                            array[i] = num;
                            break;
                        } else {
                            System.out.println("Ошибка: число не должно быть нулевым");
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

            // Генерация случайного массива ненулевых чисел
            array = random.ints(n, lowerBound, upperBound + 1)
                    .map(num -> num == 0 ? (lowerBound != 0 ? lowerBound : 1) : num) // Исключаем 0
                    .toArray();
        }

        // Вывод массива
        System.out.println("\nМассив: " + Arrays.toString(array));

        // Подсчет количества различных элементов и вывод результата
        try {
            long result = countDistinctElements(array);
            System.out.println("Количество различных элементов: " + result);
            System.out.println("Список различных элементов: " + getDistinctElementsList(array));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        scanner.close();
    }
}
