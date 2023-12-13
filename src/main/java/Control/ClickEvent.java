
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
                System.out.println("Current Tile set");
                
                if(tilesingleton.getCurrTileClicked().getPieceLabel() == null){
                   tilesingleton.setCurrTileClicked(null);
                   System.out.println("Action Cancelled");
                }
            } else{
                tilesingleton.setDestTileClicked(clickedTilePanel);
                System.out.println("Destination Tile set");
            }
            
            
            
            //checks if there is a destination and destination is not the current tile
            if (tilesingleton.getDestTileClicked()!= null && tilesingleton.getDestTileClicked() != tilesingleton.getCurrTileClicked()){
                // Get the PieceLabel from the current TilePanel
                PieceLabel clickedPiece = tilesingleton.getCurrTileClicked().getPieceLabel();
                if(clickedPiece != null){
                   tilesingleton.getCurrTileClicked().removePieceLabel();
                   tilesingleton.getDestTileClicked().setPieceLabel(clickedPiece);
                   // Reset the singleton's current and destination tiles after the move
                   System.out.println("Piece moved");
                   tilesingleton.setCurrTileClicked(null);
                   tilesingleton.setDestTileClicked(null);
                }
            } else if (tilesingleton.getDestTileClicked() == tilesingleton.getCurrTileClicked()){
                tilesingleton.setCurrTileClicked(null);
                tilesingleton.setDestTileClicked(null);
                System.out.println("Invalid move");
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
