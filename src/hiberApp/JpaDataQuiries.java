package hiberApp;

import model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.EMBuilder;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;

class JpaDataQuiries {
    void showFlightsForStudentId( int id ) {
        System.out.println( "\n======List of flights for student with id " + id + "======" );
        EntityManager em = EMBuilder.getEM( );
        CriteriaBuilder cb = em.getCriteriaBuilder( );
        CriteriaQuery<Flight> cq = cb.createQuery( Flight.class );
        Root<Flight> root = cq.from( Flight.class );
        cq.select( root ).where( cb.equal( root.get( "student" ), id ) );
        var flightsList = em.createQuery( cq ).getResultList( );
        flightsList.forEach( System.out::println );
    }

    void showCoursesStudents() {
        System.out.println( "\n======Number of students for each course type======" );
        var em = EMBuilder.getEM( );
        var cb = em.getCriteriaBuilder( );
        var cq = cb.createTupleQuery( );
        var root = cq.from( Student.class );
        var certType = root.get( "course" ).get( "certType" );
        cq.multiselect( certType, cb.count( root ) ).groupBy( certType );
        List<Tuple> results = em.createQuery( cq ).getResultList( );
        results.stream( ).map( x -> "Course Type: " + x.get( 0 ) + ", Number of students: " + x.get( 1 ) ).forEach( System.out::println );
    }

    void showStudentsFromPoland() {
        System.out.println( "\n======Name and City for students who live in Poland======" );
        var em = EMBuilder.getEM( );
        var cb = em.getCriteriaBuilder( );
        var cq = cb.createTupleQuery( );
        var root = cq.from( Person.class );
        cq.multiselect( root.get( "firstName" ), root.get( "address" ).get( "city" ) ).where( cb.equal( root.get( "address" ).get( "country" ), "Poland" ) );
        List<Tuple> results = em.createQuery( cq ).getResultList( );
        results.stream( ).map( x -> "Name: " + x.get( 0 ) + " ," + " City: " + x.get( 1 ) ).forEach( System.out::println );
    }

    void showStudentsFromCourseWithMoreThanTwoClasses() {
        System.out.println( "\n======Students attending AIRLINE_TRANSPORT_PILOT Course that have at least 2 theory classes======" );
        var em = EMBuilder.getEM( );
        var cb = em.getCriteriaBuilder( );
        var cq = cb.createTupleQuery( );
        var root = cq.from( Course.class );
        Join<Course, Student> joined = root.join( "students" );
        cq.multiselect( joined.get( "id" ), joined.get( "firstName" ) )
                .where( cb.equal( root.get( "certType" ), CertificationType.AIRLINE_TRANSPORT_PILOT ) )
                .groupBy( Arrays.asList(joined.get( "id" ), joined.get("firstName") ))
                .having( cb.ge( cb.count( joined.join("theoryClasses") ), 2) );
        List<Tuple> results = em.createQuery( cq ).getResultList();
        results.stream( ).map( x -> "ID: " + x.get( 0 ) + ", " + "Name: " + x.get( 1 ) ).forEach( System.out::println );
    }

    void showStudentsFromCourseWithMoreThanTwoClasses( SessionFactory SESSION_FACTORY ) {
        System.out.println( "\n======Students attending AIRLINE_TRANSPORT_PILOT Course that have at least 2 theory classes======" );
        try ( Session session = SESSION_FACTORY.openSession( ) ) {
            Query query = session.createQuery( "SELECT s.id, s.firstName " +
                    "FROM Course c " +
                    "INNER JOIN Student s ON c.id = s.course.id " +
                    "INNER JOIN s.theoryClasses t " +
                    "WHERE c.certType='" + CertificationType.AIRLINE_TRANSPORT_PILOT + "' " +
                    "GROUP BY s.id, s.firstName " +
                    "HAVING COUNT(t) > 1", Tuple.class );
            List<Tuple> results = query.getResultList( );
            results.forEach( x -> System.out.println( "ID: " + x.get( 0 ) + ", " + "Name: " + x.get( 1 ) ) );
        }
    }
}
