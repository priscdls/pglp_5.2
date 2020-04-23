package priscille.pglp_5_2;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Test;

public class FactoryDaoJDBCTest {

	@Test
    public void testPersonnelDaoJDBC() {
		AbstractDao<Personnel> dao = FactoryDaoJDBC.getPersonnelDao();
        assertEquals(dao.find(1),null);
    }
	
	@Test
    public void testCompositePersonnelDaoJDBC() {
		AbstractDao<CompositePersonnel> dao = FactoryDaoJDBC.getCompositePersonnelDao();
        assertTrue(dao.getAll().isEmpty());
    }
	
	@Test
    public void testAfficheParGroupeDaoJDBC() {
		AbstractDao<AfficheParGroupe> dao = FactoryDaoJDBC.getAfficheParGroupeDao();
        assertTrue(dao.getAll().isEmpty());
    }

	@Test
    public void testPersonnelDaoJDBCExistant() {
        PersonnelDaoJDBC persDao = (PersonnelDaoJDBC) FactoryDaoJDBC.getPersonnelDao();
        ArrayList<String> numTel = new ArrayList<String>();
    	numTel.add("0123456789");
    	numTel.add("0987654321");
        Personnel p = new Personnel.Builder("Daoulas", "Priscille", LocalDate.of(1996, 05, 23), numTel).build();
        persDao.create(p);
        assertEquals(persDao.find(p.getId()).toString(),p.toString());
	}
	
}
