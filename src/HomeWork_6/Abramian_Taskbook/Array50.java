package HomeWork_6.Abramian_Taskbook;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.IntStream;

 /* УСЛОВИЕ ЗАДАЧИ
 Дан целочисленный массив A размера N, являющийся перестановкой, то есть содержит все числа от 1 до N.
 Найти количество инверсий в данной перестановке, то есть таких пар элементов AI и AJ,
 в которых большее число находится слева от меньшего: AI > AJ при I < J.
 */

public class Array50 {
    // Проверка, является ли массив перестановкой
    public static int checkPermutation(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Массив должен быть не null и содержать как минимум 1 элемент");
        }
        int n = array.length;
        Set<Integer> seen = new HashSet<>();
        // Проверяем каждый элемент
        for (int i = 0; i < n; i++) {
            int num = array[i];
            if (num < 1 || num > n || !seen.add(num)) {
                return i + 1; // Номер первого недопустимого элемента
            }
        }
        return 0; // Массив является перестановкой
    }
    // Подсчет инверсий с использованием сортировки слиянием
    public static long countInversions(int[] array) {
        int[] temp = new int[array.length];
        return mergeSortAndCount(array, temp, 0, array.length - 1);
    }
    private static long mergeSortAndCount(int[] array, int[] temp, int left, int right) {
        long invCount = 0;
        if (left < right) {
            int mid = (left + right) / 2;
            invCount += mergeSortAndCount(array, temp, left, mid);
            invCount += mergeSortAndCount(array, temp, mid + 1, right);
            invCount += mergeAndCount(array, temp, left, mid, right);
        }
        return invCount;
    }
    private static long mergeAndCount(int[] array, int[] temp, int left, int mid, int right) {
        long invCount = 0;
        int i = left; // Индекс для левой половины
        int j = mid + 1; // Индекс для правой половины
        int k = left; // Индекс для временного массива
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
                invCount += mid - i + 1; // Все элементы от i до mid образуют инверсии
            }
        }
        // Копируем оставшиеся элементы левой половины
        while (i <= mid) {
            temp[k++] = array[i++];
        }
        // Копируем оставшиеся элементы правой половины
        while (j <= right) {
            temp[k++] = array[j++];
        }
        // Копируем временный массив обратно в основной
        for (i = left; i <= right; i++) {
            array[i] = temp[i];
        }

        return invCount;
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
        System.out.println("2. Сгенерировать случайную перестановку");
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
            System.out.println("\nВведите " + n + " целых чисел (для перестановки — числа от 1 до " + n + " без повторений):");
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
            // 4. Генерация случайной перестановки
            array = IntStream.rangeClosed(1, n).toArray();
            // Перемешиваем массив (алгоритм Фишера-Йетса)
            for (int i = n - 1; i > 0; i--) {
                int j = random.nextInt(i + 1);
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        // Вывод массива
        System.out.println("\nМассив: " + Arrays.toString(array));
        // Проверка перестановки и подсчет инверсий
        try {
            int permutationCheck = checkPermutation(array);
            if (permutationCheck != 0) {
                System.out.println("Массив не является перестановкой. Номер первого недопустимого элемента: " + permutationCheck);
                System.out.println("Значение недопустимого элемента: " + array[permutationCheck - 1]);
            } else {
                long inversions = countInversions(array);
                System.out.println("Количество инверсий в перестановке: " + inversions);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        scanner.close();
    }
}
