package HomeWork_7;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* УСЛОВИЕ ЗАДАЧИ
 17.	Прибавить к каждому элементу столбцов квадратной матрицы среднее арифметическое побочной диагонали.
*/

public class Matrix_17 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Запрашиваем размер квадратной матрицы
        System.out.print("Введите размер квадратной матрицы (n): ");
        int n = scanner.nextInt();
        // Проверка корректности ввода
        if (n <= 0) {
            System.out.println("Ошибка: размер матрицы должен быть положительным!");
            return;
        }
        // Создаем матрицу как List<List<Integer>>
        List<List<Integer>> matrix = new ArrayList<>();
        // Запрашиваем способ заполнения матрицы
        System.out.println("Выберите способ заполнения матрицы:");
        System.out.println("1. Вручную");
        System.out.println("2. Автоматически (случайные числа)");
        System.out.print("Введите 1 или 2: ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                // Ручное заполнение матрицы
                System.out.println("Введите элементы матрицы:");
                for (int i = 0; i < n; i++) {
                    List<Integer> row = new ArrayList<>();
                    for (int j = 0; j < n; j++) {
                        System.out.printf("Элемент [%d][%d]: ", i, j);
                        row.add(scanner.nextInt());
                    }
                    matrix.add(row);
                }
                break;
            case 2:
                // Автоматическое заполнение случайными числами
                Random random = new Random();
                for (int i = 0; i < n; i++) {
                    List<Integer> row = new ArrayList<>();
                    for (int j = 0; j < n; j++) {
                        // Генерируем числа от -100 до 100
                        row.add(random.nextInt(201) - 100);
                    }
                    matrix.add(row);
                }
                break;
            default:
                System.out.println("Ошибка: неверный выбор! Используется автоматическое заполнение.");
                // Заполнение случайными числами по умолчанию
                Random defaultRandom = new Random();
                for (int i = 0; i < n; i++) {
                    List<Integer> row = new ArrayList<>();
                    for (int j = 0; j < n; j++) {
                        row.add(defaultRandom.nextInt(201) - 100);
                    }
                    matrix.add(row);
                }
        }
        // Вывод исходной матрицы
        System.out.println("\nИсходная матрица:");
        for (List<Integer> row : matrix) {
            System.out.println(row);
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
        List<List<Double>> resultMatrix = new ArrayList<>();
        for (List<Integer> row : matrix) {
            double finalSecondaryDiagonalMean = secondaryDiagonalMean;
            List<Double> newRow = row.stream()
                    .map(num -> (double) num + finalSecondaryDiagonalMean)
                    .collect(Collectors.toList());
            resultMatrix.add(newRow);
        }
        // Вывод результирующей матрицы
        System.out.println("\nМатрица после прибавления среднего арифметического побочной диагонали к элементам столбцов:");
        for (List<Double> row : resultMatrix) {
            System.out.println(row.stream()
                    .map(num -> String.format("%.2f", num))
                    .collect(Collectors.toList()));
        }
        scanner.close();
    }
}