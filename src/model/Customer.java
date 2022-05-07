package model;

import java.util.regex.Pattern;

public class Customer {
    public String firstName;
    public String lastName;
    public String email;

    String emailRegx="^(.+)@(.+).(.+)$";
    Pattern pattern=Pattern.compile(emailRegx);

    public Customer(String firstName,String lastName,String email){
        if (!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("Please enter a valid emailID");
        }
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
    }

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail(Customer customer) {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {



        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
