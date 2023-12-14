
package Control;

import Model.Model_Main;
import View.PieceLabel;
import View.TilePanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ClickEvent extends MouseAdapter implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();

        if (source instanceof TilePanel) {
            TilePanel clickedTilePanel = (TilePanel) source;
            Model_Main tilesingleton = Model_Main.getInstance();
            
            
            //check if the currTile of the singleton is not null
            if(tilesingleton.getCurrTileClicked() == null){
                
                tilesingleton.setCurrTileClicked(clickedTilePanel);
                boolean turn = tilesingleton.getTurn();
                boolean color = tilesingleton.getCurrTileClicked().getTile().getChessPiece().isWhite;
                System.out.println("Current Tile set");
                
                
                //if the current tile does not have a piece the action or if the color of the piece in not the same as the turn in the singleton the action is cancelled
                if(tilesingleton.getCurrTileClicked().getPieceLabel() == null || color != turn){
                   tilesingleton.setCurrTileClicked(null);
                   System.out.println("Action Cancelled");
                }
                
                
            } else{
                //Set the Tile Panel as destination if there is a current tile in the singleton
                tilesingleton.setDestTileClicked(clickedTilePanel);
                System.out.println("Destination Tile set");
            }
            
            
            
            //checks if there is a destination and destination is not the current tile
            if (tilesingleton.getDestTileClicked()!= null && tilesingleton.getDestTileClicked() != tilesingleton.getCurrTileClicked()){
                // Get the PieceLabel from the current TilePanel
                PieceLabel clickedPiece = tilesingleton.getCurrTileClicked().getPieceLabel();
                boolean validMove = tilesingleton.getCurrTileClicked().getTile().getChessPiece().checkMove(tilesingleton.getCurrTileClicked().getTile().getCoordinate(), tilesingleton.getDestTileClicked().getTile().getCoordinate());
                if(clickedPiece != null && validMove == true ){
                   tilesingleton.getCurrTileClicked().removePieceLabel();
                   tilesingleton.getDestTileClicked().setPieceLabel(clickedPiece);
                   // Reset the singleton's current and destination tiles after the move
                   System.out.println("Piece moved");
                   tilesingleton.setTurn(!tilesingleton.getCurrTileClicked().getTile().getChessPiece().isWhite); //change turn
                   tilesingleton.setCurrTileClicked(null);
                   tilesingleton.setDestTileClicked(null);
                } else {
                   tilesingleton.setCurrTileClicked(null);
                   tilesingleton.setDestTileClicked(null);
                    System.out.println("Invalid move (Move not recognized)");
                }
            } else if (tilesingleton.getDestTileClicked() == tilesingleton.getCurrTileClicked()){
                tilesingleton.setCurrTileClicked(null);
                tilesingleton.setDestTileClicked(null);
                System.out.println("Invalid move (Same Tile)");
            }
        }
        
    }   

    @Override
    public void mousePressed(MouseEvent e) {
       
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
        
    }
    
}
