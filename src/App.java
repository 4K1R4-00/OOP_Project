/*
 *  The App class contains all the application logic needed to operate the program
 *
 */
import java.util.Scanner;
import java.util.ArrayList;

class App
{
    //  Current product and item catelog list
    private ArrayList<Product> products     =   new ArrayList<Product>(5);
    private ArrayList<Service> services     =   new ArrayList<Service>(5);

    public static void main(String[] args)
    {
        //  Instantiate App Object
        App app     =   new App();
        app.start();
    }

    //  Default constructor
    App() {}

    /*
     *  @params void
     *
     *  @brief
     *  This method instantiates the GUI class and starts the GUI.
     *
     *  @returns void
     */
    public void start()
    {
        testProductData();
        testServiceData();

        GUI guiInterface  =   new GUI(products, services);
        guiInterface.start();
    }

    /*
     *  @param      none
     *
     *  @brief
     *  This method just populates the product array list with items.
     *
     *  @return
     */
    public void testProductData()
    {
        products.add(new Product("Shampoo", 10.00));
        products.add(new Product("Pomade ", 20.00));
        products.add(new Product("Gel    ", 15.00));
        products.add(new Product("Conditioner", 11.00));
        products.add(new Product("Hair Dryer", 70.00));
        products.add(new Product("Hair Dye", 20.00));
        products.add(new Product("Hair Extensions", 30.00));
        products.add(new Product("Hair Syrum", 30.00));
        products.add(new Product("Comb", 11.00));
    }

    /*
     *  @param      none
     *
     *  @brief
     *  This method just populates the service array list with items.
     *
     *  @return
     */
    public void testServiceData()
    {
        services.add(new Service("Haircut", 30.00));
        services.add(new Service("Hair Perm", 60.00));
        services.add(new Service("Hair Straightening", 100.00));
    }
}
