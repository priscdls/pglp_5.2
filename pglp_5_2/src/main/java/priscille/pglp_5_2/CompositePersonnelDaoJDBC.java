package priscille.pglp_5_2;

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
     * Cree une association entre deux compositePersonnel.
     * @param idCp L'identifiant du composite qui contient le second
     * @param idCp2 L'identifiant du composite qui est contenu
     * @throws SQLException
     */
    private void createGroupeComposite(final int idCp, final int idCp2)
            throws SQLException {
        final int un = 1;
        final int deux = 2;
        PreparedStatement prepare = connect.prepareStatement(
                "INSERT INTO GroupeComposite (IdCp, IdCp2) "
                + "VALUES (?,?)");
        prepare.setInt(un, idCp);
        prepare.setInt(deux, idCp2);
        prepare.executeUpdate();
    }
    /**
     * Cree une association entre un compositePersonnel et un personnel.
     * @param idCp L'identifiant du composite
     * @param idP L'identifiant du personnel
     * @throws SQLException
     */
    private void createGroupePersonnel(final int idCp, final int idP)
            throws SQLException {
        final int un = 1;
        final int deux = 2;
        PreparedStatement prepare = connect.prepareStatement(
                "INSERT INTO GroupePersonnel (IdCp, IdP) "
                + "VALUES (?,?)");
        prepare.setInt(un, idCp);
        prepare.setInt(deux, idP);
        prepare.executeUpdate();
    }
    /**
     * Recherche les associations entre un composite donné et des composites.
     * @param idCp L'identifiant du composite cherché
     * @return La listes de ses associations
     * @throws SQLException
     */
    private ArrayList<InterfacePersonnel> findGroupeComposite(
            final int idCp) throws SQLException {
        ArrayList<InterfacePersonnel> list
        = new ArrayList<InterfacePersonnel>();
        PreparedStatement prepare = connect.prepareStatement(
                "SELECT IdCp2 FROM GroupeComposite "
                + "WHERE IdCp = ?");
        prepare.setInt(1, idCp);
        ResultSet result = prepare.executeQuery();
        while (result.next()) {
            list.add(this.find(result.getInt("IdCp2")));
        }
        return list;
    }
   /**
    * Recherche les associations entre un composite donné et des personnels.
    * @param idCp L'identifiant du composite cherché
    * @return La listes de ses associations
    * @throws SQLException
    */
    @SuppressWarnings("static-access")
    private ArrayList<InterfacePersonnel> findGroupePersonnel(
            final int idCp) throws SQLException {
        ArrayList<InterfacePersonnel> list
        = new ArrayList<InterfacePersonnel>();
        PreparedStatement prepare = connect.prepareStatement(
                "SELECT IdP FROM GroupePersonnel "
                + "WHERE IdCp = ?");
        prepare.setInt(1, idCp);
        ResultSet result = prepare.executeQuery();
        while (result.next()) {
            FactoryDaoJDBC factory = (FactoryDaoJDBC)
                    AbstractFactoryDao.getFactory(DaoType.JDBC);
            AbstractDao<Personnel> daoPers = factory.getPersonnelDao();
            Personnel p = daoPers.find(result.getInt("IdP"));
            if (p != null) {
                list.add(p);
            }
        }
        return list;
    }
    /**
     * Recherche les associations d'un composite donné.
     * @param idCp L'identifiant du composite
     * @return La listes de ses associations
     * @throws SQLException
     */
    private ArrayList<InterfacePersonnel> findGroupePersonnelComposite(
            final int idCp) throws SQLException {
        ArrayList<InterfacePersonnel> list = findGroupeComposite(idCp);
        ArrayList<InterfacePersonnel> list2 = findGroupePersonnel(idCp);
        for (InterfacePersonnel ip : list2) {
            list.add(ip);
        }
        return list;
    }
    /**
     * Supprime toutes les associations d'un composite.
     * @param idCp L'identifiant du composite
     * @throws SQLException
     */
    private void deleteGroupePersonnelComposite(final int idCp)
            throws SQLException {
        PreparedStatement prepare = connect.prepareStatement(
                "DELETE FROM GroupePersonnel WHERE IdCp = ?");
        prepare.setInt(1, idCp);
        prepare.executeUpdate();
        PreparedStatement prepare2 = connect.prepareStatement(
                "DELETE FROM GroupeComposite WHERE IdCp = ?");
        prepare2.setInt(1, idCp);
        prepare2.executeUpdate();
    }
    /**
     * Ajoute un CompositePersonnel.
     * @param cpers Le composite a ajouter
     */
    @SuppressWarnings("static-access")
    @Override
    public CompositePersonnel create(final CompositePersonnel cpers) {
        final int un = 1;
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "INSERT INTO CompositePersonnel (Id)"
                    + "VALUES (?)");
            prepare.setInt(1, cpers.getId());
            int result = prepare.executeUpdate();
            assert result == un;
            for (InterfacePersonnel ip : cpers.getList()) {
                if (ip.getClass() == CompositePersonnel.class) {
                    CompositePersonnel cp = (CompositePersonnel) ip;
                    if (this.find(cp.getId()) == null) {
                        this.create(cp);
                    }
                    this.createGroupeComposite(cpers.getId(), cp.getId());
                } else {
                    FactoryDaoJDBC factory = (FactoryDaoJDBC)
                            AbstractFactoryDao.getFactory(DaoType.JDBC);
                    AbstractDao<Personnel> daoPers = factory.getPersonnelDao();
                    Personnel pers = (Personnel) ip;
                    if (daoPers.find(pers.getId()) == null) {
                        daoPers.create(pers);
                    }
                    this.createGroupePersonnel(cpers.getId(), pers.getId());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.delete(cpers);
        }
        return cpers;
    }
    /**
     * Retourne le CompositePersonnel recherché.
     * @param id L'identifiant du composite
     * @return Le personnel trouvé
     */
    @Override
    public CompositePersonnel find(final int id) {
        CompositePersonnel cp = null;
        try {
            PreparedStatement prepare = connect.prepareStatement(
                    "SELECT * FROM CompositePersonnel WHERE id = ?");
            prepare.setInt(1, id);
            ResultSet result = prepare.executeQuery();
            if (result.next()) {
                cp = new CompositePersonnel();
                cp.setId(id);
                ArrayList<InterfacePersonnel> liste
                = this.findGroupePersonnelComposite(id);
                for (InterfacePersonnel ip : liste) {
                    cp.add(ip);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return cp;
    }
    /**
     * Modifie un CompositePersonnel.
     * @param cpers Le composite a modifier
     */
    @Override
    public CompositePersonnel update(final CompositePersonnel cpers) {
        CompositePersonnel save = this.find(cpers.getId());
        if (save != null) {
            this.delete(save);
            this.create(cpers);
            if (this.find(cpers.getId()) == null) {
                this.create(save);
            }
            return cpers;
        } else {
            return save;
        }
    }
    /**
     * Retire un CompositePersonnel.
     * @param cpers Le composite a retirer
     */
    @Override
    public void delete(final CompositePersonnel cpers) {
        try {
            this.deleteGroupePersonnelComposite(cpers.getId());
            PreparedStatement prepare = connect.prepareStatement(
                    "DELETE FROM CompositePersonnel WHERE id = ?");
            prepare.setInt(1,  cpers.getId());
            int result = prepare.executeUpdate();
            assert result == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
