package priscille.pglp_5_2;

import java.sql.Connection;
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
     * @throws SQLException 
     */
    public static Object getFactory(final DaoType type) throws SQLException {
    	Connection connect = DataBase.createBase();
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
