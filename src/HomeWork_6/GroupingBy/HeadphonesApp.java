package HomeWork_6.GroupingBy;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Основной класс с методом main - GroupingBy
public class HeadphonesApp {
    public static void main(String[] args) {
        // Создаём поток (Stream) из объектов Headphones
        Stream<Headphones> headphonesStream = Stream.of(
                new Headphones("AirPods Pro", "Apple", 18990),
                new Headphones("Galaxy Buds2 Pro", "Samsung", 9670),
                new Headphones("AirPods", "Apple", 15074),
                new Headphones("WH-1000XM5", "Sony", 31200),
                new Headphones("WF-1000XM4", "Sony", 30611),
                new Headphones("Pixel Buds Pro", "Google", 12490)
        );

        // Группируем наушники по бренду (используем метод brand(), а не getBrand!)
        Map<String, List<Headphones>> headphonesByBrand = headphonesStream.collect(
                Collectors.groupingBy(Headphones::brand)  // ← исправлено: brand(), а не getBrand()
        );

        // Выводим результат: бренд и все модели этого бренда
        for (Map.Entry<String, List<Headphones>> item : headphonesByBrand.entrySet()) {
            System.out.println(item.getKey());  // Название бренда
            for (Headphones headphone : item.getValue()) {
                // Используем name() и price(), а не getName() и getPrice()
                System.out.println("  " + headphone.name() + " — " + headphone.price() + " ₽");
            }
            System.out.println();  // Пустая строка между брендами
        }
    }
}
