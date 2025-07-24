package HomeWork_2;

import java.time.LocalDate;
interface EmployeeInfo {
    String getFullInfo();
    int calculateAge();
}
abstract class Person {
    protected String lastName;
    protected String firstName;
    protected String middleName;
    protected String gender;
    protected LocalDate birthDate;
    public Person(String lastName, String firstName, String middleName,
                  String gender, LocalDate birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.gender = gender;
        this.birthDate = birthDate;
    }
    public abstract void displayBasicInfo();
    public String getFullName() {
        return lastName + " " + firstName + " " + middleName;
    }
}
class Employee extends Person implements EmployeeInfo {
    private String educationDegree;
    private String educationField;
    private String educationSpecialty;
    private String address;
    private String position;
    private double salary;
    public Employee(String lastName, String firstName, String middleName,
                    String gender, LocalDate birthDate,
                    String educationDegree, String educationField,
                    String educationSpecialty, String address,
                    String position, double salary) {
        super(lastName, firstName, middleName, gender, birthDate);
        this.educationDegree = educationDegree;
        this.educationField = educationField;
        this.educationSpecialty = educationSpecialty;
        this.address = address;
        this.position = position;
        this.salary = salary;
    }
    @Override
    public void displayBasicInfo() {
        System.out.println("Сотрудник: " + getFullName());
    }
    @Override
    public String getFullInfo() {
        return String.format("%s, %s, %s\nСпециальность: %s, %s, %s\nДолжность: %s, Зарплата: %.2f",
                getFullName(),
                gender,
                birthDate,
                educationDegree,
                educationField,
                educationSpecialty,
                position,
                salary);
    }
    @Override
    public int calculateAge() {
        return LocalDate.now().getYear() - birthDate.getYear();
    }
    public void promote(String newPosition, double newSalary) {
        this.position = newPosition;
        this.salary = newSalary;
    }
}
class Client extends Person {
    private String company;
    private String contactPhone;
    public Client(String lastName, String firstName, String middleName,
                  String gender, LocalDate birthDate, String company,
                  String contactPhone) {
        super(lastName, firstName, middleName, gender, birthDate);
        this.company = company;
        this.contactPhone = contactPhone;
    }
    @Override
    public void displayBasicInfo() {
        System.out.println("Клиент: " + getFullName() + ", Компания: " + company);
    }
    public void makePurchase(double amount) {
        System.out.println(getFullName() + " совершил покупку на сумму: " + amount);
    }
}
class Supplier extends Person {
    private String suppliedProduct;
    private int deliveryDays;
    public Supplier(String lastName, String firstName, String middleName,
                    String gender, LocalDate birthDate, String suppliedProduct,
                    int deliveryDays) {
        super(lastName, firstName, middleName, gender, birthDate);
        this.suppliedProduct = suppliedProduct;
        this.deliveryDays = deliveryDays;
    }
    @Override
    public void displayBasicInfo() {
        System.out.println("Поставщик: " + getFullName() + ", Поставляет: " + suppliedProduct);
    }
    public void deliver() {
        System.out.println(getFullName() + ": Поставка " + suppliedProduct +
                " будет выполнена через " + deliveryDays + " дней");
    }
}
public class Main {
    public static void main(String[] args) {
        Employee emp = new Employee(
                "Иванов", "Иван", "Иванович", "Мужской",
                LocalDate.of(1970, 5, 15),
                "Высшее (специалитет)",
                "Автоматика и телемеханика",
                "Инженер-электрик",
                "г. Москва, ул. Ленина, 10",
                "Инженер по автоматизации",
                85000.0
        );
        Client client = new Client(
                "Петрова", "Мария", "Сергеевна", "Женский",
                LocalDate.of(1990, 8, 22), "ООО ТехноПром",
                "+7 (999) 123-45-67"
        );
        Supplier supplier = new Supplier(
                "Сидоров", "Алексей", "Николаевич", "Мужской",
                LocalDate.of(1978, 3, 10), "Компьютерные комплектующие",
                5
        );
        System.out.println("=== Информация о сотруднике ===");
        emp.displayBasicInfo();
        System.out.println(emp.getFullInfo());
        System.out.println("\n=== Информация о клиенте ===");
        client.displayBasicInfo();
        client.makePurchase(12500.50);
        System.out.println("\n=== Информация о поставщике ===");
        supplier.displayBasicInfo();
        supplier.deliver();
    }
}