package Model.ChessBoard;

import java.util.ArrayList;


public class ChessBoard 
{
    private final ArrayList<ArrayList<Tile>> board;
    private static ChessBoard instance;

    
    public ChessBoard()
    {
        board = new ArrayList<>();
      
        //tile creation starts at 'A1', which is black
        boolean tileColor_IsWhite = false;
        
        for (int x = 0; x < 8; ++x) //collumns
        {
            ArrayList<Tile> colArray = new ArrayList<>();
            
            String col = Character.toString((char) ('A' + x));
            
            for (int y = 0; y < 8; ++y) //rows
            {
                String coordinate = col + Character.toString((char) ('1' + y));
                
                //at the start of the game, only the first and last 2 rows are occupied
                boolean isOccupied = y < 2 || y > 5;
                
                colArray.add(new Tile(null, isOccupied, coordinate, tileColor_IsWhite));

                //tile colors alternate. E.g.: 'A1' = black; 'A2' = white
                tileColor_IsWhite = !tileColor_IsWhite;   
            }
            /* this reverts the previous color alternation.
            *  Because top collumn has the same color as the next bottom collumn.
            *  E.g.: 'A8' = white; 'B1' = white
            */
            tileColor_IsWhite = !tileColor_IsWhite;
            
            
            board.add(colArray);
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
    
    
    public Tile getTile(int col, int row)
    {
        return board.get(col).get(row);
    }
}
