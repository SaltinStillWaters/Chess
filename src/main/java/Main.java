import View.MainFrame;
import javax.swing.SwingUtilities;



public class Main 
{ 
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> 
        {
            new MainFrame();
        });
    }
}   
