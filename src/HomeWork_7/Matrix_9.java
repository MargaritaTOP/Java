package HomeWork_7;

import java.util.*;


/* УСЛОВИЕ ЗАДАЧИ
9.	Найти в каждой строке максимальный по абсолютной величине элемент и поменять его местами с первым элементом.
*/

public class Matrix_9 {
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
        // Находим максимальный по абсолютной величине элемент в каждой строке и меняем его с первым
        for (int i = 0; i < rows; i++) {
            // Находим элемент с максимальной абсолютной величиной и его индекс
            List<Integer> row = matrix.get(i);
            int maxAbsIndex = 0;
            int maxAbsValue = Math.abs(row.getFirst());
            for (int j = 1; j < cols; j++) {
                int absValue = Math.abs(row.get(j));
                if (absValue > maxAbsValue) {
                    maxAbsValue = absValue;
                    maxAbsIndex = j;
                }
            }
            // Меняем местами первый элемент и максимальный по абсолютной величине
            if (maxAbsIndex != 0) { // Если максимальный элемент не первый
                int firstElement = resultMatrix.get(i).getFirst();
                int maxAbsElement = resultMatrix.get(i).get(maxAbsIndex);
                resultMatrix.get(i).set(0, maxAbsElement);
                resultMatrix.get(i).set(maxAbsIndex, firstElement);
            }
        }
        // Вывод результирующей матрицы
        System.out.println("\nМатрица после перестановки максимального по абсолютной величине элемента с первым в каждой строке:");
        for (List<Integer> row : resultMatrix) {
            System.out.println(row);
        }
        scanner.close();
    }
}