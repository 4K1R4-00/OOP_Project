import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

class GUI extends JFrame
{
    private GridLayout itemLayout       =   new GridLayout(0, 3, 5, 5);
    private BorderLayout frameLayout    =   new BorderLayout(5, 10);

    private JPanel itemPanel            =   new JPanel(itemLayout);
    private JScrollPane itemScrollable  =   new JScrollPane(itemPanel,
                                                            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
                                                            );

    private JPanel checkoutPanel        =   new JPanel(new BorderLayout(5, 10));
    private JPanel checkoutItemHead     =   new JPanel();

    private JLabel grandTotalLabel;

    private DefaultListModel<String> checkoutListing    =   new DefaultListModel<String>();
    private JList<String> checkoutListingDisplayed      =   new JList<String>();

    private JScrollPane checkoutScroll  =   new JScrollPane(checkoutListingDisplayed,
                                                            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
                                                            );

    private JPanel checkoutTotal        =   new JPanel(new GridLayout(0, 2, 5, 5));

    // Product list and service list
    private ArrayList<Product> products;
    private ArrayList<Service> services;

    private ArrayList<Product> productCheckout      =   new ArrayList<Product>(5);
    private ArrayList<Service> serviceCheckout      =   new ArrayList<Service>(5);

    private double checkoutGrandTotal   =   0.0;

    // Default constructor
    GUI() {}

    GUI(ArrayList<Product> products, ArrayList<Service> services)
    {
        this.products   =   products;
        this.services   =   services;
    }

    /*
     *  @param  void
     *
     *  @brief
     *  Starts the swing GUI application.
     *  Creates the window and assigns the proper layout to the frame.
     *
     *  @return void
     */
    public void start()
    {
        //  Default gui settings
        this.setTitle("Salon App");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1440, 720);
        this.setLocationRelativeTo(null);

        //  Set the layout of the frame
        this.setLayout(frameLayout);

        //  Menu bar
        JMenuBar menuBar    =   new JMenuBar();
        JMenu systemMenu    =   new JMenu("System");
        JMenu settingMenu   =   new JMenu("Setting");

        JMenuItem product   =   new JMenuItem("Product");
        JMenuItem service   =   new JMenuItem("Service");

        JMenuItem info      =   new JMenuItem("Statistics");

        info.addActionListener(new InfoMenuListener());

        systemMenu.add(info);

        settingMenu.add(product);
        settingMenu.add(service);

        menuBar.add(systemMenu);
        menuBar.add(settingMenu);

        //  Set the menu bar
        this.setJMenuBar(menuBar);

        //  Create the list of item available buttons.
        createItemListing(this.products, this.services);

        this.add(itemScrollable, BorderLayout.CENTER);

        //  Set checkout
        checkoutItemHead.add(new JLabel("----------- Salon Checkout Panel ----------"));
        checkoutPanel.add(checkoutItemHead, BorderLayout.NORTH);

        checkoutPanel.add(checkoutScroll, BorderLayout.CENTER);

        checkoutTotal.add(new JLabel("Grand Total: ", SwingConstants.CENTER));
        grandTotalLabel  =   new JLabel("RM 0.0", SwingConstants.CENTER);
        checkoutTotal.add(grandTotalLabel);

        JButton checkoutButton  =   new JButton("Checkout");
        checkoutButton.addActionListener(new CheckoutListener());

        checkoutTotal.add(checkoutButton);

        checkoutPanel.add(checkoutTotal, BorderLayout.SOUTH);

        this.add(checkoutPanel, BorderLayout.EAST);

        this.setVisible(true);
    }

    /*
     *  @params     Product[]   products
     *  @params     Service[]   services
     *
     *  @brief
     *  The method creates the buttons for each item, and assigns a value to the button.
     *  The button for products is based on the size of the array.
     *  While the button for services is based on the maximum size of the products array.
     *
     *  @return     void
     */
    private void createItemListing(ArrayList<Product> products, ArrayList<Service> services)
    {
        //  Remove all component buttons in item panel for regeneration.
        itemPanel.removeAll();

        int productMaxSize      =   products.size();

        for (int i = 0; i < productMaxSize; i++)
        {
            JButton itemButton  =   new JButton(products.get(i).getName());

            itemButton.setActionCommand(String.valueOf(i));
            itemButton.addActionListener(new ItemListener());

            itemPanel.add(itemButton);
        }

        //  The starting value of the iterator is after the size of products array.
        for (int i = productMaxSize; i < (services.size() + productMaxSize); i++)
        {
            JButton itemButton  =   new JButton(services.get(i - productMaxSize).getName());

            itemButton.setActionCommand(String.valueOf(i));
            itemButton.addActionListener(new ItemListener());

            itemPanel.add(itemButton);
        }

        //  revalidate the components
        itemPanel.revalidate();
        itemPanel.repaint();
    }

    /*
     *  @params     void
     *
     *  @brief
     *  The method removes all the componenets of the panel, before reloading them back in
     *  each item is based on the items in the checkout list.
     *
     *  After the items in the checkout list have been added to the checkout item panel.
     *
     *  the method revalidates, and redraws the entire checkout item panel
     *
     *  @return     void
     */
    private void getCheckoutListing()
    {
        checkoutListing.clear();

        for (int i = 0; i < productCheckout.size(); i++)
        {
            String productInformation   =   productCheckout.get(i).getName() + "     x" +
                                            productCheckout.get(i).getQuantity() + "   RM " +
                                            productCheckout.get(i).getCost();

            checkoutListing.addElement(productInformation);
        }

        for (int i = 0; i < serviceCheckout.size(); i++)
        {
            String serviceInformation   =   serviceCheckout.get(i).getName() + "     x" +
                                            serviceCheckout.get(i).getQuantity() + "   RM " +
                                            serviceCheckout.get(i).getCost();

            checkoutListing.addElement(serviceInformation);
        }

        //checkoutListingDisplayed.setModel(checkoutListing);
        checkoutListingDisplayed.setModel(checkoutListing);

        checkoutScroll.revalidate();
        checkoutScroll.repaint();
    }

    private void getGrandTotal()
    {
        double checkoutTotal    =   0.0;

        for (int i = 0; i < productCheckout.size(); i++)
        {
            int itemQuantity    =   productCheckout.get(i).getQuantity();
            double costPerItem  =   productCheckout.get(i).getCost();

            double totalPerItem =   itemQuantity * costPerItem;

            checkoutTotal       +=  totalPerItem;
        }

        for (int i = 0; i < serviceCheckout.size(); i++)
        {
            int itemQuantity    =   serviceCheckout.get(i).getQuantity();
            double costPerItem  =   serviceCheckout.get(i).getCost();

            double totalPerItem =   itemQuantity * costPerItem;

            checkoutTotal       +=  totalPerItem;
        }

        checkoutGrandTotal      =  checkoutTotal;

        grandTotalLabel.setText("RM " + String.valueOf(this.checkoutGrandTotal));
    }

    private void clearCheckoutList()
    {
        checkoutGrandTotal     =   0.0;

        productCheckout.clear();
        serviceCheckout.clear();
        checkoutListing.clear();

        checkoutScroll.revalidate();
        checkoutScroll.repaint();

        grandTotalLabel.setText("RM 0.0");
    }

    /*
     *  @brief
     *  The MenuListener class listens for events that occur in the menubar.
     *
     *  Instentiating the SettingModalWindow class
     */
    class SettingMenuListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            GUI parentFrame     =   new GUI();

            SettingModalWindow settingDialog    =   new SettingModalWindow(parentFrame);
            settingDialog.displayServiceSetting();
        }
    }

    /*
     *  @brief
     *  The MenuListener class listens for events that occur in the menubar.
     */
    class InfoMenuListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            System.out.println("The info button has been pressed");
        }
    }
    /*
     *  @brief
     *  The ItemListener listens to the events in the item menu.
     *
     *  It then compares the value assigned to the action event of the button click,
     *  with the value of the size of item based on category.
     *
     *  once the category has been identified, prompt for a dialog box of quantity,
     *  if the item is a product.
     */
    class ItemListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            //  Get the item identifier
            int itemID          =   Integer.parseInt(ae.getActionCommand());
            int productMaxSize  =   products.size();

            //  if the item ID is lesser than the max size of product arraylist.
            if (itemID < productMaxSize)
            {
                //  Get the product ID and product information
                int productID               =   itemID;
                Product selectedProduct     =   products.get(productID);

                //  Pass it to the product dialog model.
                GUI parentFrame                 =   new GUI();
                ItemModalWindow productDialog   =   new ItemModalWindow(parentFrame, selectedProduct);

                //  Once done, return the product object.
                Product item    =   productDialog.displayProductDialog();

                //  Check if the product information is empty, if not empty then add to the
                //  product checkout list.
                if (item !=  null)
                    productCheckout.add(item);

                //  Refresh the checkout list, using revalidate and repaint
                getCheckoutListing();

                //  Add to the grand total.
                getGrandTotal();

            //  if the itemID is greater than the max isze of the product arraylist, then its a service.
            } else if (itemID >= productMaxSize)
            {
                //  Get the service ID by subtracting the product list max size and the current item ID
                int serviceID               =   itemID - productMaxSize;
                Service selectedService     =   services.get(serviceID);

                //  Pass it to the service dialog model.
                GUI parentFrame                 =   new GUI();
                ItemModalWindow serviceDialog   =   new ItemModalWindow(parentFrame, selectedService);

                // Once done, return the service object.
                Service item    =   serviceDialog.displayServiceDialog();

                //  Check if the service information is empty, if not empty then add to the
                //  service checkout list.
                if (item != null)
                    serviceCheckout.add(item);

                //  Refresh checkout list, using revalidate and repaint.
                getCheckoutListing();

                //  Add to the grand total.
                getGrandTotal();
            }
        }
    }

    /*
     *  @brief
     *  The CheckoutListener listens to the input event of the checkout button.
     *
     *  Prints the checkoutListing receipt.
     */
    class CheckoutListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            Receipt receipt     =   new Receipt(productCheckout, serviceCheckout, checkoutGrandTotal);
            receipt.generateReceipt();
            clearCheckoutList();
        }
    }
}
