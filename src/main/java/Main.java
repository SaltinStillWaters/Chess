import Model.Model_Main;
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
