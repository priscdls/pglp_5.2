package priscille.pglp_5_2;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import priscille.pglp_5_2.AbstractFactoryDao.DaoType;

/**
 * Classe App.
 */
public final class App {
    /**
     * Constructeur.
     */
    private App() {
    }
    /**
     * Début du programme.
     * @param args Les arguments donnés au démarrage de l'application
     * @throws SQLException
     */
    @SuppressWarnings("static-access")
    public static void main(final String[] args) throws SQLException {
        final int annee = 1996;
        final int mois = 05;
        final int jour = 23;
        final int annee2 = 2005;
        final int mois2 = 04;
        final int jour2 = 16;
        CompositePersonnel c1 = new CompositePersonnel();
        CompositePersonnel c2 = new CompositePersonnel();
        CompositePersonnel c3 = new CompositePersonnel();
        ArrayList<String> numTel = new ArrayList<String>();
        numTel.add("0123456789");
        numTel.add("0987654321");
        Personnel p1 = new Personnel.Builder("Daoulas", "Priscille",
                java.time.LocalDate.of(annee, mois, jour),
                numTel).build();
        Personnel p2 = new Personnel.Builder("Jean", "Jacques",
                java.time.LocalDate.of(annee2, mois2, jour2),
                null).build();
        c1.add(c2);
        c1.add(p1);
        c2.add(c3);
        c2.add(p2);
        Connection connect = DataBase.createBase();
        DataBase.deleteAllTables(connect);
        DataBase.createAllTables(connect);
        FactoryDaoJDBC factory = (FactoryDaoJDBC)
                AbstractFactoryDao.getFactory(DaoType.JDBC);
        AbstractDao<CompositePersonnel> daoCp =
                factory.getCompositePersonnelDao();
        AbstractDao<Personnel> daoPers = factory.getPersonnelDao();
        daoCp.create(c1);
        ArrayList<String> numTel2 = new ArrayList<String>();
        numTel2.add("0123456789");
        Personnel p3 = new Personnel.Builder("Daoulas", "Priscille",
                java.time.LocalDate.of(annee, mois, jour),
                numTel2).build();
        p3.setId(p1.getId());
        daoPers.update(p3);
        c2.remove(c3);
        daoCp.update(c1);
        daoPers.delete(p2);
        (daoCp.find(c1.getId())).print();
        daoCp.delete(c1);
    }
}
