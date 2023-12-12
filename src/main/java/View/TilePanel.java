package View;

import Model.ChessBoard.Tile;
import java.awt.FlowLayout;
import javax.swing.JPanel;


/**
 * View representation of the Model Tile.
 * It extends javax.swing.JPanel and contains a Tile and a PieceLabel
 * @author Salti
 */
public class TilePanel extends JPanel
{
    private final Tile tile;
    private PieceLabel pieceLabel;
    
    /**
     * An IllegalArgumentException is thrown if null is passed as an argument
     * @param tile 
     */
    public TilePanel(Tile tile)
    {
        try
        {
            if (tile == null)
            {
                throw new IllegalArgumentException("Tile cannot be null");
            }
        } catch (IllegalArgumentException e)
        {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        
        this.tile = tile;
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    /**
     * Returns the Model Tile associated with it.
     * @return 
     */
    public Tile getTile()
    {
        return tile;
    }
    
    /**
     * Returns a PieceLabel associated with it
     * @return 
     */
    public PieceLabel getPieceLabel()
    {
        return pieceLabel;
    }
    
    /**
     * Adds a PieceLabel component to the Panel
     * @param pieceLabel 
     */
    public void setPieceLabel(PieceLabel pieceLabel)
    {
        this.pieceLabel = pieceLabel;
        this.add(pieceLabel);
    }
    
    /**
     * Removes the PieceLabel component of the Panel
     */
    public void removePieceLabel()
    {
       this.remove(pieceLabel);
       
       this.revalidate();
       this.repaint();
    }
}
