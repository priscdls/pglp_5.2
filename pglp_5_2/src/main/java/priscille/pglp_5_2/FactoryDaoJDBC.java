package priscille.pglp_5_2;

import java.sql.Connection;

public class FactoryDaoJDBC extends AbstractFactoryDao {
    /**
     * Constructeur.
     * @param c Le connecteur
     */
    @SuppressWarnings("static-access")
    public FactoryDaoJDBC(final Connection c) {
        super.connect = c;
    }
    /**
     * Fabrique Dao pour Personnel.
     * @return un nouveau PersonnelDao
     */
    public static Dao<Personnel> getPersonnelDao() {
        return new PersonnelDao();
    }
    /**
     * Fabrique Dao pour CompositePersonnel.
     * @return un nouveau CompositePersonnelDao
     */
    public static Dao<CompositePersonnel> getCompositePersonnelDao() {
        return new CompositePersonnelDao();
    }
    /**
     * Fabrique Dao pour AfficheParGroupe.
     * @return un nouvel AfficheParGroupeDao
     */
    public static Dao<AfficheParGroupe> getAfficheParGroupeDao() {
        return new AfficheParGroupeDao();
    }
    /**
     * Fabrique Dao pour Personnel.
     * @param deserialize un Personnel déja inseré
     * @return un nouveau PersonnelDao
     */
    public Dao<Personnel> getPersonnelDao(final String deserialize) {
        return PersonnelDao.deSerialization(deserialize);
    }
    /**
     * Fabrique Dao pour CompositePersonnel.
     * @param deserialize un CompositePersonnel déja inseré
     * @return un nouveau CompositePersonnelDao
     */
    public Dao<CompositePersonnel> getCompositePersonnelDao(
            final String deserialize) {
        return CompositePersonnelDao.deSerialization(deserialize);
    }
    /**
     * Fabrique Dao pour AfficheParGroupe.
     * @param deserialize un AfficheParGroupe déja inseré
     * @return un nouveau AfficheParGroupeDao
     */
    public Dao<AfficheParGroupe> getAfficheParGroupeDao(
            final String deserialize) {
        return  AfficheParGroupeDao.deSerialization(deserialize);
    }

}
