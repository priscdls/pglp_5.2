package priscille.pglp_5_2;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Iterator;

import org.junit.Test;
/**
 * Tests unitaires de la classe CompositePersonnel.
 */
public class CompositePersonnelTest {
	/**
	 * Test du constructeur.
	 */
	@Test
	public void test() {
		CompositePersonnel c = new CompositePersonnel();
		Iterator<InterfacePersonnel> it = c.iterator();
		assertFalse(it.hasNext());
	}
	/**
	 * Test de l'ajout d'un membre du personnel.
	 */
	@Test
	public void testAjout() {
		CompositePersonnel c1 = new CompositePersonnel();
		Iterator<InterfacePersonnel> it = c1.iterator();
		Personnel p = new Personnel.Builder("Daoulas","Priscille",java.time.LocalDate.of(1996, 05, 23),null).build();
		c1.add(p);
		c1.print();
		assertTrue(it.hasNext());
	}
	/**
	 * Test de la suppression d'un membre du personnel.
	 */
	@Test
	public void testSuppression() {
		CompositePersonnel c1 = new CompositePersonnel();
		Iterator<InterfacePersonnel> it = c1.iterator();
		CompositePersonnel c2 = new CompositePersonnel();
		c1.add(c2);
		c1.remove(c2);
		assertFalse(it.hasNext());
	}
	/**
	 * Test de la sérialisation.
	 * @throws ClassNotFoundException 
	 */
	@Test
	public void testSerialization() throws ClassNotFoundException {
		CompositePersonnel cp = new CompositePersonnel();
		Personnel p = new Personnel.Builder("Daoulas","Priscille",java.time.LocalDate.of(1996, 05, 23),null).build();
		cp.add(p);
        cp.serialization("compoPersonnel1");
        CompositePersonnel cp2 = CompositePersonnel.deSerialization("compoPersonnel1");
        File f = new File("compoPersonnel1");
        f.delete();
        assertTrue(cp.toString().equalsIgnoreCase(cp2.toString()));
	}
	
	@Test
	public void testDeSerialization() throws ClassNotFoundException {
		@SuppressWarnings("unused")
		CompositePersonnel cp = CompositePersonnel.deSerialization("aaa");
	}
}
