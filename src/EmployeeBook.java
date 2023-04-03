import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EmployeeBook {

    private final Map<String, Employee> companyStaff;
    private final int SIZE = 10;

    EmployeeBook() throws Exception {
        try {
            companyStaff = new HashMap<>(Map.of(
                    "Пупкин Иван Сергеевич", new Employee("Пупкин Иван Сергеевич", 1, 50000),
                    "Иванов Павел Петрович", new Employee("Иванов Павел Петрович", 1, 75000),
                    "Наумова Дарья Александровна", new Employee("Наумова Дарья Александровна", 2, 36000),
                    "Филатова Ольга Сергеевна", new Employee("Филатова Ольга Сергеевна", 2, 73000),
                    "Корнеев Даннил Юрьевич", new Employee("Корнеев Даннил Юрьевич", 3, 66000),
                    "Троцких Федор Иванович", new Employee("Троцких Федор Иванович", 3, 59000),
                    "Филатова Зоя Андреевна", new Employee("Филатова Зоя Андреевна", 4, 67000),
                    "Краскова Людмила Дмитривна", new Employee("Краскова Людмила Дмитривна", 4, 58000),
                    "Сидоров Дмитрий Александрович", new Employee("Сидоров Дмитрий Александрович", 5, 73000),
                    "Яшкин Михаил Владимирович", new Employee("Яшкин Михаил Владимирович", 5, 65000)
            ));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void getListEmployees() {
        for (final var employee : companyStaff.values()) {
            System.out.println(employee);
        }
    }

    public int getTotalCosts() {
        int totalCosts = 0;
        for (final var employee : companyStaff.values()) {
            totalCosts += employee.getWages();
        }
        return totalCosts;
    }

    public void getMinimumWageEmployee() {
        boolean firstIter = true;
        int wages = 0;
        String nameEmployee = "";
        for (final var employee : companyStaff.values()) {
            if (firstIter) {
                wages = employee.getWages();
                firstIter = false;
                continue;
            }
            if (employee.getWages() < wages) {
                wages = employee.getWages();
                nameEmployee = employee.getName();
            }
        }
        System.out.println("Минимальная заработная плата у сотрудника - " + nameEmployee + "\t составляет: " + wages + " руб.\n");
    }

    public void getMaximumWageEmployee() {

        int wages = 0;
        String nameEmployee = "";
        for (final var employee : companyStaff.values()) {
            if (employee.getWages() > wages) {
                wages = employee.getWages();
                nameEmployee = employee.getName();
            }
        }
        System.out.println("Максимальная заработная плата у сотрудника - " + nameEmployee + "\t составляет: " + wages + " руб.\n");
    }

    public int getAverageValueWages() {
        return getTotalCosts() / Employee.getId();
    }

    public void getFullNameAllEmployees() {
        for (final var employee : companyStaff.values()) {
            System.out.println(employee.getIdEmployee() + ". " + employee.getName());
        }
        System.out.println("\n");
    }

    public void wageIndexation(int percent) {
        float ratio = 1.0f;
        int wage;
        if (percent > 0)
            ratio += ((float)percent / 100);
        else if (percent < 0)
            ratio -= ((float)percent / 100);
        else {
            System.out.println("Процент равен 0. Для индексирования укажите на какой процент произвести индексирование.\n");
            return;
        }
        for (var employee : companyStaff.values()) {
            wage = (int)(Math.ceil((float) employee.getWages() * ratio));
            employee.setWages(wage);
            System.out.println("Заработная плата для сотрудника " + employee.getName() + ", при индексировании в " + percent + " процентов, будет - " + employee.getWages());
        }
        System.out.println("\n");
    }

    public void getWageEmployeesBelowSpecified(int wagesLower) {
        System.out.println("Сотрудники с заработной платой ниже: " + wagesLower + "\n");
        for (final var employee : companyStaff.values()) {
            if (wagesLower > employee.getWages()) {
                System.out.println("Индекс сотрудника: " + employee.getIdEmployee() + "\nФИО сотрудника: " + employee.getName() + "\nЗаработная плата: " + employee.getWages() + "\n");
            }
        }
    }

    public void getWageEmployeesMoreSpecified(int wagesLower) {
        System.out.println("Сотрудники с заработной платой выше или равной: " + wagesLower + "\n");
        for (final var employee : companyStaff.values()) {
            if (wagesLower <= employee.getWages()) {
                System.out.println("Индекс сотрудника: " + employee.getIdEmployee() + "\nФИО сотрудника: " + employee.getName() + "\nЗаработная плата: " + employee.getWages() + "\n");
            }
        }
    }

    public void addNewEmployee() throws Exception {
        if (Employee.getId() >= SIZE)
            System.out.println("Нет свободных ячеек для записи нового сотрудника. Удалите старого сотрудника для записи нового.\n");
        else {
            Scanner inSting = new Scanner(new InputStreamReader(System.in));
            Scanner inInt = new Scanner(System.in);

            int department;
            int wage;
            String name;

            System.out.println("Введите ФИО нового сотрудника:\t");
            name = inSting.nextLine();
            if (companyStaff.containsKey(name)) {
                System.out.println("Такой сотрудник уже есть:\n");
                System.out.println(companyStaff.get(name));
                return;
            }

            System.out.println("Введите номер отдела с 1 по 5, в котором он будет работать:\t");
            department = inInt.nextInt();
            System.out.println("Введите его заработную плату:\t");
            wage = inInt.nextInt();

            companyStaff.put(name, new Employee(name, department, wage));
            System.out.println("Сотрудник добавлен!\n");
        }
    }

    public void removeEmployee() {
        Scanner in = new Scanner(System.in);
        Scanner inSting = new Scanner(new InputStreamReader(System.in));
        String name;
        int choice;

        System.out.println("Введите ФИО сотрудника, которого хотите удалить: ");
        name = inSting.nextLine();
        if (companyStaff.containsKey(name)) {
            System.out.println("Сотрдуник: " + name + "\nВы уверены что хотите удалить данного сотрудника?\t1. Да\t2. Нет \nВведите номер ответа:");
            choice = in.nextInt();
            if (choice == 1) {
                companyStaff.remove(name);
                Employee.idMinus();
                System.out.println("Сотрудник удален.\n");
            } else if (choice == 2)
                return;
            else {
                System.out.println("Не корректно введен пункт!\n");
                return;
            }
        }
        else
            System.out.println("Сотрудника '" + name + "' нет.");
    }

    public void changeWageEmployee() {
        Scanner in = new Scanner(System.in);
        Scanner inSting = new Scanner(System.in);
        int wage;
        String name;

        System.out.println("Введите ФИО сотрудника, ЗП которого хотите поменять:\t");
        name = inSting.next();
        if(!companyStaff.containsKey(name)) {
            System.out.println("Сотрудник с таким именем не найден!");
            return;
        }

        System.out.println("Вы выбрали сотрудника - " + name + "\nУкажите заработную плату:\t");
        wage = in.nextInt();
        Employee employee = companyStaff.get(name);
        employee.setWages(wage);
        System.out.println("Заработная плата сотруднику " + employee.getName() + " изменена:\n");
    }
}
