package priscille.pglp_5_2;

public abstract class AbstractFactoryDao {
    /**
     * Enumération des types de Dao.
     */
    public enum DaoType {
        /**
         * Personnel.
         */
        Personnel,
        /**
         * CompositePersonnel.
         */
        CompositePersonnel,
        /**
         * AfficheParGroupe.
         */
        AfficheParGroupe;
    }
    /**
     * Fabrique Dao pour Personnel.
     * @return un nouveau PersonnelDao
     */
    public abstract Dao<Personnel> getPersonnelDao();
    /**
     * Fabrique Dao pour CompositePersonnel.
     * @return un nouveau CompositePersonnelDao
     */
    public abstract Dao<CompositePersonnel> getCompositePersonnelDao();
    /**
     * Fabrique Dao pour AfficheParGroupe.
     * @return un nouvel AfficheParGroupeDao
     */
    public abstract Dao<AfficheParGroupe> getAfficheParGroupeDao();
    /**
     * Fabrique Dao pour Personnel.
     * @param deserialize un Personnel déja inseré
     * @return un nouveau PersonnelDao
     */
    public abstract Dao<Personnel> getPersonnelDao(String deserialize);
    /**
     * Fabrique Dao pour CompositePersonnel.
     * @param deserialize un CompositePersonnel déja inseré
     * @return un nouveau CompositePersonnelDao
     */
    public abstract Dao<CompositePersonnel> getCompositePersonnelDao(
            String deserialize);
    /**
     * Fabrique Dao pour AfficheParGroupe.
     * @param deserialize un AfficheParGroupe déja inseré
     * @return un nouveau AfficheParGroupeDao
     */
    public abstract Dao<AfficheParGroupe> getAfficheParGroupeDao(
            String deserialize);
    /**
     * Fabrique général de Dao.
     * @param type Type de Dao
     * @return Une fabrique de Dao
     */
    public static AbstractFactoryDao getFactory(final DaoType type) {
        if (type == DaoType.Personnel || type == DaoType.CompositePersonnel
                || type == DaoType.AfficheParGroupe) {
            return new FactoryDao();
        }
        return null;
    }
}
