package priscille.pglp_5_2;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayDeque;

public class AfficheParGroupeDaoJDBC
extends AbstractDao<AfficheParGroupe> {
    /**
     * Constructeur.
     */
    public AfficheParGroupeDaoJDBC(Connection c) {
        super(c);
    }
    /**
     * Retourne le personnel recherché.
     * @param id L'identifiant du personnel
     * @return Le personnel trouvé
     */
    @Override
    @SuppressWarnings({ "unchecked" })
    public AfficheParGroupe find(final int id) {
    	AfficheParGroupe apg = null;
        try {
            PreparedStatement prepare = connect.prepareStatement("SELECT * FROM afficheParGroupe WHERE id = ?");
            prepare.setInt(1, id);
            ResultSet result = prepare.executeQuery();
            if (result.first()) {
                apg = new AfficheParGroupe();
                ArrayDeque<InterfacePersonnel> file =
                        (ArrayDeque<InterfacePersonnel>) result.getArray("file");
                for(InterfacePersonnel ip : file) {
                    apg.add(ip);
                }
                apg.setId(id);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return apg;
    }
    /**
     * Ajoute un membre a la liste du personnel.
     * @param pers Le membre a ajouter
     */
    @Override
    public AfficheParGroupe create(final AfficheParGroupe apg) {
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO afficheParGroupe (id,file)"
                    + "VALUES (?,?)");
            prepare.setInt(1, apg.getId());
            prepare.setArray(2, (Array) apg.getList());
            int result = prepare.executeUpdate();
            assert result == 1;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return apg;
    }
    /**
     * Modifie un membre du personnel
     * de la liste.
     * @param pers Le membre a modifier
     * @param params Le parametre a modifier
     */
    @Override
    @SuppressWarnings({ })
    public AfficheParGroupe update(final AfficheParGroupe apg) {
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "UPDATE afficheParGroupe SET file = ? WHERE id = ?");
            prepare.setArray(1, (Array) apg.getList());
            prepare.setInt(2, apg.getId());
            int result = prepare.executeUpdate();
            assert result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apg;
    }
    /**
     * Retire un membre de la liste du personnel.
     * @param pers Le membre a retirer
     */
    @Override
    public void delete(final AfficheParGroupe apg) {
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "DELETE FROM afficheParGroupe WHERE id = ?");
            prepare.setInt(1,  apg.getId());
            int result = prepare.executeUpdate();
            assert result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
