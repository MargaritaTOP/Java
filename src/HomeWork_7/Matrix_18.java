package HomeWork_7;

import java.util.*;

/* УСЛОВИЕ ЗАДАЧИ
 18. Вычислить определитель квадратной матрицы, сделать ее транспонирование
*/
public class Matrix_18 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in); // Создаем объект Scanner для чтения ввода пользователя
        // Запрашиваем размер квадратной матрицы
        System.out.print("Введите размер квадратной матрицы (n): ");
        int n = scanner.nextInt(); // Считываем размер матрицы
        // Проверка корректности ввода
        if (n <= 0) { // Проверяем, что размер матрицы положительный
            System.out.println("Ошибка: размер матрицы должен быть положительным!");
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
                for (int i = 0; i < n; i++) { // Перебираем строки
                    List<Integer> row = new ArrayList<>(); // Создаем новую строку
                    for (int j = 0; j < n; j++) { // Перебираем столбцы
                        System.out.printf("Элемент [%d][%d]: ", i, j); // Запрашиваем элемент
                        row.add(scanner.nextInt()); // Добавляем введенный элемент в строку
                    }
                    matrix.add(row); // Добавляем заполненную строку в матрицу
                }
                break;
            case 2:
                // Автоматическое заполнение случайными числами
                Random random = new Random(); // Создаем объект Random для генерации чисел
                for (int i = 0; i < n; i++) { // Перебираем строки
                    List<Integer> row = new ArrayList<>(); // Создаем новую строку
                    for (int j = 0; j < n; j++) { // Перебираем столбцы
                        // Генерируем числа от -10 до 10 для удобства вычислений
                        row.add(random.nextInt(21) - 10); // Добавляем случайное число в строку
                    }
                    matrix.add(row); // Добавляем заполненную строку в матрицу
                }
                break;
            default:
                System.out.println("Ошибка: неверный выбор! Используется автоматическое заполнение.");
                // Заполнение случайными числами по умолчанию
                Random defaultRandom = new Random(); // Создаем объект Random для случая по умолчанию
                for (int i = 0; i < n; i++) { // Перебираем строки
                    List<Integer> row = new ArrayList<>(); // Создаем новую строку
                    for (int j = 0; j < n; j++) { // Перебираем столбцы
                        row.add(defaultRandom.nextInt(21) - 10); // Добавляем случайное число
                    }
                    matrix.add(row); // Добавляем заполненную строку в матрицу
                }
        }
        // Вывод исходной матрицы
        System.out.println("\nИсходная матрица:");
        for (List<Integer> row : matrix) { // Перебираем строки матрицы
            System.out.println(row); // Выводим каждую строку
        }
        // Вычисление определителя
        double determinant = calculateDeterminant(matrix);
        System.out.printf("\nОпределитель матрицы: %.2f%n", determinant);
        // Транспонирование матрицы
        List<List<Integer>> transposedMatrix = new ArrayList<>(); // Инициализируем транспонированную матрицу
        for (int i = 0; i < n; i++) { // Перебираем строки
            List<Integer> newRow = new ArrayList<>(); // Создаем новую строку
            for (int j = 0; j < n; j++) { // Перебираем столбцы
                newRow.add(matrix.get(j).get(i)); // Заполняем строку элементами столбца
            }
            transposedMatrix.add(newRow); // Добавляем новую строку в матрицу
        }
        // Вывод транспонированной матрицы
        System.out.println("\nТранспонированная матрица:");
        for (List<Integer> row : transposedMatrix) { // Перебираем строки транспонированной матрицы
            System.out.println(row); // Выводим каждую строку
        }

        scanner.close(); // Закрываем Scanner для освобождения ресурсов
    }
    // Метод для вычисления определителя матрицы
    private static double calculateDeterminant(List<List<Integer>> matrix) {
        int n = matrix.size();
        if (n == 1) {
            return matrix.getFirst().getFirst(); // Определитель матрицы 1x1
        }
        if (n == 2) {
            return matrix.get(0).get(0) * matrix.get(1).get(1) -
                    matrix.get(0).get(1) * matrix.get(1).get(0); // Определитель матрицы 2x2
        }
        double determinant = 0;
        for (int j = 0; j < n; j++) { // Разложение по первой строке
            determinant += matrix.getFirst().get(j) * cofactor(matrix, j);
        }
        return determinant;
    }
    // Метод для вычисления минора матрицы
    private static double cofactor(List<List<Integer>> matrix, int col) {
        return Math.pow(-1, col) * minor(matrix, col); // Кофактор = (-1)^col * минор
    }
    // Метод для получения минора матрицы
    private static double minor(List<List<Integer>> matrix, int col) {
        List<List<Integer>> subMatrix = new ArrayList<>(); // Создаем подматрицу
        int n = matrix.size();
        // Создаем подматрицу, исключая первую строку и указанный столбец
        for (int i = 0; i < n; i++) {
            if (i == 0) continue; // Пропускаем первую строку
            List<Integer> subRow = new ArrayList<>(); // Создаем новую строку подматрицы
            for (int j = 0; j < n; j++) {
                if (j == col) continue; // Пропускаем указанный столбец
                subRow.add(matrix.get(i).get(j)); // Добавляем элемент в подматрицу
            }
            subMatrix.add(subRow); // Добавляем строку в подматрицу
        }
        // Рекурсивно вычисляем определитель подматрицы
        return calculateDeterminant(subMatrix);
    }
}