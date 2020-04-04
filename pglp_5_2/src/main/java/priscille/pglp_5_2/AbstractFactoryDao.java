package priscille.pglp_5_2;

import java.sql.Connection;

public abstract class AbstractFactoryDao {
    /**
     * Enumération des types de Dao.
     */
    public enum DaoType {
        /**
         * CRUD.
         */
        CRUD,
        /**
         * JDBC.
         */
        JDBC;
    }
    /**
     * Connecteur.
     */
    protected static Connection connect;
    /**
     * Fabrique général de Dao.
     * @param type Type de Dao
     * @return Une fabrique de Dao
     */
    public static AbstractFactoryDao getFactory(final DaoType type) {
        if (type == DaoType.CRUD) {
            return new FactoryDao();
        }
        if (type == DaoType.JDBC) {
            return new FactoryDaoJDBC(connect);
        }
        return null;
    }
}
