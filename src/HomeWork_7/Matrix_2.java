package HomeWork_7;

import java.util.*;
import java.util.stream.Collectors;

/* УСЛОВИЕ ЗАДАЧИ
 2.	Составить словарь частот чисел в каждой строке (Dictionary<int, int>)
 и упорядочить его по убыванию (для этого уменьшите max для генератора случайных и чисел
 и дайте побольше размер матрицы).
*/

public class Matrix_2 {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
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
                        // Генерируем числа от -10 до 10
                        row.add(random.nextInt(21) - 10);
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
                        row.add(defaultRandom.nextInt(21) - 10);
                    }
                    matrix.add(row);
                }
        }
        // Вывод матрицы для проверки
        System.out.println("\nВаша матрица:");
        for (List<Integer> row : matrix) { // Перебираем строки матрицы
            System.out.println(row); // Выводим каждую строку
        }
        // Вычисление частот чисел в каждой строке
        System.out.println("\nЧастоты чисел в каждой строке (упорядочены по убыванию частоты):");
        for (int i = 0; i < rows; i++) { // Перебираем строки
            // Создаем словарь частот для текущей строки
            Map<Integer, Long> frequencyMap = matrix.get(i).parallelStream()
                    .collect(Collectors.groupingBy(
                            num -> num, // Ключ - число
                            Collectors.counting() // Значение - частота
                    ));
            // Сортируем словарь по убыванию частоты, а при равных частотах - по числу
            Map<Integer, Long> sortedFrequencyMap = frequencyMap.entrySet().parallelStream()
                    .sorted((e1, e2) -> {
                        int compareByCount = e2.getValue().compareTo(e1.getValue()); // По убыванию частоты
                        if (compareByCount == 0) {
                            return e2.getKey().compareTo(e1.getKey()); // При равной частоте - по убыванию числа
                        }
                        return compareByCount;
                    })
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new // Сохраняем порядок
                    ));
            // Выводим словарь частот для строки
            System.out.printf("Строка %d: %s%n", (i + 1), sortedFrequencyMap);
        }
        scanner.close();
    }
}