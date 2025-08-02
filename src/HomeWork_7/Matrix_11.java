package HomeWork_7;

import java.util.*;

/* УСЛОВИЕ ЗАДАЧИ
11. Выяснить, сколько элементов в каждой строке матрицы превышают среднее арифметическое значение, определенное для этой строки.
Из найденных величин составить новый список (массив).
*/
public class Matrix_11 {
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
        // Создаем матрицу как List<List<Integer>>
        List<List<Integer>> matrix = new ArrayList<>(); // Инициализируем пустую матрицу как список списков
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
                    List<Integer> row = new ArrayList<>(); // Создаем новую строку
                    for (int j = 0; j < cols; j++) { // Перебираем столбцы
                        System.out.printf("Элемент [%d][%d]: ", i, j); // Запрашиваем элемент
                        row.add(scanner.nextInt()); // Добавляем введенный элемент в строку
                    }
                    matrix.add(row); // Добавляем заполненную строку в матрицу
                }
                break;
            case 2:
                // Автоматическое заполнение случайными числами
                Random random = new Random(); // Создаем объект Random для генерации чисел
                for (int i = 0; i < rows; i++) { // Перебираем строки
                    List<Integer> row = new ArrayList<>(); // Создаем новую строку
                    for (int j = 0; j < cols; j++) { // Перебираем столбцы
                        // Генерируем числа от -100 до 100
                        row.add(random.nextInt(201) - 100); // Добавляем случайное число в строку
                    }
                    matrix.add(row); // Добавляем заполненную строку в матрицу
                }
                break;
            default:
                System.out.println("Ошибка: неверный выбор! Используется автоматическое заполнение.");
                // Заполнение случайными числами по умолчанию
                Random defaultRandom = new Random(); // Создаем объект Random для случая по умолчанию
                for (int i = 0; i < rows; i++) { // Перебираем строки
                    List<Integer> row = new ArrayList<>(); // Создаем новую строку
                    for (int j = 0; j < cols; j++) { // Перебираем столбцы
                        row.add(defaultRandom.nextInt(201) - 100); // Добавляем случайное число
                    }
                    matrix.add(row); // Добавляем заполненную строку в матрицу
                }
        }
        // Вывод исходной матрицы
        System.out.println("\nИсходная матрица:");
        for (List<Integer> row : matrix) { // Перебираем строки матрицы
            System.out.println(row); // Выводим каждую строку
        }
        // Создаем список для хранения количества элементов, превышающих среднее в каждой строке
        List<Integer> aboveAverageCounts = new ArrayList<>(); // Инициализируем список для результатов
        // Вычисляем среднее арифметическое и количество элементов, превышающих его, для каждой строки
        System.out.println("\nСредние арифметические и количество элементов, превышающих их:");
        for (int i = 0; i < rows; i++) { // Перебираем строки
            // Вычисляем среднее арифметическое для строки
            double average = matrix.get(i).parallelStream() // parallelStream
                    .mapToDouble(Integer::doubleValue) // Преобразуем в double
                    .average()
                    .orElse(0.0);
            // Подсчитываем количество элементов, превышающих среднее
            long countAboveAverage = matrix.get(i).parallelStream() // parallelStream
                    .filter(num -> num > average) // Фильтруем элементы больше среднего
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