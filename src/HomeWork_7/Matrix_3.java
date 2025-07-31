package HomeWork_7;

import java.util.*;
import java.util.Random;
import java.util.Scanner;

/* УСЛОВИЕ ЗАДАЧИ
 3.	Упорядочить элементы в каждой строке в порядке возрастания их значений.
*/

public class Matrix_3 {
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

        // Создаем матрицу как List<List<Integer>> для исходной матрицы
        List<List<Integer>> originalMatrix = new ArrayList<>();
        // Создаем матрицу как List<TreeSet<Integer>> для упорядоченной матрицы
        List<TreeSet<Integer>> sortedMatrix = new ArrayList<>();

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
                    TreeSet<Integer> sortedRow = new TreeSet<>();
                    for (int j = 0; j < cols; j++) {
                        System.out.printf("Элемент [%d][%d]: ", i, j);
                        int value = scanner.nextInt();
                        row.add(value);
                        sortedRow.add(value); // TreeSet автоматически сортирует и исключает дубликаты
                    }
                    originalMatrix.add(row);
                    sortedMatrix.add(sortedRow);
                }
                break;

            case 2:
                // Автоматическое заполнение случайными числами
                Random random = new Random();
                for (int i = 0; i < rows; i++) {
                    List<Integer> row = new ArrayList<>();
                    TreeSet<Integer> sortedRow = new TreeSet<>();
                    for (int j = 0; j < cols; j++) {
                        // Генерируем числа от -100 до 100
                        int value = random.nextInt(201) - 100;
                        row.add(value);
                        sortedRow.add(value); // TreeSet автоматически сортирует и исключает дубликаты
                    }
                    originalMatrix.add(row);
                    sortedMatrix.add(sortedRow);
                }
                break;

            default:
                System.out.println("Ошибка: неверный выбор! Используется автоматическое заполнение.");
                // Заполнение случайными числами по умолчанию
                Random defaultRandom = new Random();
                for (int i = 0; i < rows; i++) {
                    List<Integer> row = new ArrayList<>();
                    TreeSet<Integer> sortedRow = new TreeSet<>();
                    for (int j = 0; j < cols; j++) {
                        int value = defaultRandom.nextInt(201) - 100;
                        row.add(value);
                        sortedRow.add(value);
                    }
                    originalMatrix.add(row);
                    sortedMatrix.add(sortedRow);
                }
        }

        // Вывод исходной матрицы
        System.out.println("\nИсходная матрица:");
        for (List<Integer> row : originalMatrix) {
            System.out.println(row);
        }

        // Вывод упорядоченной матрицы
        System.out.println("\nУпорядоченная матрица (элементы в строках по возрастанию, без дубликатов):");
        for (TreeSet<Integer> row : sortedMatrix) {
            System.out.println(row);
        }

        scanner.close();
    }
}