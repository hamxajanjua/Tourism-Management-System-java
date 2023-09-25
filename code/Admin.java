import java.util.*;
//import java.lang.*;
import java.io.*;

public class Admin extends User1{
	// public static void main(String[] args){
	// 	login_admin();
	// }

    private static String User_name;
    private static String Pass;
    private static String x;
    private static boolean inValid;
    private static String option;
    static Scanner input = new Scanner(System.in);

    Admin(){
        super();
        User_name="";
        Pass="";
        inValid=true;
    }
    
    // ..................................Admin login screen................................
	public static void loginAdmin(){  
        System.out.println("\n\t\t !! Admin Account Login !! ");

        System.out.print("\nEnter User Name : ");
        User_name = input.nextLine();
        System.out.print("Enter Password : ");
        Pass = input.nextLine();

        x = User_name.trim() + " " + Pass.trim();

        try {
            File f = new File("credentials.txt");
            f.createNewFile();
            PrintWriter output = new PrintWriter(f); // writing admin username & pass in Credentials file
            output.println("admin 0123");
            output.close();
            Scanner in = new Scanner(f);            
            while (in.hasNextLine()) {             //Reading admin username & pass from Credentials file
                String data = in.nextLine();
                if (data.equals(x)) {               //verifying admin username & pass 
                    System.out.println("\nLogin Successful..");
                    System.out.println("\n\t\t!! Welcome Admin !!");
                    menuAdmin(); 
                    break;
                }
                else{
                    inValid = true;         //if invalid credentials
                    while(inValid){
                        inValid = false;
                        System.out.println("\nlogin Failed..");
                        System.out.println("\npress..\n1. Login Again \n2. Main Menu \n3. Exit");
                        option = input.nextLine();
                        switch(option){
                            case "1":
                                loginAdmin();
                                break;
                            case "2":
                                Tour.home();
                                break;
                            case "3":
                                System.out.println("\n\t\t!!..Good Bye..!!");
                                System.out.println("\t!!..Thank You For Being Here..!!");
                                System.exit(0);
                                break;
                            default:
                                System.out.printf("\n\t\t!!..Invalid Option..!!");
                                inValid = true;
                                break;
                        }        
                    }
                }
            }
        } 
        catch (IOException e) {
            System.out.println(e);
        }
    }

//......................................Admin Main Menu.................................
    public static void menuAdmin(){

        inValid = true;         
            while(inValid){
                inValid = false;
                System.out.println("\n\t\t!!..Admin Main Menu..!!");
                System.out.println("\n1. Destinations\n2. Vehicles\n3. Bookings \n\n4. Main Menu  \n0. Exit");
                System.out.print("\nOption : ");
                option = input.nextLine();
                switch(option){
                    case "1":
                        Destination.destinationMenu();
                        break;
                    case "2":
                        Vehicle.vehicleMenu();
                        break;
                    case "3":
                        bookingMenu();
                    case "4":
                        Tour.home();
                    case "0":
                        System.out.println("\n\t\t!!..Good Bye..!!");
                        System.out.println("\t\tThank you for using Tourism Management System.");
                        System.exit(0);
                        break;
                    default:
                        System.out.printf("\n\t\t!!..Invalid Option..!!");
                        inValid = true;
                        break;
                }        
            }
    }


// ..........................Admin Booking Menu............................
    public static void bookingMenu(){
        Booking Booking = new Booking();
        inValid = true;         
            while(inValid){
                inValid = false;
                System.out.println("\n\t\t!!..Bookings Menu..!!");
                System.out.println("\n1. View Bookings \n2. Search Bookings \n3. Delete Bookings \n4. Update Bookings \n\n5. <<---Back---  \n6. Main Menu ");
                System.out.print("\nOption : ");
                option = input.nextLine();
                switch(option){
                    case "1":
                        Booking.viewBookingAdmin();
                        bookingMenu();
                        break;
                    case "2":
                        Booking.searchBooking();
                        bookingMenu();
                        break;
                    case "3":
                        Booking.delBooking();
                        bookingMenu();
                        break;
                    case "4":
                        Booking.updateBooking();
                        bookingMenu();
                        break;
                    case "5":
                        menuAdmin();
                        break;
                    case "6":
                        Tour.home();
                        break;
                    default:
                        System.out.printf("\n\t\t!!..Invalid Option..!!");
                        inValid = true;
                        break;
                }        
            }
    }

//..........................Extra Methods use in Program.....................
// .........................update veh record (delete).......................
    public static void update_veh_rec_del(int iid) {
        try {
            Scanner input = new Scanner(System.in);
            File f = new File("vehicle.txt");
            f.createNewFile();
            Scanner reader = new Scanner(f);

            ArrayList<String> veh = new ArrayList<String>();

            while (reader.hasNextLine()) {
                String text = reader.nextLine();
                veh.add(text);
            }
            veh.remove(iid - 1);
            //veh.set(0, id);

            BufferedWriter writer = null;
            FileWriter filew = new FileWriter(f, false);
            writer = new BufferedWriter(filew);

            for (int i = 0; i < veh.size(); i++) {
                writer.write(veh.get(i));
                writer.write("\n");
            }
            writer.close();

        } catch (IOException e) {
            System.out.println(e);
        }

    }

// .........................update veh record (adding).......................
    public static void update_veh_rec_add(String veh) {
        try {
            Scanner input = new Scanner(System.in);
            File f = new File("vehicle.txt");
            f.createNewFile();

            FileWriter fileWriter = new FileWriter(f, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(veh + "\n");
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println(e);
        }
    }

//..........................get string input.................................                   
    // public static String get_string(){
    //     boolean stop=true;
    //     String s="oo";
    //     while(stop){
    //         stop = false;
    //         s = input.nextLine();
    //         boolean valid = s.matches("[a-zA-Z]+");
    //         if (!(valid)){
    //             System.out.print("\nEnter Correct String : ");
    //             stop=true;
    //         }  
    //     }
    //     return s;
    // }
}
