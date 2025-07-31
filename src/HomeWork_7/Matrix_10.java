package HomeWork_7;

import java.util.*;

/* УСЛОВИЕ ЗАДАЧИ
10.	Найти в каждом столбце минимальный по абсолютной величине элемент и поменять его местами с первым элементом
*/

public class Matrix_10 {
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
        // Создаем результирующую матрицу с переставленными элементами
        List<List<Integer>> resultMatrix = new ArrayList<>();
        for (List<Integer> row : matrix) {
            resultMatrix.add(new ArrayList<>(row)); // Копируем исходную матрицу
        }
        // Находим минимальный по абсолютной величине элемент в каждом столбце и меняем его с первым
        for (int j = 0; j < cols; j++) {
            final int colIndex = j;
            // Находим элемент с минимальной абсолютной величиной и его индекс
            List<Integer> column = matrix.stream()
                    .map(row -> row.get(colIndex))
                    .toList();
            int minAbsIndex = 0;
            int minAbsValue = Math.abs(column.getFirst());
            for (int i = 1; i < rows; i++) {
                int absValue = Math.abs(column.get(i));
                if (absValue < minAbsValue) {
                    minAbsValue = absValue;
                    minAbsIndex = i;
                }
            }
            // Меняем местами первый элемент и минимальный по абсолютной величине
            if (minAbsIndex != 0) { // Если минимальный элемент не первый
                int firstElement = resultMatrix.getFirst().get(colIndex);
                int minAbsElement = resultMatrix.get(minAbsIndex).get(colIndex);
                resultMatrix.getFirst().set(colIndex, minAbsElement);
                resultMatrix.get(minAbsIndex).set(colIndex, firstElement);
            }
        }
        // Вывод результирующей матрицы
        System.out.println("\nМатрица после перестановки минимального по абсолютной величине элемента с первым в каждом столбце:");
        for (List<Integer> row : resultMatrix) {
            System.out.println(row);
        }
        scanner.close();
    }
}
