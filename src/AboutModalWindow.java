import javax.swing.*;
import java.awt.*;

public class AboutModalWindow {
    private JFrame parentFrame;
    private JOptionPane dialog;
    private JPanel aboutPanel   =   new JPanel(new BorderLayout(1, 10));
    private JPanel aboutTitle   =   new JPanel(new GridLayout(0, 1));
    private JPanel studentInfo  =   new JPanel(new GridLayout(0, 2, 5, 5));


    AboutModalWindow(JFrame parentFrame){
        this.parentFrame    =   parentFrame;
    }

    public void displayInfo(){
        message("AKMAL AISY BIN RUDY", "52215220045");
        message("MOHD FAIZ BIN RADZI", "52215220049");
        message("DANSIH IMRAN BIN MOHD ARFI ARCHI", "52215220060");
        message("NUR ARIFA BINTI NOR AZLAN", "52215220050");

        aboutTitle.add(new JLabel("DEVELOPED BY OOP STUDENTS", SwingConstants.CENTER));
        aboutTitle.add(new JLabel("COURSE LECTURER: SUGUNESWARI A/P RAJA GOPAL", SwingConstants.CENTER));

        aboutPanel.add(aboutTitle, BorderLayout.NORTH);
        aboutPanel.add(studentInfo, BorderLayout.CENTER);

        dialog.showMessageDialog(parentFrame, aboutPanel, "About Salon System (Ver 1.0)", JOptionPane.PLAIN_MESSAGE);
    }

    public void message(String studentName, String studentID){
        studentInfo.add(new JLabel(studentName, SwingConstants.LEFT));
        studentInfo.add(new JLabel(studentID, SwingConstants.CENTER));
    }




}
