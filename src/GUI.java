import javax.swing.JFrame;
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

    // Default constructor
    GUI() {}

    GUI(ArrayList<Product> products, ArrayList<Service> services)
    {

        this.products   =   products;
        this.services   =   services;

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

        checkoutPanel.add(new JLabel("Salon Checkout Panel"));
        checkoutPanel.setBackground(Color.green);
        this.add(checkoutPanel, BorderLayout.EAST);
    }

    private void createItemListing(ArrayList<Product> products, ArrayList<Service> services)
    {
        for (int i = 0; i < products.size(); i++)
        {
            JButton itemButton      =   new JButton(products.get(i).getName());

            itemButton.setActionCommand(String.valueOf(i));

            itemPanel.add(itemButton);
        }

        //  The starting value of the iterator is after the size of products array.
        for (int i = products.size(); i < (services.size() + products.size()); i++)
        {
            int tempPadding         =   products.size();
            JButton itemButton      =   new JButton(services.get(i - tempPadding).getName());

            itemButton.setActionCommand(String.valueOf(i));

            itemPanel.add(itemButton);
        }
    }

    class MenuListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == infoButton)
                System.out.println("The infobutton has been pressed");
            else if (e.getSource() == settingButton)
                System.out.println("The settingButton has been pressed");
        }
    }

    class SettingFrame extends JFrame
    {
        SettingFrame()
        {
        }
    }
}
