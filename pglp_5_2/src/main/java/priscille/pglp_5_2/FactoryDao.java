package priscille.pglp_5_2;

public final class FactoryDao extends AbstractFactoryDao {
    /**
     * Constructeur.
     */
    public FactoryDao() {
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
     * Fabrique Dao pour Personnel.
     * @param deserialize un Personnel déja inseré
     * @return un nouveau PersonnelDao
     * @throws ClassNotFoundException
     */
    public static Dao<Personnel> getPersonnelDao(final String deserialize)
            throws ClassNotFoundException {
        return PersonnelDao.deSerialization(deserialize);
    }
    /**
     * Fabrique Dao pour CompositePersonnel.
     * @param deserialize un CompositePersonnel déja inseré
     * @return un nouveau CompositePersonnelDao
     * @throws ClassNotFoundException
     */
    public static Dao<CompositePersonnel> getCompositePersonnelDao(
            final String deserialize) throws ClassNotFoundException {
        return CompositePersonnelDao.deSerialization(deserialize);
    }
}
