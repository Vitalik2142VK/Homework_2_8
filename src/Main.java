import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        EmployeeBook eb = null;
        try {
            eb = new EmployeeBook();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Scanner in = new Scanner(System.in);
        int choice;
        boolean exit = true;
        int num = 3;
        do {
            System.out.println("Книга сотрудников.\n1.\tПолучить информацию о всех сотрудниках.\n2.\tПолучить расход на заработную плату сотрудникам." +
                    "\n3.\tВывести сотрудника с минимальной заработной платой.\n4.\tВывести сотрудника с максимальной заработной платой." +
                    "\n5.\tПолучить среднее значение заработных плат у сотрудников.\n6.\tВывести ФИО всех сотрудников." +
                    "\n7.\tПроизвести индексирование заработной платы.\n8.\tПоиск сотрудников с ЗП меньше указанной." +
                    "\n9.\tПоиск сотрудников с ЗП больше или равной указанной.\n10.\tДобавить нового сотрудника.\n11.\tУдалить сотрудника." +
                    "\n12.\tПоменять заработную плату у сотрудника." +
                    "\n\nВведите номер пункта:\t");
            choice = in.nextInt();
            switch (choice) {
                case 1:
                    eb.getListEmployees(); break;
                case 2:
                    System.out.println("Общие расходы на заработную плату сотрудникам: " + eb.getTotalCosts() + " руб.\n"); break;
                case 3:
                    eb.getMinimumWageEmployee(); break;
                case 4:
                    eb.getMaximumWageEmployee(); break;
                case 5:
                    System.out.println("Средняя заработная плата у сотрудников: " + eb.getAverageValueWages() + " руб.\n"); break;
                case 6:
                    eb.getFullNameAllEmployees(); break;
                case 7: {
                    System.out.println("Введите процент, на который необходимо индекстровать ЗП.\nДля отрицательного индексирования введите число с минусом \"Пример: -30\"");
                    int percent = in.nextInt();
                    eb.wageIndexation(percent);
                    break;
                }
                case 8: {
                    System.out.println("Введите сумму, для поиска сотрудников с ЗП меньше указанной суммы.");
                    int wagesLower = in.nextInt();
                    eb.getWageEmployeesBelowSpecified(wagesLower);
                    break;
                }
                case 9: {
                    System.out.println("Введите сумму, для поиска сотрудников с ЗП больше или равной указанной.");
                    int wagesLower = in.nextInt();
                    eb.getWageEmployeesMoreSpecified(wagesLower);
                    break;
                }
                case 10: {
                    try {
                        eb.addNewEmployee();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case 11:
                    eb.removeEmployee(); break;
                case 12:
                    eb.changeWageEmployee(); break;
                default: {
                    if (num == 0)
                        break;
                    System.out.println("Не корректно введен пункт!\nВнимание!!! Через '" + num + "' попыток программа завершит свою работу.");
                    num--;
                    break;
                }
            }
            System.out.println("1.\tПродолжить работу в программе.\n2.\tЗакрыть программу.\n\nВведите номер пункта:\t");
            choice = in.nextInt();
            if (choice == 1)
                exit = true;
            else if (choice == 2 || num == 0)
                exit = false;
            else {
                System.out.println("Не корректно введен пункт! Программа продолжит работу.\nВнимание!!! Через '" + num + "' попыток программа завершит свою работу.");
                num--;
            }
        }
        while (exit);
    }
}