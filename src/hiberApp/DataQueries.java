/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hiberApp;

import model1.*;
import java.util.List;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/* 
 @author LabHiber
 */
public final class DataQueries {
    
     /**
     * Queries test data
     *
     * @param SESSION_FACTORY for connecting data source
     */

    public void showOsoby(SessionFactory SESSION_FACTORY) {
        try (Session session = SESSION_FACTORY.openSession()) {
            Query query = session.createQuery("select o from Osoba o");
            List<Person> osoby = query.list();
            osoby.stream().forEach((osoba) -> {
                System.out.println(osoba.getName() + " " + osoba.getSurname()
                        + " " + osoba.getAge());
            });
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
