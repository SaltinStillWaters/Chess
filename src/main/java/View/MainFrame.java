package View;

import Model.Config;
import Model.Model_Main;
import Model.ChessBoard.ChessBoard;

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
    ImagePanel boardImagePanel;
    public ArrayList<ArrayList<TilePanel>> tilePanels;
    
    public MainFrame()
    {
        this.setSize(Config.CHESSBOARD_SIZE, Config.CHESSBOARD_SIZE);
        this.setLocationRelativeTo(null);
        
        ChessBoardPanel chessBoardPanel = ChessBoardPanel.getInstance();
        boardImagePanel = chessBoardPanel.getBoardPanel();
        tilePanels = chessBoardPanel.getTilePanelsArray();
        
        this.add(boardImagePanel);
        
        
        // Flip board when 'R' is pressed
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
        boardImagePanel.removeAll();
        
        if (Model_Main.getInstance().getIsWhiteToTurn())
        {
            for (int row = 7; row >= 0; --row)
            {
                for (int col = 7; col >= 0; --col)
                {
                    boardImagePanel.add(tilePanels.get(row).get(col));
                }
            }
            
            boardImagePanel.setBackgroundImage("/ChessBoard_BlackPOV.png");
        }
        else
        {
            for (int row = 0; row < 8; ++row)
            {
                for (int col = 0; col < 8; ++col)
                {
                    boardImagePanel.add(tilePanels.get(row).get(col));
                }
            }
            
            boardImagePanel.setBackgroundImage("/ChessBoard_WhitePOV.png");
        }
        
        ChessBoard board = ChessBoard.getInstance();
        ArrayList<ArrayList<TilePanel>> tilePanels = Model_Main.getInstance().getMainFrameInstance().tilePanels;
        System.out.println(board.getTile("A1").getChessPiece());
        System.out.println(tilePanels.get(0).get(0).getPieceLabel().getChessPiece());
        
        this.revalidate();
        this.repaint();
    }
}
