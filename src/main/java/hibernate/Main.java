package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {
        ClientsDetails details = new ClientsDetails();

        details.setName("Client");
        details.setAge(22);

        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(details);
        session.getTransaction();
        session.close();
    }
}
