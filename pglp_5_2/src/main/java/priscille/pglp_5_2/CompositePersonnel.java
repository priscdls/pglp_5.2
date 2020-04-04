package priscille.pglp_5_2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Pattern Composite CompositePersonnel
 * implémentant l'interface InterfacePersonnel.
 */
public class CompositePersonnel
implements InterfacePersonnel, Iterable<InterfacePersonnel>, Serializable {
    /**
     * Attribut de sérialisation.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Liste de membres du personnels d'un même composite.
     */
    private ArrayList<InterfacePersonnel> list =
            new ArrayList<InterfacePersonnel>();
    /**
     * Identifiant d'un composite.
     */
    private final int id;
    /**
     * Identifiant du composite suivant.
     */
    private static int idFin = 1;
    /**
     * Getter de l'identifiant du Composite.
     * @return L'identifiant
     */
    public final int getId() {
        return this.id;
    }
    /**
     * Getter de la liste.
     * @return La liste de composite
     */
    @SuppressWarnings("unchecked")
    public final ArrayList<InterfacePersonnel> getList() {
        return (ArrayList<InterfacePersonnel>) this.list.clone();
    }
    /**
     * Constructeur.
     */
    public CompositePersonnel() {
        id = idFin++;
        list = new ArrayList<InterfacePersonnel>();
    }
    /**
     * Fonction d'écriture des informations du composite.
     */
    public void print() {
        System.out.println("Id : " + id);
        for (InterfacePersonnel intP : list) {
            intP.print();
        }
    }
    /**
     * Fonction to String.
     * @return Une chaine de caractere
     */
    public String toString() {
        String s = "Id : " + id + "\n";
        for (InterfacePersonnel intP : list) {
            s += intP.toString();
        }
        return s + "\n";
    }
    /**
     * Fonction qui ajoute une personne a la liste du personnel.
     * @param intP La personne a ajouter
     */
    public void add(final InterfacePersonnel intP) {
        list.add(intP);
    }
    /**
     * Fonction qui enlève une personne a la liste du personnel.
     * @param intP La personne a enlever
     */
    public void remove(final InterfacePersonnel intP) {
        list.remove(intP);
    }
    /**
     * Fonction qui crée un Itérateur
     * de la liste des personnes du Composite.
     * @return L'iterateur
     */
    public Iterator<InterfacePersonnel> iterator() {
        return list.iterator();
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
     * @return Le composite deserialisé
     */
    public static CompositePersonnel deSerialization(final String path) {
        ObjectInputStream ois = null;
        CompositePersonnel cp = null;
        try {
            final FileInputStream fichierIn = new FileInputStream(path);
            ois = new ObjectInputStream(fichierIn);
            cp = (CompositePersonnel) ois.readObject();
        } catch (final java.io.IOException e) {
            e.printStackTrace();
        } catch (final ClassNotFoundException e) {
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
