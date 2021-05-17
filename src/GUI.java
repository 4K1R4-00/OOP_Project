import javax.swing.JFrame;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;

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
        this.setVisible(true);

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
    }

    private void createItemListing(ArrayList<Product> products, ArrayList<Service> services)
    {
        for (int i = 0; i < products.size(); i++)
        {
            JButton itemButton      =   new JButton(products.get(i).getName());

            itemButton.setActionCommand(String.valueOf(i));
            itemButton.addActionListener(new ItemListener());

            itemPanel.add(itemButton);
        }

        //  The starting value of the iterator is after the size of products array.
        for (int i = products.size(); i < (services.size() + products.size()); i++)
        {
            int tempPadding         =   products.size();
            JButton itemButton      =   new JButton(services.get(i - tempPadding).getName());

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
            int itemValue   =   Integer.parseInt(ae.getActionCommand());

            if (itemValue < products.size())
            {
                System.out.println("The item value of [" + itemValue + "] is a product.");
                ItemModalWindow productDialog   =   new ItemModalWindow(products.get(itemValue));
            }
            else if (itemValue >= products.size())
            {
                System.out.println("The item value of [" + itemValue + "] is a service.");

                int serviceID   =   itemValue - products.size();
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

        ItemModalWindow(Product product)
        {
            itemFrame           =   new JFrame();

            JButton exitButton  =   new JButton("Exit");
            JLabel itemLabel    =   new JLabel(product.getName());

            itemFrame.setLayout(new BorderLayout());
            itemFrame.setSize(300, 200);
            itemFrame.setVisible(true);

            itemFrame.add(itemLabel, BorderLayout.NORTH);
            itemFrame.add(exitButton, BorderLayout.CENTER);

            itemWindow          =   new JDialog(itemFrame, product.getName(), true);

            exitButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    itemFrame.setVisible(false);
                }
            });
        }

        ItemModalWindow(Service service)
        {
            itemFrame           =   new JFrame();

            JButton exitButton  =   new JButton("Exit");
            JLabel itemLabel    =   new JLabel(service.getName());

            itemFrame.setLayout(new BorderLayout());
            itemFrame.setSize(300, 200);
            itemFrame.setVisible(true);

            itemFrame.add(itemLabel, BorderLayout.NORTH);
            itemFrame.add(exitButton, BorderLayout.CENTER);

            itemWindow      =   new JDialog(itemFrame, service.getName(), true);

            exitButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent ae)
                {
                    itemFrame.setVisible(false);
                }
            });
        }
    }

}
