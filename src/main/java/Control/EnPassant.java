package Control;

import Model.Model_Main;
import Model.Pieces.ChessPiece;
import Model.Pieces.Pawn;
import View.ChessBoardPanel;
import View.TilePanel;

public class EnPassant
{   
    public static void enPassant(Model_Main model, TilePanel destTile, String currCoordinate, String destCoordinate)
    {
        TilePanel currTile = model.getCurrTileClicked();
        destTile.setPieceLabel(currTile.getPieceLabel());
        currTile.removePieceLabel();

        String opponentPawnCoordinate = "" + destCoordinate.charAt(0) + currCoordinate.charAt(1);

        TilePanel opponentPawnTile = ChessBoardPanel.getInstance().getTilePanel(opponentPawnCoordinate);
        opponentPawnTile.removePieceLabel();
    }

    private static boolean checkAttempt(String currTile, String destTile, ChessPiece chessPiece)
    {
        if (! (chessPiece instanceof Pawn))
        {
            return false;
        }

        int colDiff = Math.abs(destTile.charAt(0) - currTile.charAt(0));
        if (colDiff != 1)
        {
            return false;
        }

        int direction = chessPiece.isWhite ? 1 : -1;

        int rowDiff = destTile.charAt(1) - currTile.charAt(1);

        if (Math.abs(rowDiff) != 1)
        {
            return false;
        }

        if (rowDiff > 0 != direction > 0)
        {
            return false;
        }

        return true;
    }

    public static boolean checkValid(String currTile, String destTile, ChessPiece chessPiece, Model_Main model)  
    {
        if (!checkAttempt(currTile, destTile, chessPiece))
        {
            return false;
        }
        TilePanel pawnToEatTile = model.getValidEnPassantPawn();
        if (pawnToEatTile == null)
        {
            return false;
        }
        String pawnToEatCoordinate = pawnToEatTile.getTile().coordinate;
        if (pawnToEatCoordinate.charAt(0) != destTile.charAt(0))
        {
            return false;
        }
        return true;
    }

   
}
