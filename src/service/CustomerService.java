package service;

import model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService {
    public static final CustomerService SINGLETON=new CustomerService();
    private Map<String , Customer> customers=new HashMap<String, Customer>();

    public static CustomerService getSingleton() {
        return SINGLETON;
    }


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
