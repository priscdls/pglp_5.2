package priscille.pglp_5_2;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

public class FactoryDaoTest {

	@Test
    public void testPersonnelDao() {
        Dao<Personnel> dao = FactoryDao.getPersonnelDao();
        assertTrue(dao.getAll().isEmpty());
    }
	
	@Test
    public void testCompositePersonnelDao() {
        Dao<CompositePersonnel> dao = FactoryDao.getCompositePersonnelDao();
        assertTrue(dao.getAll().isEmpty());
    }
	
	@Test
    public void testAfficheParGroupeDao() {
        Dao<AfficheParGroupe> dao = FactoryDao.getAfficheParGroupeDao();
        assertTrue(dao.getAll().isEmpty());
    }
	
	@Test
    public void testPersonnelDaoExistant() {
        PersonnelDao persDao = (PersonnelDao) FactoryDao.getPersonnelDao();
        ArrayList<String> numTel = new ArrayList<String>();
    	numTel.add("0123456789");
    	numTel.add("0987654321");
        Personnel p = new Personnel.Builder("Daoulas", "Priscille", LocalDate.of(1996, 05, 23), numTel).build();
        persDao.ajouter(p);
        persDao.serialization("persDao");
        PersonnelDao persDao2 = PersonnelDao.deSerialization("persDao");
        File f = new File("persDao");
        f.delete();
        assertEquals(persDao.getAll().toString(),persDao2.getAll().toString());
	}
	
	@Test
    public void testCompositePersonnelExistant() {
        CompositePersonnelDao CpersDao = (CompositePersonnelDao) FactoryDao.getCompositePersonnelDao();
        CompositePersonnel cp = new CompositePersonnel();
        ArrayList<String> numTel = new ArrayList<String>();
    	numTel.add("0123456789");
    	numTel.add("0987654321");
        Personnel p = new Personnel.Builder("Daoulas", "Priscille", LocalDate.of(1996, 05, 23), numTel).build();
        cp.add(p);
        CpersDao.ajouter(cp);
        CpersDao.serialization("CpersDao");
        CompositePersonnelDao CpersDao2 = CompositePersonnelDao.deSerialization("CpersDao");
        File f = new File("CpersDao");
        f.delete();
        assertEquals(CpersDao.getAll().toString(),CpersDao2.getAll().toString());
	}
	
	@Test
    public void testAfficheParGroupeExistant() {
		AfficheParGroupeDao apgDao = (AfficheParGroupeDao) FactoryDao.getAfficheParGroupeDao();
		AfficheParGroupe apg = new AfficheParGroupe();
        CompositePersonnel cp = new CompositePersonnel();
        ArrayList<String> numTel = new ArrayList<String>();
    	numTel.add("0123456789");
    	numTel.add("0987654321");
        Personnel p = new Personnel.Builder("Daoulas", "Priscille", LocalDate.of(1996, 05, 23), numTel).build();
        cp.add(p);
        apg.add(cp);
        apg.parcoursLargeur(cp);
        apgDao.ajouter(apg);
        apgDao.serialization("apgDao");
        AfficheParGroupeDao apgDao2 = AfficheParGroupeDao.deSerialization("apgDao");
        File f = new File("apgDao");
        f.delete();
        assertEquals(apgDao.getAll().toString(),apgDao2.getAll().toString());
	}
}
