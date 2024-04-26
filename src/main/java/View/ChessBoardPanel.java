package View;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import Model.ChessBoard.ChessBoard;
import Model.ChessBoard.Tile;

public class ChessBoardPanel extends ImagePanel
{
    private static ChessBoardPanel instance;
    private final ArrayList<ArrayList<TilePanel>> tilePanelsArray;
    private final ImagePanel chessBoard_panel;

    public ChessBoardPanel()
    {
        //COMPONENTS
            //Chess board
            chessBoard_panel = new ImagePanel("/ChessBoard_WhitePOV.png");
            chessBoard_panel.setLayout(new GridLayout(8, 8));
            chessBoard_panel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));

            //Tiles
        {
            this.tilePanelsArray = new ArrayList<>();
            ChessBoard chessBoard = ChessBoard.getInstance();
            
            for (int row = 7; row >= 0; --row)
            {
                ArrayList<TilePanel> tilePanelsTemp = new ArrayList<>();
                
                for (int col = 0; col < 8; ++col)
                {
                    Tile tile = chessBoard.getTile(col, row);
                    
                    TilePanel tilePanel = new TilePanel(tile);
                    tilePanel.setOpaque(false);
                    
                    //add piece if tile is occupied
                    if (tile.getIsOccupied())
                    {
                        PieceLabel pieceLabel = new PieceLabel(tile.getChessPiece());
                        
                        tilePanel.setPieceLabel(pieceLabel);
                    }
                    
                    chessBoard_panel.add(tilePanel);
                    tilePanelsTemp.add(tilePanel);
                }
                
                this.tilePanelsArray.add(tilePanelsTemp);
            }
        }
    }

    public static ChessBoardPanel getInstance()
    {
        if (instance == null)
        {
            instance = new ChessBoardPanel();
        }

        return instance;
    }

    public ArrayList<ArrayList<TilePanel>> getTilePanelsArray()
    {
        return tilePanelsArray;
    } 

    public TilePanel getTilePanel(int col, int row)
    {
        return tilePanelsArray.get(col).get(row);
    }
    
    public TilePanel getTilePanel(String coordinate)
    {
        return getTilePanel(coordinate.charAt(0) - 'A', coordinate.charAt(1) - '1');
    }
}
