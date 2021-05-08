/*
 *  The App class contains all the application logic needed to operate the program
 *
 */
import java.util.Scanner;

class App
{
    //  either TUI or GUI type setting
    private int[] interfaceType = {1, 2};

    //  Current product and item catelog list
    private Product[] products  =   new Product[10];
    private Service[] services  =   new Service[10];

    //  Item product and service checkout list
    private Item[] checkout     =   new Item[20];

    //  Default constructor
    App()
    {
        System.out.println("The app has been instantiated");

        Scanner input   =   new Scanner(System.in);

        System.out.println("Select application runtime mode  ");
        System.out.println("=================================");
        System.out.println("1. TUI Mode");
        System.out.println("2. GUI Mode");
        System.out.print("Enter Selection: ");
        int appType     =   input.nextInt();

        if (appType == interfaceType[0])
            startTui();

        if (appType == interfaceType[1])
            startGui();
    }

    //  Start TUI
    public void startTui()
    {

    }

    //  Start GUI
    public void startGui() {}

    //  Add item to product list
    public void addItem() {}

    //  Remove item from product list
    public void removeItem() {}

    //  Edit item from product list
    public void editItem() {}

    //  Grand Total
    public float grandTotal() {}
}
