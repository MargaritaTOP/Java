package HomeWork_7;

import java.util.*;

/* УСЛОВИЕ ЗАДАЧИ
11.	Выяснить, сколько элементов в каждой строке матрицы превышают среднее арифметическое значение, определенное для этой строки.
Из найденных величин составить новый список (массив).
*/

public class Matrix_11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Запрашиваем количество строк и столбцов
        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();
        // Проверка корректности ввода
        if (rows <= 0 || cols <= 0) {
            System.out.println("Ошибка: количество строк и столбцов должно быть положительным!");
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
                for (int i = 0; i < rows; i++) {
                    List<Integer> row = new ArrayList<>();
                    for (int j = 0; j < cols; j++) {
                        System.out.printf("Элемент [%d][%d]: ", i, j);
                        row.add(scanner.nextInt());
                    }
                    matrix.add(row);
                }
                break;
            case 2:
                // Автоматическое заполнение случайными числами
                Random random = new Random();
                for (int i = 0; i < rows; i++) {
                    List<Integer> row = new ArrayList<>();
                    for (int j = 0; j < cols; j++) {
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
                for (int i = 0; i < rows; i++) {
                    List<Integer> row = new ArrayList<>();
                    for (int j = 0; j < cols; j++) {
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
        // Создаем список для хранения количества элементов, превышающих среднее в каждой строке
        List<Integer> aboveAverageCounts = new ArrayList<>();
        // Вычисляем среднее арифметическое и количество элементов, превышающих его, для каждой строки
        System.out.println("\nСредние арифметические и количество элементов, превышающих их:");
        for (int i = 0; i < rows; i++) {
            // Вычисляем среднее арифметическое для строки
            double average = matrix.get(i).stream()
                    .mapToDouble(Integer::doubleValue)
                    .average()
                    .orElse(0.0);
            // Подсчитываем количество элементов, превышающих среднее
            long countAboveAverage = matrix.get(i).stream()
                    .filter(num -> num > average)
                    .count();
            // Добавляем количество в список
            aboveAverageCounts.add((int) countAboveAverage);
            // Вывод среднего и количества для текущей строки
            System.out.printf("Строка %d: Среднее арифметическое = %.2f, Элементов выше среднего = %d%n",
                    (i + 1), average, countAboveAverage);
        }
        // Вывод результирующего списка
        System.out.println("\nСписок количеств элементов, превышающих среднее арифметическое в каждой строке:");
        System.out.println(aboveAverageCounts);
        scanner.close();
    }
}
