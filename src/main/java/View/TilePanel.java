package View;

import Control.ClickEvent;
import Model.ChessBoard.Tile;
import java.awt.FlowLayout;
import javax.swing.JPanel;


public class TilePanel extends JPanel
{
    private final Tile tile;
    private PieceLabel pieceLabel;
    
    public TilePanel(Tile tile)
    {
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.tile = tile;
        this.addMouseListener(new ClickEvent());
    }

    public Tile getTile()
    {
        return tile;
    }
    
    public PieceLabel getPieceLabel()
    {
        return pieceLabel;
    }
    
    public void setPieceLabel(PieceLabel pieceLabel)
    {
        this.pieceLabel = pieceLabel;
        this.add(pieceLabel);
        
        this.revalidate();
        this.repaint();
    }
    
    public void removePieceLabel()
    {
       this.remove(pieceLabel);
       
       this.revalidate();
       this.repaint();
    }
}
