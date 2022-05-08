import api.AdminResource;
import api.HotelResource;
import model.IRoom;
import model.Reservation;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;

public class MainMenu {
    public static final String Default_Date_Format="MM/dd/year";
    public static HotelResource hotelResource=HotelResource.getSingleton();
    public static AdminResource adminResource=AdminResource.getSingleton();
    public static void mainMenu() {


        String line = "";
        printmainmenu ();
        Scanner scanner = new Scanner(System.in);

        try {
            do {
                line = scanner.nextLine();
                if (line.length() == 1) {
                    switch (line.charAt(0)) {
                        case '1':
                            findAndReserveARoom();
                            break;
                        case '2':
                            seeMyReservations();
                            break;
                        case '3':
                            createAnAccount();
                            break;
                        case '4':
                            AdminMenu.adminMenu();
                            break;
                        case '5':
                            System.out.println("Exit");
                            break;
                        default:
                            System.out.println("InvalidAction\n");
                    }
                }else {
                    System.out.println("Please enter a valid Number");
                }
            }while (line.charAt(0) != 5 || line.length() != 1) ;
        }catch(StringIndexOutOfBoundsException ex) {
                    System.out.println("Empty Input Received");

        }

    }
    public static void findAndReserveARoom(){
        final Scanner scanner= new Scanner(System.in);
        System.out.println("Enter a Check-in-Date in dd/mm/year format");
        Date checkIn=getDate(scanner);
        //final Scanner scanner1=new Scanner(System.in);
        System.out.println("Enter a Check-out-Date in dd/mm/year format");
        Date checkOut=getDate(scanner);
        if(checkOut!=null && checkIn!=null){
            Collection<IRoom> availableRooms=hotelResource.findARoom(checkIn,checkOut);
            if(availableRooms.isEmpty()){
                System.out.println("No Rooms Found");
            }else {
                printAvailableRooms(availableRooms);
                reserveARoom(scanner,checkIn,checkOut,availableRooms);
            }
        }
    }
    public static void printAvailableRooms(Collection<IRoom> availableRooms){
        for(IRoom room:availableRooms){
            System.out.println(room);
        }
    }
    public static void reserveARoom(Scanner scanner,Date checkIn,Date checkOut, Collection<IRoom> availableRooms){
        System.out.println("Would you like to book a room? y/n");
        String bookARoom=scanner.nextLine();
        if("y".equals(bookARoom)){
            System.out.println("Do you have an account?");
            String acc=scanner.nextLine();
            if("y".equals(acc)){
                System.out.println("Enter your emailID");
                String email=scanner.nextLine();
                if(hotelResource.getCustomer(email)==null){
                    System.out.println("Customer not fount");
                }else{
                    System.out.println("Select a Room Number");
                    String roomN=scanner.nextLine();
                    if(availableRooms.stream().anyMatch(room -> room.getRoomNumber().equals(roomN))){
                        final IRoom room= hotelResource.getRoom(roomN);
                        final Reservation reservation=hotelResource.bookARoom(email,room,checkIn,checkOut);
                        System.out.println("Reservation Completed");
                        System.out.println(reservation);
                    }else{
                        System.out.println("Room is not available");
                    }
                }
                mainMenu();
            }else{
                System.out.println("Create an account");
                mainMenu();
            }
        }else if("n".equals(bookARoom)){
            mainMenu();
        }else reserveARoom(scanner,checkIn,checkOut,availableRooms);
    }
    public static Date getDate(Scanner date){
        try{
            return new SimpleDateFormat(Default_Date_Format).parse(date.nextLine());

        }catch (ParseException ex){
            System.out.println("Enter Invalid Date");
            findAndReserveARoom();
        }
        return null;
    }

    public static void seeMyReservations(){
        final Scanner scanner=new Scanner(System.in);
        System.out.println("Enter your EmailID");
        final String customerEmail= scanner.nextLine();
        Collection<Reservation> reservations=hotelResource.getCustomersReservations(customerEmail);
        if(reservations.isEmpty()){
            System.out.println("No Reservations found");
        }else{
            for(Reservation reservation:reservations){
                System.out.println(reservation);
            }
        }
    }
    public static void createAnAccount(){
        final Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter your Email ID");
        String email= scanner.nextLine();
        System.out.println("Enter your FirstName");
        String firstName= scanner.nextLine();
        System.out.println("Enter your LastName");
        String lastName= scanner.nextLine();
        try {
            hotelResource.createACustomer(email,firstName,lastName);
            System.out.println("Account Successfully Created");
        }catch (IllegalArgumentException ex){
            System.out.println(ex.getLocalizedMessage());
            createAnAccount();

        }
    }
    public static void printmainmenu(){
        System.out.println("\nWelcome to Hotel Reservation Application\n"+
                "-------------------------------\n"+
                "1. Find and Reserve a room\n"+
                "2. See my reservations\n"+
                "3. Create an account\n"+
                "4. Admin\n"+
                "5. Exit\n"+
                "-------------------------------\n"+
                "Please select a number for main-menu");
    }
}
