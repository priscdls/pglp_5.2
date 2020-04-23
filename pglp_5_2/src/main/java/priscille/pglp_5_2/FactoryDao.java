package priscille.pglp_5_2;

public final class FactoryDao {
    /**
     * Constructeur.
     */
    private FactoryDao() {
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
    /**
     * Fabrique Dao pour AfficheParGroupe.
     * @param deserialize un AfficheParGroupe déja inseré
     * @return un nouveau AfficheParGroupeDao
     * @throws ClassNotFoundException
     */
    public static Dao<AfficheParGroupe> getAfficheParGroupeDao(
            final String deserialize) throws ClassNotFoundException {
        return  AfficheParGroupeDao.deSerialization(deserialize);
    }
}
