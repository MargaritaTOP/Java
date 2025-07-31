package HomeWork_7;

import java.util.*;
import java.util.stream.Collectors;

/* УСЛОВИЕ ЗАДАЧИ
4.	Упорядочить элементы в каждом столбце в порядке убывания их значений
*/

public class Matrix_4 {
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
        // Создаем матрицу как List<List<Integer>> для исходной матрицы
        List<List<Integer>> originalMatrix = new ArrayList<>();
        // Создаем матрицу как List<List<Integer>> для упорядоченной матрицы
        List<List<Integer>> sortedMatrix = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            sortedMatrix.add(new ArrayList<>(Collections.nCopies(cols, 0))); // Инициализация строк нулями
        }
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
                    originalMatrix.add(row);
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
                    originalMatrix.add(row);
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
                    originalMatrix.add(row);
                }
        }
        // Вывод исходной матрицы
        System.out.println("\nИсходная матрица:");
        for (List<Integer> row : originalMatrix) {
            System.out.println(row);
        }
        // Упорядочивание элементов в каждом столбце по убыванию с использованием TreeSet
        for (int j = 0; j < cols; j++) {
            final int colIndex = j;
            // Создаем TreeSet с компаратором для сортировки по убыванию
            TreeSet<Integer> sortedColumn = new TreeSet<>(Comparator.reverseOrder());
            // Извлекаем элементы столбца
            originalMatrix.stream()
                    .map(row -> row.get(colIndex))
                    .forEach(sortedColumn::add); // TreeSet сортирует по убыванию и исключает дубликаты
            // Заполняем отсортированный столбец в новой матрице
            Iterator<Integer> iterator = sortedColumn.iterator();
            for (int i = 0; i < rows && iterator.hasNext(); i++) {
                sortedMatrix.get(i).set(colIndex, iterator.next());
            }
            // Если в столбце меньше элементов из-за исключения дубликатов, заполняем оставшиеся нули
            for (int i = sortedColumn.size(); i < rows; i++) {
                sortedMatrix.get(i).set(colIndex, 0);
            }
        }
        // Вывод упорядоченной матрицы
        System.out.println("\nУпорядоченная матрица (элементы в столбцах по убыванию, без дубликатов):");
        for (List<Integer> row : sortedMatrix) {
            System.out.println(row);
        }
        scanner.close();
    }
}