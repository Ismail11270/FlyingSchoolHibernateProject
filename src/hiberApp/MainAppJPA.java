package hiberApp;

import util.EMBuilder;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class MainAppJPA {

    private final static EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory( "project" );

    public static void main( String[] args ) {
        var queries = new JpaDataQuiries();
        queries.showFlightsForStudentId( 5 );
        queries.showCoursesStudents( );
        queries.showStudentsFromPoland();
        queries.showStudentsFromCourseWithMoreThanTwoClasses( );
        EMBuilder.closeFactory( );
        
    }



}
