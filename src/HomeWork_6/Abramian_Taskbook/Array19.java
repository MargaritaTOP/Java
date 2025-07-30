package HomeWork_6.Abramian_Taskbook;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

 /* УСЛОВИЕ ЗАДАЧИ
 Дан целочисленный массив A размера 10. Вывести порядковый номер
 последнего из тех его элементов AK, которые удовлетворяют двойному
 неравенству A1 < AK < A10. Если таких элементов нет, то вывести 0.
 */

public class Array19 {
    public static int findLastIndexInRange(int[] array) {
        if (array == null || array.length != 10) {
            throw new IllegalArgumentException("Массив должен быть не null и содержать ровно 10 элементов");
        }
        // Используем Stream для поиска последнего индекса элемента, удовлетворяющего A[0] < AK < A[9]
        return IntStream.range(0, 9) // Обрабатываем индексы 0 до 8
                .filter(i -> array[0] < array[i] && array[i] < array[9])
                .reduce((first, last) -> last) // Берем последний индекс
                .orElse(0); // Возвращаем 0, если подходящих элементов нет
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SecureRandom random = new SecureRandom();
        int n;
        // 1. Ввод размера массива
        while (true) {
            System.out.print("Введите размер массива (N = 10): ");
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n == 10) {
                    break;
                } else {
                    System.out.println("Ошибка: размер массива должен быть равен 10");
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
        // Поиск и вывод результата
        try {
            int result = findLastIndexInRange(array);
            System.out.println("Первый элемент A[1] = " + array[0]);
            System.out.println("Последний элемент A[10] = " + array[9]);
            System.out.println("Порядковый номер последнего элемента, где A[1] < AK < A[10]: " + (result == 0 ? 0 : result + 1));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        scanner.close();
    }
}
