package View;

import Model.ChessBoard.Tile;
import javax.swing.JPanel;


public class TilePanel extends JPanel
{
    private Tile tile;

    public TilePanel(Tile tile)
    {
        this.tile = tile;
    }

    public Tile getTile()
    {
        return tile;
    }
}
