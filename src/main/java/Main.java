import org.hibernate.Session;
import org.hibernate.SessionFactory;
import utils.WebServer;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = WebServer.openSessionFactory();
        Session session = sessionFactory.openSession();

        Loader loader = new Loader(session);
        loader.runProgram();

        WebServer.closeSessionFactory(sessionFactory);
    }
}
