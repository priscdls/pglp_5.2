package priscille.pglp_5_2;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;
/**
 * Tests unitaires de la classe Personnel.
 *
 */
public class PersonnelTest {
	/**
	 * Test du constructeur.
	 */
	@Test
	public void test() {
		ArrayList<String> numTel = new ArrayList<String>();
    	numTel.add("0123456789");
    	numTel.add("0987654321");
        Personnel p = new Personnel.Builder("Daoulas", "Priscille", LocalDate.of(1996, 05, 23), numTel).build();
        p.print();
	}
	
	@Test
	public void testSerialization() {
		ArrayList<String> numTel = new ArrayList<String>();
    	numTel.add("0123456789");
    	numTel.add("0987654321");
        Personnel p = new Personnel.Builder("Daoulas", "Priscille", LocalDate.of(1996, 05, 23), numTel).build();
        p.serialization("personnel1");
        Personnel p2 = Personnel.deSerialization("personnel1");
        File f = new File("personnel1");
        f.delete();
        assertTrue(p.getNom().equalsIgnoreCase(p2.getNom()) && p.getPrenom().equals(p2.getPrenom()) && p.getDateNaissance().isEqual(p2.getDateNaissance())  && p.getNumTel().containsAll(p2.getNumTel()));
	}
}
