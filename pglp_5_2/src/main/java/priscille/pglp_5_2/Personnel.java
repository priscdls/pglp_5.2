package priscille.pglp_5_2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 * Classe Personnel représente un membre du personnel.
 * Cette classe implémente l'interface InterfacePersonnel.
 */
public final class Personnel implements InterfacePersonnel, Serializable {
    /**
     * Attribut de sérialisation.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Identifiant d'un personnel.
     */
    private int id;
    /**
     * Identifiant du composite suivant.
     */
    private static int idFin = 1;
    /**
     * Nom du membre du personnel.
     */
    private final String nom;
    /**
     * Prénom du membre du personnel.
     */
    private final String prenom;
    /**
     * Date de naissance du membre du personnel.
     */
    private final java.time.LocalDate dateNaissance;
    /**
     * Liste des numéro de téléphone du membre du personnel.
     */
    private final ArrayList<String> numTel;
    /**
     * Getter de l'identifiant du personnel.
     * @return L'identifiant
     */
    public int getId() {
        return this.id;
    }
    /**
     * Setter de l'identifiant du personnel.
     * @param i L'identifiant modifié
     */
    public void setId(final int i) {
        this.id = i;
    }
    /**
     * Getter de nom.
     * @return Le nom
     */
    public String getNom() {
        return this.nom;
    }
    /**
     * Getter de prenom.
     * @return Le prenom
     */
    public String getPrenom() {
        return this.prenom;
    }
    /**
     * Getter de dateNaissance.
     * @return La date de naissance
     */
    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }
    /**
     * Getter de numero de telephone.
     * @return La liste des numero de telephone
     */
    @SuppressWarnings("unchecked")
    public ArrayList<String> getNumTel() {
        return (ArrayList<String>) this.numTel.clone();
    }
    /**
     * Pattern Builder.
     *
     */
    public static class Builder {
        /**
         * Nom du membre du personnel.
         */
        private final String nom;
        /**
         * Prénom du membre du personnel.
         */
        private final String prenom;
        /**
         * Date de naissance du membre du personnel.
         */
        private final java.time.LocalDate dateNaissance;
        /**
         * Liste des numéros de téléphone du membre du personnel.
         */
        private final ArrayList<String> numTel;
        /**
         * Constructeur.
         * @param n Nom du membre du personnel.
         * @param p Prénom du membre du personnel.
         * @param date Date de naissance du membre du personnel.
         * @param num Liste des numéro de téléphone.
         * du membre du personnel.
         */
        public Builder(final String n, final String p,
                final java.time.LocalDate date,
                final ArrayList<String> num) {
            this.nom = n;
            this.prenom = p;
            this.dateNaissance = date;
            this.numTel = num;
        }
        /**
         * Fonction qui construit une variable
         * de type Personnel a l'aide du Builder.
         * @return La variable Personnel
         */
        public Personnel build() {
            return new Personnel(this);
        }
    }
    /**
     * Constructeur.
     * @param builder
     */
    public Personnel(final Builder builder) {
        id = idFin++;
        nom = builder.nom;
        prenom = builder.prenom;
        dateNaissance = builder.dateNaissance;
        numTel = builder.numTel;
    }
    /**
     * Fonction qui écrit les informations d'un membre du Personnel.
     */
    public void print() {
        System.out.println("Nom : " + nom + ", Prenom : " + prenom
                + ", Date de Naissance : " + dateNaissance
                + ", Numero(s) de telephone : " + numTel);
    }
    /**
     * Fonction to String.
     * @return Une chaine de caractere
     */
    public String toString() {
        return "Nom : " + nom + ", Prenom : " + prenom
                + ", Date de Naissance : " + dateNaissance
                + ", Numero(s) de telephone : " + numTel + "\n";
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
     * @return Le personnel deserialisé
     */
    public static Personnel deSerialization(final String path) {
        ObjectInputStream ois = null;
        Personnel p = null;
        try {
            final FileInputStream fichierIn = new FileInputStream(path);
            ois = new ObjectInputStream(fichierIn);
            p = (Personnel) ois.readObject();
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
        return p;
    }
}
