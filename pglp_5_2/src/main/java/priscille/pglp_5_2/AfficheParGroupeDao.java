package priscille.pglp_5_2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Map;

public class AfficheParGroupeDao
implements Dao<AfficheParGroupe>, Serializable {
    /**
     * Attribut de sérialisation.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Liste des membres du personnel.
     */
    private ArrayList<AfficheParGroupe> apgList;
    /**
     * Constructeur.
     */
    public AfficheParGroupeDao() {
        this.apgList = new ArrayList<AfficheParGroupe>();
    }
    /**
     * Retourne le AfficheParGroupe recherché.
     * @param id L'identifiant du AfficheParGroupe
     * @return Le AfficheParGroupe trouvé
     */
    public AfficheParGroupe get(final int id) {
        for (AfficheParGroupe apg : apgList) {
            if (apg.getId() == id) {
                return apg;
            }
        }
        return null;
    }
    /**
     * Retourne la liste.
     * @return La liste du AfficheParGroupe.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<AfficheParGroupe> getAll() {
        return (ArrayList<AfficheParGroupe>)
            apgList.clone();
    }
    /**
     * Ajoute un afficheParGroupe a la liste.
     * @param apg Le AfficheParGroupe a ajouter
     */
    public void ajouter(final AfficheParGroupe apg) {
        apgList.add(apg);
    }
    /**
     * Modifie un AfficheParGroupe de la liste.
     * @param apg Le AfficheParGroupe a modifier
     * @param params Le parametre a modifier
     */
    @SuppressWarnings("unchecked")
    public void update(final AfficheParGroupe apg,
            final Map<String, Object> params) {
        if (params.containsKey("file")) {
            while (!apg.getList().isEmpty()) {
                apg.remove(apg.getList().getFirst());
            }
            ArrayDeque<InterfacePersonnel> nouvelle =
                    (ArrayDeque<InterfacePersonnel>)
                    params.get("file");
            for (InterfacePersonnel ip : nouvelle) {
                apg.add(ip);
            }
        }
    }
    /**
     * Retire un AfficheParGroupe de la liste.
     * @param apg Le AfficheParGroupe a retirer
     */
    public void retirer(final AfficheParGroupe apg) {
        apgList.remove(apg);
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
    public static AfficheParGroupeDao deSerialization(final String path)
            throws ClassNotFoundException {
        ObjectInputStream ois = null;
        AfficheParGroupeDao apg = null;
        try {
            final FileInputStream fichierIn = new FileInputStream(path);
            ois = new ObjectInputStream(fichierIn);
            apg = (AfficheParGroupeDao) ois.readObject();
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
        return apg;
    }
}
