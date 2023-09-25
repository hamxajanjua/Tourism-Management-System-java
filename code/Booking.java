import java.util.*;
import java.lang.*;
import java.io.*;
interface Bookings{
    public void newBooking();
    public void viewBookingCustomer();
    public void viewBookingAdmin();
    public void delBooking();
    public void updateBooking();
    public void searchBooking();

}
public class Booking implements Bookings{

    static Scanner input = new Scanner(System.in);
    
    //public static void main(String[] args){
    //     bookingMenuSolo();
    // }

//................................Menu for Solo Class Testing....................
    public static void bookingMenuSolo(){
        Booking b = new Booking();
        Scanner input = new Scanner(System.in);
        boolean inValid = true;         
        while(inValid){
            inValid = false;
            System.out.println("\n1. View Bookings \n2. Add new Booking \n3. Delete Booking \n4. Search Bookings \n5. Update Bookings \n6. Back \n7. Exit ");
            System.out.print("\nOption : ");
            String option = input.nextLine();
            switch(option){
                case "1":
                    b.viewBookingAdmin();
                    bookingMenuSolo();
                    break;
                case "2":
                    b.newBooking();
                    bookingMenuSolo();
                    break;
                case "3":
                    b.delBooking();
                    bookingMenuSolo();
                    break;
                case "4":
                    b.searchBooking();
                    bookingMenuSolo();
                    break;
                case "5":
                    b.updateBooking();
                    bookingMenuSolo();
                    break;
                case "6":
                    Admin.menuAdmin();
                    break;
                case "7":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter Integer in between 1 to 7 ");
                    inValid = true;
                    break;
            }
        }  

    }

// .................................new Booking.................................
    public void newBooking(){
        Scanner input = new Scanner(System.in);
        int id = 101;
        try{
                ArrayList<ArrayList<String>> info = new ArrayList();
                ArrayList<String> user = new ArrayList<String>();

                id = 100 + (int)(Math.random()* 900);
                user.add(String.valueOf(id));

                System.out.print("\nEnter Your Name : ");
                String name = Admin.get_string();//input.next();
                user.add(name);
                
                System.out.print("\nEnter Your Phone Number : ");
                String phoneno = Customer1.check_phoneno();//input.next();
                user.add(String.valueOf(phoneno));

                System.out.print("\nEnter your CNIC in (00000-0000000-0) format : ");
                String cnic = Customer1.check_cnic();//input.next();
                user.add(cnic);

                System.out.print("\nEnter Date for Tour (dd/mm/yyyy) : ");
                String date = Customer1.check_Date();
                user.add(date);
                
                // ...................Enter destination........................
                //System.out.println("");
                Destination.viewDestination(); //display destinations list
                boolean found = false;
                boolean inValid = true;
                while(inValid){
                    inValid=false;
                    //input.nextLine();
                    System.out.print("\nChose a Destination (by id) : ");
                    int id1 = Customer1.check_id(); 
                    File f = new File("destination.txt");
                    f.createNewFile();
                    Scanner reader = new Scanner(f);
                    int x=0;
                    String text="oo";
                    found=false;
                    while (reader.hasNextLine()) {   
                        ++x;
                        text = reader.nextLine();
                        if (x == id1){
                            user.add(text);
                            found=true;
                            break;
                        }
                    }
                    if(!found){
                        System.out.print("\n\t!!..ID Does Not Exists..!! \n");
                        inValid=true;
                    }
                }

                //.....................Enter Vehicle........................
                File fi = new File("vehicle.txt");
                fi.createNewFile();
                found = false;
                inValid = true;
                int id2=0;
                    Vehicle.viewVehicle(); //display destinations list
                    while(inValid){
                        inValid=false;
                        System.out.print("\nChose a Vehicle (by id) : ");
                        id2 = Customer1.check_id();
                        Scanner reader1 = new Scanner(fi);
                        int x=0;
                        String text="";
                        found=false;
                        while (reader1.hasNextLine()) {   
                            ++x;
                            text = reader1.nextLine();
                            if (x == id2){
                                user.add(text);
                                found= true;
                                break;
                            }
                        }
                        if(!found){
                            System.out.print("\n\t!!..ID Does Not Exists..!! \n");
                            inValid=true;
                        }
                    }
                        info.add(user);

                    Admin.update_veh_rec_del(id2);  //vehicle file updated
                
                    FileWriter fw = new FileWriter("booking.txt",true);   //writing data from arraylist to booking file
                    BufferedWriter writer = new BufferedWriter(fw);
                    for (int i=0;i< info.size();i++){
                        for (int j=0;j< info.get(i).size();j++)
                            writer.write(info.get(i).get(j) + "  ");
                        writer.write("\n");   
                    }
                    writer.close();
                    System.out.println("\n\t!!..Booking Confirmed..!!");
                    System.out.println("\n\tYour Booking ID : "+id);
        }catch (Exception e) {
            System.out.println(e);
        }
    
    }

// .................................view Booking..................................
    public void viewBookingCustomer(){

        try {
            ArrayList<ArrayList<String>> info = new ArrayList();

            File f = new File("booking.txt");
            f.createNewFile();
            Scanner reader = new Scanner(f);

            while (reader.hasNextLine()) {
                ArrayList<String> user = new ArrayList<>();
                String line = reader.nextLine();
                String[] items = line.split("  "); //reading from file as an array
                for (int i = 0; i < items.length; i++){   //changing it to array list
                    user.add(items[i]);
                }
                info.add(user);
                Arrays.fill(items, null); // to clear out the 'items' array
            }
            System.out.println("Enter your CNIC in (00000-0000000-0) format: ");
            String cnic = Customer1.check_cnic();
            
            boolean found = false;
            for (int i = 0; i < info.size(); i++)
                if(info.get(i).get(3).equals(cnic)){
                    found=true;
                }
            if(!found){
                System.out.println("\n\t!!..Record Not Found. Create New Booking first..!!");
                Customer1.bookingMenu();           
            }
            System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("ID\t\t\tName\t\t\tNumber\t\t\tCNIC\t\t\tDate\t\t\tDestination\t\tVehicle");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
            //boolean found = false;
            for (int i = 0; i < info.size(); i++){
                if(info.get(i).get(3).equals(cnic)){
                    for (int j=0;j< info.get(i).size();j++)
                        System.out.printf("%-18s\t",info.get(i).get(j));
                    System.out.println();
                }
            }
            
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("File Not Found");
        }
    
    }

//................................Admin View Booking..............................
    public void viewBookingAdmin(){
        Scanner input = new Scanner(System.in);

        try {
            ArrayList<ArrayList<String>> info = new ArrayList();

            File f = new File("booking.txt");
            f.createNewFile();
            Scanner reader = new Scanner(f);

            while (reader.hasNextLine()) {
                ArrayList<String> user = new ArrayList<>();
                String line = reader.nextLine();
                String[] items = line.split("  "); //reading from file as an array
                for (int i = 0; i < items.length; i++){   //changing it to array list
                    user.add(items[i]);
                }
                info.add(user);
                Arrays.fill(items, null); // to clear out the 'items' array
            }

            System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("ID\t\t\tName\t\t\tNumber\t\t\tCNIC\t\t\tDate\t\t\tDestination\t\tVehicle");
            System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
            boolean found = false;
            for (int i = 0; i < info.size(); i++){
                for (int j=0;j< info.get(i).size();j++)
                    System.out.printf("%-18s\t",info.get(i).get(j));
                System.out.println();
                found=true;
            }
            if(!found){
                System.out.println("\n\t!! Record not found. Add Record first.. !!");
                //customer Menu Appears
                //Enter_info();             
            }
            
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("File Not Found..");
        }
    
    }

//................................Delete Booking...................................
    public void delBooking(){
        System.out.println("\n\t\t!!..Delete Bookings..!!");
        Scanner input = new Scanner(System.in);
        try {
            ArrayList<ArrayList<String>> info = new ArrayList();

            File f = new File("booking.txt");
            f.createNewFile();
            Scanner reader = new Scanner(f);

            while (reader.hasNextLine()) {
                ArrayList<String> user = new ArrayList<>();
                String line = reader.nextLine();
                String[] items = line.split("  "); //reading from file as an array
                for (int i = 0; i < items.length; i++){   //changing it to array list
                    user.add(items[i]);
                }
                info.add(user);
                Arrays.fill(items, null); // to clear out the 'items' array
            }
            //...............................Delete by cnic / id.......................

            //String id ="oo";
            String veh = "oo";
            boolean found = false;
            boolean inValid = true;         
            while(inValid){
                inValid = false;
                System.out.println("\nDelete by \n1. ID \n2. CNIC");
                System.out.print("\nOption : ");
                String option = input.nextLine();
                switch(option){
                    case "1":
                        System.out.print("Enter Booking Id To Delete: ");
                        int id = Customer1.check_id();
                        //found = false;
                        for (int i = 0; i < info.size(); i++){
                            if(info.get(i).get(0).equals(Integer.toString(id))){
                                veh = info.get(i).get(6); //read vehicle name to update vehicle record
                                info.remove(i);
                                found=true;
                            }
                        }
                        
                        break;
                    case "2":
                        System.out.print("Enter your CNIC in (00000-0000000-0) format to Delete: ");
                        String cnic = Customer1.check_cnic();
                        
                        for (int i = 0; i < info.size(); i++){
                            if(info.get(i).get(3).equals(cnic)){
                                veh = info.get(i).get(6); //read vehicle name to update vehicle record
                                info.remove(i);
                                found=true;
                            }
                        }

                        break;
                    default:
                        System.out.println("\n\t!!..Incorrect Option..!!");
                        inValid = true;
                        break;
                }
            }
            
            //updating Rest of data back to file
            BufferedWriter writer = null;
            FileWriter filew = new FileWriter(f, false);//Turn off append mode
            writer = new BufferedWriter(filew);
            
            for (int i=0;i< info.size();i++){
                for (int j=0;j< info.get(i).size();j++)
                    writer.write(info.get(i).get(j) + "  ");
                writer.write("\n");   
            }
            writer.close();

            if(!found)
                System.out.println("\n\t!!..Record not found. Add Record first..!!");
            else{
                System.out.println("\n\t!!..Booking Deleted Sucessfully..!!");
                Admin.update_veh_rec_add(veh);  //updating vehicle record from vehicle file(add)
            }
            
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("File Not Found");
        }
    
    }

//...............................**Update Booking**................................
    public void updateBooking(){
        System.out.println("\n\t\t!!..Update Bookings..!!");
        Scanner input = new Scanner(System.in);
        try {
            ArrayList<ArrayList<String>> info = new ArrayList();

            File f = new File("booking.txt");
            f.createNewFile();
            Scanner reader = new Scanner(f);

                //  File to 2d arraylist
            while (reader.hasNextLine()) {
                ArrayList<String> user = new ArrayList<>();
                String line = reader.nextLine();
                String[] items = line.split("  "); //reading from file as an array
                for (int i = 0; i < items.length; i++){   //changing it to array list
                    user.add(items[i]);
                }
                info.add(user);
                Arrays.fill(items, null); // to clear out the 'items' array
            }
            //...............................Upadate by cnic / id.......................

            //String id ="oo";
            String option1 = "oo";
            String veh = "oo";
            boolean found0 = false;
            boolean found = false;
            boolean inValid = true;
            boolean inValid0 = true;         
            while(inValid){
                inValid = false;
                System.out.println("\nUpdate by \n1. ID \n2. CNIC");
                System.out.print("\nOption : ");
                String option = input.nextLine();
                switch(option){
                    //...............................................Update By ID.................................
                    case "1":
                        System.out.print("Enter Booking Id To Update : ");
                        String id = Integer.toString(Customer1.check_id());

                        //found = false;
                        inValid = true;         
                        while(inValid){
                            inValid = false;
                            System.out.println("\nEnter Record want to Update.");
                            System.out.println("\n1. Name \n2. Phone Number \n3. Tour Date \n4. Destination \n5. Vehicle");
                            System.out.print("\nOption : ");
                            option1 = input.nextLine();
                            switch(option1){
                                case "1":
                                    //found = false;
                                    System.out.println("Enter New Name.");
                                    String name0 = Admin.get_string();
                                    for (int i=0;i< info.size();i++){
                                        if(info.get(i).get(0).equals(id)){
                                            info.get(i).set(1, name0);
                                            found = true;
                                        }
                                    }
                                    
                                    break;
                                case "2":
                                    //found = false;
                                    System.out.println("Enter New Phone Number");
                                    String phoneno0 = Customer1.check_phoneno();
                                    for (int i=0;i< info.size();i++){
                                        if(info.get(i).get(0).equals(id)){
                                            info.get(i).set(2, phoneno0);
                                            found = true;
                                        }
                                    }
                                    break;
                                case "3":
                                    //found = false;
                                    System.out.println("Enter New Tour Date");
                                    String date0 = Customer1.check_Date();;
                                    for (int i=0;i< info.size();i++){
                                        if(info.get(i).get(0).equals(id)){
                                            info.get(i).set(4, date0);
                                            found = true;
                                        }
                                    }
                                    break;
                                case "4":// .....................destinstion Update by id....................
                                    //Asking for new vehicle from vehicle list to be updated.
                                    String destination0 ="oo";
                                    Destination.viewDestination(); //display destinations list
                                    
                                    while(inValid0){
                                        inValid0=false;
                                        //input.nextLine();
                                        System.out.print("\nChose a Destination (by id) : ");
                                        int id1 = Customer1.check_id(); 
                                        File f0 = new File("destination.txt");
                                        f0.createNewFile();
                                        Scanner reader0 = new Scanner(f0);
                                        int x=0;
                                        String text="oo";
                                        found0=false;
                                        while (reader0.hasNextLine()) {   
                                            ++x;
                                            text = reader0.nextLine();
                                            if (x == id1){
                                                destination0= text;
                                                found0=true;
                                                break;
                                            }
                                        }
                                        if(!found0){
                                            System.out.print("\n\t!!..Destination ID Does Not Exists..!! \n");
                                            inValid0=true;
                                        }
                                    }
                                    
                                    //found = false;
                                    for (int i=0;i< info.size();i++){
                                        if(info.get(i).get(0).equals(id)){
                                            info.get(i).set(5, destination0);
                                            found = true;
                                        }
                                    }
                                    break;
                                case "5":// ....................Vehicles Update by id......................
                                    //Asking for new vehicle from vehicle list to be updated.
                                    File fi = new File("vehicle.txt");
                                    fi.createNewFile();
                                    String vehicle0 = "oo";
                                    int id2=0;
                                    found = true;
                                    if(fi.length()==0)
                                        System.out.print("\n\t!!..No More Vehicles Available To Update..!!");
                                    else{
                                        Vehicle.viewVehicle(); //display vehicles list
                                        found0 = true;
                                        inValid0 = true;
                                        while(inValid0){
                                            inValid0=false;
                                            System.out.print("\nChoose a New Vehicle (by id) : ");
                                            id2 = Customer1.check_id();
                                            System.out.print(id2);
                                            Scanner reader1 = new Scanner(fi);
                                            int x=0;
                                            String text="oo";
                                            found0=false;
                                            while (reader1.hasNextLine()) {   
                                                System.out.print(id2);
                                                ++x;
                                                text = reader1.nextLine();
                                                if (x == id2){
                                                    vehicle0 = text;
                                                    found0= true;
                                                    break;
                                                }
                                            }
                                            if(!found0){
                                                System.out.print("\n\t!!..Vehicle ID Does Not Exists..!! \n");
                                                inValid0=true;
                                            }
                                        }
                                        found=false;
                                        for (int i=0;i< info.size();i++){
                                            if(info.get(i).get(0).equals(id)){
                                                veh = info.get(i).get(6); //read vehicle name to update vehicle record
                                                info.get(i).set(6, vehicle0);
                                                found = true;
                                            }
                                        }
                                         Admin.update_veh_rec_del(id2);  //vehicle file updated
                                    }
                                    break;
                                default:
                                    System.out.println("\n\t!!..Incorrect Option..!!");
                                    inValid = true;
                                    break;
                            }
                        }
                        break;
                    //...........................................Update By CNIC..........................
                    case "2":
                        System.out.println("Enter your CNIC in (00000-0000000-0) format to Update: ");
                        String cnic = Customer1.check_cnic();
                        
                        //found = false;
                        inValid = true;         
                        while(inValid){
                            inValid = false;
                            System.out.println("Enter Record want to Update.");
                            System.out.println("\n1. Name \n2. Phone Number \n3. Tour Date \n4. Destination \n.5 Vehicle ");
                            System.out.print("\nOption : ");
                            option1 = input.nextLine();
                            switch(option1){
                                case "1":
                                    //found = false;
                                    System.out.println("Enter New Name.");
                                    String name0 = input.nextLine();
                                    for (int i=0;i< info.size();i++){
                                        if(info.get(i).get(3).equals(cnic)){
                                            info.get(i).set(1, name0);
                                            found = true;
                                        }
                                    }
                                    break;
                                case "2":
                                    //found = false;
                                    System.out.println("Enter New Phone Number");
                                    String phoneno0 = input.nextLine();
                                    for (int i=0;i< info.size();i++){
                                        if(info.get(i).get(3).equals(cnic)){
                                            info.get(i).set(2, phoneno0);
                                            found = true;
                                        }
                                    }
                                    break;
                                case "3":
                                    found = false;
                                    System.out.println("Enter New Tour Date");
                                    String date0 = Customer1.check_Date();;
                                    for (int i=0;i< info.size();i++){
                                        if(info.get(i).get(3).equals(cnic)){
                                            info.get(i).set(4, date0);
                                            found = true;
                                        }
                                    }
                                    break;
                                case "4":  // .....................destinstion Update by cnic....................
                                    //Asking for new vehicle from vehicle list to be updated.
                                    String destination0 ="oo";
                                    Destination.viewDestination(); //display destinations list
                                    while(inValid0){
                                        inValid0=false;
                                        //input.nextLine();
                                        System.out.print("\nChose a Destination (by id) : ");
                                        int id1 = Customer1.check_id(); 
                                        File f0 = new File("destination.txt");
                                        f0.createNewFile();
                                        Scanner reader0 = new Scanner(f0);
                                        int x=0;
                                        String text="oo";
                                        found0=false;
                                        while (reader0.hasNextLine()) {   
                                            ++x;
                                            text = reader0.nextLine();
                                            if (x == id1){
                                                destination0= text;
                                                found0=true;
                                                break;
                                            }
                                        }
                                        if(!found0){
                                            System.out.print("\n\t!!..Destination ID Does Not Exists..!! \n");
                                            inValid0=true;
                                        }
                                    }
                                    //Updating Old Vehicle with New Vehicle in Vehicle file
                                    found = false;
                                    for (int i=0;i< info.size();i++){
                                        if(info.get(i).get(3).equals(cnic)){
                                            info.get(i).set(5, destination0);
                                            found = true;
                                        }
                                    }
                                    break;
                                case "5":// ....................Vehicles Update by cnic......................
                                    //Asking for new vehicle from vehicle list to be update
                                    File fi = new File("vehicle.txt");
                                    fi.createNewFile();
                                    String vehicle0 = "oo";
                                    int id2=0;
                                    found = true;
                                    if(fi.length()==0)
                                        System.out.print("\n\t!!..No More Vehicles Available To Update..!!");
                                    else{
                                        Vehicle.viewVehicle(); //display vehicles list
                                        while(inValid0){
                                            inValid0=false;
                                            System.out.print("\nChose a New Vehicle (by id) : ");
                                            id2 = Customer1.check_id();
                                            //File fi = new File("vehicle.txt");
                                            //fi.createNewFile();
                                            Scanner reader1 = new Scanner(fi);
                                            int x=0;
                                            String text="oo";
                                            found0=false;
                                            while (reader1.hasNextLine()) {   
                                                ++x;
                                                text = reader1.nextLine();
                                                if (x == id2){
                                                    vehicle0 = text;
                                                    found0= true;
                                                    break;
                                                }
                                            }
                                            if(!found0){
                                                System.out.print("\n\t!!..Vehicle ID Does Not Exists..!! \n");
                                                inValid0=true;
                                            }
                                        }
                                        //Updating Old Vehicle with New Vehicle in Vehicle file
                                        found = false;
                                        for (int i=0;i< info.size();i++){
                                            if(info.get(i).get(3).equals(cnic)){
                                                veh = info.get(i).get(6); //read vehicle name to update vehicle record
                                                info.get(i).set(6, vehicle0);
                                                found = true;
                                            }
                                        }
                                         Admin.update_veh_rec_del(id2);  //vehicle file updated
                                    }
                                    break;
                                
                                default:
                                    System.out.println("\n\t!!..Incorrect Option..!!");
                                    inValid = true;
                                    break;
                            }
                        } 

                        break;
                    default:
                        System.out.println("\n\t!!..Incorrect Option..!!");
                        inValid = true;
                        break;
                }
            }            
            //updating Rest of data back to file
            BufferedWriter writer = null;
            FileWriter filew = new FileWriter(f, false);//Turn off append mode
            writer = new BufferedWriter(filew);
            for (int i=0;i< info.size();i++){
                for (int j=0;j< info.get(i).size();j++)
                    writer.write(info.get(i).get(j) + "  ");
                writer.write("\n");   
            }
            writer.close();
            if(!found)
                System.out.println("\n\t!!..Relevent Customer Record not found. Add Record first..\\!!");          
            else if(found&&found0){
                System.out.println("\n\t!!..Booking Updated Sucessfully..!!");
                if(veh!="oo")
                    Admin.update_veh_rec_add(veh);  //updating vehicle record from vehicle file(add)
            }
            
        } catch (IOException e) {
            System.out.println("Exception..");
        }
    }

//...............................Search Bookings Admin..............................
    public void searchBooking(){

        try {
            ArrayList<ArrayList<String>> info = new ArrayList();

            File f = new File("booking.txt");
            f.createNewFile();
            Scanner reader = new Scanner(f);

            while (reader.hasNextLine()) {
                ArrayList<String> user = new ArrayList<>();
                String line = reader.nextLine();
                String[] items = line.split("  "); //reading from file as an array
                for (int i = 0; i < items.length; i++){   //changing it to array list
                    user.add(items[i]);
                }
                info.add(user);
                Arrays.fill(items, null); // to clear out the 'items' array
            }
            //...............................by cnic / id / dest  / veh.......................

            //String id ="oo";
            boolean found = false;
            boolean inValid = true;         
            while(inValid){
                inValid = false;
                System.out.println("\nSearch by \n1. ID \n2. CNIC \n3. Destination \n4. Vehicle");
                System.out.print("\nOption : ");
                String option = input.nextLine();
                switch(option){
                    //....................................................By ID.................................
                    case "1":
                        System.out.print("Enter Booking Id To Search Record: ");
                        int id = Customer1.check_id(); //input.next();

                        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("ID\t\t\tName\t\t\tNumber\t\t\tCNIC\t\t\tDate\t\t\tDestination\t\tVehicle");
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");
                        found = false;
                        for (int i = 0; i < info.size(); i++){
                            if(info.get(i).get(0).equals(Integer.toString(id))){
                                for (int j=0;j< info.get(i).size();j++)
                                    System.out.printf("%-18s\t",info.get(i).get(j));
                                System.out.println();
                                found=true;
                            }
                        }
                        break;
                    //..............................................By CNIC..........................
                    case "2":
                        System.out.print("Enter your CNIC in (00000-0000000-0) format to Update: ");
                        String cnic = Customer1.check_cnic();//input.next();

                        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("ID\t\t\tName\t\t\tNumber\t\t\tCNIC\t\t\tDate\t\t\tDestination\t\tVehicle");
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");                        found = false;
                        for (int i = 0; i < info.size(); i++){
                            if(info.get(i).get(3).equals(cnic)){
                                for (int j=0;j< info.get(i).size();j++)
                                    System.out.printf("%-18s\t",info.get(i).get(j));
                                System.out.println();
                                found=true;
                            }
                        }
                        break;
                    //..............................................By Destination..........................
                    case "3":                        
                        System.out.print("Enter Destination to Search Record:: ");
                        String dest = input.next();//admin.get_string();

                        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("ID\t\t\tName\t\t\tNumber\t\t\tCNIC\t\t\tDate\t\t\tDestination\t\tVehicle");
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");                        System.out.println("-----------------------------------------------------------------------------------------------------------------");
                        found = false;
                        for (int i = 0; i < info.size(); i++){
                            if(info.get(i).get(5).equalsIgnoreCase(dest)){
                                for (int j=0;j< info.get(i).size();j++)
                                    System.out.printf("%-18s\t",info.get(i).get(j));
                                System.out.println();
                                found=true;
                            }
                        }
                        break;
                        //..............................................By vehicle..........................
                    case "4":
                        System.out.print("Enter Vehicle to Search Record: ");
                        String veh = Customer1.isValidUsername(); //input.next();
                        
                        System.out.println("\n---------------------------------------------------------------------------------------------------------------------------------------------------------");
                        System.out.println("ID\t\t\tName\t\t\tNumber\t\t\tCNIC\t\t\tDate\t\t\tDestination\t\tVehicle");
                        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------");                        found = false;
                        for (int i = 0; i < info.size(); i++){
                            if(info.get(i).get(6).equalsIgnoreCase(veh)){
                                for (int j=0;j< info.get(i).size();j++)
                                    System.out.printf("%-18s\t",info.get(i).get(j));
                                System.out.println();
                                found=true;
                            }
                        }
                        break;
                    default:
                        System.out.printf("\n\t\t!!..Invalid Option..!!");
                        inValid = true;
                        break;
                }
            }            
            //updating Rest of data back to file
            // BufferedWriter writer = null;
            // FileWriter filew = new FileWriter(f, false);//Turn off append mode
            // writer = new BufferedWriter(filew);
            // for (int i=0;i< info.size();i++){
            //     for (int j=0;j< info.get(i).size();j++)
            //         writer.write(info.get(i).get(j) + "  ");
            //     writer.write("\n");   
            // }
            // writer.close();
            
            if(!found)
                System.out.println("\n\t!!..Record Not Found..!!");
            
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}