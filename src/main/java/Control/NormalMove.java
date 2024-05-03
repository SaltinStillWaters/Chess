package Control;

import Model.Model_Main;
import Model.Pieces.ChessPiece;
import Model.Pieces.Pawn;
import View.TilePanel;

public class NormalMove 
{
    public static void executeMove(Model_Main model, TilePanel clickedTile, String destCoordinate)
    {
        TilePanel currTile = model.getCurrTileClicked();
        clickedTile.setPieceLabel(currTile.getPieceLabel());
        currTile.removePieceLabel();
        
        ChessPiece piece = clickedTile.getPieceLabel().getChessPiece();

        if (piece.name.equals("Pawn") && ((Pawn) piece).checkPromotion(destCoordinate)) {
            clickedTile.promotePawn(piece.isWhite);
        } else {
            clickedTile.setPieceLabel(currTile.getPieceLabel());
            currTile.removePieceLabel();
        }

    }
    
    public static boolean checkValid(String currCoordinate, String destCoordinate, TilePanel clickedTile, ChessPiece currPiece)
    {
        ChessPiece destPiece = clickedTile.getTile().getChessPiece();
        boolean canCapture = checkCanCapture(currPiece, destPiece);
        boolean validMove = currPiece.checkMove(currCoordinate, destCoordinate);
        return validMove && canCapture;        
    }

    private static boolean checkCanCapture(ChessPiece currPiece, ChessPiece destPiece)
    {
        if (destPiece != null)
        {
            return currPiece.canCapture(destPiece);
        }

        return true;
    }
}
