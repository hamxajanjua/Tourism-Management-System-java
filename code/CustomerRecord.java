import java.util.*;
import java.io.*;

class CustomerRecord extends Customer1{

    static Scanner input = new Scanner(System.in);


    //............................Customer Record Menu.................
    public static void customerRecordMenu() {
        System.out.println("\n\t\t !! Customer Record Menu !!");

        boolean inValid = true;         
        while(inValid){
            inValid = false;
            System.out.println("1. Enter info");
            System.out.println("2. update info");
            System.out.println("3. delete info");
            System.out.println("4. view info");
            System.out.println("\n5. <<---Back---");
            System.out.println("6. Main Menu");
            System.out.print("\nOption : ");
            option = input.next();
            
            switch (option) {
                case "1": 
                    enterInfo();
                    break;
                case "2":
                    updateInfo();
                    break;
                case "3":
                    deleteInfo();
                    break;
                case "4":
                    viewInfo();
                    break;
                case "5":
                    customerRecordMenu();
                    break;
                case "6":
                    Tour.home();
                    break;
                default:
                    System.out.println("\n\t\t!!..Invalid Option..!!");
                    inValid = true;
                    break;
            }        
        }
    }

//................................add customer information.........
    public static void enterInfo() {
        
        ArrayList<ArrayList<String>> info = new ArrayList();
        ArrayList<String> user = new ArrayList<String>();
        System.out.print("\nEnter Your name : ");
        name = check_name();
        user.add(name);
        System.out.print("\nEnter your age : ");
        age = Integer.parseInt(check_age());
        user.add(String.valueOf(age));
        System.out.print("\nEnter your phone no(without dashes) : ");
        phoneno = Long.parseLong(check_phoneno());
        user.add(String.valueOf(phoneno));
        System.out.print("\nEnter your CNIC in (00000-0000000-0) format : ");
        cnic = check_cnic();
        user.add(cnic);
        System.out.print("\nEnter your address (alphabets only) : ");
        address = check_address();
        user.add(address);
        info.add(user);
        try {
            FileWriter f = new FileWriter("customer.txt",true);
            BufferedWriter writer = new BufferedWriter(f);
            for (int i=0;i< info.size();i++){
                for (int j=0;j< info.get(i).size();j++){
                    writer.write(info.get(i).get(j) + " ");
                }
                writer.write("\n");
            }
            writer.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("\n\t\t!!..Your Information Saved Successfully..!!");
        customerRecordMenu();
    }
//................................view customer information.........
    public static void viewInfo() {
        try {
            File f = new File("customer.txt");
            Scanner reader = new Scanner(f);
            ArrayList<ArrayList<String>> info = new ArrayList();

            System.out.println("Enter your CNIC in (00000-0000000-0) format: ");
            cnic = check_cnic();
            while (reader.hasNextLine()) {
                ArrayList<String> user = new ArrayList<>();
                final String line = reader.nextLine();
                final String[] items = line.split(" "); //reading from file as an array
                for (int i = 0; i < items.length; i++){   //changing it to array list
                    user.add(items[i]);
                }
                info.add(user);
                Arrays.fill(items, null); // to clear out the 'items' array
            }
            System.out.println("\n----------------------------------------------------------------------------------------");
            System.out.println("Name\t\t Age\t\t    Phone Number\t   CNIC\t        Address");
            System.out.println("----------------------------------------------------------------------------------------");
            found = false;
            for (int i = 0; i < info.size(); i++){
                if(info.get(i).get(3).equals(cnic)){
                    for (int j=0;j< info.get(i).size();j++)
                        System.out.printf("%-15s   ",info.get(i).get(j));
                    System.out.println();
                    found=true;
                }
            }
            if(!found){
                System.out.println("\n\t\t!!..Record not found. Add Record first..!!");
            }
            customerRecordMenu();

        }
        catch(Exception e) {
            System.out.println(e);
        }
}
//................................update customer information.........
    public static void updateInfo() {
        try {
            File f = new File("customer.txt");
            Scanner reader = new Scanner(f);
            ArrayList<ArrayList<String>> info = new ArrayList<>();

            System.out.println("Enter your CNIC in (00000-0000000-0) format to update your record: ");
            cnic = check_cnic();
            while (reader.hasNextLine()) {
                ArrayList<String> user = new ArrayList<>();
                String line = reader.nextLine();
                String[] items = line.split(" "); //reading from file as an array
                for (int i = 0; i < items.length; i++)  //changing it to array list
                    user.add(items[i]);
                info.add(user);
                Arrays.fill(items, null); // to clear out the 'items' array
            }
            //int col = 5;
            found = false;
            inValid = true;
            while(inValid){
                inValid = false;
                System.out.println("Enter Record want to Update.");
                System.out.println("\n1. Name \n2. Age \n3. Phone Number \n4. Address ");
                String option = input.nextLine();
                switch(option){
                    case "1":
                        found = false;
                        System.out.println("Enter New Name:");
                        String name = check_name();
                        for (int i=0;i< info.size();i++){
                            if(info.get(i).get(3).equals(cnic)){
                                info.get(i).set(0, name);
                                found = true;
                            }
                        }

                        break;
                    case "2":
                        found = false;
                        System.out.println("Enter New Age:");
                        String age = check_age();
                        for (int i=0;i< info.size();i++){
                            if(info.get(i).get(3).equals(cnic)){
                                info.get(i).set(1, age);
                                found = true;
                            }
                        }
                        break;
                    case "3":
                        found = false;
                        System.out.println("Enter New Phone Number: ");
                        String phoneno = check_phoneno();
                        for (int i=0;i< info.size();i++){
                            if(info.get(i).get(3).equals(cnic)){
                                info.get(i).set(2, phoneno);
                                found = true;
                            }
                        }
                        break;
                    case "4":
                        found = false;
                        System.out.println("Enter New Address: ");
                        String address = check_address();
                        for (int i=0;i< info.size();i++){
                            if(info.get(i).get(3).equals(cnic)){
                                info.get(i).set(4, address);
                                found = true;
                            }
                        }
                        break;

                    default:
                        System.out.printf("\n\t\t!!..Invalid Option..!!");
                        inValid = true;
                        break;
                }
            }
            if (!found) {
                System.out.println("\n\t\t!!..Record not found. Add Record first..!!");
            }
            else{
                //updating Rest of data back to file
                BufferedWriter writer = null;
                FileWriter filew = new FileWriter(f, false);//Turn off append mode
                writer = new BufferedWriter(filew);

                for (int i=0;i< info.size();i++){
                    for (int j=0;j< info.get(i).size();j++)
                        writer.write(info.get(i).get(j) + " ");
                    writer.write("\n");
                }
                writer.close();
                System.out.println("\n\t\t!!..Record Updated Successfully..!!");
            }
            customerRecordMenu();
        }
        catch(Exception e) {
            System.out.println(e);
}
    }
//................................Delete customer information....................
    public static void deleteInfo() {
        try {
            ArrayList<ArrayList<String>> info = new ArrayList();
            File f = new File("customer.txt");
            Scanner reader = new Scanner(f);
            while (reader.hasNextLine()) {
                ArrayList<String> user = new ArrayList<>();
                String line = reader.nextLine();
                String[] items = line.split(" "); //reading from file as an array
                for (int i = 0; i < items.length; i++) {   //changing it to array list
                    user.add(items[i]);
                }
                info.add(user);
                Arrays.fill(items, null); // to clear out the 'items' array
            }
            System.out.println("Enter your CNIC in (00000-0000000-0) format to Delete your record: ");
            cnic = check_cnic();
            found = false;
            for (int i = 0; i < info.size(); i++) {
                if (info.get(i).get(3).equals(cnic)) {
                    info.remove(i);
                    found = true;
                }
            }
            //updating Rest of data back to file
            BufferedWriter writer = null;
            FileWriter filew = new FileWriter(f, false);//clering File before updating Rest of data
            writer = new BufferedWriter(filew);
            for (int i = 0; i < info.size(); i++) {               //writing data to file
                for (int j = 0; j < info.get(i).size(); j++){
                    writer.write(info.get(i).get(j) + " ");}
                writer.write("\n");
            }
            writer.close();

            if (!found) {
                System.out.println("\n\t\t!!..Record not found. Add Record first..!!");
            }
            else
                System.out.println("\n\t\t!!..Info Deleted Successfully..!!");
                customerRecordMenu();

        } catch (IOException e) {
            System.out.println(e);
        }
    }


}