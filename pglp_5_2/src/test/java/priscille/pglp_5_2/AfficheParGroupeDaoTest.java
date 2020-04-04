package priscille.pglp_5_2;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class AfficheParGroupeDaoTest {

	@Test
	public void testAjouter() {
		AfficheParGroupeDao apgDao = new AfficheParGroupeDao();
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
        apgDao.ajouter(apg);
        
        assertEquals(apgDao.get(apg.getId()), apg);
	}
	
	@Test
	public void testRetirer() {
		AfficheParGroupeDao apgDao = new AfficheParGroupeDao();
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
        apgDao.ajouter(apg);
        apgDao.retirer(apg);
        
        assertEquals(apgDao.get(apg.getId()), null);
	}
	
	@Test
	public void testGetAll() {
		AfficheParGroupeDao apgDao = new AfficheParGroupeDao();
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
        apgDao.ajouter(apg);
				
		AfficheParGroupe apg2 = new AfficheParGroupe();
		CompositePersonnel c4 = new CompositePersonnel();
		c4.add(c2);
		c4.add(p1);
		c4.add(p2);
		apg2.parcoursLargeur(c4);
        apgDao.ajouter(apg2);
        
		ArrayList<AfficheParGroupe> expected = new ArrayList<AfficheParGroupe>();
		expected.add(apg);
		expected.add(apg2);
		
		assertEquals(apgDao.getAll(), expected);
	}
	
	@Test
	public void testUpdate() {
		AfficheParGroupeDao apgDao = new AfficheParGroupeDao();
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
        apgDao.ajouter(apg);
        
        AfficheParGroupe apg2 = new AfficheParGroupe();
        CompositePersonnel c4 = new CompositePersonnel();
		c4.add(c2);
		c4.add(p1);
		c4.add(p2);
		apg2.parcoursLargeur(c4);
		
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("file", apg2.getList());
        apgDao.update(apg, param);
        
        assertEquals(apgDao.getAll().get(0).getList().toString(), apg2.getList().toString());
	}
	
	@Test
	public void testSerialization() {
		AfficheParGroupeDao apgDao = new AfficheParGroupeDao();
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
        apgDao.ajouter(apg);
        
        apgDao.serialization("apg1");
        AfficheParGroupeDao apg2 = AfficheParGroupeDao.deSerialization("apg1");
        File f = new File("apg1");
        f.delete();
        
        assertEquals(apgDao.getAll().toString(), apg2.getAll().toString());
	}
}
