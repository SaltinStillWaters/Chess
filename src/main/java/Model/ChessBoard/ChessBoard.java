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

    
    private ChessBoard()
    {
        board = new ArrayList<>();
      
        //tile creation starts at 'H8', which is black
        boolean tileColor_IsWhite = true;
        
        for (int x = 0; x < 8; ++x) 
        {
            ArrayList<Tile> rows = new ArrayList<>();
            
            String row = Character.toString((char) ('8' - x));
            
            for (int y = 0; y < 8; ++y)
            {
                String coordinate =  Character.toString((char) ('A' + y)) + row;
                
                rows.add(new Tile(null, false, coordinate, tileColor_IsWhite));

                //tile colors alternate. E.g.: 'A1' = black; 'A2' = white
                tileColor_IsWhite = !tileColor_IsWhite;   
            }
            /* this reverts the previous color alternation.
            *  Because top collumn has the same color as the next bottom collumn.
            *  E.g.: 'A8' = white; 'B1' = white
            */
            tileColor_IsWhite = !tileColor_IsWhite;
            
            
            board.add(rows);
        }
        
        this.initPieces();
    }
    
    public void printPieces()
    {
        for (int x = 0; x < 8; ++x)
        {
            for (int y = 0; y < 8; ++y)
            {
                System.out.print(board.get(x).get(y) + "    ");
            }
            System.out.println();
        }
        System.out.println();
    }
    private void initPieces()
    {
        //Pawns
            //White
            for (int col = 0; col < 8; ++col)
            {
                Tile tile = board.get(1).get(col);
                tile.setChessPiece(new Pawn(false));
            }
            
            //Black
            for (int col = 0; col < 8; ++col)
            {
                Tile tile = board.get(6).get(col);
                tile.setChessPiece(new Pawn(true));
            }
        
        //Rooks
            {
                //White
                board.get(0).get(0).setChessPiece(new Rook(false));
                board.get(0).get(7).setChessPiece(new Rook(false));
                
                //Black
                board.get(7).get(7).setChessPiece(new Rook(true));
                board.get(7).get(0).setChessPiece(new Rook(true));
            }
            
        //Knights
            {
                //White
                board.get(0).get(1).setChessPiece(new Knight(false));
                board.get(0).get(6).setChessPiece(new Knight(false));
                
                //Black
                board.get(7).get(1).setChessPiece(new Knight(true));
                board.get(7).get(6).setChessPiece(new Knight(true));
            }
            
        //Bishops
            {
                //White
                board.get(0).get(2).setChessPiece(new Bishop(false));
                board.get(0).get(5).setChessPiece(new Bishop(false));
                
                //Black
                board.get(7).get(2).setChessPiece(new Bishop(true));
                board.get(7).get(5).setChessPiece(new Bishop(true));
            }
        
        //Queens
            {
                //White
                board.get(0).get(3).setChessPiece(new Queen(false));
                
                //Black
                board.get(7).get(3).setChessPiece(new Queen(true));
            }
         
        //Kings
            {
                //White
                board.get(0).get(4).setChessPiece(new King(false));
                
                //Black
                board.get(7).get(4).setChessPiece(new King(true));
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
     * @param col Tile column (A-H)
     * @param row Tile row (1-8)
     * @return Returns tile of column 'col' and row 'row'
     */
    public Tile getTile(int row, int col)
    {
        return board.get(row).get(col);
    }
    
    public Tile getTile(String coordinate)
    {
        return getTile(coordinate.charAt(1) - '1', coordinate.charAt(0) - 'A');
    }
}