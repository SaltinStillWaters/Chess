package Control;

import Model.ChessBoard.ChessBoard;
import Model.Pieces.ChessPiece;
import Model.Pieces.King;
import Model.Pieces.Rook;
import View.ChessBoardPanel;
import View.TilePanel;

public class Castling 
{
    private static boolean checkObstructionFree(String currCoordinate, String destCoordinate)
    {
        int colDiff = destCoordinate.charAt(0) - currCoordinate.charAt(0);
        int rookCol = colDiff > 0 ? 7 : 0;
        
        //row of King and Rook as they share the same row
        int row = '8' - currCoordinate.charAt(1);

        ChessBoard chessBoard = ChessBoard.getInstance();

        int direction = colDiff / Math.abs(colDiff);
        for (int kingCol = 4 + direction; kingCol != rookCol; kingCol += direction)
        {
            if (chessBoard.getTile(row, kingCol).getIsOccupied())
            {
                return false;
            }
        }

        return true;
    }

    public static void castle(TilePanel kingCurrTile, String currCoordinate, String destCoordinate)
    {
        int colDiff = destCoordinate.charAt(0) - currCoordinate.charAt(0);
        int rookCol = colDiff > 0 ? 7 : 0;
        
        //row of King and Rook as they share the same row
        int row = '8' - currCoordinate.charAt(1);
        
        ChessBoardPanel chessBoardPanel = ChessBoardPanel.getInstance();
        
        TilePanel rookCurrTile = chessBoardPanel.getTilePanel(row, rookCol);
        
        
        //dest
        int kingDestCol = colDiff > 0 ? currCoordinate.charAt(0) - 'A' + 2 : currCoordinate.charAt(0) - 'A' - 2;
        int rookDestCol = colDiff > 0 ? kingDestCol - 1 : kingDestCol + 1;

        TilePanel kingDestTile = chessBoardPanel.getTilePanel(row, kingDestCol);
        kingDestTile.setPieceLabel(kingCurrTile.getPieceLabel());
        kingCurrTile.removePieceLabel();

        TilePanel rookDestTile = chessBoardPanel.getTilePanel(row, rookDestCol);
        rookDestTile.setPieceLabel(rookCurrTile.getPieceLabel());
        rookCurrTile.removePieceLabel();


        King king = (King) kingDestTile.getPieceLabel().getChessPiece();
        king.setHasMoved();

        Rook rook = (Rook) rookDestTile.getPieceLabel().getChessPiece();
        rook.setHasMovedToTrue();
    }

    public static boolean checkValid(String currCoordinate, String destCoordinate, ChessPiece currPiece)
    {
        if (currPiece.name != "King")
        {
            return false;
        }

        King king = (King) currPiece;
        if (king.getHasMoved())
        {
            return false;
        }

        if (!checkValidDestTile(currCoordinate, destCoordinate))
        {
            return false;
        }

        Rook rook = getRook(currCoordinate, destCoordinate);
        if (rook == null)
        {
            return false;
        }

        if (rook.getHasMoved())
        {
            return false;
        }

        if (!checkObstructionFree(currCoordinate, destCoordinate))
        {
            return false;
        }

        return true;
    }

    private static boolean checkValidDestTile(String currCoordinate, String destCoordinate)
    {
        boolean validRow = true;
        int rowDiff = Math.abs(currCoordinate.charAt(1) - destCoordinate.charAt(1));        
        if (rowDiff > 0)    
        {
            //this means that the dest tile is not in the same row as the king
            validRow = false;
        }


        boolean validCol = false;
        int[] validCols = {1, 2, 6};
        for (int x = 0; x < validCols.length; ++x)
        {
            if (destCoordinate.charAt(0) - 'A' == validCols[x])
            {
                validCol = true;
            }
        }

        return (validCol && validRow);
    }

    private static Rook getRook(String currCoordinate, String destCoordinate)
    {
        int rookRow = '8' - currCoordinate.charAt(1);

        int colDiff = destCoordinate.charAt(0) - currCoordinate.charAt(0);
        int rookCol = colDiff > 0 ? 7 : 0;

        ChessPiece piece = ChessBoard.getInstance().getTile(rookRow, rookCol).getChessPiece();
        if (piece == null)
        {
            return null;
        }

        if (!piece.name.equals("Rook"))
        {
            return null;
        }

        return (Rook) piece;
    }
}
