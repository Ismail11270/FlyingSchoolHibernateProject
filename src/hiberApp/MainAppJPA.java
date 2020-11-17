package hiberApp;

import model.Course;
import model.FlightInstructor;
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
        System.out.println("============All Students===========");
        var studentsList = em.createQuery("select s from Student s").getResultList();
        studentsList.forEach(System.out::println);

        System.out.println("============All Courses===========");
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery(Course.class);
        Root<Course> root = criteria.from(Course.class);
        criteria.select(root);
        var coursesList = em.createQuery(criteria).getResultList();
        coursesList.forEach(System.out::println);

        System.out.println("============All Instructors' Names===========");
        builder = em.getCriteriaBuilder();
        criteria = builder.createQuery(String.class);
        root = criteria.from(FlightInstructor.class);
        criteria.select(root.get("firstName"));
        var instructorNames = em.createQuery(criteria).getResultList();
        instructorNames.forEach(System.out::println);
        em.close();
    }

}
