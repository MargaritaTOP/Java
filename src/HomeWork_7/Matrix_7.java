package HomeWork_7;

import java.util.*;
import java.util.stream.Collectors;

/* УСЛОВИЕ ЗАДАЧИ
 7.	В каждой строке матрицы отсортировать четные числа по возрастанию
*/

public class Matrix_7 {
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
        // Создаем результирующую матрицу с отсортированными четными элементами
        List<List<Integer>> sortedMatrix = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            // Извлекаем четные элементы строки и сортируем их по возрастанию
            List<Integer> evenElements = matrix.get(i).stream()
                    .filter(num -> num % 2 == 0)
                    .sorted()
                    .toList();
            // Создаем новую строку, сохраняя нечетные элементы на своих местах
            List<Integer> newRow = new ArrayList<>(matrix.get(i));
            int evenIndex = 0; // Индекс для отсортированных четных элементов
            for (int j = 0; j < cols; j++) {
                if (newRow.get(j) % 2 == 0) {
                    // Заменяем четный элемент на следующий отсортированный
                    if (evenIndex < evenElements.size()) {
                        newRow.set(j, evenElements.get(evenIndex));
                        evenIndex++;
                    }
                }
            }
            sortedMatrix.add(newRow);
        }
        // Вывод результирующей матрицы
        System.out.println("\nМатрица с отсортированными четными элементами в строках по возрастанию:");
        for (List<Integer> row : sortedMatrix) {
            System.out.println(row);
        }
        scanner.close();
    }
}