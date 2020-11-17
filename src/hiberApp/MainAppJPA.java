package hiberApp;

import model.Student;
import util.EMBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public final class MainAppJPA {

    private final static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("project");

    public static void main(String[] args) {
        showAllStudents();
        EMBuilder.closeFactory();

    }

    public static void showAllStudents() {
        EntityManager em = EMBuilder.getEM();
        List<Student> result = em.createQuery("select s from Student s").getResultList();
        result.forEach(System.out::println);
        System.out.println("=======================");
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery(Student.class);
        Root<Student> root = criteria.from(Student.class);
        criteria.select(root);
        result = em.createQuery(criteria).getResultList();
        result.forEach(System.out::println);

        builder = em.getCriteriaBuilder();
        criteria = builder.createQuery(String.class);
        root = criteria.from(Student.class);
        criteria.select(root.get("firstName"));
        List<String> names = em.createQuery(criteria).getResultList();
        names.forEach(System.out::println);
        em.close();
    }

}
