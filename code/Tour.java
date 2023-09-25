import java.util.Scanner;

public class Tour {
    static Scanner input = new Scanner(System.in);
    private static String option;

    public static void main(String[] args) {
        System.out.println("\n\t!!! Tourism Management System !!!");
        home();
    }

    public static void home() {

        boolean inValid = true;
        while (inValid) {
            inValid = false;
            System.out.println("\n\t\t!!..Main Menu..!!");
            System.out.println("\n1. Customer Menu \n2. Admin Menu \n\n0. Exit ");
            System.out.print("\nOption : ");
            option = input.next();

            switch (option) {
                case "1":
                    Customer1.loginCustomer();  // Sends to customer Menu
                    break;
                case "2":
                    Admin.loginAdmin();  // Sends to Admin Menu
                    break;
                case "0":
                    System.out.println("\n\t\t!!..Good Bye..!!");
                    System.out.println("\t!!..Thank You For Being Here..!!\n");
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
