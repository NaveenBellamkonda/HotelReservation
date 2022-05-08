import api.AdminResource;
import api.HotelResource;
import model.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AdminMenu {

    public static void adminMenu() {
        String line="";
        Scanner scanner=new Scanner(System.in);
        printAdminMenu();

        try {
            do {
                line = scanner.nextLine();

                if (line.length() == 1) {
                    if (line.equals("1")) {
                        seeAllCustomers();
                        break;
                    }
                    if (line.equals("2")) {
                        seeAllRooms();
                        break;
                    }
                    if (line.equals("3")) {
                        seeAllReservations();
                        break;
                    }
                    if (line.equals("4")) {
                        addARoom();
                        break;
                    }
                    if (line.equals("5") ){
                        MainMenu.mainMenu();
                        break;
                    }
                    System.out.println("Invalid Input");
                }
            }while (line.length()!=1||line.charAt(0)!=5);
        }catch (StringIndexOutOfBoundsException ex ){
            System.out.println("Empty input Received");
        }

    }
    public static void seeAllCustomers(){
        Collection<Customer> allCustomers= AdminResource.getSingleton().getAllCustomers();
        for (Customer customer:allCustomers){
            System.out.println(customer);
        }
    }
    public static void seeAllRooms(){
        Collection<IRoom> allRooms= AdminResource.getSingleton().getAllRooms();
        for(IRoom room:allRooms){
            System.out.println(room);
        }
    }
    public static void seeAllReservations(){
        AdminResource.getSingleton().DisplayAllReservations();
    }
    public static void addARoom(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("plese enter a room number");
        String roomNumber=scanner.nextLine();
        System.out.println("plese enter a room price");
        Double price= Double.parseDouble(scanner.nextLine());
        System.out.println("plese enter a room type");
        RoomType roomType=RoomType.valueOf(scanner.nextLine());
        Room room=new Room(roomNumber,price,roomType);
        AdminResource.getSingleton().addRoom(Collections.singletonList(room));
    }
    public static void printAdminMenu(){
        System.out.println("\nWelcome to the Hotel Reservation App\n"+
                "------------------------------------------\n"+
                "1. See all customers\n"+
                "2. See all rooms\n"+
                "3. See all reservations\n"+
                "4. Add a Room\n"+
                "5. Back to Main menu\n"+
                "-------------------------------------------");
    }
}
