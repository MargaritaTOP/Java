package HomeWork_7;

import java.util.*;


/* УСЛОВИЕ ЗАДАЧИ
 6.	Найти сумму и среднее арифметическое нечетных элементов в каждом столбце
*/

public class Matrix_6 {
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
        // Вывод матрицы для проверки
        System.out.println("\nВаша матрица:");
        for (List<Integer> row : matrix) {
            System.out.println(row);
        }
        // Вычисление суммы и среднего арифметического нечетных элементов в каждом столбце
        System.out.println("\nРезультаты для нечетных элементов в каждом столбце:");
        for (int j = 0; j < cols; j++) {
            final int colIndex = j;
            // Извлекаем нечетные элементы столбца
            List<Integer> oddElements = matrix.stream()
                    .map(row -> row.get(colIndex))
                    .filter(num -> num % 2 != 0) // Фильтруем нечетные элементы
                    .toList();
            // Вычисляем сумму нечетных элементов
            int oddSum = oddElements.stream()
                    .mapToInt(Integer::intValue)
                    .sum();
            // Вычисляем среднее арифметическое нечетных элементов
            double oddAverage = oddElements.isEmpty() ? 0 :
                    oddElements.stream()
                            .mapToDouble(Integer::doubleValue)
                            .average()
                            .orElse(0);
            // Вывод результатов
            System.out.printf("Сумма нечетных элементов в %d-м столбце: %d%n",
                    (colIndex + 1), oddSum);
            if (oddElements.isEmpty()) {
                System.out.printf("Среднее арифметическое нечетных элементов в %d-м столбце: нет нечетных элементов%n",
                        (colIndex + 1));
            } else {
                System.out.printf("Среднее арифметическое нечетных элементов в %d-м столбце: %.2f%n",
                        (colIndex + 1), oddAverage);
            }
        }
        scanner.close();
    }
}