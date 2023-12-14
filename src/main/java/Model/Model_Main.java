package Model;
import View.TilePanel;

public class Model_Main 
{
    private static Model_Main instance;
    private TilePanel currTile;
    private TilePanel destTile;
    private boolean isWhiteTurn = true;
    
    
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
    
    public void setTurn(boolean isWhiteTurn){
        this.isWhiteTurn = isWhiteTurn;
        if(this.isWhiteTurn == true){
            System.out.println("White turn");
        } else{
            System.out.println("Black turn");
        }
    }
    
    public boolean getTurn(){
        return this.isWhiteTurn;
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
