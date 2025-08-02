package HomeWork_7;

import java.util.*;

/* УСЛОВИЕ ЗАДАЧИ
 15. Поменять местами элементы столбцов, содержащих бо́льшее и ме́ньшее число положительных элементов.
*/
public class Matrix_15 {
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
        // Создаем результирующую матрицу с переставленными столбцами
        List<List<Integer>> resultMatrix = new ArrayList<>(); // Инициализируем результирующую матрицу
        for (List<Integer> row : matrix) { // Копируем исходную матрицу
            resultMatrix.add(new ArrayList<>(row)); // Копия каждой строки
        }
        // Вычисляем количество положительных элементов в каждом столбце
        List<Integer> positiveCounts = new ArrayList<>(); // Инициализируем список для количества положительных элементов
        for (int j = 0; j < cols; j++) { // Перебираем столбцы
            final int colIndex = j; // Сохраняем индекс столбца как константу для лямбда-выражения
            long positiveCount = matrix.parallelStream() // parallelStream
                    .map(row -> row.get(colIndex)) // Извлекаем элементы j-го столбца
                    .filter(num -> num > 0) // Фильтруем положительные элементы
                    .count();
            positiveCounts.add((int) positiveCount);
        }
        // Находим индексы столбцов с максимальным и минимальным количеством положительных элементов
        int maxPositiveIndex = 0;
        int minPositiveIndex = 0;
        int maxPositiveCount = positiveCounts.getFirst();
        int minPositiveCount = positiveCounts.getFirst();
        for (int j = 0; j < cols; j++) { // Перебираем столбцы
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
        for (int j = 0; j < cols; j++) { // Перебираем столбцы
            System.out.printf("Столбец %d: Количество положительных = %d%n", (j + 1), positiveCounts.get(j));
        }
        System.out.printf("Столбец с максимальным количеством положительных (%d): %d%n", maxPositiveCount, (maxPositiveIndex + 1));
        System.out.printf("Столбец с минимальным количеством положительных (%d): %d%n", minPositiveCount, (minPositiveIndex + 1));
        // Меняем местами столбцы с максимальным и минимальным количеством положительных элементов
        if (maxPositiveIndex != minPositiveIndex) { // Проверяем, что столбцы разные
            for (int i = 0; i < rows; i++) { // Перебираем строки для обмена элементами
                int temp = resultMatrix.get(i).get(maxPositiveIndex);
                resultMatrix.get(i).set(maxPositiveIndex, resultMatrix.get(i).get(minPositiveIndex));
                resultMatrix.get(i).set(minPositiveIndex, temp);
            }
        }
        // Вывод результирующей матрицы
        System.out.println("\nМатрица после перестановки столбцов с максимальным и минимальным количеством положительных элементов:");
        for (List<Integer> row : resultMatrix) { // Перебираем строки результирующей матрицы
            System.out.println(row); // Выводим каждую строку
        }
        scanner.close();
    }
}