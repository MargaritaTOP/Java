package HomeWork_7;

import java.util.*;
import java.util.stream.Collectors;

/* УСЛОВИЕ ЗАДАЧИ
4. Упорядочить элементы в каждом столбце в порядке убывания их значений
*/
public class Matrix_4 {
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
        // Создаем матрицу как List<List<Integer>> для исходной матрицы
        List<List<Integer>> originalMatrix = new ArrayList<>(); // Инициализируем исходную матрицу
        // Создаем матрицу как List<List<Integer>> для упорядоченной матрицы
        List<List<Integer>> sortedMatrix = new ArrayList<>(); // Инициализируем упорядоченную матрицу
        for (int i = 0; i < rows; i++) { // Заполняем sortedMatrix строками из нулей
            sortedMatrix.add(new ArrayList<>(Collections.nCopies(cols, 0))); // Инициализация строк нулями
        }
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
                    originalMatrix.add(row); // Добавляем заполненную строку в исходную матрицу
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
                    originalMatrix.add(row); // Добавляем заполненную строку в исходную матрицу
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
                    originalMatrix.add(row); // Добавляем заполненную строку в исходную матрицу
                }
        }
        // Вывод исходной матрицы
        System.out.println("\nИсходная матрица:");
        for (List<Integer> row : originalMatrix) { // Перебираем строки исходной матрицы
            System.out.println(row); // Выводим каждую строку
        }
        // Упорядочивание элементов в каждом столбце по убыванию с использованием TreeSet
        for (int j = 0; j < cols; j++) { // Перебираем столбцы
            final int colIndex = j; // Сохраняем индекс столбца как константу для лямбда-выражения
            // Создаем TreeSet с компаратором для сортировки по убыванию
            TreeSet<Integer> sortedColumn = new TreeSet<>(Comparator.reverseOrder());
            // Извлекаем элементы столбца с использованием Parallel Stream API
            originalMatrix.parallelStream() //  parallelStream()
                    .map(row -> row.get(colIndex)) // Извлекаем элементы j-го столбца
                    .forEach(sortedColumn::add); // TreeSet сортирует по убыванию и исключает дубликаты
            // Заполняем отсортированный столбец в новой матрице
            Iterator<Integer> iterator = sortedColumn.iterator(); // Получаем итератор для TreeSet
            for (int i = 0; i < rows && iterator.hasNext(); i++) { // Заполняем элементы столбца
                sortedMatrix.get(i).set(colIndex, iterator.next()); // Устанавливаем отсортированный элемент
            }
            // Если в столбце меньше элементов из-за исключения дубликатов, заполняем оставшиеся нули
            for (int i = sortedColumn.size(); i < rows; i++) {
                sortedMatrix.get(i).set(colIndex, 0); // Заполняем нули
            }
        }
        // Вывод упорядоченной матрицы
        System.out.println("\nУпорядоченная матрица (элементы в столбцах по убыванию, без дубликатов):");
        for (List<Integer> row : sortedMatrix) { // Перебираем строки упорядоченной матрицы
            System.out.println(row); // Выводим каждую строку
        }
        scanner.close();
    }
}