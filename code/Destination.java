import java.util.*;
import java.io.*;

class Destination extends Admin{

    // public static void main(String[] args) {
    //     destinationMenu();
    // }

    private static boolean inValid;
    private static boolean found;
    private static String option;
    private static String destination;
    private static String text;
    private static int id;
    static Scanner input = new Scanner(System.in);

    //constructors
    Destination(){
        inValid=true;
        found=false;
        option="";
        destination="";
        text="";
        id=0;
    }
    Destination(String des,String txt,int id){
        this.destination=des;
        this.text=txt;
        this.id=id;
    }
    //get/set
    public String getDestination() {
        return destination;
    }
    public int getId() {
        return id;
    }
    public String getText() {
        return text;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }
    public void setId(int id) {
        this.id = id;
    }
//................................Admin Destination Menu....................
public static void destinationMenu(){
    //Scanner input = new Scanner(System.in);

    while(true){
        //inValid = false;
        System.out.println("\n\t\t!! Destination Menu !!");
        System.out.println("\n1. Add Destinations \n2. View Destinations \n3. Delete Destination \n\n4. <<---Back--- \n5. Main Menu");
        System.out.print("\nOption : ");
        option = input.next();
        if(!(option.equals("1")||option.equals("2")||option.equals("3")||option.equals("4")||option.equals("5")))
        {
            System.out.printf("\n\t\t!!..Invalid Option..!!");
            continue;
        }
        if(option.equals("1"))
            addDestination();
        if(option.equals("2"))
            viewDestination();
        if(option.equals("3"))
            deleteDestination();
        if(option.equals("4"))
        {
            Admin.menuAdmin();
            break;
        }
        if(option.equals("5")){
            Tour.home();
            break;
        }
    }
}

// ..............................Add Destination...........................
public static void addDestination(){
    try{
        //Scanner input = new Scanner(System.in);
        File f = new File("destination.txt");
        f.createNewFile();
        FileWriter filew = new FileWriter(f, true);
        BufferedWriter writer = new BufferedWriter(filew);

        System.out.println("Enter Destinations : ");
        destination = input.nextLine();//admin.get_string();

        writer.write(destination + "\n");
        writer.close();
        
        System.out.println("Destinations Added sucessfully !! ");
    } 
    catch (IOException e) {
        System.out.println(e);
    }
}    

//.............................Delete Destination..........................
public static void deleteDestination(){
    try{
        //Scanner input = new Scanner(System.in);
        File f = new File("destination.txt");
        f.createNewFile();
        Scanner reader = new Scanner(f);
        
        ArrayList<String> dest = new ArrayList<String>();

        while (reader.hasNextLine()) {
            text = reader.nextLine();   
            dest.add(text); 
        }
        inValid = true;         
        while(inValid){
            inValid = false;
            System.out.print("Enter Destination id to delete : ");
            int id = User1.check_id();
            if(id<=dest.size()&&id>0){
                dest.remove(id-1);

                BufferedWriter writer = null;
                FileWriter filew = new FileWriter(f, false);
                writer = new BufferedWriter(filew);

                for (int i=0;i< dest.size();i++){
                    writer.write(dest.get(i));
                    writer.write("\n");
                    found = true;
                }
                writer.close();
            }
        }
        if(!found){
            inValid=true;
            System.out.println("\n\t!!..Record not found. Add Record first..!!");
        }
        else
            System.out.println("\n\t!!..Destination Deleted Sucessfully..!!");
    } 
    catch (IOException e) {
        System.out.println(e);
    }
}

//...............................View destination.........................
public static void viewDestination(){
    try{
        //Scanner input = new Scanner(System.in);
        File f = new File("destination.txt");
        f.createNewFile();
        Scanner reader = new Scanner(f);

        int i = 0;

        System.out.println("\n\t\t\t----------------------\n\t\t\tID   Destinations\n\t\t\t----------------------");
        while (reader.hasNextLine()) {
            text = reader.nextLine();
            System.out.println("\t\t\t" + (++i)+"    "+text);
        }
        
    }
    catch (IOException e) {
        System.out.println(e);
    }
}

}
