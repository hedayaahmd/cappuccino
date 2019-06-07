/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intity_Controller;

import Intity.Customer;
import java.sql.ResultSet;
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
public class Customer_ControllerIT {
    
    public Customer_ControllerIT() {
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
     * Test of get_Customer_id method, of class Customer_Controller.
     */
    @Test
    public void testGet_Customer_id() throws Exception {
        System.out.println("get_Customer_id");
        String Customer_name = "";
        Customer_Controller instance = new Customer_Controller();
        String expResult = "";
        String result = instance.get_Customer_id(Customer_name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCustomerName method, of class Customer_Controller.
     */
    @Test
    public void testGetCustomerName() throws Exception {
        System.out.println("getCustomerName");
        String customer_id = "";
        Customer_Controller instance = new Customer_Controller();
        String expResult = "";
        String result = instance.getCustomerName(customer_id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get_AccountofCustomer method, of class Customer_Controller.
     */
    @Test
    public void testGet_AccountofCustomer() throws Exception {
        System.out.println("get_AccountofCustomer");
        Customer customer = null;
        Customer_Controller instance = new Customer_Controller();
        instance.get_AccountofCustomer(customer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add_Customer_data method, of class Customer_Controller.
     */
    @Test
    public void testAdd_Customer_data() {
        System.out.println("add_Customer_data");
        Customer customer = null;
        Customer_Controller instance = new Customer_Controller();
        boolean expResult = false;
        boolean result = instance.add_Customer_data(customer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete_Customer_data method, of class Customer_Controller.
     */
    @Test
    public void testDelete_Customer_data() {
        System.out.println("delete_Customer_data");
        int customer_number = 0;
        Customer_Controller instance = new Customer_Controller();
        boolean expResult = false;
        boolean result = instance.delete_Customer_data(customer_number);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update_Customer_data method, of class Customer_Controller.
     */
    @Test
    public void testUpdate_Customer_data() {
        System.out.println("update_Customer_data");
        Customer customer = null;
        Customer_Controller instance = new Customer_Controller();
        boolean expResult = false;
        boolean result = instance.update_Customer_data(customer);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAccountsList method, of class Customer_Controller.
     */
    @Test
    public void testGetAccountsList() {
        System.out.println("getAccountsList");
        Customer_Controller instance = new Customer_Controller();
        ResultSet expResult = null;
        ResultSet result = instance.getAccountsList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNewCustomer_Number method, of class Customer_Controller.
     */
    @Test
    public void testGetNewCustomer_Number() {
        System.out.println("getNewCustomer_Number");
        Customer_Controller instance = new Customer_Controller();
        int expResult = 0;
        int result = instance.getNewCustomer_Number();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectCustomers method, of class Customer_Controller.
     */
    @Test
    public void testSelectCustomers() {
        System.out.println("selectCustomers");
        Customer_Controller instance = new Customer_Controller();
        ResultSet expResult = null;
        ResultSet result = instance.selectCustomers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
