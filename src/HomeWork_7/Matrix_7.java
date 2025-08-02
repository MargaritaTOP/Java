package HomeWork_7;

import java.util.*;
import java.util.stream.Collectors;

/* УСЛОВИЕ ЗАДАЧИ
 7. В каждой строке матрицы отсортировать четные числа по возрастанию
*/
public class Matrix_7 {
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
        // Создаем результирующую матрицу с отсортированными четными элементами
        List<List<Integer>> sortedMatrix = new ArrayList<>(); // Инициализируем результирующую матрицу
        for (int i = 0; i < rows; i++) { // Перебираем строки
            // Извлекаем четные элементы строки и сортируем их по возрастанию
            List<Integer> evenElements = matrix.get(i).parallelStream() // parallelStream
                    .filter(num -> num % 2 == 0) // Фильтруем четные элементы
                    .sorted() // Сортируем по возрастанию
                    .toList();
            // Создаем новую строку, сохраняя нечетные элементы на своих местах
            List<Integer> newRow = new ArrayList<>(matrix.get(i)); // Копия исходной строки
            int evenIndex = 0; // Индекс для отсортированных четных элементов
            for (int j = 0; j < cols; j++) { // Перебираем элементы строки
                if (newRow.get(j) % 2 == 0) { // Если элемент четный
                    // Заменяем четный элемент на следующий отсортированный
                    if (evenIndex < evenElements.size()) {
                        newRow.set(j, evenElements.get(evenIndex));
                        evenIndex++;
                    }
                }
            }
            sortedMatrix.add(newRow); // Добавляем новую строку в результирующую матрицу
        }
        // Вывод результирующей матрицы
        System.out.println("\nМатрица с отсортированными четными элементами в строках по возрастанию:");
        for (List<Integer> row : sortedMatrix) { // Перебираем строки результирующей матрицы
            System.out.println(row); // Выводим каждую строку
        }
        scanner.close();
    }
}