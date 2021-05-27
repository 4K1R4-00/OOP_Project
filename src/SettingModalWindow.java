import javax.swing.JFrame;
import javax.swing.JDialog;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JList;
import javax.swing.DefaultListModel;

import java.util.ArrayList;

import javax.swing.JTextField;

import java.awt.GridLayout;

public class SettingModalWindow
{
    private JFrame parentFrame;

    private JPanel inputPanel   =   new JPanel(new GridLayout(0, 2));
    private JPanel listPanel    =   new JPanel();

    private ArrayList<Product> productList;
    private ArrayList<Service> serviceList;

    public SettingModalWindow(JFrame parentFrame)
    {
        this.parentFrame    =   parentFrame;
    }

    /*
     *  @param      void
     *
     *  @brief
     *  The method initialises the dialog window for the settings panel.
     *
     *  @return     void
     */
    public void displaySettingDialog()
    {
        JDialog settingWindow   =   new JDialog(this.parentFrame, "System settings", true);

        settingWindow.setSize(600, 600);
        settingWindow.setLocationRelativeTo(null);
        settingWindow.setAlwaysOnTop(true);

        settingWindow.setLayout(new GridLayout(0, 2));

        settingWindow.add(inputPanel);
        settingWindow.add(listPanel);

        settingWindow.setVisible(true);
    }

    /*
     *  @param      void
     *
     *  @brief
     *
     *  @return     Service
     */
    public ArrayList displayServiceSetting()
    {
        JLabel serviceNameLabel     =   new JLabel("Service Name:");
        JTextField serviceName      =   new JTextField();

        JLabel serviceCostLabel     =   new JLabel("Service Cost:");
        JTextField serviceCost      =   new JTextField();

        inputPanel.add(serviceNameLabel);
        inputPanel.add(serviceName);

        inputPanel.add(serviceCostLabel);
        inputPanel.add(serviceCost);

        JButton cancel  =   new JButton("Cancel");
        JButton save    =   new JButton("Save");

        cancel.addActionListener(new CancelButton());
        inputPanel.add(cancel);

        save.addActionListener(new SaveButton());
        inputPanel.add(save);

        displaySettingDialog();

        return this.serviceList;
    }

    /*
     *  @param      void
     *
     *  @brief
     *
     *
     *  @return     Product
     */
    public ArrayList displayProductSetting()
    {
        JLabel productNameLabel     =   new JLabel("Product Name:");
        JTextField productName      =   new JTextField();

        JLabel productCostLabel     =   new JLabel("Product Cost:");
        JTextField productCost      =   new JTextField();

        displaySettingDialog();

        return this.productList;
    }

    public void setServiceList(ArrayList<Service> service)
    {
        this.serviceList    =   service;
    }

    public void setProductList(ArrayList<Product> product)
    {
        this.productList    =   product;
    }

    /*
     *  @brief
     *
     */
    class CancelButton implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {

        }
    }

    /*
     *  @brief
     *
     */
    class SaveButton implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {

        }
    }
}
