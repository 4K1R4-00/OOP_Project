import javax.swing.*;

public class AboutModalWindow {
    private JFrame parentFrame;
    private String developer = "";



    AboutModalWindow(JFrame parentFrame){
        this.parentFrame    =   parentFrame;
    }

    public void displayInfo(){
        setDeveloper("Akmal");
        setDeveloper("Faiz");
        setDeveloper("Danish");
        setDeveloper("Arifa");

        JOptionPane.showMessageDialog(parentFrame,"Developer of P.O.S Salon: \n" + this.developer, "About (Ver 1.0)", JOptionPane.PLAIN_MESSAGE);
    }

    public void setDeveloper(String name){
        this.developer  +=  "" + name + "\n";

    }




}
