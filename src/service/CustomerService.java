package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService {

    private Map<String , Customer> customers=new HashMap<String, Customer>();


    public void addCustomer(String email, String firstName, String lastName){
        customers.put(email, new Customer(firstName,lastName,email));
    }

    public Customer getCustomer(String customerEmail) {
        return customers.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values();
    }

}
