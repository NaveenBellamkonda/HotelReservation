package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;
import java.util.stream.Collectors;

public class ReservationService {
    private Integer DefaultplusDays=7;

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
        if (customerReservations ==null){
            customerReservations=new LinkedList<Reservation>();
        }
        customerReservations.add(reservation);
        reservations.put(customer.getEmail(customer), customerReservations);
        return reservation;

    }
    public Collection<IRoom> findRooms(Date CheckInDate, Date CheckOutDate){
        return findAvailableRooms(CheckInDate,CheckOutDate);
    }
    public Collection<IRoom> findAvailableRooms(Date CheckInDate,Date CheckOutDate){
        Collection<Reservation> allReservations=getAllReservations();
        Collection<Reservation> notAvailableRooms=new ArrayList<Reservation>();
        for(Reservation reservation:allReservations){
            if(reservationOverlaps(reservation, CheckInDate,CheckOutDate)){
                notAvailableRooms.add(reservation);
            }
        }
        return rooms.values().stream().filter(room -> notAvailableRooms.stream().noneMatch(notAvailableRoom ->notAvailableRoom.equals(room))).collect(Collectors.toList());
    }
    public Boolean reservationOverlaps(Reservation reservation, Date CheckInDate, Date CheckOutDate){
        return CheckInDate.before(reservation.getCheckOutDate()) && CheckOutDate.after(reservation.getCheckOutDate());
    }
    public Collection<Reservation> getCustomersReservation(Customer customer){
        return reservations.get(customer.getEmail(customer));
    }
    public void printAllReservation(){
        Collection<Reservation> allReservations=getAllReservations();
        for(Collection<Reservation> reservations:reservations.values()){
            allReservations.addAll(reservations);
        }
        for(Reservation reservation:allReservations){
            System.out.println(reservation);
        }

    }
    public Collection<Reservation> getAllReservations(){
        Collection<Reservation> allReservations=new LinkedList<Reservation>();
        for (Collection<Reservation> reservations:reservations.values()){
            allReservations.addAll(reservations);
        }
        return allReservations;
    }
}
