package View;

import java.awt.Graphics;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


/**
 * Extends javax.swing.JPanel.
 * Used for creating panels with a background image.
 * @author Salti
 */
public class ImagePanel extends JPanel
{
    private ImageIcon backgroundImage;

    public ImagePanel()
    {
        super();
    }
    
    public ImagePanel(String imagePath)
    {
        try
        {
            URL imageURL = getClass().getResource(imagePath);
            backgroundImage = new ImageIcon(imageURL);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g); 
        
        if (backgroundImage != null)
        {
            g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    /**
     * Sets the background image of the panel.
     * @param imagePath The file path of the image relative to the /resources folder
     */
    public void setBackgroundImage(String imagePath)
    {
        try
        {
            URL imageURL = getClass().getResource(imagePath);
            backgroundImage = new ImageIcon(imageURL);
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        this.repaint();
        this.revalidate();
    }
    
    /**
     * Removes the background image of the panel.
     * Does nothing if the panel has no background image to begin with.
     */
    public void removeBackgroundImage()
    {
        if (this.backgroundImage != null)
        {
            this.backgroundImage = null;
            this.repaint();
            this.revalidate();
        }
    }
}
