package HomeWork_6.Abramian_Taskbook;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

 /* УСЛОВИЕ ЗАДАЧИ
 Дано число R и массив размера N. Найти два соседних элемента массива, сумма которых наиболее близка к числу R,
 и вывести эти элементы в порядке возрастания их индексов
 */

public class Array42 {
    public static int[] findClosestSumAdjacentElements(int[] array, int R) {
        if (array == null || array.length < 2) {
            throw new IllegalArgumentException("Массив должен содержать как минимум 2 элемента и не быть null");
        }
        // Используем Stream для поиска пары соседних элементов, сумма которых наиболее близка к R
        int minIndex = IntStream.range(0, array.length - 1)
                .reduce((i, j) -> Math.abs((array[i] + array[i + 1]) - R) <= Math.abs((array[j] + array[j + 1]) - R) ? i : j)
                .orElseThrow(() -> new IllegalStateException("Не удалось найти пару элементов"));

        return new int[]{array[minIndex], array[minIndex + 1]};
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SecureRandom random = new SecureRandom();
        int n, R;
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
        // Вывод массива и числа R
        System.out.println("\nМассив: " + Arrays.toString(array));
        System.out.println("Число R: " + R);
        // Поиск пары соседних элементов с суммой, наиболее близкой к R, и вывод результата
        try {
            int[] result = findClosestSumAdjacentElements(array, R);
            System.out.println("Соседние элементы с суммой, наиболее близкой к " + R + ": " + Arrays.toString(result));
            // Вывод индексов
            long firstIndex = Arrays.stream(array).boxed()
                    .takeWhile(x -> x != result[0]).count() + 1;
            System.out.println("Их индексы: [" + firstIndex + ", " + (firstIndex + 1) + "]");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        scanner.close();
    }
}
