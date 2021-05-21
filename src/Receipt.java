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
    private FileWriter receiptOutput;

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
        boolean folderExist     =   false;

        receiptFolder           =   new File("receipts");

        folderExist             =   receiptFolder.exists();

        if (folderExist)
        {
            return folderExist;
        }
        else
        {
            folderExist         =   receiptFolder.mkdir();
            return folderExist;
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

        String receiptName  =   dateTime.format(dateTimeFormat);

        return receiptName;
    }

    private void receiptFormat()
    {
        String receiptDir   =   receiptFolder.getAbsolutePath() +"/" + receiptName() + ".txt";

        try
        {
            //  Create the file object.
            this.receiptOutput       =   new FileWriter(receiptDir);

            //  Iterate through the checkout list, then print the items name, quantity and cost on receipt.
            for (int i = 0; i < checkoutList.size(); i++)
            {
                Item item   =   checkoutList.get(i);

                String itemOutput   =   item.getName() +
                                        "   X" + item.getQuantity() +
                                        "  RM" + item.getCost() +
                                        "\n";

                this.receiptOutput.write(itemOutput);
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
