package View;


import Control.TilePanelClickEvent;
import Model.ChessBoard.Tile;
import java.awt.FlowLayout;


/**
 * View representation of the Model Tile.
 * It extends ImagePanel and contains a Tile and a PieceLabel
 * @author Salti
 */
public class TilePanel extends ImagePanel
{
    private final Tile tile;
    private PieceLabel pieceLabel;
    private boolean isEmpty;
    
    /**
     * An IllegalArgumentException is thrown if null is passed as an argument
     * @param tile The Model.Tile to be associated with the TilePanel
     */
    public TilePanel(Tile tile)
    {
        super();
        
        this.isEmpty = true;
        
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
        this.addMouseListener(new TilePanelClickEvent());
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
     * @param pieceLabel The pieceLabel that will be placed inside the TilePanel
     */
    public void setPieceLabel(PieceLabel pieceLabel)
    {
        this.tile.setChessPiece(pieceLabel.getChessPiece());
        
        if (this.pieceLabel != null)
        {
            this.remove(this.pieceLabel);
        }
        
        this.pieceLabel = pieceLabel;
        this.add(pieceLabel);
        
        this.isEmpty = false;
        
        this.revalidate();
        this.repaint();
    }
    
    /**
     * Removes the PieceLabel component of the Panel
     */
    public void removePieceLabel()
    {
        this.tile.removeChessPiece();
        this.remove(pieceLabel);

        this.isEmpty = true;

        this.revalidate();
        this.repaint();
    }
    
    /**
     * Highlights the tile, marking it as 'selected'
     */
    public void markAsSelected()
    {
        this.setBackgroundImage("/TileSelected.png");
    }
    
    /**
     * Removes the tile's highlight.
     */
    public void removeMarkAsSelected()
    {
        this.removeBackgroundImage();
    }
    
    public boolean isEmpty()
    {
        return isEmpty;
    }
}
