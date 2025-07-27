package Generics;

// Generic интерфейс
interface Pair<K, V> {
    K key();
    V value();
}

// Реализация интерфейса
record OrderedPair<K, V>(K key, V value) implements Pair<K, V> {
}

public class Interface {
    public static void main(String[] args) {
        Pair<String, Integer> p1 = new OrderedPair<>("Age", 30);
        Pair<String, String> p2 = new OrderedPair<>("Name", "John");

        System.out.println(p1.key() + ": " + p1.value()); // Age: 30
        System.out.println(p2.key() + ": " + p2.value()); // Name: John
    }
}