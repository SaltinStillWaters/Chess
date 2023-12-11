package View;

import Model.ChessBoard.ChessBoard;
import Model.ChessBoard.Tile;
import Model.Config;
import Model.Pieces.Queen;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MainFrame extends JFrame
{
    private ArrayList<ArrayList<TilePanel>> tilePanelsArray;
    
    public MainFrame()
    {
        this.setSize(Config.CHESSBOARD_SIZE, Config.CHESSBOARD_SIZE);
        this.setLocationRelativeTo(null);
        
        
        //COMPONENTS
            //Chess board
            ImagePanel chessBoard_panel = new ImagePanel("src/main/resources/ChessBoard.png");
            chessBoard_panel.setLayout(new GridLayout(8, 8));
            chessBoard_panel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));

            this.add(chessBoard_panel);
        
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
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.pack();
        this.setVisible(true);
    }
    
}
