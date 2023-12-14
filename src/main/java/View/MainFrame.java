package View;

import Model.ChessBoard.ChessBoard;
import Model.ChessBoard.Tile;
import Model.Config;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;


/**
 * Acts as the main frame of the program.
 * It holds the entire process for playing a chess game
 * @author Salti
 */
public class MainFrame extends JFrame
{
    private final ArrayList<ArrayList<TilePanel>> tilePanelsArray;
    private final ImagePanel chessBoard_panel;
    private boolean isWhitePOV;
    
    public MainFrame()
    {
        this.setSize(Config.CHESSBOARD_SIZE, Config.CHESSBOARD_SIZE);
        this.setLocationRelativeTo(null);
        
        this.isWhitePOV = true;
        
        //COMPONENTS
            //Chess board
            chessBoard_panel = new ImagePanel("/ChessBoard_WhitePOV.png");
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
        
        
        //Flip board when 'R' is pressed
            InputMap inputMap = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
            ActionMap actionMap = this.getRootPane().getActionMap();

            inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), "flipBoardAction");
            actionMap.put("flipBoardAction", new AbstractAction() 
            {
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    flipBoard();
                }
            });
        
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.pack();
        this.setVisible(true);
    }
    
    
    /**
     * Flips the chessBoard view.
     * E.g.: Switches the POV to White/Black's perspective
     */
    public void flipBoard()
    { 
        chessBoard_panel.removeAll();
        
        if (isWhitePOV)
        {
            for (int row = 7; row >= 0; --row)
            {
                for (int col = 7; col >= 0; --col)
                {
                    chessBoard_panel.add(tilePanelsArray.get(row).get(col));
                }
            }
            
            chessBoard_panel.setBackgroundImage("/ChessBoard_BlackPOV.png");
            isWhitePOV = false;
        }
        else
        {
            for (int row = 0; row < 8; ++row)
            {
                for (int col = 0; col < 8; ++col)
                {
                    chessBoard_panel.add(tilePanelsArray.get(row).get(col));
                }
            }
            
            chessBoard_panel.setBackgroundImage("/ChessBoard_WhitePOV.png");
            isWhitePOV = true;
        }
        
        this.revalidate();
        this.repaint();
    }
}
