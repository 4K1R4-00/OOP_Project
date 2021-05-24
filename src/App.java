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

    public static void main(String[] args)
    {
        //  Instantiate App Object
        App app     =   new App();
    }

    //  Default constructor
    App()
    {
        System.out.println("The app has been instantiated");

        Scanner input           =   new Scanner(System.in);

        System.out.println("Select application runtime mode  ");
        System.out.println("=================================");
        System.out.println("1. TUI Mode");
        System.out.println("2. GUI Mode");
        System.out.print("Enter Selection: ");
        int interfaceType     =   input.nextInt();

        if (interfaceType == this.interfaceType[0])
            Tui();

        if (interfaceType == this.interfaceType[1])
            Gui();

        input.close();
    }

    //  Start TUI
    public void Tui()
    {
        //  runTest();
        testProductData();
        testServiceData();

        //TUI tuiInterface    =   new TUI(products, services);
        //tuiInterface.start();
    }

    /*
     *  @params void
     *
     *  @brief
     *  This method instantiates the GUI class and starts the GUI.
     *
     *  @returns void
     */
    public void Gui()
    {
        testProductData();
        testServiceData();

        GUI guiInterface  =   new GUI(products, services);
        guiInterface.start();
    }

    //  Add item to product list
    public void addItem(String type)
    {
        if (type == "product") {}

        if (type == "service") {}
    }

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
            products.remove(index);

        if (type == "service")
            services.remove(index);
    }

    /*
     *  @params int     index
     *  @params String  type
     *
     *  @brief
     *  Edit the item in the ArrayList according to the item type and index number associated.
     *  Then use the provided arguments to edit the chosen ArrayList index.
     *
     *  @return void
     */
    public void editItem(int index, String type, String itemName, double itemCost)
    {
        if (type == "product")
        {
            products.get(index).setName(itemName);
            products.get(index).setCost(itemCost);
        }

        if (type == "service")
        {
            services.get(index).setName(itemName);
            services.get(index).setCost(itemCost);
        }
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
        products.add(new Product("Conditioner", 11.00));
        products.add(new Product("Hair Dryer", 70.00));
        products.add(new Product("Straightener", 65.00));
        products.add(new Product("Hair Dye", 20.00));
        products.add(new Product("Hair Extensions", 30.00));
        products.add(new Product("Hair Syrum", 30.00));
        products.add(new Product("Comb", 11.00));
    }

    public void testServiceData()
    {
        services.add(new Service("Haircut", 30.00));
        services.add(new Service("Hair Perm", 60.00));
    }
}
