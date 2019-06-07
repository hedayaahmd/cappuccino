/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intity;

import java.sql.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author USER
 */
public class CustomerIT {
    
    public CustomerIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAccount_num method, of class Customer.
     */
    @Test
    public void testGetAccount_num() {
        System.out.println("getAccount_num");
        Customer instance = null;
        int expResult = 0;
        int result = instance.getAccount_num();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAccount_num method, of class Customer.
     */
    @Test
    public void testSetAccount_num() {
        System.out.println("setAccount_num");
        int account_num = 0;
        Customer instance = null;
        instance.setAccount_num(account_num);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Customer.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Customer instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Customer.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Customer instance = null;
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPhone method, of class Customer.
     */
    @Test
    public void testGetPhone() {
        System.out.println("getPhone");
        Customer instance = null;
        String expResult = "";
        String result = instance.getPhone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPhone method, of class Customer.
     */
    @Test
    public void testSetPhone() {
        System.out.println("setPhone");
        String phone = "";
        Customer instance = null;
        instance.setPhone(phone);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAddress method, of class Customer.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        Customer instance = null;
        String expResult = "";
        String result = instance.getAddress();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAddress method, of class Customer.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String address = "";
        Customer instance = null;
        instance.setAddress(address);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Customer.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Customer instance = null;
        String expResult = "";
        String result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class Customer.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        String id = "";
        Customer instance = null;
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRegistry_Date method, of class Customer.
     */
    @Test
    public void testGetRegistry_Date() {
        System.out.println("getRegistry_Date");
        Customer instance = null;
        Date expResult = null;
        Date result = instance.getRegistry_Date();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRegistry_Date method, of class Customer.
     */
    @Test
    public void testSetRegistry_Date() {
        System.out.println("setRegistry_Date");
        Date registry_Date = null;
        Customer instance = null;
        instance.setRegistry_Date(registry_Date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSymbol method, of class Customer.
     */
    @Test
    public void testGetSymbol() {
        System.out.println("getSymbol");
        String expResult = "";
        String result = Customer.getSymbol();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSymbol method, of class Customer.
     */
    @Test
    public void testSetSymbol() {
        System.out.println("setSymbol");
        String aSymbol = "";
        Customer.setSymbol(aSymbol);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
