import core.Course;
import org.hibernate.Session;

import java.util.Scanner;

public class CourseCard {
    Session session;
    int id = -1;

    public CourseCard(Session session) {
        this.session = session;
    }

    public void getCourseInfo() {

        System.out.println("""
                Введите id курса, по которому нужно узнать количество студентов.
                Для этого введите число от 1 до 46.
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
                System.out.println("Завершение программы по выводу информации о студентах..\n");
            }
            if (id > 46 || id < 0) {
                System.out.println("У нас пока только 46 курсов");
                return -1;
            }
        } catch (NumberFormatException ex) {
            System.out.println("Неверный ввод");
            return -1;
        }
        return id;
    }

    private Course createAnObject(int id) {
        return session.get(Course.class, id);
    }

    private void printInfo(Course course) {
        System.out.println("На курсе «" + course.getName() + "» "
                + course.getStudentsCount() + " студентов");
    }
}
