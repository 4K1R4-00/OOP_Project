import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.Color;

import java.util.ArrayList;

class GUI extends JFrame
{
    private GridLayout itemLayout       =   new GridLayout(0, 3, 5, 5);
    private BorderLayout frameLayout    =   new BorderLayout(5, 10);

    private JPanel infoPanel            =   new JPanel();

    private JPanel itemPanel            =   new JPanel(itemLayout);
    private JScrollPane scrollableItem  =   new JScrollPane(itemPanel,
                                                            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                                                            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
                                                            );

    private JPanel checkoutPanel        =   new JPanel();

    // Product list and service list
    private ArrayList<Product> products;
    private ArrayList<Service> services;

    GUI()
    {
        //  Default gui settings
        this.setTitle("Salon App");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1080, 450);
        this.setVisible(true);

        //  Set the layout of the frame
        this.setLayout(frameLayout);

        for (int i = 0; i < 15; i++)
        {
            JButton itemButton      =   new JButton("Item");

            itemPanel.add(itemButton);
        }

        infoPanel.add(new JButton("Info"));
        infoPanel.add(new JButton("Setting"));
        this.add(infoPanel, BorderLayout.NORTH);

        scrollableItem.setBackground(Color.red);
        this.add(scrollableItem, BorderLayout.CENTER);

        checkoutPanel.add(new JLabel("Salon Checkout Panel"));
        checkoutPanel.setBackground(Color.green);
        this.add(checkoutPanel, BorderLayout.EAST);
    }

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

        createItemListing(this.products, this.services);

        infoPanel.add(new JButton("Info"));
        infoPanel.add(new JButton("Setting"));
        this.add(infoPanel, BorderLayout.NORTH);

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


    private void addItemButtons()
    {

    }
}
