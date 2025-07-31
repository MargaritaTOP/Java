package HomeWork_7;

import java.util.*;

/* УСЛОВИЕ ЗАДАЧИ
 13.	Найти среднеквадратическое отклонение элементов матрицы в каждой строке.
*/

public class Matrix_13 {
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
        // Создаем список для хранения среднеквадратических отклонений для каждой строки
        List<Double> standardDeviations = new ArrayList<>();
        // Вычисляем среднеквадратическое отклонение для каждой строки
        System.out.println("\nСреднеквадратические отклонения для каждой строки:");
        for (int i = 0; i < rows; i++) {
            // Вычисляем среднее арифметическое для строки
            double mean = matrix.get(i).stream()
                    .mapToDouble(Integer::doubleValue)
                    .average()
                    .orElse(0.0);
            // Вычисляем сумму квадратов разностей от среднего
            double sumOfSquaredDifferences = matrix.get(i).stream()
                    .mapToDouble(num -> Math.pow(num - mean, 2))
                    .sum();
            // Вычисляем среднеквадратическое отклонение
            double standardDeviation = Math.sqrt(sumOfSquaredDifferences / cols);
            // Добавляем результат в список
            standardDeviations.add(standardDeviation);
            // Вывод среднего и среднеквадратического отклонения для текущей строки
            System.out.printf("Строка %d: Среднее арифметическое = %.2f, Среднеквадратическое отклонение = %.2f%n",
                    (i + 1), mean, standardDeviation);
        }
        // Вывод результирующего списка
        System.out.println("\nСписок среднеквадратических отклонений для каждой строки:");
        System.out.println(standardDeviations);
        scanner.close();
    }
}
