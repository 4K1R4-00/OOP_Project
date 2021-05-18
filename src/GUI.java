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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Color;

import java.util.ArrayList;

class GUI extends JFrame
{
    private GridLayout itemLayout       =   new GridLayout(0, 3, 5, 5);
    private BorderLayout frameLayout    =   new BorderLayout(5, 10);

    private JMenuItem infoButton        =   new JMenuItem("Information");
    private JMenuItem settingButton     =   new JMenuItem("Setting");

    private JPanel itemPanel            =   new JPanel(itemLayout);
    private JScrollPane scrollableItem  =   new JScrollPane(itemPanel,
                                                            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
                                                            );

    private JPanel checkoutPanel        =   new JPanel();

    // Product list and service list
    private ArrayList<Product> products;
    private ArrayList<Service> services;

    private ArrayList<Item> checkout;

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
        this.setSize(1080, 450);

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

        scrollableItem.setBackground(Color.red);
        this.add(scrollableItem, BorderLayout.CENTER);

        //  Set checkout
        checkoutPanel.add(new JLabel("Salon Checkout Panel"));
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
        int productMaxSize  =   products.size();

        for (int i = 0; i < productMaxSize; i++)
        {
            JButton itemButton      =   new JButton(products.get(i).getName());

            itemButton.setActionCommand(String.valueOf(i));
            itemButton.addActionListener(new ItemListener());

            itemPanel.add(itemButton);
        }

        //  The starting value of the iterator is after the size of products array.
        for (int i = productMaxSize; i < (services.size() + productMaxSize); i++)
        {
            JButton itemButton      =   new JButton(services.get(i - productMaxSize).getName());

            itemButton.setActionCommand(String.valueOf(i));
            itemButton.addActionListener(new ItemListener());

            itemPanel.add(itemButton);
        }
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
            int itemID   =   Integer.parseInt(ae.getActionCommand());
            int productMaxSize  =   products.size();

            if (itemID < productMaxSize)
            {
                int productID   =   itemID;
                ItemModalWindow productDialog   =   new ItemModalWindow(products.get(productID));
            }
            else if (itemID >= productMaxSize)
            {
                int serviceID   =   itemID - productMaxSize;

                ItemModalWindow serviceDialog   =   new ItemModalWindow(services.get(serviceID));
            }
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

        private Service service;
        private Product product;

        ItemModalWindow(Product product)
        {
            this.product        =   product;

            itemFrame           =   new JFrame();

            JPanel itemPanel    =   new JPanel(new GridLayout(0, 1));
            JPanel itemModal    =   new JPanel(new GridLayout(0, 2));

            itemModal.add(new JLabel("Item Name: ", SwingConstants.CENTER));
            itemModal.add(new JLabel(this.product.getName(), SwingConstants.CENTER));

            itemModal.add(new JLabel("Cost: ", SwingConstants.CENTER));

            //  Required for double to String conversion.
            String itemName     =   String.valueOf(this.product.getCost());
            itemModal.add(new JLabel(itemName, SwingConstants.CENTER));

            //  Quantity button
            JButton removeQty   =   new JButton("-");

            String itemQty      =   String.valueOf(this.product.getpQuantity());
            JLabel quantity     =   new JLabel(itemQty, SwingConstants.CENTER);

            JButton addQty      =   new JButton("+");

            JPanel itemQtyModal =   new JPanel(new GridLayout(0, 3));

            itemQtyModal.add(removeQty);
            itemQtyModal.add(quantity);
            itemQtyModal.add(addQty);

            itemPanel.add(itemModal);
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

            itemFrame.setLayout(new BorderLayout());
            itemFrame.setSize(300, 200);
            itemFrame.setLocationRelativeTo(null);
            itemFrame.setAlwaysOnTop(true);

            itemFrame.add(itemPanel, BorderLayout.CENTER);
            itemFrame.add(modalButton, BorderLayout.SOUTH);

            itemFrame.setVisible(true);

            itemWindow          =   new JDialog(itemFrame, this.product.getName(), true);

        }

        ItemModalWindow(Service service)
        {
            this.service        =   service;

            itemFrame           =   new JFrame();

            JPanel itemModal    =   new JPanel(new GridLayout(2, 3));

            itemModal.add(new JLabel("Item Name: "));
            itemModal.add(new JLabel(this.service.getName()));

            modalButton         =   new JPanel(new GridLayout(1, 2));
            JButton cancel      =   new JButton("Cancel");
            JButton confirm     =   new JButton("Confirm");

            itemFrame.setLayout(new BorderLayout());
            itemFrame.setSize(300, 200);
            itemFrame.setVisible(true);
            itemFrame.setLocationRelativeTo(null);

            itemFrame.add(itemModal, BorderLayout.CENTER);
            itemFrame.add(modalButton, BorderLayout.SOUTH);

            itemWindow          =   new JDialog(itemFrame, service.getName(), true);

            cancel.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    itemFrame.setVisible(false);
                }
            });
        }

    }

}
