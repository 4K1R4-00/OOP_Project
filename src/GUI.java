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

        /*
        this.infoPanel.setBackground(Color.red);

        this.itemPanel.setBackground(Color.green);
        this.itemPanel.setLayout(itemLayout);

        this.buttonItemPanel.add(new JPanel(itemButtonPanel));
        this.buttonItemPanel.add();

        this.itemPanel.add(buttonItemPanel);

        this.checkoutPanel.setBackground(Color.blue);

        this.add(infoPanel, BorderLayout.NORTH);
        this.add(itemPanel, BorderLayout.CENTER);
        this.add(checkoutPanel, BorderLayout.EAST);
        */
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

    public static void main(String[] args)
    {
        JFrame gui      =   new GUI();
        gui.setTitle("GUI test");
        gui.setLocationRelativeTo(null);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(1080, 500);
        gui.setVisible(true);
    }
}
