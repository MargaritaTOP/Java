package HomeWork_7;

import java.util.*;

/* УСЛОВИЕ ЗАДАЧИ
 13. Найти среднеквадратическое отклонение элементов матрицы в каждой строке.
*/
public class Matrix_13 {
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
        // Создаем список для хранения среднеквадратических отклонений для каждой строки
        List<Double> standardDeviations = new ArrayList<>(); // Инициализируем список для результатов
        // Вычисляем среднеквадратическое отклонение для каждой строки
        System.out.println("\nСреднеквадратические отклонения для каждой строки:");
        for (int i = 0; i < rows; i++) { // Перебираем строки
            // Вычисляем среднее арифметическое для строки
            double mean = matrix.get(i).parallelStream() // parallelStream
                    .mapToDouble(Integer::doubleValue) // Преобразуем в double
                    .average()
                    .orElse(0.0);
            // Вычисляем сумму квадратов разностей от среднего
            double sumOfSquaredDifferences = matrix.get(i).parallelStream() // parallelStream
                    .mapToDouble(num -> Math.pow(num - mean, 2)) // Вычисляем квадрат разности
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