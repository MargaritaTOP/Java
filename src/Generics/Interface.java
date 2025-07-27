package Generics;

// Generic интерфейс
interface Pair<K, V> {
    K getKey();
    V getValue();
}

// Реализация интерфейса
class OrderedPair<K, V> implements Pair<K, V> {
    private K key;
    private V value;

    public OrderedPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() { return key; }
    @Override
    public V getValue() { return value; }
}

public class Interface {
    public static void main(String[] args) {
        Pair<String, Integer> p1 = new OrderedPair<>("Age", 30);
        Pair<String, String> p2 = new OrderedPair<>("Name", "John");

        System.out.println(p1.getKey() + ": " + p1.getValue()); // Age: 30
        System.out.println(p2.getKey() + ": " + p2.getValue()); // Name: John
    }
}