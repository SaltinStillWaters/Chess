import Model.Pieces.Knight;
import Model.Pieces.Pawn;
import View.MainFrame;
import javax.swing.SwingUtilities;



public class Main 
{ 
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> 
        {
            new MainFrame();
            
            Pawn pawn = new Pawn();
            
            System.out.println(pawn.checkMove("F2", "F4"));
        });
    }
}   
