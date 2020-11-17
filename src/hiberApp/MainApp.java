package hiberApp;

import org.hibernate.SessionFactory;
import util.HiberUtil;

public final class MainApp {

    private static final SessionFactory SESSION_FACTORY = HiberUtil.getSessionFactory(HiberUtil.Mapping.ANN);

    public static void main(String[] args) {

//        new DataLoad().createData(SESSION_FACTORY);
        final DataQueries dataQueries = new DataQueries();
//        dataQueries.showPersons(SESSION_FACTORY);
        dataQueries.showCoursesStudents(SESSION_FACTORY);
//        dataQueries.showAll(SESSION_FACTORY);
    }
}
