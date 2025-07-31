package HomeWork_7;

import java.util.*;

/* УСЛОВИЕ ЗАДАЧИ
 18.	Вычислить определитель квадратной матрицы, сделать ее транспонирование
*/

public class Matrix_18 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Запрашиваем размер квадратной матрицы
        System.out.print("Введите размер квадратной матрицы (n): ");
        int n = scanner.nextInt();
        // Проверка корректности ввода
        if (n <= 0) {
            System.out.println("Ошибка: размер матрицы должен быть положительным!");
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
                for (int i = 0; i < n; i++) {
                    List<Integer> row = new ArrayList<>();
                    for (int j = 0; j < n; j++) {
                        System.out.printf("Элемент [%d][%d]: ", i, j);
                        row.add(scanner.nextInt());
                    }
                    matrix.add(row);
                }
                break;
            case 2:
                // Автоматическое заполнение случайными числами
                Random random = new Random();
                for (int i = 0; i < n; i++) {
                    List<Integer> row = new ArrayList<>();
                    for (int j = 0; j < n; j++) {
                        // Генерируем числа от -10 до 10 для удобства вычислений
                        row.add(random.nextInt(21) - 10);
                    }
                    matrix.add(row);
                }
                break;
            default:
                System.out.println("Ошибка: неверный выбор! Используется автоматическое заполнение.");
                // Заполнение случайными числами по умолчанию
                Random defaultRandom = new Random();
                for (int i = 0; i < n; i++) {
                    List<Integer> row = new ArrayList<>();
                    for (int j = 0; j < n; j++) {
                        row.add(defaultRandom.nextInt(21) - 10);
                    }
                    matrix.add(row);
                }
        }
        // Вывод исходной матрицы
        System.out.println("\nИсходная матрица:");
        for (List<Integer> row : matrix) {
            System.out.println(row);
        }
        // Вычисление определителя
        double determinant = calculateDeterminant(matrix);
        System.out.printf("\nОпределитель матрицы: %.2f%n", determinant);
        // Транспонирование матрицы
        List<List<Integer>> transposedMatrix = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> newRow = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                newRow.add(matrix.get(j).get(i));
            }
            transposedMatrix.add(newRow);
        }
        // Вывод транспонированной матрицы
        System.out.println("\nТранспонированная матрица:");
        for (List<Integer> row : transposedMatrix) {
            System.out.println(row);
        }

        scanner.close();
    }
    // Метод для вычисления определителя матрицы
    private static double calculateDeterminant(List<List<Integer>> matrix) {
        int n = matrix.size();
        if (n == 1) {
            return matrix.getFirst().getFirst();
        }
        if (n == 2) {
            return matrix.get(0).get(0) * matrix.get(1).get(1) -
                    matrix.get(0).get(1) * matrix.get(1).get(0);
        }
        double determinant = 0;
        for (int j = 0; j < n; j++) {
            determinant += matrix.getFirst().get(j) * cofactor(matrix, j);
        }
        return determinant;
    }
    // Метод для вычисления минора матрицы
    private static double cofactor(List<List<Integer>> matrix, int col) {
        return Math.pow(-1, col) * minor(matrix, col);
    }
    // Метод для получения минора матрицы
    private static double minor(List<List<Integer>> matrix, int col) {
        List<List<Integer>> subMatrix = new ArrayList<>();
        int n = matrix.size();
        // Создаем подматрицу, исключая указанную строку и столбец
        for (int i = 0; i < n; i++) {
            if (i == 0) continue;
            List<Integer> subRow = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if (j == col) continue;
                subRow.add(matrix.get(i).get(j));
            }
            subMatrix.add(subRow);
        }
        // Рекурсивно вычисляем определитель подматрицы
        return calculateDeterminant(subMatrix);
    }
}