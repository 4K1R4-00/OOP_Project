import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.math.RoundingMode;

import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Receipt
{
    private File receiptFolder;
    private String receiptName;

    private ArrayList<Product> productList  =   new ArrayList<Product>(5);
    private ArrayList<Service> serviceList  =   new ArrayList<Service>(5);
    private double grandTotal;

    //  Default object constructor
    Receipt() {}

    Receipt(ArrayList<Product> checkoutProductList,
            ArrayList<Service> checkoutServiceList,
            double checkoutGrandTotal)
    {
        this.productList    =   checkoutProductList;
        this.serviceList    =   checkoutServiceList;
        this.grandTotal     =   checkoutGrandTotal;
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
        this.receiptFolder      =   new File("receipts/");

        boolean fileExist       =   this.receiptFolder.exists();

        if (fileExist)
        {
            return fileExist;
        }
        else
        {
            this.receiptFolder.mkdir();

            fileExist           =   this.receiptFolder.exists();
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
        DateTimeFormatter dateTimeFormat    =   DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");

        String receiptName  =   dateTime.format(dateTimeFormat) + ".txt";

        return receiptName;
    }

    private void receiptFormat()
    {
        this.receiptName    =   receiptName();

        NumberFormat df     =   DecimalFormat.getInstance();

        df.setMinimumFractionDigits(2);
        df.setMaximumFractionDigits(4);
        df.setRoundingMode(RoundingMode.DOWN);

        try
        {

            //  Create the file object.
            FileWriter receiptOutput       =   new FileWriter(receiptFolder.getAbsolutePath() + "/" + receiptName);

            receiptOutput.write("===========================================\n");
            receiptOutput.write("       THANK YOU FOR COMING TO SALON       \n");
            receiptOutput.write("===========================================\n");

            for (int i = 0; i < this.productList.size(); i++)
            {
                String itemOutput   =   String.format("%s          Qty %d            RM %s \n",
                                                       this.productList.get(i).getName().substring(0, 6),
                                                       this.productList.get(i).getQuantity(),
                                                       df.format(this.productList.get(i).getCost()));

                receiptOutput.write(itemOutput);
            }

            for (int i = 0; i < this.serviceList.size(); i++)
            {
                String itemOutput   =   String.format("%s          Qty %d            RM %s \n",
                                                       this.serviceList.get(i).getName().substring(0, 6),
                                                       this.serviceList.get(i).getQuantity(),
                                                       df.format(this.serviceList.get(i).getCost()));

                String itemAppointment  =   "";
                if (this.serviceList.get(i).getServiceAppointmentType() == 1)
                {
                    itemAppointment     =   String.format("Scheduled:                       %s \n\n",
                                                            this.serviceList.get(i).getServiceAppointmentDate());
                } else {
                    itemAppointment     =   String.format("Scheduled:                       Walk-in \n\n");
                }

                receiptOutput.write(itemOutput);
                receiptOutput.write(itemAppointment);
            }

            String grandTotalFormat     =   String.format("Grand Total:                     RM %s \n",
                                                            df.format(grandTotal));

            receiptOutput.write("===========================================\n");
            receiptOutput.write(grandTotalFormat);
            receiptOutput.write("===========================================\n");

            //  Close the file object before exit.
            receiptOutput.close();

            System.out.println("Receipt " + this.receiptName + " was generated.");

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
    public void generateReceipt()
    {
        if (checkReceiptFolderExist())
        {
            receiptFormat();
        }
    }
}
