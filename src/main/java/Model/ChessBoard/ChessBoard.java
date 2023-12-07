package Model.ChessBoard;

import java.util.ArrayList;


public class ChessBoard 
{
    private ArrayList<ArrayList<Tile>> board;
    private static ChessBoard instance;

    public ChessBoard()
    {
        board = new ArrayList<>();
        
        boolean tileColor_IsWhite = false;
        for (int x = 0; x < 8; ++x)
        {
            String col = Character.toString((char) ('A' + x));
            
            for (int y = 0; y < 8; ++y)
            {
                boolean isOccupied = true;
                
                if (y < 2 || y > 5) isOccupied = true;
                else                isOccupied = false;
                
                String coordinate = col + Character.toString((char) ('1' + y));
                
                Tile tile = new Tile(null, isOccupied, coordinate, tileColor_IsWhite);
                
                if (tileColor_IsWhite)  tileColor_IsWhite = false;
                else                    tileColor_IsWhite = true;
                    
            }
            
            if (tileColor_IsWhite)  tileColor_IsWhite = false;
            else                    tileColor_IsWhite = true;
        }
    }
    
    public static ChessBoard getInstance()
    {
        if (instance == null)
        {
            instance = new ChessBoard();
        }
        
        return instance;
    }
    
}
