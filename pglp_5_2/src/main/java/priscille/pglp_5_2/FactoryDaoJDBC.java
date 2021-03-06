package priscille.pglp_5_2;

import java.sql.Connection;

public class FactoryDaoJDBC extends AbstractFactoryDao {
    /**
     * Connecteur a la base de données.
     */
    private static Connection connect;
    /**
     * Constructeur.
     * @param c Le connecteur a la base de donnees
     */
    public FactoryDaoJDBC(final Connection c) {
        connect = c;
    }
    /**
     * Fabrique Dao pour Personnel.
     * @return un nouveau PersonnelDao
     */
    public static AbstractDao<Personnel> getPersonnelDao() {
        return new PersonnelDaoJDBC(connect);
    }
    /**
     * Fabrique Dao pour CompositePersonnel.
     * @return un nouveau CompositePersonnelDao
     */
    public static AbstractDao<CompositePersonnel> getCompositePersonnelDao() {
        return new CompositePersonnelDaoJDBC(connect);
    }
}
