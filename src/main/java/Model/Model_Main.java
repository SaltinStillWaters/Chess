package Model;
import View.MainFrame;
import View.TilePanel;

public class Model_Main 
{
    private static Model_Main instance;
    private final MainFrame mainFrameInstance;
    private TilePanel currTile;
    private TilePanel destTile;
    private boolean isWhiteToTurn;
    private boolean hasCurrTile;
    private boolean hasDestTile;
    
    private Model_Main()
    {
        this.mainFrameInstance = new MainFrame();
        
        isWhiteToTurn = true;
        hasCurrTile = false;
        hasDestTile = false;
    }

    public MainFrame getMainFrameInstance()
    {
        return mainFrameInstance;
    }

    public void setCurrTileClicked(TilePanel currTile)
    {
        this.currTile = currTile;
        this.hasCurrTile = true;
    }
    
    public TilePanel getCurrTileClicked()
    {
        return this.currTile;
    }
    
    
    public void setDestTileClicked(TilePanel destTile)
    {
        this.destTile = destTile;
        this.hasDestTile = true;
    }
    
    public TilePanel getDestTileClicked()
    {
        return this.destTile;
    }
    
    public boolean hasCurrTile()
    {
        return (hasCurrTile);
    }
    
    public boolean hasDestTile()
    {
        return (hasDestTile);
    }
    
    public void clearDestAndCurrTiles()
    {
        currTile = null;
        destTile = null;
        
        hasCurrTile = false;
        hasDestTile = false;
    }
    
    public void switchTurn()
    {
        this.isWhiteToTurn = !isWhiteToTurn;
    }
    
    public boolean getIsWhiteToTurn()
    {
        return this.isWhiteToTurn;
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
