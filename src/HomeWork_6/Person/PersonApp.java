package HomeWork_6.Person;

import java.util.Comparator;
import java.util.stream.Stream;

// Класс, представляющий человека
record Person(String name, String city, int age) {
    // Для удобного вывода
    @Override
    public String toString() {
        return String.format("%s (%s) - %d лет", name, city, age);
    }
}
// Компаратор для сравнения по возрасту
class AgeComparator implements Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
        return Integer.compare(a.age(), b.age());
        /*
         Альтернативный способ (закомментирован):
         if (a.getAge() < b.getAge()) return -1;
         else if (a.getAge() == b.getAge()) return 0;
         else return 1;
        */
    }
}
// Компаратор для сравнения по имени (без учёта регистра)
class NameComparator implements Comparator<Person> {
    @Override
    public int compare(Person a, Person b) {
        return a.name().toUpperCase().compareTo(b.name().toUpperCase());
    }
}
// Основной класс с методом main
public class PersonApp {
    public static void main(String[] args) {
        // Создаём поток людей
        Stream<Person> personStream = Stream.of(
                new Person("Александр", "Чебоксары", 58),
                new Person("Борис", "Санкт-Петербург", 45),
                new Person("Андрей", "Казань", 24),
                new Person("Дмитрий", "Новосибирск", 31),
                new Person("Елена", "Екатеринбург", 29)
        );
        System.out.println("По имени (в алфавитном порядке, без учёта регистра):");
        personStream
                .sorted(new NameComparator())
                .forEach(System.out::println);
        // Нужно создать новый поток — потоки одноразовые
        Stream<Person> phStream = Stream.of(
                new Person("Александр", "Чебоксары", 58),
                new Person("Борис", "Санкт-Петербург", 45),
                new Person("Андрей", "Казань", 24),
                new Person("Дмитрий", "Новосибирск", 31),
                new Person("Елена", "Екатеринбург", 29)
        );
        System.out.println("\nПо возрасту (от младшего к старшему):");
        phStream
                .sorted(new AgeComparator())
                .forEach(System.out::println);
    }
}
