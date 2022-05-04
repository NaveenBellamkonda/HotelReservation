package model;

import java.util.Date;

public class Reservation {
    public Customer customer;
    public IRoom room;
    public Date checkInDate;
    public Date checkOutDate;
     public Reservation(Customer customer, IRoom room, Date CheckInDate, Date CheckOutDate){
         this.customer=customer;
         this.room=room;
         this.checkInDate=checkInDate;
         this.checkOutDate=checkOutDate;
     }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer +
                ", room=" + room +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                '}';
    }
}

