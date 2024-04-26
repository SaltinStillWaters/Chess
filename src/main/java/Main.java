import Model.Model_Main;
import Model.ChessBoard.ChessBoard;
import View.MainFrame;
import View.TilePanel;

import java.util.ArrayList;

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
