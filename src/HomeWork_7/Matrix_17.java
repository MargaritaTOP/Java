package HomeWork_7;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* УСЛОВИЕ ЗАДАЧИ
 17. Прибавить к каждому элементу столбцов квадратной матрицы среднее арифметическое побочной диагонали.
*/
public class Matrix_17 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in); // Создаем объект Scanner для чтения ввода пользователя
        // Запрашиваем размер квадратной матрицы
        System.out.print("Введите размер квадратной матрицы (n): ");
        int n = scanner.nextInt(); // Считываем размер матрицы
        // Проверка корректности ввода
        if (n <= 0) { // Проверяем, что размер матрицы положительный
            System.out.println("Ошибка: размер матрицы должен быть положительным!");
            return; // Завершаем программу при некорректном вводе
        }
        // Создаем матрицу как List<List<Integer>>
        List<List<Integer>> matrix = new ArrayList<>(); // Инициализируем пустую матрицу как список списков
        // Запрашиваем способ заполнения матрицы
        System.out.println("Выберите способ заполнения матрицы:");
        System.out.println("1. Вручную");
        System.out.println("2. Автоматически (случайные числа)");
        System.out.print("Введите 1 или 2: ");
        int choice = scanner.nextInt(); // Считываем выбор способа заполнения
        switch (choice) {
            case 1:
                // Ручное заполнение матрицы
                System.out.println("Введите элементы матрицы:");
                for (int i = 0; i < n; i++) { // Перебираем строки
                    List<Integer> row = new ArrayList<>(); // Создаем новую строку
                    for (int j = 0; j < n; j++) { // Перебираем столбцы
                        System.out.printf("Элемент [%d][%d]: ", i, j); // Запрашиваем элемент
                        row.add(scanner.nextInt()); // Добавляем введенный элемент в строку
                    }
                    matrix.add(row); // Добавляем заполненную строку в матрицу
                }
                break;
            case 2:
                // Автоматическое заполнение случайными числами
                Random random = new Random(); // Создаем объект Random для генерации чисел
                for (int i = 0; i < n; i++) { // Перебираем строки
                    List<Integer> row = new ArrayList<>(); // Создаем новую строку
                    for (int j = 0; j < n; j++) { // Перебираем столбцы
                        // Генерируем числа от -100 до 100
                        row.add(random.nextInt(201) - 100); // Добавляем случайное число в строку
                    }
                    matrix.add(row); // Добавляем заполненную строку в матрицу
                }
                break;
            default:
                System.out.println("Ошибка: неверный выбор! Используется автоматическое заполнение.");
                // Заполнение случайными числами по умолчанию
                Random defaultRandom = new Random(); // Создаем объект Random для случая по умолчанию
                for (int i = 0; i < n; i++) { // Перебираем строки
                    List<Integer> row = new ArrayList<>(); // Создаем новую строку
                    for (int j = 0; j < n; j++) { // Перебираем столбцы
                        row.add(defaultRandom.nextInt(201) - 100); // Добавляем случайное число
                    }
                    matrix.add(row); // Добавляем заполненную строку в матрицу
                }
        }
        // Вывод исходной матрицы
        System.out.println("\nИсходная матрица:");
        for (List<Integer> row : matrix) { // Перебираем строки матрицы
            System.out.println(row); // Выводим каждую строку
        }
        // Вычисляем среднее арифметическое побочной диагонали
        double secondaryDiagonalMean = 0;
        secondaryDiagonalMean = IntStream.range(0, n)
                .mapToDouble(i -> matrix.get(i).get(n - 1 - i))
                .average()
                .orElse(0.0);
        // Вывод средней арифметической побочной диагонали
        System.out.printf("\nСреднее арифметическое побочной диагонали: %.2f%n", secondaryDiagonalMean);
        // Создаем результирующую матрицу, прибавляя среднее к каждому элементу столбцов
        List<List<Double>> resultMatrix = new ArrayList<>(); // Инициализируем результирующую матрицу
        for (List<Integer> row : matrix) { // Перебираем строки
            double finalSecondaryDiagonalMean = secondaryDiagonalMean; // Сохраняем константу для лямбда-выражения
            List<Double> newRow = row.parallelStream() // parallelStream
                    .map(num -> (double) num + finalSecondaryDiagonalMean) // Прибавляем среднее диагонали
                    .collect(Collectors.toList());
            resultMatrix.add(newRow);
        }
        // Вывод результирующей матрицы
        System.out.println("\nМатрица после прибавления среднего арифметического побочной диагонали к элементам столбцов:");
        for (List<Double> row : resultMatrix) { // Перебираем строки результирующей матрицы
            System.out.println(row.parallelStream() // parallelStream
                    .map(num -> String.format("%.2f", num)) // Форматируем числа до двух знаков
                    .collect(Collectors.toList()));
        }
        scanner.close();
    }
}