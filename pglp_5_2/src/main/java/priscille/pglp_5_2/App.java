package priscille.pglp_5_2;

import java.util.ArrayList;

/**
 * Classe App.
 */
public final class App {
    /**
     * Constructeur.
     */
    private App() {
    }
    /**
     * Début du programme.
     * @param args Les arguments donnés au démarrage de l'application
     */
    public static void main(final String[] args) {
        final int annee = 1996;
        final int mois = 05;
        final int jour = 23;
        final int annee2 = 2005;
        final int mois2 = 04;
        final int jour2 = 16;
        CompositePersonnel c1 = new CompositePersonnel();
        CompositePersonnel c2 = new CompositePersonnel();
        CompositePersonnel c3 = new CompositePersonnel();
        ArrayList<String> numTel = new ArrayList<String>();
        numTel.add("0123456789");
        numTel.add("0987654321");
        Personnel p1 = new Personnel.Builder("Daoulas", "Priscille",
                java.time.LocalDate.of(annee, mois, jour),
                numTel).build();
        Personnel p2 = new Personnel.Builder("Jean", "Jacques",
                java.time.LocalDate.of(annee2, mois2, jour2),
                null).build();
        AfficheParGroupe apg = new AfficheParGroupe();
        c1.add(c2);
        c1.add(p1);
        c2.add(c3);
        c2.add(p2);
        System.out.println("Parcours en profondeur : ");
        c1.print();
        System.out.println("\nParcours en largeur : ");
        apg.parcoursLargeur(c1);
        apg.print();
    }
}
