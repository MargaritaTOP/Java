package HomeWork_5;

import java.util.*;
import java.util.function.Predicate;

public class SelectiveAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        // Ввод размера коллекции
        System.out.print("Введите размер коллекции: ");
        int size = scanner.nextInt();
        // Заполнение коллекции случайными числами
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            numbers.add(random.nextInt(100) + 1); // числа от 1 до 100
        }
        System.out.println("Сгенерированная коллекция: " + numbers);
        // Выбор типа позиций для расчета среднего
        System.out.println("\nВыберите тип позиций для расчета среднего:");
        System.out.println("1 - Нечетные позиции");
        System.out.println("2 - Четные позиции");
        System.out.println("3 - Кратные 3");
        System.out.println("4 - Кратные 4");
        System.out.println("5 - Кратные N (введите число)");
        System.out.print("Ваш выбор: ");
        int choice = scanner.nextInt();
        Predicate<Integer> positionFilter;
        switch (choice) {
            case 1:
                positionFilter = SelectiveAverage::isOddPosition;
                break;
            case 2:
                positionFilter = SelectiveAverage::isEvenPosition;
                break;
            case 3:
                positionFilter = SelectiveAverage::isMultipleOf3;
                break;
            case 4:
                positionFilter = SelectiveAverage::isMultipleOf4;
                break;
            case 5:
                System.out.print("Введите число N: ");
                int n = scanner.nextInt();
                positionFilter = pos -> isMultipleOfN(pos, n);
                break;
            default:
                System.out.println("Неверный выбор, используются нечетные позиции");
                positionFilter = SelectiveAverage::isOddPosition;
        }
        // Получаем результат: список чисел и среднее
        Result result = getNumbersAndAverage(numbers, positionFilter);
        // Выводим выбранные числа
        System.out.println("Числа на выбранных позициях из коллекции: " + result.selectedNumbers());
        // Выводим среднее
        System.out.printf("Среднее арифметическое для выбранных позиций из коллекции: %.2f\n", result.average());
        scanner.close();
    }
    // класс record
    public record Result(List<Integer> selectedNumbers, double average) {}
    // Метод, который возвращает и список чисел, и среднее
    public static Result getNumbersAndAverage(List<Integer> numbers, Predicate<Integer> positionFilter) {
        List<Integer> selectedNumbers = new ArrayList<>();
        double sum = 0.0;
        for (int i = 0; i < numbers.size(); i++) {
            int position = i + 1;
            if (positionFilter.test(position)) {
                int value = numbers.get(i);
                selectedNumbers.add(value);
                sum += value;
            }
        }
        double average = selectedNumbers.isEmpty() ? 0.0 : sum / selectedNumbers.size();
        return new Result(selectedNumbers, average); // создаём record
    }
    public static boolean isOddPosition(int position) {
        return position % 2 != 0;
    }
    public static boolean isEvenPosition(int position) {
        return position % 2 == 0;
    }
    public static boolean isMultipleOf3(int position) {
        return position % 3 == 0;
    }
    public static boolean isMultipleOf4(int position) {
        return position % 4 == 0;
    }
    public static boolean isMultipleOfN(int position, int n) {
        return position % n == 0;
    }
}