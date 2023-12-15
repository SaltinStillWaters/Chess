package Control;


import Model.Model_Main;
import Model.Pieces.ChessPiece;
import View.TilePanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


/**
 * Handles TilePanel click events where the user performs moves
 * @author Salti
 */
public class TilePanelClickEvent extends MouseAdapter implements MouseListener
{

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        Model_Main model = Model_Main.getInstance();
        TilePanel clickedTile = (TilePanel) e.getSource();
        

        //set current and dest tiles and check for invalid        
        boolean validTile = true;
        
        if (!model.hasCurrTile())
        {
            if (clickedTile.isEmpty())  
            {
                validTile = false;
            }
            else                        
            {
                //check for correct side's turn
                boolean currPieceIsWhite = clickedTile.getPieceLabel().getChessPiece().isWhite;
                
                if (currPieceIsWhite == model.getIsWhiteToTurn())
                {
                    model.setCurrTileClicked(clickedTile);
                    clickedTile.markAsSelected();
                }
                else
                {
                    validTile = false;
                }
            }
        }
        else 
        {
            if (model.getCurrTileClicked() == clickedTile)  
            {
                validTile = false;
            }
            else                                            
            {
                model.setDestTileClicked(clickedTile);
            }
        }
        
        
        //possible early return
        if (!validTile)
        {
            postMoveCleanUp();
            return;
        }
        
        
            
        if (model.hasDestTile())
        {
            ChessPiece currPiece = model.getCurrTileClicked().getTile().getChessPiece();
            ChessPiece destPiece = clickedTile.getTile().getChessPiece();
            
            //check if move is valid
                String currCoordinate = model.getCurrTileClicked().getTile().getCoordinate();
                String destCoordinate = model.getDestTileClicked().getTile().getCoordinate();

                boolean validMove = currPiece.checkMove(currCoordinate, destCoordinate);
            
            //check if piece is capturing its own side (white piece capturing another white piece)
                boolean canCapture = true;
                if (destPiece != null)
                {
                    canCapture = currPiece.canCapture(destPiece);
                }
            
            //Execute move
            if (validMove && canCapture)
            {
                TilePanel currTile = model.getCurrTileClicked();
                clickedTile.setPieceLabel(currTile.getPieceLabel());
                currTile.removePieceLabel();
                
                model.getMainFrameInstance().flipBoard();
                model.switchTurn();
            }
            
            postMoveCleanUp();
        }
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
    
    
    @Override
    public void mousePressed(MouseEvent e) 
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
