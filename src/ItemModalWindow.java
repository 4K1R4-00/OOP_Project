import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JDialog;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.BorderLayout;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;

/*
 *  @brief
 *
 *  The ItemModalWindow is the dialog window that appears after a
 *  product or service is selected, in order to ensure the quantity and cost.
 *
 */
public class ItemModalWindow implements WindowListener
{
    private JDialog itemWindow;

    private JFrame parentFrame;
    private JPanel modalButton;
    private JLabel quantity;

    private JPanel itemPanel    =   new JPanel(new GridLayout(0, 1));
    private JPanel itemInfo     =   new JPanel(new GridLayout(0, 2));
    private JPanel itemQtyModal =   new JPanel(new GridLayout(0, 3));

    private int quantityCounter =   1;

    private Service service;
    private Product product;

    private String itemName;
    private double itemCost;
    private int itemQuantity;

    ItemModalWindow(JFrame parentFrame, Product product)
    {
        this.parentFrame        =   parentFrame;
        this.product            =   product;

        this.itemName           =   product.getName();
        this.itemCost           =   product.getCost();
        this.itemQuantity       =   1;
    }

    ItemModalWindow(JFrame parentFrame, Service service)
    {
        this.parentFrame        =   parentFrame;
        this.service            =   service;

        this.itemName           =   service.getName();
        this.itemCost           =   service.getCost();
        this.itemQuantity       =   1;
    }

    /*
     *  @param      void
     *
     *  @brief
     *  This method displays the dialog window for when a product item is the argument provided.
     *
     *  @return     Product     this.product
     */
    public Product displayProductDialog()
    {
        displayItemDetail();

        displayQuantityButton();
        itemPanel.add(itemQtyModal);

        displayBottomButtons();

        return this.product;
    }

    /*
     *  @param      void
     *
     *  @brief
     *  This method displays the dialog window for when a service item is the provided argument.
     *
     *  @return     Service     this.service
     */
    public Service displayServiceDialog()
    {
        displayItemDetail();

        itemPanel.add(itemQtyModal);

        displayBottomButtons();

        return this.service;
    }

    /*
     *  @param      void
     *
     *  @brief
     *  The method displays the item detail for the Item Modal Window.
     *
     *  @return     void
     */
    private void displayItemDetail()
    {
        itemInfo.add(new JLabel("Item Name: ", SwingConstants.CENTER));
        itemInfo.add(new JLabel(itemName, SwingConstants.CENTER));

        itemInfo.add(new JLabel("Cost: ", SwingConstants.CENTER));

        //  Required for double to String conversion.
        itemInfo.add(new JLabel(String.valueOf(itemCost), SwingConstants.CENTER));

        itemPanel.add(itemInfo);
    }

    /*
     *  @params
     *
     *  @brief
     *  The method displays the quantity button and label for a product.
     *
     *  @return
     */
    private void displayQuantityButton()
    {
        //  if item quantity is greater than 1, then it is a product.
        if (itemQuantity > 0)
        {
            JButton removeQty   =   new JButton("-");

            quantity            =   new JLabel(String.valueOf(itemQuantity), SwingConstants.CENTER);

            JButton addQty      =   new JButton("+");

            addQty.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    quantityCounter     =   incProductQuantity(quantityCounter);
                }
            });

            removeQty.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    quantityCounter     =   decProductQuantity(quantityCounter);
                }
            });

            itemQtyModal.add(removeQty);
            itemQtyModal.add(quantity);
            itemQtyModal.add(addQty);
        }

    }

    /*
     *  @param      void
     *
     *  @brief
     *  The method displays the cancel and confirm butto for the dialog window.
     *
     *  @return     void
     */
    private void displayBottomButtons()
    {
        //  Buttons for cancel and confirm item
        modalButton         =   new JPanel(new GridLayout(1, 2));
        JButton cancel      =   new JButton("Cancel");
        JButton confirm     =   new JButton("Confirm");

        modalButton.add(cancel);
        modalButton.add(confirm);

        itemWindow          =   new JDialog(parentFrame, itemName, true);
        itemWindow.addWindowListener(this);

        cancel.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                service     =   null;
                product     =   null;

                itemWindow.setVisible(false);
                itemWindow.dispose();
            }
        });

        confirm.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                setConfirmItem();

                itemWindow.setVisible(false);
                itemWindow.dispose();
            }
        });

        itemWindow.setLayout(new BorderLayout());
        itemWindow.setSize(300, 200);
        itemWindow.setLocationRelativeTo(null);
        itemWindow.setAlwaysOnTop(true);

        itemWindow.add(itemPanel, BorderLayout.CENTER);
        itemWindow.add(modalButton, BorderLayout.SOUTH);

        itemWindow.setVisible(true);
    }

    /*
     *  @params     int     productQuantity
     *
     *  @brief
     *  The method increase the current selected product quantity by 1
     *  Then it sets the quantity text label to the value.
     *
     *  @return     int     productQuantity
     */
    private int incProductQuantity(int productQuantity)
    {
        productQuantity++;
        quantity.setText(String.valueOf(productQuantity));

        return productQuantity;
    }

    /*
     *  @params     int     productQuantity
     *
     *  @brief
     *  The method decreases the current selected product quantity by 1
     *  but limits the product quantity to a value greater than 1
     *  Then it sets the quantity text label to the value.
     *
     *  @return     int     productQuantity
     */
    private int decProductQuantity(int productQuantity)
    {
        if (productQuantity > 1)
        {
            productQuantity--;
            quantity.setText(String.valueOf(productQuantity));

            return productQuantity;
        } else
        {
            return 1;
        }
    }

    /*
     *  @params     void
     *
     *  @brief
     *  This method sets the product quantity to the specified input.
     *  Then updates the product quantity.
     *
     *  @return     void
     */
    private void setConfirmItem()
    {
        this.product.updateQuantity(quantityCounter);
    }

    /*
     *  @brief
     *  Below method is the required method to be declared in order to implement
     *  the WindowListener class.
     *
     *  In this instance we are only using the window on close method to assign
     *  a null value to the product and service if not selected.
     */
    @Override
    public void windowOpened(WindowEvent e)
    {

    }

    @Override
    public void windowClosing(WindowEvent e)
    {
        product     =   null;
        service     =   null;
        itemWindow.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e)
    {

    }

    @Override
    public void windowIconified(WindowEvent e)
    {

    }

    @Override
    public void windowDeiconified(WindowEvent e)
    {

    }

    @Override
    public void windowActivated(WindowEvent e)
    {

    }

    @Override
    public void windowDeactivated(WindowEvent e)
    {

    }
}
