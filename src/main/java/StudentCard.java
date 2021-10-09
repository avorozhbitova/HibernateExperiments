import core.Student;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StudentCard {
    public static final List<String> months = Stream.of("января", "февраля", "марта",
            "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября",
            "декабря").collect(Collectors.toList());
    Session session;
    int id = -1;

    public StudentCard(Session session) {
        this.session = session;
    }

    public void getStudentInfo() {
        System.out.println("""
                \nВведите id студента, информацию о котором нужно узнать.
                Для этого введите число от 1 до 100.
                Если Вы хотите вернуться к выбору, введите 0.""");
        while (id != 0) {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            id = getIdFromConsole(input);

            if (id == -1 || id == 0) {
                continue;
            }
            printInfo(createAnObject(id));
        }
    }

    private int getIdFromConsole(String input) {
        int id;
        try {
            id = Integer.parseInt(input);
            if (id == 0) {
                System.out.println("Завершение программы по выводу информации о курсах..\n");
            }
            if (id > 100 || id < 0) {
                System.out.println("У нас пока только 100 студентов");
                return -1;
            }
        } catch (NumberFormatException ex) {
            System.out.println("Неверный ввод");
            return -1;
        }
        return id;
    }

    private Student createAnObject(int id) {
        return session.get(Student.class, id);
    }

    private void printInfo(Student student) {
        System.out.println("Студент " + student.getName() + ", " + student.getAge() + " " + getEnding(student) +
                ", дата регистрации - " + getRegDatePrettyView(student));
    }

    private String getEnding(Student student) {
        String s = String.valueOf(student.getAge());
        if (s.endsWith("1")) {
            return "год";
        } else if (s.endsWith("2") || s.endsWith("3") || s.endsWith("4")) {
            return "года";
        } else {
            return "лет";
        }
    }

    private String getRegDatePrettyView(Student student) {
        return student.getRegistrationDate().get(Calendar.DAY_OF_MONTH) + " " +
                months.get(student.getRegistrationDate().get(Calendar.MONTH)) + " " +
                student.getRegistrationDate().get(Calendar.YEAR) + " года";
    }
}
