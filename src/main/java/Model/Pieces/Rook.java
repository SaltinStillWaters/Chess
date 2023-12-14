package Model.Pieces;


public class Rook extends ChessPiece 
{
    private boolean hasMoved;
    
    public Rook(boolean isWhite)
    {
        super(isWhite, "Rook");
    }

    @Override
    public boolean checkMove(String currCoordinate, String destCoordinate) throws IllegalArgumentException
    {
        this.checkCoordinate(currCoordinate);
        this.checkCoordinate(destCoordinate);
        
        int colDiff = currCoordinate.charAt(0) - destCoordinate.charAt(0);
        int rowDiff = currCoordinate.charAt(1) - destCoordinate.charAt(1);
        
        if(rowDiff == 0 && colDiff !=0)
        {
            return true;
        } 
        else 
        {
            return colDiff == 0 && rowDiff !=0;
        }  
    }
    
    /**
     * Returns a boolean whether or not the King has moved.
     * @return 
     */
    public boolean getHasMoved()
    {
        return this.hasMoved;
    }
    
    /**
     * Marks that the King has moved.
     */
    public void setHasMovedToTrue()
    {
        this.hasMoved = true;
    }
}