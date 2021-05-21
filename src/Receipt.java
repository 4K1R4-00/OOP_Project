import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Receipt
{
    private ArrayList<Item> checkoutList    =   new ArrayList<Item>(5);
    private File receiptFolder;

    //  Default object constructor
    Receipt() {}

    Receipt(ArrayList<Item> checkoutList)
    {
        this.checkoutList       =   checkoutList;
    }

    /*
     *  @param      void
     *
     *  @brief
     *  The method checks for whether the receipts/ directory exist.
     *
     *  if it does not exist, then create a folder name receipts.
     *
     *  then return true, as a confirmation that it exist.
     *
     *  @return     boolean     folderExist
     */
    private boolean checkReceiptFolderExist()
    {
        receiptFolder           =   new File("receipts/");

        boolean fileExist       =   receiptFolder.exists();

        if (fileExist)
        {
            return fileExist;
        }
        else
        {
            receiptFolder.mkdir();

            fileExist           =   receiptFolder.exists();
            return fileExist;
        }
    }

    /*
     *  @param      void
     *
     *  @brief
     *  The method generates the receipt name everytime it is called.
     *  The naming convention is based on the date and time of the local machine.
     *
     *  @return     void
     */
    private String receiptName()
    {
        LocalDateTime dateTime              =   LocalDateTime.now();
        DateTimeFormatter dateTimeFormat    =   DateTimeFormatter.ofPattern("dd-MM-yyyy_HH:mm:ss");

        String receiptName  =   dateTime.format(dateTimeFormat) + ".txt";

        return receiptName;
    }

    private void receiptFormat()
    {
        try
        {
            //  Create the file object.
            FileWriter receiptOutput       =   new FileWriter("receipts/" + receiptName());

            //  Iterate through the checkout list, then print the items name, quantity and cost on receipt.
            for (int i = 0; i < checkoutList.size(); i++)
            {
                Item item   =   checkoutList.get(i);

                String itemOutput   =   item.getName() +
                                        "   X" + item.getQuantity() +
                                        "  RM" + item.getCost() +
                                        "\n";

                receiptOutput.write(itemOutput);
            }

            //  Close the file object before exit.
            receiptOutput.close();

            System.out.println("Receipt " + receiptPath + " was generated.");

        } catch (IOException e)
        {
            System.out.println("A file error has occured during receipt generation.");
            e.printStackTrace();
        }
    }

    /*
     *  @param      void
     *
     *  @brief
     *  The method just prints the receipt to the receipts folder.
     *
     *  @return     void
     */
    public void printReceipt()
    {
        if (checkReceiptFolderExist())
        {
            receiptFormat();
        }
    }
}
