package priscille.pglp_5_2;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class PersonnelDaoTest {

	@Test
	public void testAjouter() {
		PersonnelDao personnels = new PersonnelDao();
		ArrayList<String> numTel = new ArrayList<String>();
    	numTel.add("0123456789");
    	numTel.add("0987654321");
        Personnel p = new Personnel.Builder("Daoulas", "Priscille", LocalDate.of(1996, 05, 23), numTel).build();
        personnels.ajouter(p);
        assertEquals(personnels.get(p.getId()), p);
	}
	
	@Test
	public void testRetirer() {
		PersonnelDao personnels = new PersonnelDao();
		ArrayList<String> numTel = new ArrayList<String>();
    	numTel.add("0123456789");
    	numTel.add("0987654321");
        Personnel p = new Personnel.Builder("Daoulas", "Priscille", LocalDate.of(1996, 05, 23), numTel).build();
        personnels.ajouter(p);
        personnels.retirer(p);
        assertEquals(personnels.get(p.getId()), null);
	}
	
	@Test
	public void testGetAll() {
		PersonnelDao personnels = new PersonnelDao();
		ArrayList<String> numTel = new ArrayList<String>();
    	numTel.add("0123456789");
    	numTel.add("0987654321");
		Personnel p1 = new Personnel.Builder("Daoulas", "Priscille", java.time.LocalDate.of(1996, 05, 23), numTel).build();
		Personnel p2 = new Personnel.Builder("Jean", "Jacques", java.time.LocalDate.of(2005, 04, 16), null).build();
		personnels.ajouter(p1);
		personnels.ajouter(p2);
		ArrayList<Personnel> persList = personnels.getAll();
		
		ArrayList<Personnel> expected = new ArrayList<Personnel>();
		expected.add(p1);
		expected.add(p2);
		
		assertEquals(persList, expected);
	}
	
	@Test
	public void testUpdate() {
		PersonnelDao personnels = new PersonnelDao();
		ArrayList<String> numTel = new ArrayList<String>();
    	numTel.add("0123456789");
    	numTel.add("0987654321");
        Personnel p = new Personnel.Builder("Daoulas", "Priscille", LocalDate.of(1996, 05, 23), numTel).build();
        personnels.ajouter(p);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("nom", "Dls");
        personnels.update(p, param);
        
        Personnel expected = new Personnel.Builder("Dls", "Priscille", LocalDate.of(1996, 05, 23), numTel).build();
        
        assertTrue(personnels.getAll().get(0).getNom() == expected.getNom());
	}
	
	@Test
	public void testSerialization() {
		PersonnelDao personnels = new PersonnelDao();
		ArrayList<String> numTel = new ArrayList<String>();
    	numTel.add("0123456789");
    	numTel.add("0987654321");
        Personnel p = new Personnel.Builder("Daoulas", "Priscille", LocalDate.of(1996, 05, 23), numTel).build();
        personnels.ajouter(p);
        personnels.serialization("personnel1");
        PersonnelDao p2 = PersonnelDao.deSerialization("personnel1");
        File f = new File("personnel1");
        f.delete();
        assertEquals(personnels.getAll().get(0).toString(),p2.getAll().get(0).toString());
	}
}
