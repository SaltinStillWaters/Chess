package View;


import Control.TilePanelClickEvent;
import Model.ChessBoard.Tile;
import Model.Pieces.ChessPiece;
import Model.Pieces.Queen;

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
    
    /**
     * An IllegalArgumentException is thrown if null is passed as an argument
     * @param tile The Model.Tile to be associated with the TilePanel
     */
    public TilePanel(Tile tile)
    {
        super();
        
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
     * ands updates the the piece member of its associated Tile
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
        return !tile.getIsOccupied();
    }

    public void promotePawn(boolean isWhite) {
        // Remove existing
        if (pieceLabel != null) {
            this.remove(pieceLabel);
        }

        // Create a queen and replace
        ChessPiece queen = new Queen(isWhite);
        pieceLabel = new PieceLabel(queen);

        // Update
        tile.setChessPiece(queen);
        this.add(pieceLabel);

        this.revalidate();
        this.repaint();
    }
}
