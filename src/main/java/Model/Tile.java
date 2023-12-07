package Model;


public class Tile 
{
    private ChessPiece chessPiece;
    private boolean isOccupied;
    
    public final String coordinate;
    public final boolean isWhite;

    
    public Tile(ChessPiece chessPiece, boolean isOccupied, String coordinate, boolean isWhite)
    {
        this.chessPiece = chessPiece;
        this.isOccupied = isOccupied;
        this.coordinate = coordinate;
        this.isWhite = isWhite;
    }

    
    public void setChessPiece(ChessPiece chessPiece)
    {
        this.chessPiece = chessPiece;
    }

    public void setIsOccupied(boolean isOccupied)
    {
        this.isOccupied = isOccupied;
    }
    

    public ChessPiece getChessPiece()
    {
        return chessPiece;
    }

    public boolean getIsOccupied()
    {
        return isOccupied;
    }
   
}
