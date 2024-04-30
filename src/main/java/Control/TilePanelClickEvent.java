package Control;


import Model.Model_Main;
import Model.Pieces.ChessPiece;
import View.TilePanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Model.Pieces.King;
import Model.Pieces.Pawn;
import Model.Pieces.Queen;
import Model.Pieces.Rook;

/**
 * Handles TilePanel click events where the user performs moves
 * @author Salti
 */
public class TilePanelClickEvent extends MouseAdapter
{

    @Override
    public void mousePressed(MouseEvent e) 
    {
        Model_Main model = Model_Main.getInstance();
        TilePanel clickedTile = (TilePanel) e.getSource();
        
        boolean validTile = checkValidTileClicked(model, clickedTile);     

        if (!validTile)
        {
            postMoveCleanUp(model);
            return;
        }
        
        setCurrOrDestTiles(model, clickedTile);
        markCurrTile(model, clickedTile);

        if (!model.hasDestTile())
        {
            //return because no move is supposed to be executed yet
            return;
        }


        //validate move and execute it
        ChessPiece currPiece = model.getCurrTileClicked().getTile().getChessPiece();
        String currCoordinate = model.getCurrTileClicked().getTile().getCoordinate();
        String destCoordinate = model.getDestTileClicked().getTile().getCoordinate();
        
        if (Castling.checkValidCastle(currCoordinate, destCoordinate, currPiece))
        {
            Castling.castle(model.getCurrTileClicked(), currCoordinate, destCoordinate);
            
            model.getMainFrameInstance().flipBoard();
            model.switchTurn();
        }
        else
        {    
            boolean validMove = currPiece.checkMove(currCoordinate, destCoordinate);
                
            ChessPiece destPiece = clickedTile.getTile().getChessPiece();
            boolean canCapture = checkCanCapture(currPiece, destPiece);
            
            validMove = validMove && canCapture;        
            executeMove(validMove, model, clickedTile , destCoordinate);
        }

        postMoveCleanUp(model);
    }

    private void markCurrTile(Model_Main model, TilePanel currTile)
    {
        if (!model.hasDestTile())
        {
            currTile.markAsSelected();
        }
    }

    private void executeMove(boolean validMove, Model_Main model, TilePanel clickedTile, String destCoordinate)
    {
        if (!validMove)
        {
            return;
        }

        
        TilePanel currTile = model.getCurrTileClicked();
        clickedTile.setPieceLabel(currTile.getPieceLabel());
        currTile.removePieceLabel();
        
        ChessPiece piece = clickedTile.getPieceLabel().getChessPiece();

        if (piece.name.equals("King"))
        {
            King king = (King) piece;
            king.setHasMoved();
        }
        else if (piece.name.equals("Rook"))
        {
            Rook rook = (Rook) piece;
            rook.setHasMovedToTrue();
        }

        if (piece.name.equals("Pawn") && ((Pawn) piece).checkPromotion(destCoordinate)) {
            clickedTile.promotePawn(piece.isWhite);
        } else {
            clickedTile.setPieceLabel(currTile.getPieceLabel());
            currTile.removePieceLabel();
        }

        model.getMainFrameInstance().flipBoard();
        model.switchTurn();
    }

    private boolean checkCanCapture(ChessPiece currPiece, ChessPiece destPiece)
    {
        if (destPiece != null)
        {
            return currPiece.canCapture(destPiece);
        }

        return true;
    }
    private void setCurrOrDestTiles(Model_Main model, TilePanel clickedTile)
    {
        if (!model.hasCurrTile())
        {
            model.setCurrTileClicked(clickedTile);
        }
        else
        {
            model.setDestTileClicked(clickedTile);
        }
    }

    private boolean checkValidTileClicked(Model_Main model, TilePanel clickedTile)
    {
        if (!model.hasCurrTile() && clickedTile.isEmpty())
        {
            return false;
        }
        else if (!model.hasCurrTile() && clickedTile.getPieceLabel().getChessPiece().isWhite != model.getIsWhiteToTurn())                       
        {
            return false;
        }
        else if (model.getCurrTileClicked() == clickedTile)
        {
            return false;
        }

        return true;
    }

    /**
     * Sets the necessary state of the Model_Main singleton to its default value.
     * It also clears the Mark on the 'currTileClicked'
     */
    private void postMoveCleanUp(Model_Main model)
    {
        if (model.hasCurrTile())
        {
            model.getCurrTileClicked().removeMarkAsSelected();
        }

        model.clearDestAndCurrTiles();
    }
    
    
    @Override
    public void mouseClicked(MouseEvent e) 
    {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        
        
    }
    
}
