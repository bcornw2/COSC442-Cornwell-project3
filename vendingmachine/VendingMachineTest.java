package vendingmachine;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

	@Before
	public void setUp() throws Exception {
		VendingMachine vend1 = new VendingMachine();
	}
	
	@Test
	public void testAddItem() {
		//void addItem(VendingMachineItem item, String code)
		VendingMachine vend1 = new VendingMachine();
		VendingMachineItem test = new VendingMachineItem("Cookie", 0.50); 
		vend1.addItem(test, "A");
		assertEquals(test, vend1.getItem("A"));
	}
	
	@Test
	public void testRemoveItem() {
		VendingMachine vend1 = new VendingMachine();
		VendingMachineItem test = new VendingMachineItem("Crackers", 1.00);
		vend1.addItem(test, "A");
		vend1.removeItem("A");
		assertEquals(null, vend1.getItem("A"));	
	}
	
	@Test
	public void testInsertMoney() {
		VendingMachine vend1 = new VendingMachine();
		vend1.insertMoney(2.00);
		assertEquals(2.00, vend1.balance, 0.0001);
	}
	
	@Test
	public void testGetBalance() {
		VendingMachine vend1 = new VendingMachine();
		assertEquals(0, vend1.getBalance(), 0.0001);
		
		vend1.insertMoney(1.0);
		assertEquals(1, vend1.getBalance(), 0.0001);
	}
	
	@Test
	public void testMakePurchase() {
		VendingMachine vend1 = new VendingMachine();
		//no money in yet, will be false
		assertEquals(false, vend1.makePurchase("A"));
		
		//not enough money in yet, will fail
		vend1.insertMoney(1.00);
		VendingMachineItem test = new VendingMachineItem("Cookie", 1.50); 
		vend1.addItem(test, "A");
		assertEquals(false, vend1.makePurchase("A"));
		
		//has enough money, will be true
		vend1.insertMoney(1.00); 
		//still buying 1.50 cookie, but now with 2 dollars
		assertEquals(true, vend1.makePurchase("A"));
		
		//balance has been spent, will fail again
		assertEquals(false, vend1.makePurchase("A"));
		
	}
	
	@Test
	public void returnChange() {
		VendingMachine vend1 = new VendingMachine();
		//no cash in yet
		assertEquals(0, vend1.returnChange(), 0.0001);
		
		//2 dollars added, 1.50 worth of food ordered
		VendingMachineItem test = new VendingMachineItem("Cookie", 1.50);
		vend1.addItem(test, "A");
		vend1.insertMoney(2.00);
		vend1.makePurchase("A");
		assertEquals(0.50, vend1.returnChange(), 0.0001);
		
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

}
