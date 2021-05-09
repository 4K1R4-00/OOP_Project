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
    public void removeItem() {}

    //  Edit item from product list
    public void editItem() {}

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
        // fill test data
        testProductData();

        for(int i = 0; i < products.length; i++)
        {
            System.out.print("product: " + products[i].getName());
            System.out.println("  cost   : " + products[i].getCost());
        }

        // fill test data
        testServiceData();

        for(int i = 0; i < services.length; i++)
        {
            System.out.print("service: " + services[i].getName());
            System.out.println("  cost   : " + services[i].getCost());
        }
    }

    public void testProductData()
    {
        products[0]     =   new Product("Shampoo", 10.00);
        products[1]     =   new Product("Shampoo", 10.00);
        products[2]     =   new Product("Shampoo", 10.00);
        products[3]     =   new Product("Shampoo", 10.00);
        products[4]     =   new Product("Shampoo", 10.00);
        products[5]     =   new Product("Shampoo", 10.00);
        products[6]     =   new Product("Shampoo", 10.00);
        products[7]     =   new Product("Shampoo", 10.00);
        products[8]     =   new Product("Shampoo", 10.00);
        products[9]     =   new Product("Shampoo", 10.00);
    }

    public void testServiceData()
    {
        services[0]     =   new Service("Haircut", 20.00);
        services[1]     =   new Service("Haircut", 20.00);
        services[2]     =   new Service("Haircut", 20.00);
        services[3]     =   new Service("Haircut", 20.00);
        services[4]     =   new Service("Haircut", 20.00);
        services[5]     =   new Service("Haircut", 20.00);
        services[6]     =   new Service("Haircut", 20.00);
        services[7]     =   new Service("Haircut", 20.00);
        services[8]     =   new Service("Haircut", 20.00);
        services[9]     =   new Service("Haircut", 20.00);
    }
}
