/*  Customer Record Menu.....190
    Booking Menu............470
    Extra Methods...........510
*/
import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer1 extends User1{

    // private static String Uname;
    // public String getUname() {
    //     return Uname;
    // }
    // public void setUname(String uname) {
    //     Uname = uname;
    // }
    
    //public static void main(String[] args){
    //     tour tour = new tour();
    //     customer1 customer1 = new customer1();
    //     booking booking = new booking();
    //     //customer_menu();
    //     login_customer();
    //}String cnic, name, address;
    //long phoneno;
    //int age;
    
    //private static boolean inValid;
    //private static String option;
    private static int flag;
    private static String x;

    static Scanner input = new Scanner(System.in);
    Customer1(){
        super();
    }

// ........................customer login screen.............................
    public static void loginCustomer(){

        System.out.println("\n\t\t !! Customer Account !! ");
        inValid = true;
        while(inValid){
            inValid = false;
            System.out.println("\n1. SignIn \n2. SignUp \n\n3. Main Menu \n0. Exit");
            System.out.print("\noption: ");
            option = input.nextLine();
            switch (option) {
                case "1":
                    signin();
                    break;
                case "2":
                    signup();
                    break;
                case "3":
                    Tour.home();
                    break;
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

//.................................signup.........................
    public static void signup() {
        try {
            System.out.println("\n\t\t !! Signup menu !!");
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter User Name : ");
            String Uname = isValidUsername();
            
            System.out.print("Enter Password : ");
            String Pass = isValidPassword();
            
            inValid = true;
            while (inValid){
                inValid = false;
                System.out.print("Confirm Password : ");
                String ConPass = sc.nextLine();

                Uname = Uname.trim();
                Pass = Pass.trim();
                ConPass = ConPass.trim();

                if (!(Pass.equals(ConPass))){
                    System.out.println("\n\t\t!!..Recheck Please..!!");
                    inValid = true;
                }
            }
            String x = Uname + " " + Pass;

            File f = new File("account.txt");
            f.createNewFile();
            Scanner in = new Scanner(f);
            int flag = 0;
            while (in.hasNextLine()) {
                String data = in.nextLine();
                if (data.equals(x)) {
                    System.out.println("\n\t\t!!..Already Registered..!!");
                    flag = 1;
                    loginCustomer();
                    break;
                }

            }
            if (flag == 0) {
                FileWriter filew = new FileWriter(f, true);
                BufferedWriter writer = new BufferedWriter(filew);
                writer.write(Uname + " " + Pass + "\n");
                writer.close();
            }

            System.out.println("\n\t\tSuccessfully signed up");
            System.out.println("\nProceeding to SignIn Menu...");
            signin();
            
        }catch (IOException e) {
            System.out.println(e);
        }
    }

//.............................signin........................
    public static void signin() {

        System.out.println("\n\t\t !! SignIn Menu !!");
        Scanner sc = new Scanner(System.in);

        System.out.print("\nEnter User Name: ");
        String Uname = sc.nextLine();//is_notEmpty();


        System.out.print("Enter Password: ");
        String Pass = sc.nextLine();//is_notEmpty();

        Uname = Uname.trim();
        Pass = Pass.trim();
        x = Uname + " " + Pass;


        try {
            File f = new File("account.txt");
            f.createNewFile();
            Scanner in = new Scanner(f);
            int flag = 0;
            while (in.hasNextLine()) {
                String data = in.nextLine();
                if (data.equals(x)) {
                    System.out.println("\nLogin Successful..");
                    flag = 1;
                    customerMenu();
                    break;
                }
            }
            inValid = true;
            if (flag == 0)
                while(inValid){
                    inValid = false;
                    System.out.println("\n\t\t!!..Incorrect password..!!\nlogin Failed..\nCreate a New Account OR Enter Correct Credentials..");
                    loginCustomer();   //use recursion
                }
        }

        catch (IOException e) {
            System.out.println(e);
        }
    }

//,............................display customer menu................
    public static void customerMenu(){
        System.out.println("\n\t\t !! Customer Main Menu !!");

        inValid = true;         
            while(inValid){
                inValid = false;
                System.out.println("\n1. Customer Record\n2. Tour Bookings\n\n3. Main Menu \n0. Exit");
                System.out.print("\nOption : ");
                String option = input.nextLine();
                switch(option){
                    case "1":
                        CustomerRecord.customerRecordMenu();
                        break;
                    case "2":
                        bookingMenu();
                        break;
                    case "3":
                        Tour.home();
                    case "0":
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

//...................................Booking_menu_Customer........................
    public static void bookingMenu(){
        Booking Booking = new Booking();
        
        try{
        System.out.println("\n\t\t!! Customer Tour Booking Menu !!");
        boolean inValid = true;         
            while(inValid){
                inValid = false;
                System.out.println("\n1. Create New Booking \n2. View Bookings \n3. Delete Bookings \n4. Update Bookings  \n\n5. <<---Back---  \n6. Main Menu ");
                System.out.print("\nOption : ");
                String option = input.nextLine();
                switch(option){
                    case "1":
                        File fi = new File("vehicle.txt");
                        fi.createNewFile();
                        if(fi.length()==0)
                            System.out.print("\n\t!!..All Vehicles Are Already Booked..!!");
                        else
                        Booking.newBooking();
                        bookingMenu();
                        break;
                    case "2":
                        Booking.viewBookingCustomer();
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
                        customerMenu();
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
        catch(Exception e){
            System.out.print("\n\t!!..All Vehicles Are Already Booked..!!");
            bookingMenu();
            System.out.println(e);

        }
    }

// //..............Extra Validation Methods used in program...................
//     public static String isValidUsername(){
//         Scanner input = new Scanner(System.in);
//         boolean stop=true;
//         String s= " ";
//         while(stop){
//             stop = false;
//             s = input.nextLine();
//             boolean valid = s.matches("^\\w{3,16}$");
//             if (!valid){
//                 System.out.println("Please Enter correct Username (length: 3-16) & (alphabets/numbers/_): ");
//                 stop=true;
//             }
//         }
//         return s;
//     }
//     public static String isValidPassword(){
//         Scanner input = new Scanner(System.in);
//         boolean stop=true;
//         String s= " ";
//         while(stop){
//             stop = false;
//             s = input.nextLine();
//             boolean valid = s.matches("^\\w{8,16}$");
//             if (!valid){
//                 System.out.println("Please Enter Strong password (length: 8-16) & (alphabets/numbers/_): ");
//                 stop=true;
//             }
//         }
//         return s;
//     }
//     public static String check_name(){
//         Scanner input = new Scanner(System.in);
//         boolean stop=true;
//         String s= " ";
//         while(stop){
//             stop = false;
//             s = input.nextLine();
//             boolean valid = s.matches("[a-zA-Z]+");
//             if (!valid){
//                 System.out.print("\nEnter Correct name : ");
//                 stop=true;
//             }
//         }
//     return s;
//     }
//     public static String check_cnic() {
//         Scanner input = new Scanner(System.in);
//         boolean stop=true;
//         String s= " ";
//         while(stop){
//             stop = false;
//             s = input.nextLine();
//             boolean valid = s.matches("^[0-9+]{5}-[0-9+]{7}-[0-9]{1}$");
//             if (!valid){
//                 System.out.print("\nEnter correct CNIC in (00000-0000000-0) format : ");
//                 stop=true;
//             }
//         }
//         return s;
//     }
//     public static String check_phoneno() {
//         Scanner input = new Scanner(System.in);
//         boolean stop=true;
//         String s= " ";
//         while(stop){
//             stop = false;
//             s = input.nextLine();
//             boolean valid = s.matches("\\d{11}");
//             if (!valid){
//                 System.out.print("\nEnter your correct phone no(without dashes) : ");
//                 stop=true;
//             }
//         }
//         return s;
//     }
//     public static String check_age() {
//         Scanner input = new Scanner(System.in);
//         boolean stop=true;
//         String s= " ";
//         while(stop){
//             stop = false;
//             s = input.nextLine();
//             boolean valid = s.matches("\\d{2}");
//             if (!valid){
//                 System.out.println("Enter 2 digits only: ");
//                 stop=true;
//             }
//         }
//         return s;
//     }
//     public static String check_address(){
//         Scanner input = new Scanner(System.in);
//         boolean stop=true;
//         String s= " ";
//         while(stop){
//             stop = false;
//             s = input.nextLine();
//             boolean valid = s.matches("[a-zA-Z,]+$");
//             if (!valid){
//                 System.out.println("Enter correct address in (city,province) format: ");
//                 stop=true;
//             }
//         }
//         return s;
//     }
//     public static String check_Date(){
//         Scanner input = new Scanner(System.in);
//         boolean stop=true;
//         String s= " ";
//         while(stop){
//             stop = false;
//             s = input.nextLine();
//             boolean valid = s.matches("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$");
//             if (!valid){
//                 System.out.print("Enter correct Date in (dd/mm/yyyy) format : ");
//                 stop=true;
//             }
//         }
//         return s;
//     }
//     public static int check_id() {
//         Scanner input = new Scanner(System.in);
//         boolean stop=true;
//         int s=0;
//         while(stop){
//             stop = false;
//             try{
//             s = input.nextInt();
//             //boolean valid = s.matches("\\d{1}");
//             }
//             catch(Exception e){
//                 System.out.print("\nEnter correct ID : ");
//                 input.nextLine();
//                 stop=true;
//             }
//         }
//         return s;
//     }

    //public static String check_date(){
    //     Scanner input = new Scanner(System.in);
    //     boolean stop=true;
    //     String s= " ";
    //     while(stop){
    //         stop = false;
    //         s = input.nextLine();
    //         boolean valid = s.matches("^(19|20)\d\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$");
    //         if (!valid){
    //             System.out.println("Enter correct address in (city,province) format: ");
    //             stop=true;
    //         }
    //     }
    //     return s;
    //}
}
