import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Receipt
{
    private ArrayList<Item> checkoutList    =   new ArrayList<Item>(5);
    private FileWriter receiptOutput;

    //  Default object constructor
    Receipt() {}

    Receipt(ArrayList<Item> checkoutList)
    {
        this.checkoutList   =   checkoutList;
    }

    private String receiptName()
    {
        LocalDateTime dateTime              =   LocalDateTime.now();
        DateTimeFormatter dateTimeFormat    =   DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss");

        String receiptName  =   dateTime.format(dateTimeFormat);

        return receiptName;
    }

    public void printReceipt()
    {
        String receiptDir   =   "receipts/" + receiptName() + ".txt";

        try
        {
            //  Create the file object.
            this.receiptOutput       =   new FileWriter(receiptDir);

            //  Iterate through the checkout list, then print the items name, quantity and cost on receipt.
            for (int i = 0; i < checkoutList.size(); i++)
            {
                Item item   =   checkoutList.get(i);

                String itemOutputFormat     =   item.getName() +
                                                "   X" + item.getQuantity() +
                                                "  RM" + item.getCost() +
                                                "\n";

                this.receiptOutput.write(itemOutputFormat);
            }

            //  Close the file object before exit.
            receiptOutput.close();

            System.out.println("Receipt " + receiptDir + " was generated.");

        } catch (IOException e)
        {
            System.out.println("A file error has occured during receipt generation.");
            e.printStackTrace();
        }
    }
}
