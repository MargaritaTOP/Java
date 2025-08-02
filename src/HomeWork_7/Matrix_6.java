package HomeWork_7;

import java.util.*;

/* УСЛОВИЕ ЗАДАЧИ
 6. Найти сумму и среднее арифметическое нечетных элементов в каждом столбце
*/
public class Matrix_6 {
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
        // Вывод матрицы для проверки
        System.out.println("\nВаша матрица:");
        for (List<Integer> row : matrix) { // Перебираем строки матрицы
            System.out.println(row); // Выводим каждую строку
        }
        // Вычисление суммы и среднего арифметического нечетных элементов в каждом столбце
        System.out.println("\nРезультаты для нечетных элементов в каждом столбце:");
        for (int j = 0; j < cols; j++) { // Перебираем столбцы
            final int colIndex = j; // Сохраняем индекс столбца как константу для лямбда-выражения
            // Извлекаем нечетные элементы столбца
            List<Integer> oddElements = matrix.parallelStream() // parallelStream
                    .map(row -> row.get(colIndex)) // Извлекаем элементы j-го столбца
                    .filter(num -> num % 2 != 0) // Фильтруем нечетные элементы
                    .toList();
            // Вычисляем сумму нечетных элементов
            int oddSum = oddElements.parallelStream() // parallelStream
                    .mapToInt(Integer::intValue)
                    .sum();
            // Вычисляем среднее арифметическое нечетных элементов
            double oddAverage = oddElements.isEmpty() ? 0 :
                    oddElements.parallelStream() // parallelStream
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