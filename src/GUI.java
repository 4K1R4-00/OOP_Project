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
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;
import java.util.Arrays;

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

    //  checkoutListing stores the temporary checkout list to pass onto the JList display
    private DefaultListModel<String> checkoutListing    =   new DefaultListModel<String>();
    private JList<String> checkoutListingDisplayed      =   new JList<String>(checkoutListing);

    private JScrollPane checkoutScroll  =   new JScrollPane(checkoutListingDisplayed,
                                                            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
                                                            );

    private JPanel checkoutTotal        =   new JPanel(new GridLayout(0, 2, 5, 5));

    // Product list and service list
    private ArrayList<Product> products;
    private ArrayList<Service> services;

    //  List to hold the product and services. Very in-efficient but I have no
    //  Knowledge on how to make it more efficient as of now O(n^2).
    private ArrayList<Product> productCheckout      =   new ArrayList<Product>(5);
    private ArrayList<Service> serviceCheckout      =   new ArrayList<Service>(5);

    //  Globally save the checkout grand total per customer in order to
    //  Easily pass the value on.
    private double checkoutGrandTotal;

    private double totalDailySales;

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
        JMenuBar menuBar        =   new JMenuBar();
        JMenu systemMenu        =   new JMenu("System");
        JMenu settingMenu       =   new JMenu("Setting");

        JMenuItem product       =   new JMenuItem("Product");
        JMenuItem service       =   new JMenuItem("Service");

        JMenuItem about         =   new JMenuItem("About");
        JMenuItem statistics    =   new JMenuItem("Daily Earnings");

        about.addActionListener(new InfoMenuListener());
        systemMenu.add(about);

        //  in-line action listener implementation for statistic menu item
        statistics.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                GUI parentFrame     =   new GUI();

                JPanel statsPanel   =   new JPanel(new BorderLayout(5, 5));
                statsPanel.add(new JLabel("System Statistics"), BorderLayout.NORTH);

                JPanel formatPanel  =   new JPanel(new GridLayout(0, 2, 4, 4));
                formatPanel.add(new JLabel("Total Daily Earnings:"));
                formatPanel.add(new JLabel(String.valueOf(totalDailySales), SwingConstants.CENTER));

                statsPanel.add(formatPanel, BorderLayout.CENTER);

                //  Generate the message using this main window as a parent and apply the stats panel
                JOptionPane.showMessageDialog(parentFrame, statsPanel, "Stored Earnings", JOptionPane.PLAIN_MESSAGE);
            }
        });

        systemMenu.add(statistics);
        menuBar.add(systemMenu);

        /*
        product.addActionListener(new ProductSettingMenuListener());
        settingMenu.add(product);

        service.addActionListener(new ServiceSettingMenuListener());
        settingMenu.add(service);

        menuBar.add(settingMenu);
        */

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
     *  The drawing method used might be in-efficient due to O(n^2) but due to inexperience
     *  There is no other solution that can be applied.
     *
     *  @return     void
     */
    private void createItemListing(ArrayList<Product> products, ArrayList<Service> services)
    {
        //  Remove all component buttons in item panel for regeneration.
        itemPanel.removeAll();

        //  Assigns the maximum size of the product array.
        int productMaxSize      =   products.size();

        //  Loop over the product listing first and apply to the item panel based on
        //  the product array max size.
        for (int i = 0; i < productMaxSize; i++)
        {
            //  Create a button with the product name.
            JButton itemButton  =   new JButton(products.get(i).getName());

            //  Assign the value of the item array index to the button in order to
            //  easily differenciate the difference of products per button.
            itemButton.setActionCommand(String.valueOf(i));
            itemButton.addActionListener(new ItemListener());

            //  Add the button to the itemPanel
            itemPanel.add(itemButton);
        }

        //  Loop over the service array but start with the value after the product array max
        //  size.
        //
        //  Formula; Pmx <= x < (Smx + Pmx)
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
        //  This is to avoid readding ontop of existing components
        checkoutListing.clear();

        //  Add the productCheckout list to the single checkout list first
        for (int i = 0; i < productCheckout.size(); i++)
        {
            //  Convert the information to a writable string
            String productInformation   =   productCheckout.get(i).getName() + "     x" +
                                            productCheckout.get(i).getQuantity() + "   RM " +
                                            productCheckout.get(i).getCost();

            //  Add the String information to the checkout list
            checkoutListing.addElement(productInformation);
        }

        //  Then add the service checkout list to the single checkout list
        for (int i = 0; i < serviceCheckout.size(); i++)
        {
            //  Convert the infomration to a writable String.
            String serviceInformation   =   serviceCheckout.get(i).getName() + "     x" +
                                            serviceCheckout.get(i).getQuantity() + "   RM " +
                                            serviceCheckout.get(i).getCost();

            //  Add the String information to the checkout list.
            checkoutListing.addElement(serviceInformation);
        }

        //  revalidate the readded components
        checkoutScroll.revalidate();
        checkoutScroll.repaint();
    }

    /*
     *  @param      void
     *
     *  @brief
     *  Get the grand total of both products and services by iterating through the values of
     *  the stored items and get the total before storing them in the global grandtotal
     *
     *  @return     void
     */
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

    /*
     *  @param      void
     *
     *  @brief
     *  Clears the checkout list and all displayed item in the checkout scroll panel.
     *
     *  @return     void
     */
    private void clearCheckoutList()
    {
        //  Set current grand total amount to 0.0
        checkoutGrandTotal     =   0.0;

        //  Clear all the arrays that are releated to checkingout.
        productCheckout.clear();
        serviceCheckout.clear();
        checkoutListing.clear();

        checkoutScroll.revalidate();
        checkoutScroll.repaint();

        //  Set the grand total label to 0.0
        grandTotalLabel.setText("RM 0.0");
    }

    /*
     *  @brief
     *  The ProductSettingMenuListener class listens for events that occur in the menubar.
     *
     *  Instentiating the SettingModalWindow class
     */
    class ProductSettingMenuListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            GUI parentFrame     =   new GUI();

            SettingModalWindow settingDialog    =   new SettingModalWindow(parentFrame);
            settingDialog.setProductList(products);
        }
    }

    /*
     *  @brief
     *  The ServiceSettingMenuListener class listens for events that occur in the menubar.
     *
     *  Instentiating the SettingModalWindow class
     */
    class ServiceSettingMenuListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            GUI parentFrame     =   new GUI();

            SettingModalWindow serviceSetting   =   new SettingModalWindow(parentFrame);
            serviceSetting.setServiceList(services);
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
            GUI parentFrame =   new GUI();
            AboutModalWindow aboutDialog    =   new AboutModalWindow(parentFrame);
            aboutDialog.displayInfo();
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

            //  Generate the receipts
            receipt.generateReceipt();

            //  Append the current grand total to the daily sales
            totalDailySales     +=  checkoutGrandTotal;

            //  Clear the checkout list
            clearCheckoutList();
        }
    }
}
