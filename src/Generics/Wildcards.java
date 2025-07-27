package Generics;
//Wildcards (Подстановки)
import java.util.List;
import java.util.Arrays;

public class Wildcards  {
    // Метод принимает List любого типа, но только для чтения
    public static void printList(List<?> list) {
        for (Object elem : list) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }
    // Метод принимает List чисел (Number и его подклассы)
    public static double sumOfList(List<? extends Number> list) {
        double sum = 0.0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }
    // Метод принимает List Integer и его суперклассов
    public static void addNumbers(List<? super Integer> list) {
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
    }
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3);
        List<Double> doubleList = Arrays.asList(1.1, 2.2, 3.3);
        List<Object> objList = Arrays.asList("one", 2, 3.3);
        printList(intList);
        printList(doubleList);
        printList(objList);
        System.out.println("Sum of intList: " + sumOfList(intList));
        System.out.println("Sum of doubleList: " + sumOfList(doubleList));
        List<Number> numberList = new java.util.ArrayList<>();
        addNumbers(numberList);
        System.out.println("Number list after adding: " + numberList);
    }
}
