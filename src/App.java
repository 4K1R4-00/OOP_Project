/*
 *  The App class contains all the application logic needed to operate the program
 *
 */
import java.util.Scanner;

class App
{
    //  either TUI or GUI type setting
    private int[] type = {1, 2};

    //  Item product and services array
    private Item[] items    =   new Item[10];

    
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

        if (appType == type[0])
            startTui();

        if (appType == type[1])
            startGui();
    }

    //  Start TUI
    public void startTui() {}

    //  Start GUI
    public void startGui() {}

}
