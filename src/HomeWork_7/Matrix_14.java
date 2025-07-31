package HomeWork_7;

import java.util.*;

/* УСЛОВИЕ ЗАДАЧИ
 14.	Поменять местами элементы строки с максимальной и минимальной суммой элементов.
*/

public class Matrix_14 {
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
        // Создаем результирующую матрицу с переставленными строками
        List<List<Integer>> resultMatrix = new ArrayList<>();
        for (List<Integer> row : matrix) {
            resultMatrix.add(new ArrayList<>(row)); // Копируем исходную матрицу
        }
        // Вычисляем суммы строк и находим индексы строк с максимальной и минимальной суммой
        int maxSumIndex = 0;
        int minSumIndex = 0;
        int maxSum = matrix.getFirst().stream().mapToInt(Integer::intValue).sum();
        int minSum = maxSum;
        for (int i = 0; i < rows; i++) {
            int rowSum = matrix.get(i).stream().mapToInt(Integer::intValue).sum();
            if (rowSum > maxSum) {
                maxSum = rowSum;
                maxSumIndex = i;
            }
            if (rowSum < minSum) {
                minSum = rowSum;
                minSumIndex = i;
            }
        }
        // Вывод сумм строк для наглядности
        System.out.println("\nСуммы элементов в каждой строке:");
        for (int i = 0; i < rows; i++) {
            int rowSum = matrix.get(i).stream().mapToInt(Integer::intValue).sum();
            System.out.printf("Строка %d: Сумма = %d%n", (i + 1), rowSum);
        }
        System.out.printf("Строка с максимальной суммой (%d): %d%n", maxSum, (maxSumIndex + 1));
        System.out.printf("Строка с минимальной суммой (%d): %d%n", minSum, (minSumIndex + 1));
        // Меняем местами строки с максимальной и минимальной суммой
        if (maxSumIndex != minSumIndex) {
            List<Integer> temp = resultMatrix.get(maxSumIndex);
            resultMatrix.set(maxSumIndex, resultMatrix.get(minSumIndex));
            resultMatrix.set(minSumIndex, temp);
        }
        // Вывод результирующей матрицы
        System.out.println("\nМатрица после перестановки строк с максимальной и минимальной суммой:");
        for (List<Integer> row : resultMatrix) {
            System.out.println(row);
        }
        scanner.close();
    }
}