package priscille.pglp_5_2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class PersonnelDao implements Dao<Personnel>, Serializable {
    /**
     * Attribut de sérialisation.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Liste des membres du personnel.
     */
    private ArrayList<Personnel> personnels;
    /**
     * Constructeur.
     */
    public PersonnelDao() {
        this.personnels = new ArrayList<Personnel>();
    }
    /**
     * Retourne le personnel recherché.
     * @param id L'identifiant du personnel
     * @return Le personnel trouvé
     */
    public Personnel get(final int id) {
        for (Personnel pers : personnels) {
            if (pers.getId() == id) {
                return pers;
            }
        }
        return null;
    }
    /**
     * Retourne la liste du personnel.
     * @return La liste du personnel.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Personnel> getAll() {
        return (ArrayList<Personnel>) personnels.clone();
    }
    /**
     * Ajoute un membre a la liste du personnel.
     * @param pers Le membre a ajouter
     */
    public void ajouter(final Personnel pers) {
        personnels.add(pers);
    }
    /**
     * Modifie un membre du personnel
     * de la liste.
     * @param pers Le membre a modifier
     * @param params Le parametre a modifier
     */
    @SuppressWarnings("unchecked")
    public void update(final Personnel pers,
            final Map<String, Object> params) {
        if (personnels.remove(pers)) {
            Personnel nouveau;
            String nom;
            String prenom;
            LocalDate date;
            ArrayList<String> numTel;
            if (params.containsKey("nom")) {
                nom = (String) params.get("nom");
            } else {
                nom = pers.getNom();
            }
            if (params.containsKey("prenom")) {
                prenom = (String) params.get("prenom");
            } else {
                prenom = pers.getPrenom();
            }
            if (params.containsKey("dateNaissance")) {
                date = (LocalDate) params.get("dateNaissance");
            } else {
                date = pers.getDateNaissance();
            }
            if (params.containsKey("numTel")) {
                numTel = (ArrayList<String>) params.get("numTel");
            } else {
                numTel = pers.getNumTel();
            }
            nouveau = new Personnel
                    .Builder(nom, prenom, date, numTel).build();
            personnels.add(nouveau);
        }
    }
    /**
     * Retire un membre de la liste du personnel.
     * @param pers Le membre a retirer
     */
    public void retirer(final Personnel pers) {
        personnels.remove(pers);
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
    public static PersonnelDao deSerialization(final String path)
            throws ClassNotFoundException {
        ObjectInputStream ois = null;
        PersonnelDao p = null;
        try {
            final FileInputStream fichierIn = new FileInputStream(path);
            ois = new ObjectInputStream(fichierIn);
            p = (PersonnelDao) ois.readObject();
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
        return p;
    }
}
