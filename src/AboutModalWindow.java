import javax.swing.*;
import java.awt.*;

public class AboutModalWindow {
    private JFrame parentFrame;
    private JOptionPane dialog;
    private JPanel aboutPanel   = new JPanel(new GridLayout(0,1,0,5));
    private String developer    = "";


    AboutModalWindow(JFrame parentFrame){
        this.parentFrame    =   parentFrame;
    }

    public void displayInfo(){
       // Message Content
        message("==== Developed By ====");
        message("Akmal");
        message("Faiz");
        message("Danish");
        message("Arifa");



        dialog.showMessageDialog(parentFrame, aboutPanel, "About POS System (Ver 1.0)", JOptionPane.PLAIN_MESSAGE);

    }

    public void message(String content){
        aboutPanel.add(new JLabel(content, SwingConstants.CENTER));

    }




}
