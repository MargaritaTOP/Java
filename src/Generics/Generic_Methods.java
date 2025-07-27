package Generics;

public class Generic_Methods {
    // Generic метод с одним параметром типа
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
    // Generic метод с несколькими параметрами типа
    public static <T, U> void printPair(T first, U second) {
        System.out.println("First: " + first + ", Second: " + second);
    }
    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 4, 5};
        String[] stringArray = {"Hello", "World"};
        printArray(intArray);
        printArray(stringArray);
        printPair(10, "Text");
        printPair(3.14, true);
    }
}
