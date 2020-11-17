package hiberApp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.Tuple;
import java.util.List;

public final class DataQueries {

    /**
     * Queries test data
     *
     * @param SESSION_FACTORY for connecting data source
     */

    public void showPersons(SessionFactory SESSION_FACTORY) {
        try (Session session = SESSION_FACTORY.openSession()) {
            Query query = session.createQuery("select p from Person p");
            var persons = query.list();
            persons.stream().map(x -> x.toString()).forEach(System.out::println);
        }
    }

    public void showCoursesStudents(SessionFactory SESSION_FACTORY) {
        try (Session session = SESSION_FACTORY.openSession()) {
            Query query = session.createQuery("SELECT s.course.id, count(s)\n " +
                    "FROM Student s \n" +
                    "GROUP BY s.course.id", Tuple.class);

            List<Tuple> results = query.getResultList();
            results.stream().map(x -> x.get(0) + " " + x.get(1)).forEach(System.out::println);
//            results;
        }
    }

    public void showAll(SessionFactory SESSION_FACTORY) {
        try (Session session = SESSION_FACTORY.openSession()) {
            Query query = session.createQuery("select o from java.lang.Object o");
            query.setComment("All objects list");
            List<Object> all = query.list();
            all.forEach(System.out::println);
        }
    }
}
