package unittest;

import dao.DAO;
import dao.CustomerDAO6;
import model.Customer6;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestCustomerDAO6 {
    private CustomerDAO6 customerDAO;

    @Before
    public void setUp() throws Exception {
        customerDAO = new CustomerDAO6();
        if (DAO.con != null) {
            DAO.con.setAutoCommit(false);
        }
    }

    @After
    public void tearDown() throws Exception {
        if (DAO.con != null) {
            DAO.con.rollback();
            DAO.con.setAutoCommit(true);
        }
    }

    @Test
    public void testCheckDuplicate_NotFound() {
        boolean dup = customerDAO.checkDuplicate("nonexistent_user_999", "nonexistent_999@gmail.com");
        Assert.assertFalse("User should not exist", dup);
    }

    @Test
    public void testSaveCustomer_Success() {
        Customer6 cust = new Customer6();
        cust.setUsername("testuser_b23dcat186");
        cust.setPassword("password123");
        cust.setFullName("Nguyễn Văn Test");
        cust.setEmail("test_b23dcat186@gmail.com");
        cust.setPhone("0988776655");
        cust.setCustomerCode("KH_TEST");
        cust.setCustomerType("THUONG");

        boolean res = customerDAO.saveCustomer(cust);
        Assert.assertTrue("Save customer should succeed", res);
        Assert.assertTrue("Generated account ID should be > 0", cust.getId() > 0);
    }
}
