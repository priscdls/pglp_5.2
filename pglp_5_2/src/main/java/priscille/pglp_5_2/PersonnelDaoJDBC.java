package priscille.pglp_5_2;

import java.sql.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersonnelDaoJDBC extends AbstractDao<Personnel> {
    /**
     * Constructeur.
     */
    public PersonnelDaoJDBC(Connection c) {
        super(c);
    }
    /**
     * Retourne le personnel recherché.
     * @param id L'identifiant du personnel
     * @return Le personnel trouvé
     */
    @Override
    @SuppressWarnings({ "deprecation", "unchecked" })
    public Personnel find(final int id) {
        LocalDate date;
        Personnel p = null;
        try {
            PreparedStatement prepare = connect.prepareStatement("SELECT * FROM personnels WHERE id = ?");
            prepare.setInt(1, id);
            ResultSet result = prepare.executeQuery();
            if (result.first()) {
                date = LocalDate.of(
                        result.getDate(4).getYear() + 1900,
                        result.getDate(4).getMonth() + 1,
                        result.getDate(4).getDay());
                p = new Personnel.Builder(
                        result.getString("nom"),
                        result.getString("prenom"),
                        date,
                        (ArrayList<String>) result.getArray(5)
                        ).build();
                p.setId(id);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return p;
    }
    /**
     * Ajoute un membre du personnel.
     * @param pers Le membre a ajouter
     */
    @Override
    @SuppressWarnings("deprecation")
    public Personnel create(final Personnel pers) {
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO personnels (id,nom,prenom,dateNaissance,numTel)"
                    + "VALUES (?,?,?,?,?)");
            prepare.setInt(1, pers.getId());
            prepare.setString(2, pers.getNom());
            prepare.setString(3, pers.getPrenom());
            Date date = new Date(
                    pers.getDateNaissance().getYear() - 1900,
                    pers.getDateNaissance().getMonthValue() - 1,
                    pers.getDateNaissance().getDayOfMonth());
            prepare.setDate(4, date);
            prepare.setArray(5, (Array) pers.getNumTel());
            int result = prepare.executeUpdate();
            assert result == 1;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return pers;
    }
    /**
     * Modifie un membre du personnel.
     * @param pers Le membre a modifier
     * @param params Le parametre a modifier
     */
    @Override
    @SuppressWarnings({ "deprecation" })
    public Personnel update(final Personnel pers) {
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "UPDATE personnes SET nom = ?, prenom = ?,"
                    + "dateNaissance = ?, numTel = ? WHERE id = ?");
	        prepare.setString(1, pers.getNom());
	        prepare.setString(2, pers.getPrenom());
	        Date date = new Date(
	                pers.getDateNaissance().getYear() - 1900,
	                pers.getDateNaissance().getMonthValue() - 1,
	                pers.getDateNaissance().getDayOfMonth());
	        prepare.setDate(3, date);
	    	prepare.setArray(4, (Array) pers.getNumTel());
	        prepare.setInt(5, pers.getId());
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
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "DELETE FROM personnels WHERE id = ?");
            prepare.setInt(1,  pers.getId());
            int result = prepare.executeUpdate();
	        assert result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
