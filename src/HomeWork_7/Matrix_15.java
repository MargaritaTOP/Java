package HomeWork_7;

import java.util.*;

/* УСЛОВИЕ ЗАДАЧИ
 15.	Поменять местами элементы столбцов, содержащих бо́льшее и ме́ньшее число положительных элементов.
*/

public class Matrix_15 {
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
        // Создаем результирующую матрицу с переставленными столбцами
        List<List<Integer>> resultMatrix = new ArrayList<>();
        for (List<Integer> row : matrix) {
            resultMatrix.add(new ArrayList<>(row)); // Копируем исходную матрицу
        }
        // Вычисляем количество положительных элементов в каждом столбце
        List<Integer> positiveCounts = new ArrayList<>();
        for (int j = 0; j < cols; j++) {
            final int colIndex = j;
            long positiveCount = matrix.stream()
                    .map(row -> row.get(colIndex))
                    .filter(num -> num > 0)
                    .count();
            positiveCounts.add((int) positiveCount);
        }
        // Находим индексы столбцов с максимальным и минимальным количеством положительных элементов
        int maxPositiveIndex = 0;
        int minPositiveIndex = 0;
        int maxPositiveCount = positiveCounts.getFirst();
        int minPositiveCount = positiveCounts.getFirst();
        for (int j = 0; j < cols; j++) {
            int count = positiveCounts.get(j);
            if (count > maxPositiveCount) {
                maxPositiveCount = count;
                maxPositiveIndex = j;
            }
            if (count < minPositiveCount) {
                minPositiveCount = count;
                minPositiveIndex = j;
            }
        }
        // Вывод количества положительных элементов в каждом столбце для наглядности
        System.out.println("\nКоличество положительных элементов в каждом столбце:");
        for (int j = 0; j < cols; j++) {
            System.out.printf("Столбец %d: Количество положительных = %d%n", (j + 1), positiveCounts.get(j));
        }
        System.out.printf("Столбец с максимальным количеством положительных (%d): %d%n", maxPositiveCount, (maxPositiveIndex + 1));
        System.out.printf("Столбец с минимальным количеством положительных (%d): %d%n", minPositiveCount, (minPositiveIndex + 1));
        // Меняем местами столбцы с максимальным и минимальным количеством положительных элементов
        if (maxPositiveIndex != minPositiveIndex) {
            for (int i = 0; i < rows; i++) {
                int temp = resultMatrix.get(i).get(maxPositiveIndex);
                resultMatrix.get(i).set(maxPositiveIndex, resultMatrix.get(i).get(minPositiveIndex));
                resultMatrix.get(i).set(minPositiveIndex, temp);
            }
        }
        // Вывод результирующей матрицы
        System.out.println("\nМатрица после перестановки столбцов с максимальным и минимальным количеством положительных элементов:");
        for (List<Integer> row : resultMatrix) {
            System.out.println(row);
        }
        scanner.close();
    }
}