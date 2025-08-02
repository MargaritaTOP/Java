package HomeWork_7;

import java.util.*;

/* УСЛОВИЕ ЗАДАЧИ
 14. Поменять местами элементы строки с максимальной и минимальной суммой элементов.
*/
public class Matrix_14 {
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
        // Создаем результирующую матрицу с переставленными строками
        List<List<Integer>> resultMatrix = new ArrayList<>(); // Инициализируем результирующую матрицу
        for (List<Integer> row : matrix) { // Копируем исходную матрицу
            resultMatrix.add(new ArrayList<>(row)); // Копия каждой строки
        }
        // Вычисляем суммы строк и находим индексы строк с максимальной и минимальной суммой
        int maxSumIndex = 0;
        int minSumIndex = 0;
        int maxSum = matrix.getFirst().parallelStream().mapToInt(Integer::intValue).sum(); // parallelStream
        int minSum = maxSum;
        for (int i = 0; i < rows; i++) { // Перебираем строки
            int rowSum = matrix.get(i).parallelStream().mapToInt(Integer::intValue).sum(); // parallelStream
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
        for (int i = 0; i < rows; i++) { // Перебираем строки
            int rowSum = matrix.get(i).parallelStream().mapToInt(Integer::intValue).sum(); // parallelStream
            System.out.printf("Строка %d: Сумма = %d%n", (i + 1), rowSum);
        }
        System.out.printf("Строка с максимальной суммой (%d): %d%n", maxSum, (maxSumIndex + 1));
        System.out.printf("Строка с минимальной суммой (%d): %d%n", minSum, (minSumIndex + 1));
        // Меняем местами строки с максимальной и минимальной суммой
        if (maxSumIndex != minSumIndex) { // Проверяем, что строки разные
            List<Integer> temp = resultMatrix.get(maxSumIndex);
            resultMatrix.set(maxSumIndex, resultMatrix.get(minSumIndex));
            resultMatrix.set(minSumIndex, temp);
        }
        // Вывод результирующей матрицы
        System.out.println("\nМатрица после перестановки строк с максимальной и минимальной суммой:");
        for (List<Integer> row : resultMatrix) { // Перебираем строки результирующей матрицы
            System.out.println(row); // Выводим каждую строку
        }
        scanner.close();
    }
}