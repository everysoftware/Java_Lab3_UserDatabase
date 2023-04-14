import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class UserDB {
    private final ArrayList<User> users = new ArrayList<User>();
    public UserDB(){

    }
    public void add(String surname, String name, String second_name, String birthday) throws DateTimeException, IndexOutOfBoundsException {
        char sex = defineSex(second_name);
        calculateAge(birthday);
        users.add(new User(surname, name, second_name, birthday, sex));
    }
    public User get(int id) throws IndexOutOfBoundsException {
        return users.get(id);
    }
    public int size() {
        return users.size();
    }
    public int calculateAge(String birthday) throws DateTimeException {
        String[] a = birthday.split("\\.");
        LocalDate date = LocalDate.of(
                Integer.parseInt(a[2]),
                Integer.parseInt(a[1]),
                Integer.parseInt(a[0])
        );
        return Period.between(date, LocalDate.now()).getYears();
    }
    public String defineAgeWord(int age) {
        if (age % 10 == 1 && age % 100 != 11) {
            return "год";
        } else if (age % 10 >= 2 && age % 10 <= 4 && (age < 12 || age > 14)) {
            return "года";
        } else {
            return "лет";
        }
    }
    private char defineSex(String second_name) throws IndexOutOfBoundsException {
        if (second_name.charAt(second_name.length() - 1) == 'ч') {
            return 'М';
        } else {
            return 'Ж';
        }
    }
}
