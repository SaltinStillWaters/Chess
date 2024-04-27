package Control;


import Model.Config;
import Model.Model_Main;
import Model.ChessBoard.ChessBoard;
import Model.Pieces.ChessPiece;
import View.ChessBoardPanel;
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
        
        if (!model.hasDestTile())
        {
            return;
        }

        ChessPiece currPiece = model.getCurrTileClicked().getTile().getChessPiece();
        ChessPiece destPiece = clickedTile.getTile().getChessPiece();
        
        String currCoordinate = model.getCurrTileClicked().getTile().getCoordinate();
        String destCoordinate = model.getDestTileClicked().getTile().getCoordinate();
        
        
            
        
        {
            boolean validMove = currPiece.checkMove(currCoordinate, destCoordinate);
            //check if piece is capturing its own side (white piece capturing another white piece)
                boolean canCapture = true;
                if (destPiece != null)
                {
                    canCapture = currPiece.canCapture(destPiece);
                }
                //if destPiece = null, then destTile has no piece; therefore no need to check if you can capture

            //Execute move
            if (validMove && canCapture)
            {
                TilePanel currTile = model.getCurrTileClicked();
                clickedTile.setPieceLabel(currTile.getPieceLabel());
                currTile.removePieceLabel();
                
                if (Config.AUTO_FLIP_BOARD)
                {
                    model.getMainFrameInstance().flipBoard();
                }
                
                model.switchTurn();
            }    
        }
        postMoveCleanUp();
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
