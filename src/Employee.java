public class Employee {

    Employee(String name, int department, int wages) throws Exception {
        id++;
        idEmployee = id;
        this.name = name;
        if (department <= 0 || department >= 6) throw new Exception("Не корректно указан отдел сотрудника. Отделов модет быть от 1 до 5!");
        this.department = department;
        this.wages = wages;
    }

    private static int id = 0;
    private int idEmployee; // индификатор сотрудника
    private String name; // имя сотрудника
    private int department; // отдел сотрудника
    private int wages; // зарплата сотрудника

    // Дополнительные методы
    public static void close(Employee employee) {
        id--;
        employee = null;
    }
    public void shiftingDataObject(Employee other) {
        this.name = other.name;
        this.department = other.department;
        this.wages = other.wages;
    }


    // Геттеры
    public static int getId() {
        return id;
    }
    public int getIdEmployee() {
        return idEmployee;
    }
    public String getName() {
        return name;
    }
    public int getDepartment() {
        return department;
    }
    public int getWages() {
        return wages;
    }

    // Сеттеры
    public void setDepartment(int department) {
        this.department = department;
    }
    public void setWages(int wages) {
        this.wages = wages;
    }

    // toString

    public String toString() {
        return "Индекс сотрудника: " + idEmployee + "\nФИО сотрудника: " + name + "\nОтдел: " + department + "\nЗаработная плата: " + wages + "\n";
    }
}
