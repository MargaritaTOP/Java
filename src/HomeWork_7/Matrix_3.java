package HomeWork_7;

import java.util.*;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

/* УСЛОВИЕ ЗАДАЧИ
 3. Упорядочить элементы в каждой строке в порядке возрастания их значений.
*/
public class Matrix_3 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in); // Создаем объект Scanner для чтения ввода пользователя

        // Запрашиваем количество строк и столбцов
        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt(); // Считываем количество строк
        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt(); // Считываем количество столбцов

        // Проверка корректности ввода
        if (rows <= 0 || cols <= 0) { // Проверяем, что размеры матрицы положительные
            System.out.println("Ошибка: количество строк и столбцов должно быть положительным!");
            return; // Завершаем программу при некорректном вводе
        }

        // Создаем матрицу как List<List<Integer>> для исходной матрицы
        List<List<Integer>> originalMatrix = new ArrayList<>(); // Инициализируем исходную матрицу
        // Создаем матрицу как List<List<Integer>> для упорядоченной матрицы
        List<List<Integer>> sortedMatrix = new ArrayList<>(); // Инициализируем упорядоченную матрицу

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
                for (int i = 0; i < rows; i++) { // Перебираем строки
                    List<Integer> row = new ArrayList<>(); // Создаем новую строку для исходной матрицы
                    for (int j = 0; j < cols; j++) { // Перебираем столбцы
                        System.out.printf("Элемент [%d][%d]: ", i, j); // Запрашиваем элемент
                        int value = scanner.nextInt(); // Считываем элемент
                        row.add(value); // Добавляем элемент в строку
                    }
                    originalMatrix.add(row); // Добавляем заполненную строку в исходную матрицу
                    // Сортируем строку с использованием Parallel Stream API
                    List<Integer> sortedRow = row.parallelStream() // Используем parallelStream для сортировки
                            .sorted() // Сортируем элементы по возрастанию
                            .collect(Collectors.toList()); // Собираем в список
                    sortedMatrix.add(sortedRow); // Добавляем отсортированную строку в упорядоченную матрицу
                }
                break;

            case 2:
                // Автоматическое заполнение случайными числами
                Random random = new Random(); // Создаем объект Random для генерации чисел
                for (int i = 0; i < rows; i++) { // Перебираем строки
                    List<Integer> row = new ArrayList<>(); // Создаем новую строку для исходной матрицы
                    for (int j = 0; j < cols; j++) { // Перебираем столбцы
                        // Генерируем числа от -100 до 100
                        int value = random.nextInt(201) - 100; // Генерируем случайное число
                        row.add(value); // Добавляем элемент в строку
                    }
                    originalMatrix.add(row); // Добавляем заполненную строку в исходную матрицу
                    // Сортируем строку с использованием Parallel Stream API
                    List<Integer> sortedRow = row.parallelStream() // Используем parallelStream для сортировки
                            .sorted() // Сортируем элементы по возрастанию
                            .collect(Collectors.toList()); // Собираем в список
                    sortedMatrix.add(sortedRow); // Добавляем отсортированную строку в упорядоченную матрицу
                }
                break;
            default:
                System.out.println("Ошибка: неверный выбор! Используется автоматическое заполнение.");
                // Заполнение случайными числами по умолчанию
                Random defaultRandom = new Random(); // Создаем объект Random для случая по умолчанию
                for (int i = 0; i < rows; i++) { // Перебираем строки
                    List<Integer> row = new ArrayList<>(); // Создаем новую строку для исходной матрицы
                    for (int j = 0; j < cols; j++) { // Перебираем столбцы
                        int value = defaultRandom.nextInt(201) - 100; // Генерируем случайное число
                        row.add(value); // Добавляем элемент в строку
                    }
                    originalMatrix.add(row); // Добавляем заполненную строку в исходную матрицу
                    // Сортируем строку с использованием Parallel Stream API
                    List<Integer> sortedRow = row.parallelStream() // Используем parallelStream для сортировки
                            .sorted() // Сортируем элементы по возрастанию
                            .collect(Collectors.toList()); // Собираем в список
                    sortedMatrix.add(sortedRow); // Добавляем отсортированную строку в упорядоченную матрицу
                }
        }
        // Вывод исходной матрицы
        System.out.println("\nИсходная матрица:");
        for (List<Integer> row : originalMatrix) { // Перебираем строки исходной матрицы
            System.out.println(row); // Выводим каждую строку
        }
        // Вывод упорядоченной матрицы
        System.out.println("\nУпорядоченная матрица (элементы в строках по возрастанию):");
        for (List<Integer> row : sortedMatrix) { // Перебираем строки упорядоченной матрицы
            System.out.println(row); // Выводим каждую строку
        }
        scanner.close();
    }
}