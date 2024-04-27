package Control;


import Model.Config;
import Model.Model_Main;
import Model.ChessBoard.ChessBoard;
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
            postMoveCleanUp();
            return;
        }
        
        setCurrOrDestTiles(model, clickedTile);
        markCurrTile(model, clickedTile);

        if (!model.hasDestTile())
        {
            return;
        }


        //check if valid move and execute move

        ChessPiece currPiece = model.getCurrTileClicked().getTile().getChessPiece();
        String currCoordinate = model.getCurrTileClicked().getTile().getCoordinate();
        String destCoordinate = model.getDestTileClicked().getTile().getCoordinate();
        
        boolean validMove = currPiece.checkMove(currCoordinate, destCoordinate);

        ChessPiece destPiece = clickedTile.getTile().getChessPiece();
        boolean canCapture = checkCanCapture(currPiece, destPiece);
        
        validMove = validMove && canCapture;        
        executeMove(validMove, model, clickedTile);

        postMoveCleanUp();
    }   

    private void markCurrTile(Model_Main model, TilePanel currTile)
    {
        if (!model.hasDestTile())
        {
            currTile.markAsSelected();
        }
    }
    private void executeMove(boolean validMove, Model_Main model, TilePanel clickedTile)
    {
        if (!validMove)
        {
            return;
        }

        TilePanel currTile = model.getCurrTileClicked();
        clickedTile.setPieceLabel(currTile.getPieceLabel());
        currTile.removePieceLabel();
        
        if (Config.AUTO_FLIP_BOARD)
        {
            model.getMainFrameInstance().flipBoard();
        }
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
    private void postMoveCleanUp()
    {
        Model_Main model = Model_Main.getInstance();
        
        if (model.hasCurrTile())
        {
            model.getCurrTileClicked().removeMarkAsSelected();
        }

        model.clearDestAndCurrTiles();
    }
    
    private int checkCastling(ChessPiece currPiece, ChessPiece destPiece, String currCoordinate, String destCoordinate)
    {
        if (destPiece != null || currPiece.name != "King" || currPiece.checkMove(currCoordinate, destCoordinate))
        {
            return 0;
        }

        King king = (King) currPiece;
        if (king.getHasMoved())
        {
            return 0;
        }
        
        ChessPiece possibleRook = null;
        ChessBoard board = ChessBoard.getInstance();
        int result = 0;
        if (king.isWhite) 
        {
            if (destCoordinate.equals("G1")) 
            {
                possibleRook = board.getTile("G1").getChessPiece();
                result = 2;
            } 
            else if (destCoordinate.equals("C1") || destCoordinate.equals("B1")) 
            {
                possibleRook = board.getTile("A1").getChessPiece();
                result = 1;
            }
        } 
        else 
        {
            if (destCoordinate.equals("G8")) 
            {
                possibleRook = board.getTile("G8").getChessPiece();
                result = 4;
            } 
            else if (destCoordinate.equals("C8") || destCoordinate.equals("B8")) 
            {
                possibleRook = board.getTile("A8").getChessPiece();
                result = 3;
            }
        }

        if (possibleRook == null || possibleRook.name != "Rook" || possibleRook.isWhite != king.isWhite)
        {
            return 0;
        }

        Rook rook = (Rook) possibleRook;
        if (rook.getHasMoved())
        {
            return 0;
        }

        return result;
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
