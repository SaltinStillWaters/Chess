package Model.ChessBoard;

import Model.Pieces.*;
import java.util.ArrayList;

/**
 * A Singleton that represents a Chessboard.
 * It holds information about every tile in an 8x8 board.
 */
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
                
                colArray.add(new Tile(null, false, coordinate, tileColor_IsWhite));

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
        
        this.initPieces();
    }
    
    private void initPieces()
    {
        //Pawns
            //White
            for (int col = 0; col < 8; ++col)
            {
                Tile tile = board.get(col).get(1);
                tile.setChessPiece(new Pawn(true));
            }
            
            //Black
            for (int col = 0; col < 8; ++col)
            {
                Tile tile = board.get(col).get(6);
                tile.setChessPiece(new Pawn(false));
            }
        
        //Rooks
            {
                //White
                board.get(0).get(0).setChessPiece(new Rook(true));
                board.get(7).get(0).setChessPiece(new Rook(true));
                
                //Black
                board.get(0).get(7).setChessPiece(new Rook(false));
                board.get(7).get(7).setChessPiece(new Rook(false));
            }
            
        //Knights
            {
                //White
                board.get(1).get(0).setChessPiece(new Knight(true));
                board.get(6).get(0).setChessPiece(new Knight(true));
                
                //Black
                board.get(1).get(7).setChessPiece(new Knight(false));
                board.get(6).get(7).setChessPiece(new Knight(false));
            }
            
        //Bishops
            {
                //White
                board.get(2).get(0).setChessPiece(new Bishop(true));
                board.get(5).get(0).setChessPiece(new Bishop(true));
                
                //Black
                board.get(2).get(7).setChessPiece(new Bishop(false));
                board.get(5).get(7).setChessPiece(new Bishop(false));
            }
        
        //Queens
            {
                //White
                board.get(3).get(0).setChessPiece(new Queen(true));
                
                //Black
                board.get(3).get(7).setChessPiece(new Queen(false));
            }
         
        //Kings
            {
                //White
                board.get(4).get(0).setChessPiece(new King(true));
                
                //Black
                board.get(4).get(7).setChessPiece(new King(false));
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
    
    /**
     * Returns a reference to a Tile, according to the col and row arguments
     * @param col
     * @param row
     * @return 
     */
    public Tile getTile(int col, int row)
    {
        return board.get(col).get(row);
    }
}
