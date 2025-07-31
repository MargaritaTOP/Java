package HomeWork_7;

import java.util.*;
import java.util.stream.Collectors;

/* УСЛОВИЕ ЗАДАЧИ
8.	В каждом столбце отсортировать нечетные числа по убыванию.
*/

public class Matrix_8 {
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
        // Создаем результирующую матрицу с отсортированными нечетными элементами
        List<List<Integer>> sortedMatrix = new ArrayList<>();
        for (List<Integer> row : matrix) {
            sortedMatrix.add(new ArrayList<>(row)); // Копируем исходную матрицу
        }
        // Сортировка нечетных элементов в каждом столбце по убыванию
        for (int j = 0; j < cols; j++) {
            final int colIndex = j;
            // Извлекаем нечетные элементы столбца и сортируем их по убыванию
            List<Integer> oddElements = matrix.stream()
                    .map(row -> row.get(colIndex))
                    .filter(num -> num % 2 != 0)
                    .sorted(Comparator.reverseOrder())
                    .toList();
            // Заменяем нечетные элементы в столбце на отсортированные
            int oddIndex = 0; // Индекс для отсортированных нечетных элементов
            for (int i = 0; i < rows; i++) {
                if (sortedMatrix.get(i).get(colIndex) % 2 != 0) {
                    // Заменяем нечетный элемент на следующий отсортированный
                    if (oddIndex < oddElements.size()) {
                        sortedMatrix.get(i).set(colIndex, oddElements.get(oddIndex));
                        oddIndex++;
                    }
                }
            }
        }
        // Вывод результирующей матрицы
        System.out.println("\nМатрица с отсортированными нечетными элементами в столбцах по убыванию:");
        for (List<Integer> row : sortedMatrix) {
            System.out.println(row);
        }
        scanner.close();
    }
}
