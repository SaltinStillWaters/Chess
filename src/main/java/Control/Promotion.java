package Control;

import Model.Pieces.ChessPiece;
import Model.Pieces.Pawn;
import Model.Pieces.Queen;
import View.PieceLabel;
import View.PromotionFrame;
import View.TilePanel;

public class Promotion {
  public static void promotePawn(boolean isWhite, TilePanel pawnCurrTile, TilePanel pawnDestTile, ChessPiece selectedPiece) {
    if (pawnCurrTile.getPieceLabel() != null) {
        pawnCurrTile.removePieceLabel();
    }

    PromotionFrame promotionFrame = new PromotionFrame(isWhite, pawnDestTile);
    promotionFrame.setVisible(true);

    pawnDestTile.setPieceLabel(new PieceLabel(selectedPiece));

    pawnDestTile.revalidate();
    pawnDestTile.repaint();
  }


  public static boolean checkPromotion(String destCoordinate, ChessPiece pawnPiece){

    if(pawnPiece instanceof Pawn){
      if(destCoordinate.charAt(1) == '8' && pawnPiece.isWhite) {
        return true;
      } else if (destCoordinate.charAt(1) == '1' && !pawnPiece.isWhite) {
        return true;
      } else {
        return false;
      }
      } else {
      return false;
      }
    }
}
