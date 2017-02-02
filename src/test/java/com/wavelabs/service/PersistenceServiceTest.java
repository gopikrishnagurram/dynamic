package com.wavelabs.service;

import org.hibernate.internal.util.xml.XmlDocument;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.wavelabs.metadata.ClassAttributes;
import com.wavelabs.metadata.HbmFileMetaData;
import com.wavelabs.metadata.XmlDocumentBuilder;
import com.wavelabs.model.Car;
import com.wavelabs.service.PersistanceService;
import com.wavelabs.tableoperations.CRUDTest;
import com.wavelabs.utility.Helper;

/**
 * Checks all {@link PersistanceService} class methods using unit test cases
 * 
 * @author gopikrishnag
 *	
 */
public class PersistenceServiceTest {

	private HbmFileMetaData carHbm = null;
	private CRUDTest crud = null;

	@BeforeTest
	public void intillization() {
		XmlDocumentBuilder xdb = new XmlDocumentBuilder();
		XmlDocument xd = xdb.getXmlDocumentObject("src/main/resources/com/wavelabs/model/Car.hbm.xml");
		carHbm = new HbmFileMetaData(xd, Helper.getSessionFactory());
		crud = new CRUDTest(Helper.getSessionFactory(), Helper.getConfiguration(), Helper.getSession());
	}

	@Test(priority = 1, description = "To test car records inserted or not in table")
	public void testCreateCar() {
		PersistanceService.createCar(1, "MARUTHI", 1.8, 20);
		PersistanceService.createCar(2, "BENZ", 40, 25);
		PersistanceService.createCar(3, "SANTRO", 4.5, 24);
		PersistanceService.createCar(4, "VOLKS WAGON", 3, 26);
		crud.setSession(Helper.getSession());
		Assert.assertEquals(crud.isRecordInserted(Car.class, 1), true);
		Assert.assertEquals(crud.isRecordInserted(Car.class, 2), true);
		Assert.assertEquals(crud.isRecordInserted(Car.class, 3), true);
		Helper.getSession().close();
	}
	@Test(priority = 2, description = "checks updation of cost column of Car")
	public void testUpdateCar() {
		PersistanceService.updateCarCost(1, 2.5);
		crud.setSession(Helper.getSession());
		Assert.assertEquals(crud.isColumnUpdated(Car.class, "cost", 2.5, 1), true);
		Helper.getSession().close();
	}
	@Test(priority = 3, description = "checks dynamic-insert attribute value, If attribute is not used gives null")
	public void testDyanamicInsert() {
		Assert.assertEquals(carHbm.getClassAttribute(ClassAttributes.dynamicinsert), "true");

	}
	@Test(priority = 4, description = "checks dynamic-update attribute value, If attribute is not used gives null")
	public void testDynamicUpdate() {
		Assert.assertEquals(carHbm.getClassAttribute(ClassAttributes.dynamicupdate), "true");
	}
	@Test(priority = 5, description = "To checks records is deleted from table or not")
	public void testDelete() {
		PersistanceService.deleteCar(3);
		crud.setSession(Helper.getSession());
		Assert.assertEquals(crud.isRecordDeleted(Car.class, 3), true);
	}
	@AfterTest
	public void closeResources() {
		try {
			Helper.getSessionFactory().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
