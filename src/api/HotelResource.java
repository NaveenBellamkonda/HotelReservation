package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {
    public static final HotelResource SINGLETON= new HotelResource();
    public CustomerService customerService=CustomerService.getSingleton();
    public ReservationService reservationService=ReservationService.getSingleton();
    public static HotelResource getSingleton(){
        return SINGLETON;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);
    }
    public void createACustomer(String email,String firstName,String lastName){
        customerService.getCustomer(email);
    }
    public IRoom getRoom(String roomNumber){
        return reservationService.getARoom(roomNumber);
    }
    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate,Date checkOutDate){
        return reservationService.reserveARoom(getCustomer(customerEmail),room,checkInDate,checkOutDate);
    }
    public Collection<Reservation> getCustomersReservations(String CustomerEmail){
        return reservationService.getCustomersReservation(getCustomer(CustomerEmail));
    }
    public Collection<IRoom> findARoom(Date checkInDate,Date checkOutDate){
        return reservationService.findRooms(checkInDate,checkOutDate);
    }
}
