/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hiberApp;

import util.EMBuilder;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model1.Employee;


/* 
 @author LabHiber
 */
public final class MainAppJPA {

    private final static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("labPU");

    public static void main(String[] args) {
        showAllEmployees();
        EMBuilder.closeFactory();

    }

    public static void showAllEmployees() {
        EntityManager em = EMBuilder.getEM();
        List<Employee> result = em.createQuery("select e from Employee e").getResultList();
        result.forEach(System.out::println);
        System.out.println("=======================");
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery criteria = builder.createQuery(Employee.class);
        Root<Employee> root = criteria.from(Employee.class);
        criteria.select(root);
        result = em.createQuery(criteria).getResultList();
        result.forEach(System.out::println);
        
        builder = em.getCriteriaBuilder();
        criteria = builder.createQuery(String.class);
        root = criteria.from(Employee.class);
        criteria.select(root.get("name"));
        List<String> names = em.createQuery(criteria).getResultList();
        names.forEach(System.out::println);
        em.close();
    }
   
}
