/*
 *  The App class contains all the application logic needed to operate the program
 *
 */
import java.util.Scanner;
import java.util.ArrayList;

class App
{
    //  either TUI or GUI type setting
    private int[] interfaceType = {1, 2};

    //  Current product and item catelog list
    private ArrayList<Product> products     =   new ArrayList<Product>(5);
    private ArrayList<Service> services     =   new ArrayList<Service>(5);

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

        input.close();
    }

    //  Start TUI
    public void startTui()
    {
        runTest();
    }

    //  Start GUI
    public void startGui() {}

    //  Add item to product list
    public void addItem() {}

    //  Remove item from product list
    /*
     *  @params int     index
     *  @params String  type
     *  
     *  @brief
     *  Remove item in arraylist according to the item type, and index number associated.
     *
     *  @return void
     */
    public void removeItem(int index, String type)
    {
        if (type == "product")
        {
            products.remove(index);
        }

        if (type == "service")
        {
            services.remove(index);
        }
    }

    //  Edit item from product list
    public void editItem(int index) 
    {

    }

    //  Grand Total
    //public float grandTotal() {}

    /*
     *  @params void
     *
     *  @brief
     *   This method runs all the test
     *
     *   @return void
     */
    public void runTest()
    {
        System.out.println("\nRunning Product Tests");
        System.out.println("==============================");

        // fill test data
        testProductData();

        for(int i = 0; i < products.size(); i++)
        {
            System.out.print("product: " + products.get(i).getName());
            System.out.println("  cost   : " + products.get(i).getCost());
        }

        System.out.println("\nTest 1: removeItem() test");

        removeItem(1, "product");

        for(int i = 0; i < products.size(); i++)
        {
            System.out.print("product: " + products.get(i).getName());
            System.out.println("  cost   : " + products.get(i).getCost());
        }

        System.out.println("\nRunning Service Test");
        System.out.println("==============================");

        // fill test data
        testServiceData();

        for(int i = 0; i < services.size(); i++)
        {
            System.out.print("service: " + services.get(i).getName());
            System.out.println("  cost   : " + services.get(i).getCost());
        }
    }

    public void testProductData()
    {
        products.add(new Product("Shampoo", 10.00));
        products.add(new Product("Pomade ", 20.00));
        products.add(new Product("Gel    ", 15.00));
    }

    public void testServiceData()
    {
        services.add(new Service("Haircut", 30.00));
    }
}
