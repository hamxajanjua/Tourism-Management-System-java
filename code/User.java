import java.util.*;

class User{
    public String name;
    protected static String password;
    protected static String cnic;
    protected static String address;
    protected static long phoneno;
    protected static int age;
    private static boolean stop;
    private static String data;
    private static int value;
    static Scanner input = new Scanner(System.in);

    User(){
        name="";
        password="";
        cnic="";
        address="";
        phoneno=0;
        age=0;
        data="";
        value=0;

    }
    User(String name, String password, String cnic, String address, long phoneno, int age ){
        this.name=name;
        this.password=password;
        this.cnic=cnic;
        this.address=address;
        this.phoneno=phoneno;
        this.age=age;

    }
    

    //..............Validation Methods...................
    public static String isValidUsername(){
        stop=true;
        data= " ";
        while(stop){
            stop = false;
            data = input.nextLine();
            boolean valid = data.matches("^\\w{3,16}$");
            if (!valid){
                System.out.println("Please Enter correct Username (length: 3-16) & (alphabets/numbers/_): ");
                stop=true;
            }
        }
        return data;
    }
    public static String isValidPassword(){
        stop=true;
        data= " ";
        while(stop){
            stop = false;
            data = input.nextLine();
            boolean valid = data.matches("^\\w{8,16}$");
            if (!valid){
                System.out.println("Please Enter Strong password (length: 8-16) & (alphabets/numbers/_): ");
                stop=true;
            }
        }
        return data;
    }
    public static String check_name(){
        stop=true;
        data= " ";
        while(stop){
            stop = false;
            data = input.nextLine();
            boolean valid = data.matches("[a-zA-Z]+");
            if (!valid){
                System.out.print("\nEnter Correct name : ");
                stop=true;
            }
        }
    return data;
    }
    public static String check_cnic() {
        stop=true;
        data= " ";
        while(stop){
            stop = false;
            data = input.nextLine();
            boolean valid = data.matches("^[0-9+]{5}-[0-9+]{7}-[0-9]{1}$");
            if (!valid){
                System.out.print("\nEnter correct CNIC in (00000-0000000-0) format : ");
                stop=true;
            }
        }
        return data;
    }
    public static String check_phoneno() {
        stop=true;
        data= " ";
        while(stop){
            stop = false;
            data = input.nextLine();
            boolean valid = data.matches("\\d{11}");
            if (!valid){
                System.out.print("\nEnter your correct phone no(without dashes) : ");
                stop=true;
            }
        }
        return data;
    }
    public static String check_age() {
        stop=true;
        data= " ";
        while(stop){
            stop = false;
            data = input.nextLine();
            boolean valid = data.matches("\\d{2}");
            if (!valid){
                System.out.println("Enter 2 digits only: ");
                stop=true;
            }
        }
        return data;
    }
    public static String check_address(){
        stop=true;
        data= " ";
        while(stop){
            stop = false;
            data = input.nextLine();
            boolean valid = data.matches("[a-zA-Z,]+$");
            if (!valid){
                System.out.println("Enter correct address in (city,province) format: ");
                stop=true;
            }
        }
        return data;
    }
    public static String check_Date(){
        stop=true;
        data= " ";
        while(stop){
            stop = false;
            data = input.nextLine();
            boolean valid = data.matches("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$");
            if (!valid){
                System.out.print("Enter correct Date in (dd/mm/yyyy) format : ");
                stop=true;
            }
        }
        return data;
    }
    public static int check_id() {
        stop=true;
        value=0;
        while(stop){
            stop = false;
            try{
            value = input.nextInt();
            //boolean valid = data.matches("\\d{1}");
            }
            catch(Exception e){
                System.out.print("\nEnter correct ID : ");
                input.nextLine();
                stop=true;
            }
        }
        return value;
    }
//..........................get string input.................................                   
public static String get_string(){
    stop=true;
    data=" ";
    while(stop){
        stop = false;
        data = input.nextLine();
        boolean valid = data.matches("[a-zA-Z]+");
        if (!(valid)){
            System.out.print("\nEnter Correct String : ");
            stop=true;
        }  
    }
    return data;
}
}


