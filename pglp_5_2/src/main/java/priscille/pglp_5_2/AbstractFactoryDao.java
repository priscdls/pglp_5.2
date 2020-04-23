package priscille.pglp_5_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
     * Fabrique général de Dao.
     * @param type Type de Dao
     * @return Une fabrique de Dao
     */
    public static AbstractFactoryDao getFactory(final DaoType type) {
    	Connection connect = null;
    	try {
            connect = DriverManager.getConnection("jdbc:derby:compositePattern;create=false");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    	if (type == DaoType.CRUD) {
            return new FactoryDao();
        }
        else if (type == DaoType.JDBC) {
            return new FactoryDaoJDBC(connect);
        }
        else {
        	return null;
        }
    }
}
