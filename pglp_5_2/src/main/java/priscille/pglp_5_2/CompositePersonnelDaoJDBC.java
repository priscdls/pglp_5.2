package priscille.pglp_5_2;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompositePersonnelDaoJDBC
extends AbstractDao<CompositePersonnel> {
    /**
     * Constructeur.
     */
    public CompositePersonnelDaoJDBC(Connection c) {
        super(c);
    }
    /**
     * Retourne le CompositePersonnel recherché.
     * @param id L'identifiant du composite
     * @return Le personnel trouvé
     */
    @Override
    @SuppressWarnings({ "unchecked" })
    public CompositePersonnel find(final int id) {
        CompositePersonnel cp = null;
        try {
            PreparedStatement prepare = connect.prepareStatement("SELECT * FROM compositePersonnels WHERE id = ?");
            prepare.setInt(1, id);
            ResultSet result = prepare.executeQuery();
            if (result.first()) {
                cp = new CompositePersonnel();
                ArrayList<InterfacePersonnel> liste =
                        (ArrayList<InterfacePersonnel>) result.getArray("list");
                for(InterfacePersonnel ip : liste) {
                    cp.add(ip);
                }
                cp.setId(id);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return cp;
    }
    /**
     * Ajoute un CompositePersonnel.
     * @param cpers Le composite a ajouter
     */
    @Override
    public CompositePersonnel create(final CompositePersonnel cpers) {
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO compositePersonnels (id,list)"
                    + "VALUES (?,?)");
            prepare.setInt(1, cpers.getId());
            prepare.setArray(2, (Array) cpers.getList());
            int result = prepare.executeUpdate();
            assert result == 1;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return cpers;
    }
    /**
     * Modifie un CompositePersonnel.
     * @param cpers Le composite a modifier
     */
    @Override
    @SuppressWarnings({ })
    public CompositePersonnel update(final CompositePersonnel cpers) {
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "UPDATE compositePersonnels SET list = ? WHERE id = ?");
            prepare.setArray(1, (Array) cpers.getList());
            prepare.setInt(2, cpers.getId());
            int result = prepare.executeUpdate();
            assert result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cpers;
    }
    /**
     * Retire un CompositePersonnel.
     * @param cpers Le composite a retirer
     */
    @Override
    public void delete(final CompositePersonnel cpers) {
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "DELETE FROM compositePersonnels WHERE id = ?");
            prepare.setInt(1,  cpers.getId());
            int result = prepare.executeUpdate();
            assert result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
