package priscille.pglp_5_2;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;
/**
 * Tests unitaires de la classe AffichageParGroupe.
 *
 */
public class AfficheParGroupeTest {
	/**
	 * Test
	 */
	@Test
	public void test() {
		CompositePersonnel c1 = new CompositePersonnel();
    	CompositePersonnel c2 = new CompositePersonnel();
    	CompositePersonnel c3 = new CompositePersonnel();
    	ArrayList<String> numTel = new ArrayList<String>();
    	numTel.add("0123456789");
    	numTel.add("0987654321");
		Personnel p1 = new Personnel.Builder("Daoulas", "Priscille",
				java.time.LocalDate.of(1996, 05, 23),
				numTel).build();
		Personnel p2 = new Personnel.Builder("Jean", "Jacques",
				java.time.LocalDate.of(2005, 04, 16),
				null).build();
		AfficheParGroupe apg = new AfficheParGroupe();
        c1.add(c2);
        c1.add(p1);
        c2.add(c3);
        c2.add(p2);
        apg.parcoursLargeur(c1);
        apg.print();
        ArrayList<InterfacePersonnel> listParcoursExacte = new ArrayList<InterfacePersonnel>();
        listParcoursExacte.add(c1);
        listParcoursExacte.add(c2);
        listParcoursExacte.add(p1);
        listParcoursExacte.add(c3);
        listParcoursExacte.add(p2);
        Iterator<InterfacePersonnel> it = apg.iterator();
        ArrayList<InterfacePersonnel> listParcours = new ArrayList<InterfacePersonnel>();
        while (it.hasNext()) {
        	listParcours.add(it.next());
        }
        assertTrue(listParcoursExacte.toString().equalsIgnoreCase(listParcours.toString()));
	}
	/**
	 * Test de la serialisation.
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSerialization() throws ClassNotFoundException {
		CompositePersonnel c1 = new CompositePersonnel();
    	CompositePersonnel c2 = new CompositePersonnel();
    	CompositePersonnel c3 = new CompositePersonnel();
    	ArrayList<String> numTel = new ArrayList<String>();
    	numTel.add("0123456789");
    	numTel.add("0987654321");
		Personnel p1 = new Personnel.Builder("Daoulas", "Priscille",
				java.time.LocalDate.of(1996, 05, 23),
				numTel).build();
		Personnel p2 = new Personnel.Builder("Jean", "Jacques",
				java.time.LocalDate.of(2005, 04, 16),
				null).build();
		AfficheParGroupe apg = new AfficheParGroupe();
        c1.add(c2);
        c1.add(p1);
        c2.add(c3);
        c2.add(p2);
        apg.parcoursLargeur(c1);
        apg.serialization("AffGroupe1");
        AfficheParGroupe apg2 = AfficheParGroupe.deSerialization("AffGroupe1");
        File f = new File("AffGroupe1");
        f.delete();
        assertTrue(apg.toString().equals(apg2.toString()));
	}
	
	/**
	 * Test de la deserialisation.
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testDeSerialization() throws ClassNotFoundException {
		@SuppressWarnings("unused")
		AfficheParGroupe apg = AfficheParGroupe.deSerialization("aaa");
	}
}
