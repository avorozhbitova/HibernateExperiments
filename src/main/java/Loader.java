import org.hibernate.Session;

import java.util.Scanner;

public class Loader {
    Session session;
    int inputNumber = -1;

    public Loader(Session session) {
        this.session = session;
    }

    public void runProgram() {
        while (inputNumber != 0) {
            System.out.println("""
                    Если Вы хотите увидеть информацию о курсах, введите 1.
                    Если Вы хотите увидеть информацию о студентах, введите 2.
                    Чтобы остановить работу программы, введите 0.""");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            chooseProgram(input, session);
        }
    }

    private void chooseProgram(String input, Session session) {
        try {
            int inputNumber = Integer.parseInt(input);
            if (inputNumber == 1) {
                CourseCard courseCard = new CourseCard(session);
                courseCard.getCourseInfo();
            } else if (inputNumber == 2) {
                StudentCard studentCard = new StudentCard(session);
                studentCard.getStudentInfo();
            } else if (inputNumber > 2 || inputNumber < 0) {
                System.out.println("Неверный ввод");
            } else {
                this.inputNumber = 0;
                System.out.println("Завершение программы..");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Неверный ввод");
        }
    }
}
