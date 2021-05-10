import javax.swing.JFrame;
import javax.swing.JPanel;
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
    // private JPanel itemButtonPanel      =   new JPanel(new BorderLayout(3, 5));
    // private JPanel itemSettingPanel     =   new JPanel(new GridLayout(1, 2, 3, 3));

    private JPanel checkoutPanel        =   new JPanel();

    GUI()
    {
        //  Default gui settings
        this.setTitle("Salon App");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1080, 450);
        this.setVisible(true);

        //  Set the layout of the frame
        setLayout(frameLayout);

        for (int i = 0; i < 15; i++)
        {
            JPanel itemButtonPanel  =   new JPanel(new BorderLayout(3, 5));
            JButton itemButton      =   new JButton("Item");
            itemButtonPanel.add(itemButton, BorderLayout.CENTER);

            JPanel itemSettingPanel =   new JPanel(new GridLayout(1, 2, 3, 3));
            JButton removeButton    =   new JButton("Remove");
            JButton editButton      =   new JButton("Edit");

            itemSettingPanel.add(removeButton);
            itemSettingPanel.add(editButton);
            itemButtonPanel.add(itemSettingPanel, BorderLayout.SOUTH);

            itemPanel.add(itemButtonPanel);
        }

        this.add(infoPanel, BorderLayout.NORTH);

        scrollableItem.setBackground(Color.red);
        this.add(scrollableItem, BorderLayout.WEST);

        checkoutPanel.setBackground(Color.green);
        this.add(checkoutPanel, BorderLayout.CENTER);

    }

    GUI(ArrayList<Product> products, ArrayList<Service> services)
    {

    }

    private void addButtonPanels()
    {

    }

    private void addItemButtons()
    {
        //  Loop through products
        for (int i = 0; i < 10; i++)
        {

        }

        //  Loop through services
    }
}
