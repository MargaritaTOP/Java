package HomeWork_7;

import java.util.*;

/* УСЛОВИЕ ЗАДАЧИ
9. Найти в каждой строке максимальный по абсолютной величине элемент и поменять его местами с первым элементом.
*/
public class Matrix_9 {
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
        // Создаем результирующую матрицу с переставленными элементами
        List<List<Integer>> resultMatrix = new ArrayList<>(); // Инициализируем результирующую матрицу
        for (List<Integer> row : matrix) { // Копируем исходную матрицу
            resultMatrix.add(new ArrayList<>(row)); // Копия каждой строки
        }
        // Находим максимальный по абсолютной величине элемент в каждой строке и меняем его с первым
        for (int i = 0; i < rows; i++) { // Перебираем строки
            // Находим элемент с максимальной абсолютной величиной и его индекс
            List<Integer> row = matrix.get(i);
            int maxAbsIndex = 0;
            int maxAbsValue = Math.abs(row.getFirst());
            for (int j = 1; j < cols; j++) { // Перебираем элементы строки
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
        for (List<Integer> row : resultMatrix) { // Перебираем строки результирующей матрицы
            System.out.println(row); // Выводим каждую строку
        }
        scanner.close(); // Закрываем Scanner для освобождения ресурсов
    }
}