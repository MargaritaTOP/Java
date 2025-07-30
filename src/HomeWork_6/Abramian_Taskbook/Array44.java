package HomeWork_6.Abramian_Taskbook;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

 /* УСЛОВИЕ ЗАДАЧИ
 Дан целочисленный массив размера N, содержащий ровно два одинаковых элемента. Найти номера одинаковых элементов и вывести эти
 номера в порядке возрастания.
 */

public class Array44 {
    public static int[] findDuplicateIndices(int[] array) {
        if (array == null || array.length < 2) {
            throw new IllegalArgumentException("Массив должен содержать как минимум 2 элемента и не быть null");
        }
        // Используем HashMap для подсчета частоты элементов и сохранения их индексов
        Map<Integer, int[]> indexMap = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int finalI = i;
            indexMap.compute(array[i], (k, v) -> {
                if (v == null) {
                    return new int[]{finalI + 1, -1}; // Сохраняем первый индекс (1-based)
                } else if (v[1] == -1) {
                    v[1] = finalI + 1; // Сохраняем второй индекс
                    return v;
                } else {
                    return null; // Если элемент встретился более двух раз, помечаем как null
                }
            });
        }
        // Находим пару индексов для элемента, который встретился ровно дважды
        return indexMap.entrySet().stream()
                .filter(entry -> entry.getValue() != null && entry.getValue()[1] != -1)
                .map(Map.Entry::getValue)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Массив должен содержать ровно два одинаковых элемента"));
    }
    // Проверка, содержит ли массив ровно два одинаковых элемента
    public static boolean hasExactlyTwoDuplicates(int[] array) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : array) {
            frequencyMap.merge(num, 1, Integer::sum);
        }
        long duplicateCount = frequencyMap.values().stream().filter(count -> count == 2).count();
        return duplicateCount == 1 && frequencyMap.values().stream().allMatch(count -> count == 1 || count == 2);
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
        System.out.println("1. Ввести массив вручную (массив должен содержать ровно два одинаковых элемента)");
        System.out.println("2. Сгенерировать случайный массив с ровно двумя одинаковыми элементами");
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
            // 3. Ручной ввод массива с проверкой на ровно два одинаковых элемента
            while (true) {
                array = new int[n];
                System.out.println("\nВведите " + n + " ненулевых целых чисел (ровно два должны быть одинаковыми):");
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
                if (hasExactlyTwoDuplicates(array)) {
                    break;
                } else {
                    System.out.println("Ошибка: массив должен содержать ровно два одинаковых элемента. Попробуйте снова.");
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
            // Генерация случайного массива с ровно двумя одинаковыми элементами
            array = new int[n];
            // Заполняем массив уникальными числами, кроме одного дубликата
            int duplicateValue = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
            if (duplicateValue == 0) {
                duplicateValue = lowerBound != 0 ? lowerBound : 1; // Исключаем 0
            }
            int duplicateIndex1 = random.nextInt(n);
            int duplicateIndex2;
            do {
                duplicateIndex2 = random.nextInt(n);
            } while (duplicateIndex2 == duplicateIndex1);
            // Заполняем массив
            for (int i = 0; i < n; i++) {
                if (i == duplicateIndex1 || i == duplicateIndex2) {
                    array[i] = duplicateValue;
                } else {
                    int num;
                    do {
                        num = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
                        if (num == 0) {
                            num = lowerBound != 0 ? lowerBound : 1; // Исключаем 0
                        }
                    } while (num == duplicateValue); // Гарантируем уникальность остальных элементов
                    array[i] = num;
                }
            }
        }
        // Вывод массива
        System.out.println("\nМассив: " + Arrays.toString(array));
        // Поиск номеров одинаковых элементов и вывод результата
        try {
            int[] result = findDuplicateIndices(array);
            System.out.println("Номера одинаковых элементов: " + Arrays.toString(result));
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        scanner.close();
    }
}