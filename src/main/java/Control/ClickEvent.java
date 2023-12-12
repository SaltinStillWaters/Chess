
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
            } else{
                tilesingleton.setDestTileClicked(clickedTilePanel);
            }
            
            //checks if there is a destination
            if (tilesingleton.getDestTileClicked()!= null){
                // Get the PieceLabel from the current TilePanel
                PieceLabel clickedPiece = tilesingleton.getCurrTileClicked().getPieceLabel();
                if(clickedPiece != null){
                   tilesingleton.getCurrTileClicked().removePieceLabel();
                   tilesingleton.getDestTileClicked().setPieceLabel(clickedPiece);
                   // Reset the singleton's current and destination tiles after the move
                   tilesingleton.setCurrTileClicked(null);
                   tilesingleton.setDestTileClicked(null);
                }
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
