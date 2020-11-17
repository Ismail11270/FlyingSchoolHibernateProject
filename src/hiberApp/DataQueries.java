package hiberApp;

import model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public final class DataQueries {

    /**
     * Queries test data
     *
     * @param SESSION_FACTORY for connecting data source
     */

    public void showOsoby(SessionFactory SESSION_FACTORY) {
        try (Session session = SESSION_FACTORY.openSession()) {
            Query query = session.createQuery("select p from Person p");
            List<Person> osoby = query.list();
            osoby.stream().map(x -> x.toString()).forEach(System.out::println);
        }
    }

    public void showCoursesStudents(SessionFactory SESSION_FACTORY) {
        try (Session session = SESSION_FACTORY.openSession()) {
            Query query = session.createQuery("SELECT s.course, count(s)\n " +
                    "FROM Student s \n" +
                    "GROUP BY s.course");

            var osoby = query.getResultList();
            System.out.println(osoby);
//            osoby.stream().map( x -> x.toString() ).forEach(System.out::println);
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
