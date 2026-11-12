package control;

import dao.CustomerDAO6;
import model.Customer6;

public class CustomerControl6 {
    private CustomerDAO6 customerDAO;

    public CustomerControl6() {
        this.customerDAO = new CustomerDAO6();
    }

    public boolean validateInput(Customer6 cust) {
        if (cust == null) return false;
        if (cust.getFullName() == null || cust.getFullName().trim().isEmpty()) return false;
        if (cust.getUsername() == null || cust.getUsername().trim().isEmpty()) return false;
        if (cust.getPassword() == null || cust.getPassword().trim().isEmpty()) return false;
        if (cust.getEmail() == null || !cust.getEmail().contains("@")) return false;
        return true;
    }

    public boolean registerCustomer(Customer6 cust) {
        if (!validateInput(cust)) {
            return false;
        }
        if (customerDAO.checkDuplicate(cust.getUsername(), cust.getEmail())) {
            return false;
        }
        return customerDAO.saveCustomer(cust);
    }
}
