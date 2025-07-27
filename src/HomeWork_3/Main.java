package HomeWork_3;



import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите число n: ");
            int n = scanner.nextInt();
            if (n <= 0) {
                throw new IllegalArgumentException("Размер коллекции должен быть положительным");
            }
            ArrayList<Integer> numbers = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                numbers.add(random.nextInt(100) + 1);
            }
            System.out.println("Исходная коллекция: " + numbers);
            CollectionProcessor.processCollection(numbers);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}