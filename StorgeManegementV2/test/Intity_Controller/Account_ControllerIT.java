/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Intity_Controller;

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
public class Account_ControllerIT {
    
    public Account_ControllerIT() {
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
     * Test of updateAccount method, of class Account_Controller.
     */
    @Test
    public void testUpdateAccount() {
        System.out.println("updateAccount");
        int account_number = 0;
        double credit_amount = 0.0;
        double debit_amount = 0.0;
        String bill_number = "";
        Account_Controller instance = new Account_Controller();
        boolean expResult = false;
        boolean result = instance.updateAccount(account_number, credit_amount, debit_amount, bill_number);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBalance method, of class Account_Controller.
     */
    @Test
    public void testGetBalance() {
        System.out.println("getBalance");
        String customer_number = "";
        Account_Controller instance = new Account_Controller();
        double expResult = 0.0;
        double result = instance.getBalance(customer_number);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDebit method, of class Account_Controller.
     */
    @Test
    public void testGetDebit() {
        System.out.println("getDebit");
        String customer_number = "";
        Account_Controller instance = new Account_Controller();
        double expResult = 0.0;
        double result = instance.getDebit(customer_number);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
