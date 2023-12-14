package Model.Pieces;


public class Knight extends ChessPiece
{

    public Knight(boolean isWhite)
    {
        super(isWhite, "Knight");
    }
    
    @Override
    public boolean checkMove(String currCoordinate, String destCoordinate) throws IllegalArgumentException
    {
        this.checkCoordinate(currCoordinate);
        this.checkCoordinate(destCoordinate);
        
        int colDiff = currCoordinate.charAt(0) - destCoordinate.charAt(0);
        colDiff = Math.abs(colDiff);
        
        int rowDiff = currCoordinate.charAt(1) - destCoordinate.charAt(1);
        rowDiff = Math.abs(rowDiff);
        
        
        boolean isValid = false;
        if ((rowDiff == 1 && colDiff == 2) || (rowDiff == 2 && colDiff == 1))
        {
            isValid = true;
        }
        
        
        return isValid;
    }
}