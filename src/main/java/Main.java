import Model.Model_Main;
import View.MainFrame;
import javax.swing.SwingUtilities;



public class Main 
{ 
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> 
        {
            Model_Main.getInstance();
        });
    }
}   
