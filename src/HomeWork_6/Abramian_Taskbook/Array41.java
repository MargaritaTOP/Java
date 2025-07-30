package HomeWork_6.Abramian_Taskbook;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

 /* УСЛОВИЕ ЗАДАЧИ
 Дан массив размера N. Найти два соседних элемента, сумма которых максимальна, и вывести эти элементы в порядке возрастания их индексов.
 */

public class Array41 {
    public static int[] findMaxSumAdjacentElements(int[] array) {
        if (array == null || array.length < 2) {
            throw new IllegalArgumentException("Массив должен содержать как минимум 2 элемента и не быть null");
        }
        // Используем Stream для поиска пары соседних элементов с максимальной суммой
        int maxIndex = IntStream.range(0, array.length - 1)
                .reduce((i, j) -> (array[i] + array[i + 1]) >= (array[j] + array[j + 1]) ? i : j)
                .orElseThrow(() -> new IllegalStateException("Не удалось найти пару элементов"));

        return new int[]{array[maxIndex], array[maxIndex + 1]};
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SecureRandom random = new SecureRandom();
        int n;
        // 1. Ввод размера массива
        while (true) {
            System.out.print("Введите размер массива (N > 1): ");
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                if (n > 1) {
                    break;
                } else {
                    System.out.println("Ошибка: размер массива должен быть больше 1");
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
        // Поиск пары соседних элементов с максимальной суммой и вывод результата
        try {
            int[] result = findMaxSumAdjacentElements(array);
            System.out.println("Соседние элементы с максимальной суммой: " + Arrays.toString(result));
            System.out.println("Их индексы: [" + (Arrays.stream(array).boxed()
                    .takeWhile(x -> x != result[0]).count() + 1) + ", " +
                    (Arrays.stream(array).boxed().takeWhile(x -> x != result[0]).count() + 2) + "]");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        scanner.close();
    }
}
