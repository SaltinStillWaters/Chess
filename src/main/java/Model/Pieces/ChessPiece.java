package Model.Pieces;

import Model.ChessBoard.Tile;


public abstract class ChessPiece 
{
    abstract boolean checkMove(Tile destTile);
    
    public void move(String destCoordinate)
    {
        //code
    }
            
}
