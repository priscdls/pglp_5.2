package priscille.pglp_5_2;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import priscille.pglp_5_2.AbstractFactoryDao.DaoType;

public class CompositePersonnelDaoJDBC
extends AbstractDao<CompositePersonnel> {
    /**
     * Constructeur.
     * @param c Le connecteur
     */
    public CompositePersonnelDaoJDBC(final Connection c) {
        super(c);
    }
    /**
     * Création d'un composant de tyoe composite.
     * @param idComposite L'identifiant du composite
     * @param idComposant L'identifiant du composant du composite
     * @throws SQLException
     */
    private void createComposantComposite(final int idComposite, final int idComposant) throws SQLException {
        PreparedStatement prepare = connect.prepareStatement(
                "INSERT INTO ComposantComposite (idComposite,idComposant)"
                + " VALUES(?, ?)");
        prepare.setInt(1, idComposite);
        prepare.setInt(2, idComposant);
        int result = prepare.executeUpdate();
        assert result == 1;
    }
    /**
     * Création d'un composant de type Personnel.
     * @param idComposite L'identifiant du composite
     * @param idPersonnel L'identifiant du Personnel
     * @throws SQLException
     */
    public void createComposantPersonnel(final int idComposite, final int idPersonnel) throws SQLException {
        PreparedStatement prepare = connect.prepareStatement(
                "INSERT INTO ComposantPersonnel (idComposite,idPersonnel)"
                + " VALUES(?, ?)");
        prepare.setInt(1, idComposite);
        prepare.setInt(2, idPersonnel);
        int result = prepare.executeUpdate();
        assert result == 1;
    }
    /**
     * Ajoute un CompositePersonnel de type composite ou personnel.
     * @param cpers Le composite a ajouter
     */
    @Override
    public CompositePersonnel create(final CompositePersonnel cpers) {
        final int un = 1;
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO CompositePersonnels (id)"
                    + "VALUES (?)");
            prepare.setInt(1, cpers.getId());
            int result = prepare.executeUpdate();
            assert result == un;
            for (InterfacePersonnel ip : cpers.getList()) {
            	if (ip.getClass() == CompositePersonnel.class) {
                    createComposantComposite(cpers.getId(), ip.getId());
                } else {
                    createComposantPersonnel(cpers.getId(), ip.getId());
                    }
            } 
        } catch (SQLException e) {
            e.printStackTrace();
            this.delete(cpers);
        }
        return cpers;
    }
    /**
     * Cherche un CompositePersonnel de type Composite.
     * @param idComposite L'identifiant du composite recherché
     * @return La liste des composants du composite recherché
     * @throws SQLException
     */
    private ArrayList<InterfacePersonnel> findComposantComposite(final int idComposite) throws SQLException {
        ArrayList<InterfacePersonnel> list =
                new ArrayList<InterfacePersonnel> ();
        PreparedStatement prepare = connect.prepareStatement(
                "SELECT idComposant FROM ComposantComposite WHERE idComposite = ?");
        prepare.setInt(1, idComposite);
        ResultSet result = prepare.executeQuery();
        while (result.next()) {
            CompositePersonnel cp = this.find(result.getInt("idComposant"));
            if(cp != null) list.add(cp);
        }
        return list;
    }
    /**
     * Cherche un CompositePersonnel de type Personnel.
     * @param idComposite L'identifiant du composite recherché
     * @return La liste des composants de ce composite
     * @throws SQLException
     */
    private ArrayList<InterfacePersonnel> findComposantPersonnel(final int idComposite) throws SQLException {
        ArrayList<InterfacePersonnel> list =
                new ArrayList<InterfacePersonnel> ();
        FactoryDaoJDBC factorytmp = (FactoryDaoJDBC) AbstractFactoryDao.getFactory(DaoType.JDBC);
        PersonnelDaoJDBC daoPersonnels = (PersonnelDaoJDBC) factorytmp.getPersonnelDao();
        PreparedStatement prepare = connect.prepareStatement(
                "SELECT idPersonnel FROM ComposantPersonnel WHERE idComposite = ?");
        prepare.setInt(1, idComposite);
        ResultSet result = prepare.executeQuery();
        while (result.next()) {
            Personnel p = daoPersonnels.find(result.getInt("idPersonnel"));
            if(p != null) list.add(p);
        }
        return list;
    }
    /**
     * Cherche un composite.
     * @param idComposite l'identifiant du composite recherché.
     * @return La liste des composants du composite
     * @throws SQLException
     */
    private ArrayList<InterfacePersonnel> findComposant(final int idComposite) throws SQLException {
        ArrayList<InterfacePersonnel> listComposite =
                findComposantComposite(idComposite);
        ArrayList<InterfacePersonnel> listPersonnel =
                findComposantPersonnel(idComposite);
        for (InterfacePersonnel ip : listPersonnel) {
            listComposite.add(ip);
        }
        return listComposite;
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
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT * FROM CompositePersonnel WHERE id = ?");
            prepare.setInt(1, id);
            ResultSet result = prepare.executeQuery();
            if (result.first()) {
                cp = new CompositePersonnel();
                cp.setId(id);
                ArrayList<InterfacePersonnel> liste =
                        (ArrayList<InterfacePersonnel>) result.getArray("list");
                for (InterfacePersonnel ip : liste) {
                    cp.add(ip);
                }
                cp.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cp;
    }
    /**
     * Supprime un CompositePersonnel de type Composite.
     * @param id L'identifiant du composite
     * @throws SQLException
     */
    private void deleteComposantComposite(final int id) throws SQLException {
        PreparedStatement prepare = connect.prepareStatement(
                "DELETE FROM ComposantComposite WHERE idComposite = ?");
        prepare.setInt(1, id);
        prepare.executeUpdate();
    }
    /**
     * Supprime un CompositePersonnel de type Personnel.
     * @param id L'identifiant du composite
     * @throws SQLException
     */
    private void deleteComposantPersonnel(final int id) throws SQLException {
        PreparedStatement prepare = connect.prepareStatement(
                "DELETE FROM ComposantPersonnel WHERE idComposite = ?");
        prepare.setInt(1, id);
        prepare.executeUpdate();
    }
    /**
     * Supprime un CompositePersonnel.
     * @param idComposite L'identifiant du composite
     * @throws SQLException 
     */
    private void deleteComposant(final int idComposite) throws SQLException {
        deleteComposantPersonnel(idComposite);
        deleteComposantComposite(idComposite);
    }
    /**
     * Retire un CompositePersonnel.
     * @param cpers Le composite a retirer
     */
    @Override
    public void delete(final CompositePersonnel cpers) {
        try {
        	this.deleteComposant(cpers.getId());
            PreparedStatement prepare = connect.prepareStatement(
                    "DELETE FROM CompositePersonnel WHERE id = ?");
            prepare.setInt(1,  cpers.getId());
            int result = prepare.executeUpdate();
            assert result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    public CompositePersonnels update(final CompositePersonnels object) {
        CompositePersonnels before = this.find(object.getId());
        if (before != null) {
            try {
                this.deleteComposant(object.getId());
                for (InterfacePersonnels ip : object.getList()) {
                    if (ip.getClass() == CompositePersonnels.class) {
                        createComposantComposite(object.getId(), ip.getId());
                    } else {
                        createComposantPersonnel(object.getId(), ip.getId());
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                this.delete(object);
                this.create(before);
                return before;
            }
        }
        return object;
}
}
