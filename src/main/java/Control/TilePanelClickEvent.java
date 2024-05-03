package Control;


import Model.Model_Main;
import Model.Pieces.ChessPiece;
import View.TilePanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Model.Pieces.King;
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
        
        //tracker of changes in enPassantTile
        //if enPassantTile is unchaged, model.getValidEnPassantPawn must be set to null
        TilePanel enPassantTile = model.getValidEnPassantPawn();

        if (Castling.checkValid(currCoordinate, destCoordinate, currPiece))
        {
            Castling.castle(model.getCurrTileClicked(), currCoordinate, destCoordinate);
        }
        else if (NormalMove.checkValid(currCoordinate, destCoordinate, clickedTile, currPiece))
        {
            System.out.println("normal move");
            NormalMove.executeMove(model, clickedTile, destCoordinate);
        }
        else if (EnPassant.checkValid(currCoordinate, destCoordinate, currPiece, model))
        {
            System.out.println("en passant");
            EnPassant.enPassant(model, clickedTile, currCoordinate, destCoordinate);
        }
        else
        {
            //means invalid move
            postMoveCleanUp(model);
            return;
        }
        
        if (enPassantTile == model.getValidEnPassantPawn())
        {
            model.removeValidEnPassantPawn();
        }

        
        model.getMainFrameInstance().flipBoard();
        model.switchTurn();

        updateModel(model, clickedTile, destCoordinate, currCoordinate);
        postMoveCleanUp(model);
    }

    private void updateModel(Model_Main model, TilePanel clickedTile, String destCoordinate, String currCoordinate)
    {
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

        if (piece.name.equals("Pawn") && Math.abs(destCoordinate.charAt(1) - currCoordinate.charAt(1)) == 2)
        {
            model.setValidEnPassantPawn(clickedTile);
        }
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

    private void markCurrTile(Model_Main model, TilePanel currTile)
    {
        if (!model.hasDestTile())
        {
            currTile.markAsSelected();
        }
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
