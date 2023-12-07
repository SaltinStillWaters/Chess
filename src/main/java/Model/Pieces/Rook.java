package Model.Pieces;


import Model.ChessBoard.Tile;
import java.util.ArrayList;


public class Rook extends ChessPiece {

    public Rook()
    {
    }

    @Override
    boolean checkMove(Tile destTile) {
        int colDiff = this.currTileIndex.charAt(0) - destTile.coordinate.charAt(0);
        int rowDiff = this.currTileIndex.charAt(1) - destTile.coordinate.charAt(1);
        
        if(rowDiff == 0 && colDiff !=0){
            return true;
        } else if (colDiff == 0 && rowDiff !=0){
            return true;
        } else {
            return false;
        }
    }
    
}
