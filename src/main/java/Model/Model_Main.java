package Model;
import View.TilePanel;

public class Model_Main 
{
    private static Model_Main instance;
    private TilePanel currTile;
    private TilePanel destTile;
    
    private Model_Main()
    {
        
    }
    
    
    public void setCurrTileClicked(TilePanel currTile)
    {
        this.currTile = currTile;
    }
    
    public TilePanel getCurrTileClicked()
    {
        return this.currTile;
    }
    
    
    public void setDestTileClicked(TilePanel destTile)
    {
        this.destTile = destTile;
    }
    
    public TilePanel getDestTileClicked()
    {
        return this.destTile;
    }
    
    
    public static Model_Main getInstance()
    {
        if (instance == null)
        {
            instance = new Model_Main();
        }
        
        return instance;
    }
    
}
