package hibernate2;

import hibernate2.entity.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AuthorHelper {
    private SessionFactory sessionFactory;

    public AuthorHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Author> getAuthorList () {
        Session session = sessionFactory.openSession();

        CriteriaBuilder criteria = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteria.createQuery();
        Root<Author> root = criteriaQuery.from(Author.class);
        criteriaQuery.select(root);

        Query query = session.createQuery(criteriaQuery);

        List<Author> authorList = query.getResultList();

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
