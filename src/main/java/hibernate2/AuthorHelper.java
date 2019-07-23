package hibernate2;

import hibernate2.entity.Author;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class AuthorHelper {
    private SessionFactory sessionFactory;

    public AuthorHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Author> getAuthorList () {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Author.class);

        List<Author> authorList = criteria.list();

        session.close();
        return authorList;
    }

    public Author getAuthorById (long id){
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id);
        session.close();
        return author;
    }

    public Author addAuthor (Author author) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.save(author);

        session.getTransaction().commit();

        session.close();

        return author;
    }
}
