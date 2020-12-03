package hiberApp;

import model.CertificationType;
import model.Flight;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.Tuple;
import java.util.List;

public final class DataQueries {

    /**
     * Selection
     * Selects flights that belond to a student with a give id
     * @param id - id of the student
     * @param SESSION_FACTORY
     */
    public void showFlightsForStudentId( SessionFactory SESSION_FACTORY, int id ) {
        System.out.println( "\n======List of flights for student with id " + id + "======" );
        try ( Session session = SESSION_FACTORY.openSession( ) ) {
            Query query = session.createQuery( "SELECT f\n " +
                    "FROM Flight f \n" +
                    "WHERE f.student.id = " + id, Flight.class );
            List<Flight> results = query.getResultList( );
            results.forEach( System.out::println );
        }
    }

    /**
     * Display names and cities of students who live in Poland
     * Using explicit join
     * @param SESSION_FACTORY
     */
    public void showStudentsFromPoland( SessionFactory SESSION_FACTORY ) {
        System.out.println( "\n======Name and City for students who live in Poland======" );
        try ( Session session = SESSION_FACTORY.openSession( ) ) {
            Query query = session.createQuery( "SELECT p.firstName , p.address.city FROM Person p WHERE p.address.country = 'Poland' ", Tuple.class );
            List<Tuple> results = query.getResultList( );
            results.stream( ).map( x -> "Name: " + x.get( 0 ) + " ," + " City: " + x.get( 1 ) ).forEach( System.out::println );
        }
    }

    /**
     * IDs and Names of students attending AIRLINE_TRANSPORT_PILOT Course that have at least 2 theory classes
     * implicit join and aggregation
     * @param SESSION_FACTORY
     */
    public void showStudentsFromCourseWithMoreThanTwoClasses( SessionFactory SESSION_FACTORY) {
        System.out.println( "\n======Students attending AIRLINE_TRANSPORT_PILOT Course that have at least 2 theory classes======" );
        try ( Session session = SESSION_FACTORY.openSession( ) ) {
            Query query = session.createQuery( "SELECT s.id, s.firstName " +
                    "FROM Student s " +
                    "WHERE s.course.certType='" + CertificationType.AIRLINE_TRANSPORT_PILOT + "' " +
                    "GROUP BY s.id, s.firstName " +
                    "HAVING s.theoryClasses.size > 1", Tuple.class );
            List<Tuple> results = query.getResultList( );
            results.forEach( x -> System.out.println( "ID: " + x.get( 0 ) + ", " + "Name: " + x.get( 1 ) ) );
        }
    }


    /**
     * Show number of students for each type of course
     * Using aggregation and grouping
     * @param SESSION_FACTORY
     */
    public void showCoursesStudents( SessionFactory SESSION_FACTORY ) {
        System.out.println( "\n======Number of students for each course type======" );
        try ( Session session = SESSION_FACTORY.openSession( ) ) {
            Query query = session.createQuery( "SELECT s.course.certType, count(s.id)\n " +
                    "FROM Student s\n" +
                    "GROUP BY s.course.certType", Tuple.class );

            List<Tuple> results = query.getResultList( );
            results.stream( ).map( x -> "Course Type: " + x.get( 0 ) + ", Number of students: " + x.get( 1 ) ).forEach( System.out::println );
        }
    }

}
