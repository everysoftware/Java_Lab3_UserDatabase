import java.time.DateTimeException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static final UserDB db = new UserDB();
    private static void addUser(Scanner in) {
        System.out.println("Введите данные в формате Фамилия Имя Отчество dd.MM.yyyy");
        try {
            String surname = in.next();
            String name = in.next();
            String second_name = in.next();
            String birthday = in.next();
            db.add(surname, name, second_name, birthday);
        } catch (InputMismatchException ex) {
            System.out.println("Ошибка: неверный формат данных!");
            return;
        } catch (DateTimeException ex) {
            System.out.println("Ошибка: неверный формат даты рождения!");
            return;
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Ошибка: неверный формат ФИО!");
            return;
        }
        System.out.printf("Пользователь #%d успешно добавлен!%n", db.size());
        System.out.println(db.get(db.size() - 1));
    }
    private static void getUser(Scanner in) {
        System.out.println("Введите ID пользователя");
        try {
            int id = in.nextInt();
            System.out.println(db.get(id - 1));
        } catch (IndexOutOfBoundsException | InputMismatchException ex) {
            System.out.println("Ошибка: некорректный ID");
        }
    }
    private static boolean menu(Scanner in) {
        System.out.println("Программа управления пользователями");
        System.out.println("1. Добавить пользователя");
        System.out.println("2. Информация о пользователе");
        System.out.println("3. Выход");
        boolean status = true;
        try {
            int choice = in.nextInt();
            switch (choice) {
                case 1 -> addUser(in);
                case 2 -> getUser(in);
                case 3 -> status = false;
                default -> System.out.println("Неизвестная команда");
            }
        } catch (InputMismatchException ex) {
            System.out.println("Ошибка: некорректный формат команды");
            status = false;
        }
        System.out.println();
        return status;
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        boolean st = menu(in);
        while(st) {
            st = menu(in);
        }
        in.close();
    }
}
