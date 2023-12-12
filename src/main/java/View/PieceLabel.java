package View;

import Model.Config;
import Model.Pieces.ChessPiece;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 * View representation of the Model ChessPiece.
 * It extends javax.swing.JLabel.
 * Contains an ImageIcon that will act as the icon of the label
 * @author Salti
 */
public class PieceLabel extends JLabel
{
    private ImageIcon image;
    
    public PieceLabel(ChessPiece chessPiece)
    { 
        try
        {
            URL imageURL = getClass().getResource("/Pieces/" + chessPiece.imagePath + ".png");
            image = new ImageIcon(imageURL);
            
            Image scaledImage = image.getImage().getScaledInstance(Config.PIECE_SIZE, Config.PIECE_SIZE, Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(scaledImage));
            
        } catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        
        
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
    }
    
}
