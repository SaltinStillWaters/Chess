package Model.Pieces;

import Model.ChessBoard.Tile;


public abstract class ChessPiece 
{
    public abstract boolean checkMove(String currCoordinate, String destCoordinate);
    
    public void move(String currCoordinate, String destCoordinate)
    {
        //code
    }
            
}
