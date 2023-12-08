package View;

import Model.ChessBoard.ChessBoard;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MainFrame extends JFrame
{

    public MainFrame()
    {
        this.setSize(640, 640);
        this.setLocationRelativeTo(null);
        
        
        //COMPONENTS
            //Chess board
            ImagePanel chessBoard_panel = new ImagePanel("src/main/resources/ChessBoard.png");
            chessBoard_panel.setLayout(new GridLayout(8, 8));
            chessBoard_panel.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));

            this.add(chessBoard_panel);
        
            
        {
            ChessBoard chessBoard = ChessBoard.getInstance();
            
            for (int row = 7; row >= 0; --row)
            {
                for (int col = 0; col < 8; ++col)
                {
                    TilePanel tilePanel = new TilePanel(chessBoard.getTile(col, row));
                    tilePanel.setOpaque(false);
                    
                    JLabel lbl = new JLabel(tilePanel.getTile().coordinate);
                    tilePanel.add(lbl);
                    
                    chessBoard_panel.add(tilePanel);
                }

            }
        }
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.pack();
        this.setVisible(true);
    }
    
}
