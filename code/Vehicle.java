import java.util.*;
import java.lang.*;
import java.io.*;
class Vehicle {
    // public static void main(String[] args) throws IOException {
    //     vehicleMenu();
    // }

    private static String vehicleName;
    private static String regNo;
    private static String category;
    private static boolean inValid;
    private static boolean found;
    static Scanner input =  new Scanner(System.in);

    Vehicle(){
        vehicleName = "";
        regNo = "";
        category = "";
    }
    Vehicle(String vname,String reg,String cat){
        vehicleName = vname;
        regNo = reg;
        category = cat;
    }
    void setvehicleName(String vname){
        vehicleName = vname;
    }
    void setregNo(String reg){
        regNo = reg;
    }
    void setCateory(String cat){
        category = cat;
    }
    public String getvehicleName(){
        return vehicleName;
    }
    public String getregNo(){
        return regNo;
    }
    public String getcategory(){
        return category;
    }
    
    public static void vehicleMenu(){
        try{
        boolean inValid = true;
            while (inValid) {
                inValid = false;
                System.out.println("\n\t\t!! Vehicles Menu !!");
                System.out.println("\n1. Add Vehicles \n2. View Vehicles \n3. Delete Vehicles \n\n4. <<---back--- \n5. Main Menu");
                System.out.print("\nOption : ");
                String option = input.nextLine();
                switch (option) {
                    case "1":
                        addVehicle();
                        vehicleMenu();
                        break;
                    case "2":
                        viewVehicle();
                        vehicleMenu();
                        break;
                    case "3":
                        deleteVehicle();
                        vehicleMenu();
                        break;
                    case "4":
                        Admin.menuAdmin();
                        break;
                    case "5":
                        Tour.home();
                        break;
                    default:
                        System.out.print("\n\t\t!!..Invalid Option..!!");
                        inValid = true;
                        break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addVehicle(){
        ArrayList<ArrayList<String>> info = new ArrayList();
        ArrayList<String> user = new ArrayList<String>();

        System.out.print("\nEnter Car Reg_no : ");
        String regNo = input.nextLine();//admin.get_string();//input.next();
        //int regNo = 100 + (int)(Math.random()* 900);
        user.add(regNo);

        System.out.print("\nEnter Car Name : ");
        String vehicleName = input.nextLine();//admin.get_string();//input.next();
        user.add(vehicleName);

        System.out.print("\nEnter Car Category : ");
        String category = input.nextLine();//admin.get_string(); 
        user.add(category);

        info.add(user);
        try (FileWriter fw = new FileWriter("vehicle.txt",true)) {
            BufferedWriter writer = new BufferedWriter(fw);
            for (int i=0;i< info.size();i++){
                for (int j=0;j< info.get(i).size();j++)
                    writer.write(info.get(i).get(j) + "  ");
                writer.write("\n");   
            }
            writer.close();
            System.out.println("\n\t!!..CAR ADDED..!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void viewVehicle() throws IOException {
        ArrayList<ArrayList<String>> info = new ArrayList();

        File f = new File("vehicle.txt");
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
        System.out.println("\n------------------------------------------------------------------------------------------------------------------");
        System.out.println("ID\t\t\tVehicleName\t\tCategory");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        //boolean found = false;
        for (int i = 0; i < info.size(); i++){
                for (int j=0;j< info.get(i).size();j++)
                    System.out.printf("%-18s\t",info.get(i).get(j));
                System.out.println();
        }
    }
    public static void deleteVehicle() throws IOException {

            ArrayList<ArrayList<String>> info = new ArrayList();

            File f = new File("vehicle.txt");
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
            System.out.print("Enter Vehicle id to delete : ");
            String id = input.nextLine();//customer1.check_id();
            found = false;
            for (int i = 0; i < info.size(); i++){
                if(info.get(i).get(0).equals(id)){
                    info.remove(i);
                    found=true;
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
            if(!found){
                inValid=true;
                System.out.println("\n\t!!..Vehicle not found. Add Vehicle first..!!");
            }
            else
                System.out.println("\n\t!!..Vehicle Deleted Sucessfully..!!");

    }

}

