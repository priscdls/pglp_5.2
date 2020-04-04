package priscille.pglp_5_2;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class CompositePersonnelDaoTest {

	@Test
	public void testAjouter() {
		CompositePersonnelDao cpersonnels = new CompositePersonnelDao();
		CompositePersonnel cp = new CompositePersonnel();
		Personnel p = new Personnel.Builder("Daoulas","Priscille",java.time.LocalDate.of(1996, 05, 23),null).build();
		cp.add(p);
		cpersonnels.ajouter(cp);
        assertEquals(cpersonnels.get(cp.getId()), cp);
	}
	
	@Test
	public void testRetirer() {
		CompositePersonnelDao cpersonnels = new CompositePersonnelDao();
		CompositePersonnel cp = new CompositePersonnel();
		Personnel p = new Personnel.Builder("Daoulas","Priscille",java.time.LocalDate.of(1996, 05, 23),null).build();
		cp.add(p);
		cpersonnels.ajouter(cp);
		cpersonnels.retirer(cp);
        assertEquals(cpersonnels.get(cp.getId()), null);
	}
	
	@Test
	public void testGetAll() {
		CompositePersonnelDao cpersonnels = new CompositePersonnelDao();
		CompositePersonnel cp = new CompositePersonnel();
		CompositePersonnel cp2 = new CompositePersonnel();
		ArrayList<String> numTel = new ArrayList<String>();
    	numTel.add("0123456789");
    	numTel.add("0987654321");
		Personnel p = new Personnel.Builder("Daoulas","Priscille",java.time.LocalDate.of(1996, 05, 23),numTel).build();
		Personnel p2 = new Personnel.Builder("Jean", "Jacques", java.time.LocalDate.of(2005, 04, 16), null).build();
		cp.add(p);
		cp2.add(p2);
		cpersonnels.ajouter(cp);
		cpersonnels.ajouter(cp2);
		
		ArrayList<CompositePersonnel> cpersList = cpersonnels.getAll();
		
		ArrayList<CompositePersonnel> expected = new ArrayList<CompositePersonnel>();
		expected.add(cp);
		expected.add(cp2);
		
		assertEquals(cpersList, expected);
	}
	
	@Test
	public void testUpdate() {
		CompositePersonnelDao cpersonnels = new CompositePersonnelDao();
		CompositePersonnel cp = new CompositePersonnel();
		ArrayList<String> numTel = new ArrayList<String>();
    	numTel.add("0123456789");
    	numTel.add("0987654321");
        Personnel p = new Personnel.Builder("Daoulas", "Priscille", LocalDate.of(1996, 05, 23), numTel).build();
		cp.add(p);
        cpersonnels.ajouter(cp);
        
		CompositePersonnel cp2 = new CompositePersonnel();
		Personnel p2 = new Personnel.Builder("Daoulas", "Priscille", LocalDate.of(1996, 05, 23), null).build();
		cp2.add(p2);
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("list", cp2.getList());
        cpersonnels.update(cp, param);
        
        assertTrue(cpersonnels.getAll().get(0).getList().equals(cp2.getList()));
	}
	
	@Test
	public void testSerialization() {
		CompositePersonnelDao cpersonnels = new CompositePersonnelDao();
		CompositePersonnel cp = new CompositePersonnel();
		ArrayList<String> numTel = new ArrayList<String>();
    	numTel.add("0123456789");
    	numTel.add("0987654321");
        Personnel p = new Personnel.Builder("Daoulas", "Priscille", LocalDate.of(1996, 05, 23), numTel).build();
		cp.add(p);
        cpersonnels.ajouter(cp);
        
        cpersonnels.serialization("cpersonnel1");
        CompositePersonnelDao cp2 = CompositePersonnelDao.deSerialization("cpersonnel1");
        File f = new File("cpersonnel1");
        f.delete();
        
        assertEquals(cpersonnels.getAll().toString(), cp2.getAll().toString());
	}

}
