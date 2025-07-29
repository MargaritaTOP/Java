package HomeWork_6.GroupingBy;

/**
 * Класс, представляющий наушники.
 * Поля: name — название модели, brand — бренд, price — цена в рублях.
 */
public record Headphones(String name, String brand, int price) {
    // record автоматически создаёт конструктор и геттеры: name(), brand(), price()
}

