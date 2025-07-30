package HomeWork_6.Abramian_Taskbook;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;

 /* УСЛОВИЕ ЗАДАЧИ
 Дано число R и массив A размера N. Найти элемент массива, который наиболее близок к числу R (то есть такой элемент AK,
 для которого величина |AK R| является минимальной).
 */

public class Array40 {
    public static int findClosestElement(int[] array, int R) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Массив должен быть не null и содержать как минимум 1 элемент");
        }
        // Используем Stream для поиска элемента, наиболее близкого к R
        return Arrays.stream(array)
                .reduce((a, b) -> Math.abs(a - R) <= Math.abs(b - R) ? a : b)
                .orElseThrow(() -> new IllegalStateException("Не удалось найти элемент"));
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SecureRandom random = new SecureRandom();
        int n, R;
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
        // 2. Ввод числа R
        while (true) {
            System.out.print("Введите число R (ненулевое целое): ");
            if (scanner.hasNextInt()) {
                R = scanner.nextInt();
                if (R != 0) {
                    break;
                } else {
                    System.out.println("Ошибка: число R не должно быть нулевым");
                }
            } else {
                System.out.println("Ошибка: введите целое число");
                scanner.next();
            }
        }
        // 3. Выбор способа ввода массива
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
            // 4. Ручной ввод массива
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
            // 5. Ввод диапазона случайных чисел
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
        System.out.println("Число R: " + R);
        // Поиск элемента, наиболее близкого к R, и вывод результата
        try {
            int result = findClosestElement(array, R);
            System.out.println("Элемент, наиболее близкий к " + R + ": " + result);
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        scanner.close();
    }
}