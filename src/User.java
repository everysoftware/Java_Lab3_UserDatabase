import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Period;

public class User {
    private String surname;
    private String name;
    private String second_name;
    private String birthday;
    private char sex;

    public User() {
    }

    public User(String surname, String name, String second_name, String birthday, char sex) {
        this.surname = surname;
        this.name = name;
        this.second_name = second_name;
        this.birthday = birthday;
        this.sex = sex;
    }

    @Override
    public String toString() {
        int age;
        try {
            age = Main.db.calculateAge(birthday);
        } catch (DateTimeException ex) {
            age = -1;
        }
        String age_word = Main.db.defineAgeWord(age);
        return String.format("%s %c.%c.   %c   %d %s",
                surname, name.charAt(0), second_name.charAt(0), sex, age, age_word);
    }
}
