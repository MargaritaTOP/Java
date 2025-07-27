package Generics;
//Ограничения типов (Bounded Type Parameters)
public class Bounded_Type_Parameters<T extends Number> {
    private T number;
    public Bounded_Type_Parameters(T number) {
        this.number = number;
    }
    // Метод, работающий только с Number и его подклассами
    public double square() {
        return number.doubleValue() * number.doubleValue();
    }
    // Сравнение с другим NumberOperations
    public <U extends Number> boolean isEqual(Bounded_Type_Parameters<U> other) {
        return this.number.doubleValue() == other.number.doubleValue();
    }
    public static void main(String[] args) {
        Bounded_Type_Parameters<Integer> intOp = new Bounded_Type_Parameters<>(5);
        System.out.println("Square of 5: " + intOp.square());
        Bounded_Type_Parameters<Double> doubleOp = new Bounded_Type_Parameters<>(3.5);
        System.out.println("Square of 3.5: " + doubleOp.square());
        System.out.println("Are equal? " + intOp.isEqual(doubleOp));
    }
}
