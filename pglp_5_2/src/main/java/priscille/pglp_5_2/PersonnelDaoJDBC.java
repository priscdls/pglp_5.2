package priscille.pglp_5_2;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import priscille.pglp_5_2.AbstractFactoryDao.DaoType;

public class PersonnelDaoJDBC extends AbstractDao<Personnel> {
    /**
     * Constructeur.
     * @param c Le connecteur
     */
    public PersonnelDaoJDBC(final Connection c) {
        super(c);
    }
    /**
     * Recherche d'un Composite Personnel.
     * @param id
     * @return Le compositePersonnel recherché
     * @throws SQLException
     */
    private ArrayList<CompositePersonnel> findComposantPersonnel(final int id)
            throws SQLException {
        ArrayList<CompositePersonnel> list =
                new ArrayList<CompositePersonnel> ();
        FactoryDaoJDBC factorytmp = (FactoryDaoJDBC)
                AbstractFactoryDao.getFactory(DaoType.JDBC);
        CompositePersonnelDaoJDBC daoComposite = (CompositePersonnelDaoJDBC)
                factorytmp.getCompositePersonnelDao();
        PreparedStatement prepare = connect.prepareStatement(
                "SELECT idComposite FROM composantPersonnel WHERE idPersonnel = ?");
        prepare.setInt(1, id);
        ResultSet result = prepare.executeQuery();
        while (result.next()) {
            CompositePersonnel cp = daoComposite.find(result.getInt("idComposite"));
            if(cp != null) list.add(cp);
        }
        return list;
}
    /**
     * Fonction pour créer un numero de telephone a un Personnel. 
     * @param num le numero a ajouter
     * @param id l'identifiant du Personnel
     * @return Le numero ajouté
     */
    private String createNumTel(final String num, final int id) {
    	final int un = 1;
    	final int deux = 2;
        PreparedStatement prepare;
		try {
			prepare = connect.prepareStatement(
			"INSERT INTO NumeroTelephone"
			+ " (id,numTel)"
			+ " VALUES(?, ?)");
			prepare.setInt(un, id);
            prepare.setString(deux, num);
            int result = prepare.executeUpdate();
            assert result == un;
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return num;
    }
    /**
     * Retourne le(s) numero(s) de telephone du Personnel cherché.
     * @param id L'identifiant du Personnel
     * @return La liste de numero de telephone du Personnel
     * @throws SQLException
     */
    private ArrayList<String> findNumTel(final int id)
            throws SQLException {
        final int un = 1;
        ArrayList<String> numeroTelephone = new ArrayList<String> ();
        PreparedStatement prepare = connect.prepareStatement(
                "SELECT numTel FROM NumeroTelephone WHERE id = ?");
        prepare.setInt(un, id);
        ResultSet result = prepare.executeQuery();
        while (result.next()) {
            numeroTelephone.add(result.getString("numTel"));
        }
        return numeroTelephone;
    }
    /**
     * Supprime tous les numeros de telephone d'un Personnel.
     * @param id L'identifiant du Personnel
     * @throws SQLException
     */
    private void deleteAllNumTel(final int id) throws SQLException {
        final int un = 1;
        PreparedStatement prepare = connect.prepareStatement(
                "DELETE FROM NumeroTelephone WHERE id = ?");
        prepare.setInt(un, id);
        prepare.executeUpdate();
    }
    /**
     * Ajoute un membre du personnel.
     * @param pers Le membre a ajouter
     */
    @Override
    @SuppressWarnings("deprecation")
    public Personnel create(final Personnel pers) {
        try {
            final int un = 1;
            final int deux = 2;
            final int trois = 3;
            final int quatre = 4;
            final int suppr = 1900;
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO Personnel (id,nom,prenom,"
                    + "dateNaissance)"
                    + "VALUES (?,?,?,?)");
            prepare.setInt(un, pers.getId());
            prepare.setString(deux, pers.getNom());
            prepare.setString(trois, pers.getPrenom());
            Date date = new Date(
                    pers.getDateNaissance().getYear() - suppr,
                    pers.getDateNaissance().getMonthValue() - un,
                    pers.getDateNaissance().getDayOfMonth());
            prepare.setDate(quatre, date);
            int result = prepare.executeUpdate();
            assert result == un;
            for (String num : pers.getNumTel()) {
                createNumTel(num, pers.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.delete(pers);
        }
        return pers;
    }
    /**
     * Retourne le personnel recherché.
     * @param id L'identifiant du personnel
     * @return Le personnel trouvé
     */
    @Override
    @SuppressWarnings({ "deprecation" })
    public Personnel find(final int id) {
        LocalDate date;
        Personnel p = null;
        try {
            final int un = 1;
            final int suppr = 1900;
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT * FROM Personnel WHERE id = ?");
            prepare.setInt(un, id);
            ResultSet result = prepare.executeQuery();
            if (result.first()) {
                date = LocalDate.of(
                        result.getDate("dateNaissance").getYear() + suppr,
                        result.getDate("dateNaissance").getMonth() + un,
                        result.getDate("dateNaissance").getDay());
                p = new Personnel.Builder(
                        result.getString("nom"),
                        result.getString("prenom"),
                        date,
                        this.findNumTel(id)
                        ).build();
                p.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
    /**
     * Modifie un membre du personnel.
     * @param pers Le membre a modifier
     */
    @Override
    @SuppressWarnings({ "deprecation" })
    public Personnel update(final Personnel pers) {
        try {
            final int un = 1;
            final int deux = 2;
            final int trois = 3;
            final int quatre = 4;
            final int cinq = 5;
            final int suppr = 1900;
            PreparedStatement prepare = connect.prepareStatement(
                    "UPDATE personnes SET nom = ?, prenom = ?,"
                    + "dateNaissance = ?, numTel = ? WHERE id = ?");
            prepare.setString(un, pers.getNom());
            prepare.setString(deux, pers.getPrenom());
            Date date = new Date(
                    pers.getDateNaissance().getYear() - suppr,
                    pers.getDateNaissance().getMonthValue() - un,
                    pers.getDateNaissance().getDayOfMonth());
            prepare.setDate(trois, date);
            prepare.setArray(quatre, (Array) pers.getNumTel());
            prepare.setInt(cinq, pers.getId());
            int result = prepare.executeUpdate();
            assert result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pers;
    }
    /**
     * Retire un membre du personnel.
     * @param pers Le membre a retirer
     */
    @Override
    public void delete(final Personnel pers) {
    	final int un = 1;
        try {
        	this.deleteComposantPersonnel(pers.getId());
            this.deleteAllNumTel(pers.getId());
            PreparedStatement prepare = connect.prepareStatement(
                    "DELETE FROM Personnel WHERE id = ?");
            prepare.setInt(1,  pers.getId());
            int result = prepare.executeUpdate();
            assert result == un;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
