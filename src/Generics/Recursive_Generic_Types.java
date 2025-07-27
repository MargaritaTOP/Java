package Generics;

// Интерфейс с рекурсивным типом
interface MyComparable<T> {
    int compareTo(T other);
}
// Класс, реализующий рекурсивный generic
class Person implements Comparable<Person> {
    private final String name;
    private final int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public int compareTo(Person other) {
        return this.age - other.age;
    }
    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}
public class Recursive_Generic_Types {
    public static <T extends Comparable<T>> T max(T a, T b) {
        return a.compareTo(b) > 0 ? a : b;
    }
    public static void main(String[] args) {
        Person p1 = new Person("Alice", 25);
        Person p2 = new Person("Bob", 30);
        System.out.println("Older person: " + max(p1, p2));
    }
}
