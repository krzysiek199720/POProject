package POProject.db.core.utils;

import POProject.app.core.*;
import POProject.db.app.core.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtils {

    private static ServiceRegistry serviceRegistry;
    private static String mainConfiguration;
    private static SessionFactory sessionFactory;

    private Session currentSession;
    private Transaction currentTransaction;

    private static Configuration setClassesConfiguration(Configuration configuration){

        configuration.addAnnotatedClass(Author.class);
        configuration.addAnnotatedClass(Book.class);
        configuration.addAnnotatedClass(Category.class);
        configuration.addAnnotatedClass(Publisher.class);
        configuration.addAnnotatedClass(Series.class);

        return configuration;
    }

    private static void setSessionFactory(String pathConfiguration) {
        Configuration configuration = new Configuration()
                .configure(pathConfiguration);

        configuration = setClassesConfiguration(configuration);

        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    public static void OpenConnection(String configurationPath){
        mainConfiguration = configurationPath;
        setSessionFactory(mainConfiguration);
    }

    public static void CloseConnection(){
        sessionFactory.close();
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }

    public static String getMainConfiguration(){
        return mainConfiguration;
    }

    public static ServiceRegistry getServiceRegistry() {
        return HibernateUtils.serviceRegistry;
    }



    public Session openCurrentSession() {
        currentSession = sessionFactory.openSession();
        return currentSession;
    }

    public Session openCurrentSessionWithTransaction() {
        currentSession = sessionFactory.openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

}
