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
     * @param c Le connecteur
     */
    public PersonnelDaoJDBC(final Connection c) {
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
            final int un = 1;
            final int suppr = 1900;
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT * FROM personnels WHERE id = ?");
            prepare.setInt(1, id);
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
                        (ArrayList<String>) result.getArray("numTel")
                        ).build();
                p.setId(id);
            }
        } catch (SQLException e) {
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
            final int un = 1;
            final int deux = 2;
            final int trois = 3;
            final int quatre = 4;
            final int cinq = 5;
            final int suppr = 1900;
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO personnels (id,nom,prenom,"
                    + "dateNaissance,numTel)"
                    + "VALUES (?,?,?,?,?)");
            prepare.setInt(un, pers.getId());
            prepare.setString(deux, pers.getNom());
            prepare.setString(trois, pers.getPrenom());
            Date date = new Date(
                    pers.getDateNaissance().getYear() - suppr,
                    pers.getDateNaissance().getMonthValue() - un,
                    pers.getDateNaissance().getDayOfMonth());
            prepare.setDate(quatre, date);
            prepare.setArray(cinq, (Array) pers.getNumTel());
            int result = prepare.executeUpdate();
            assert result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pers;
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
