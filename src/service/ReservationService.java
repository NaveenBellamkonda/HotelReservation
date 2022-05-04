package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;

public class ReservationService {

    private Map<String ,IRoom> rooms=new HashMap<String, IRoom>();
    private Map<String,Collection<Reservation>> reservations=new HashMap<String, Collection<Reservation>>();

    public void addRoom(IRoom room){
        rooms.put(room.getRoomNumber(),room);
    }
    public IRoom getARoom(String RoomID){
        return rooms.get(RoomID);
    }

    public Reservation reserveARoom(Customer customer,IRoom room,Date CheckInDate,Date CheckOutDate){
        Reservation reservation=new Reservation(customer,room,CheckInDate,CheckOutDate);
        Collection<Reservation> customerReservations=getCustomersReservation(customer);
        if (customerReservations == null){
            customerReservations=new LinkedList<Reservation>();
        }
        customerReservations.add(reservation);
        reservations.put(customer.getEmail(), customerReservations);
        return reservation;

    }
    public Collection<IRoom> findRooms(Date CheckInDate, Date CheckOutDates){
        return findAvailableRooms(CheckInDate,CheckOutDates);
    }

    public Collection<Reservation> getCustomersReservation(Customer customer){

    }
    public void printAllReservation(){
        private Collection<Reservation> allReservations=new LinkedList<Reservation>();
        for(Collection<Reservation> reservations:reservations.values()){
            allReservations.addAll(reservations);
        }
        for(Reservation reservation:allReservations){
            System.out.println(reservation);
        }

    }
}
