package priscille.pglp_5_2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

public class CompositePersonnelDao
implements Dao<CompositePersonnel>, Serializable {
    /**
     * Attribut de sérialisation.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Liste des composites.
     */
    private ArrayList<CompositePersonnel> compositePersonnels;
    /**
     * Constructeur.
     */
    public CompositePersonnelDao() {
        this.compositePersonnels = new ArrayList<CompositePersonnel>();
    }
    /**
     * Retourne le composite recherché.
     * @param id L'identifiant du composite
     * @return Le composite personnel trouvé
     */
    public CompositePersonnel get(final int id) {
        for (CompositePersonnel cp : compositePersonnels) {
            if (cp.getId() == id) {
                return cp;
            }
        }
        return null;
    }
    /**
     * Retourne la liste.
     * @return La liste du composite.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<CompositePersonnel> getAll() {
        return (ArrayList<CompositePersonnel>)
                compositePersonnels.clone();
    }
    /**
     * Ajoute un composite a la liste.
     * @param cp Le composite a ajouter
     */
    public void ajouter(final CompositePersonnel cp) {
        compositePersonnels.add(cp);
    }
    /**
     * Modifie un composite de la liste.
     * @param cp Le composite a modifier
     * @param params Le parametre a modifier
     */
    @SuppressWarnings("unchecked")
    public void update(final CompositePersonnel cp,
            final Map<String, Object> params) {
        if (params.containsKey("list")) {
            while (!cp.getList().isEmpty()) {
                cp.remove(cp.getList().get(0));
            }
            ArrayList<InterfacePersonnel> nouvelle =
                    (ArrayList<InterfacePersonnel>)
                    params.get("list");
            for (InterfacePersonnel ip : nouvelle) {
                cp.add(ip);
            }
        }
    }
    /**
     * Retire un composite de la liste.
     * @param cp Le composite a retirer
     */
    public void retirer(final CompositePersonnel cp) {
        compositePersonnels.remove(cp);
    }
    /**
     * Fonction de sérialisation.
     * @param path Adresse du fichier
     */
    public void serialization(final String path) {
        ObjectOutputStream oos = null;
        try {
            final FileOutputStream fichierOut = new FileOutputStream(path);
            oos = new ObjectOutputStream(fichierOut);
            oos.writeObject(this);
            oos.flush();
            oos.close();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final java.io.IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    /**
     * Fonction de désérialisation.
     * @param path Adresse du fichier
     * @return Le personnelDao deserialisé
     * @throws ClassNotFoundException
     */
    public static CompositePersonnelDao deSerialization(final String path)
            throws ClassNotFoundException {
        ObjectInputStream ois = null;
        CompositePersonnelDao cp = null;
        try {
            final FileInputStream fichierIn = new FileInputStream(path);
            ois = new ObjectInputStream(fichierIn);
            cp = (CompositePersonnelDao) ois.readObject();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final java.io.IOException ex) {
                ex.printStackTrace();
            }
        }
        return cp;
    }
}
