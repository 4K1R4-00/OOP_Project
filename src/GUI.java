import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import javax.swing.JScrollPane;
import javax.swing.JButton;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.ArrayList;

class GUI extends JFrame
{
    private GridLayout itemLayout       =   new GridLayout(0, 3, 5, 5);
    private BorderLayout frameLayout    =   new BorderLayout(5, 10);

    private JMenuItem infoButton        =   new JMenuItem("Information");
    private JMenuItem settingButton     =   new JMenuItem("Setting");

    private JPanel itemPanel            =   new JPanel(itemLayout);
    private JScrollPane itemScrollable  =   new JScrollPane(itemPanel,
                                                            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
                                                            );

    private JPanel checkoutPanel        =   new JPanel(new BorderLayout(5, 10));
    private JPanel checkoutItemHead     =   new JPanel();
    private JPanel checkoutItemPanel    =   new JPanel();

    private JLabel grandTotalLabel;

    private JScrollPane checkoutScroll  =   new JScrollPane(checkoutItemPanel,
                                                            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
                                                            );
    private JPanel checkoutTotal        =   new JPanel(new GridLayout(0, 2, 5, 5));

    // Product list and service list
    private ArrayList<Product> products;
    private ArrayList<Service> services;

    private ArrayList<Item> checkout    =   new ArrayList<Item>(5);
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
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1440, 720);

        //  Set the layout of the frame
        this.setLayout(frameLayout);

        //  Menu bar
        JMenuBar menuBar    =   new JMenuBar();
        JMenu systemMenu    =   new JMenu("System");

        settingButton.addActionListener(new MenuListener());
        infoButton.addActionListener(new MenuListener());

        systemMenu.add(infoButton);
        systemMenu.add(settingButton);
        menuBar.add(systemMenu);

        //  Set the menu bar
        this.setJMenuBar(menuBar);

        //  Create the list of item available buttons.
        createItemListing(this.products, this.services);

        this.add(itemScrollable, BorderLayout.CENTER);

        //  Set checkout
        checkoutItemHead.add(new JLabel("----------- Salon Checkout Panel ----------"));

        checkoutPanel.add(checkoutItemHead, BorderLayout.NORTH);

        checkoutItemPanel.setLayout(new BoxLayout(checkoutItemPanel, BoxLayout.Y_AXIS));

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
    private void displayCheckoutListing()
    {

        checkoutItemPanel.removeAll();

        for (int i = 0; i < checkout.size(); i++)
        {
            JPanel checkoutItem     =   new JPanel();
            checkoutItem.setLayout(new GridLayout(0, 3));

            checkoutItem.add(new JLabel(checkout.get(i).getName(), SwingConstants.LEFT));
            checkoutItem.add(new JLabel("x" + String.valueOf(checkout.get(i).getQuantity()), SwingConstants.CENTER));
            checkoutItem.add(new JLabel(String.valueOf(checkout.get(i).getCost()), SwingConstants.RIGHT));

            checkoutItemPanel.add(checkoutItem);
        }

        checkoutItemPanel.revalidate();

        checkoutItemPanel.repaint();
    }


    private void displayGrandTotal()
    {
        double checkoutTotal    =   0.0;

        for (int i = 0; i < checkout.size(); i++)
        {

            int itemQuantity    =   checkout.get(i).getQuantity();
            double costPerItem  =   checkout.get(i).getCost();

            double totalPerItem =   itemQuantity * costPerItem;

            checkoutTotal       +=  totalPerItem;
        }

        checkoutGrandTotal      =  checkoutTotal;

        grandTotalLabel.setText("RM " + String.valueOf(this.checkoutGrandTotal));
    }

    private void resetCheckoutList()
    {
        checkoutGrandTotal     =   0.0;

        checkout.clear();

        checkoutItemPanel.removeAll();
        checkoutItemPanel.revalidate();
        checkoutItemPanel.repaint();

        grandTotalLabel.setText("RM 0.0");
    }

    /*
     *  @brief
     *  The MenuListener class listens for events that occur in the menubar.
     */
    class MenuListener implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            if (ae.getSource() == infoButton)
                System.out.println("The infobutton has been pressed");
            else if (ae.getSource() == settingButton)
                System.out.println("The settingButton has been pressed");
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

            if (itemID < productMaxSize)
            {
                int productID   =   itemID;

                ItemModalWindow productDialog   =   new ItemModalWindow(products.get(productID));

            } else if (itemID >= productMaxSize)
            {
                int serviceID   =   itemID - productMaxSize;

                ItemModalWindow serviceDialog   =   new ItemModalWindow(services.get(serviceID));
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
            Receipt receipt     =   new Receipt(checkout);
            receipt.generateReceipt();
            resetCheckoutList();
        }
    }

    /*
     *  @brief
     *
     *  The ItemModalWindow is the dialog window that appears after a
     *  product or service is selected, in order to ensure the quantity and cost.
     *
     */
    class ItemModalWindow
    {
        private JDialog itemWindow;
        private JFrame itemFrame;
        private JPanel modalButton;
        private JLabel quantity;

        private int quantityCounter;

        private Service service;
        private Product product;

        ItemModalWindow(Product product)
        {
            this.product            =   product;

            //  A product has a minimum of 1 quantity
            this.quantityCounter    =   1;

            itemModalInit(this.product.getName(), this.product.getCost(), quantityCounter);
        }

        ItemModalWindow(Service service)
        {
            this.service            =   service;

            //  A service is not quantitative like a product, so it is set to 0.
            this.quantityCounter    =   0;

            itemModalInit(this.service.getName(), this.service.getCost(), quantityCounter);
        }

        /*
         *  @param  String  itemName
         *  @param  double  itemCost
         *  @param  int     itemQty
         *
         *  @brief
         *
         *  @return void
         */
        private void itemModalInit(String itemName, double itemCost, int itemQty)
        {
            //  Create a modelles frame for the itemModal with the item name as the title.
            itemFrame           =   new JFrame(itemName);

            JPanel itemPanel    =   new JPanel(new GridLayout(0, 1));
            JPanel itemInfo     =   new JPanel(new GridLayout(0, 2));
            JPanel itemQtyModal =   new JPanel(new GridLayout(0, 3));

            itemInfo.add(new JLabel("Item Name: ", SwingConstants.CENTER));
            itemInfo.add(new JLabel(itemName, SwingConstants.CENTER));

            itemInfo.add(new JLabel("Cost: ", SwingConstants.CENTER));

            //  Required for double to String conversion.
            itemInfo.add(new JLabel(String.valueOf(itemCost), SwingConstants.CENTER));

            //  if item quantity is greater than 1, then it is a product.
            if (itemQty > 0)
            {
                JButton removeQty   =   new JButton("-");

                quantity            =   new JLabel(String.valueOf(itemQty), SwingConstants.CENTER);

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

            itemPanel.add(itemInfo);
            itemPanel.add(itemQtyModal);


            //  Buttons for cancel and confirm item
            modalButton         =   new JPanel(new GridLayout(1, 2));
            JButton cancel      =   new JButton("Cancel");
            JButton confirm     =   new JButton("Confirm");

            modalButton.add(cancel);
            modalButton.add(confirm);

            cancel.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    itemFrame.setVisible(false);
                }
            });

            confirm.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    setConfirmItem();
                    itemFrame.setVisible(false);
                }
            });

            itemFrame.setLayout(new BorderLayout());
            itemFrame.setSize(300, 200);
            itemFrame.setLocationRelativeTo(null);
            itemFrame.setAlwaysOnTop(true);

            itemFrame.add(itemPanel, BorderLayout.CENTER);
            itemFrame.add(modalButton, BorderLayout.SOUTH);

            itemFrame.setVisible(true);

            itemWindow          =   new JDialog(itemFrame, itemName, true);
        }

        /*
         *  @params     int     productQuantity
         *
         *  @brief
         *
         *  @return     int     productQuantity
         */
        public int incProductQuantity(int productQuantity)
        {
            productQuantity++;
            quantity.setText(String.valueOf(productQuantity));

            return productQuantity;
        }

        /*
         *  @params     int     productQuantity
         *
         *  @brief
         *
         *  @return     int     productQuantity
         */
        public int decProductQuantity(int productQuantity)
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
         *  This method just compares the quantityCounter to 1, in order to check whether
         *  it is a product, or a service; because services is not quantitative.
         *
         *  It then assigned the quantity to the product if it is a product, and adds
         *  it to the checkout ArrayList of Item.
         *
         *  else it will add the service to the ArrayList of Item.
         *
         *  @return     void
         */
        public void setConfirmItem()
        {
            if (quantityCounter >= 1)
            {
                this.product.updateQuantity(quantityCounter);

                checkout.add(this.product);
            } else
            {
                checkout.add(this.service);
            }

            for (int i = 0; i < checkout.size(); i++)
            {
                System.out.println(checkout.get(i).getName());
                System.out.println(checkout.get(i).getCost());
                System.out.println(checkout.get(i).getQuantity());
            }

            displayCheckoutListing();
            displayGrandTotal();
        }
    }

}
