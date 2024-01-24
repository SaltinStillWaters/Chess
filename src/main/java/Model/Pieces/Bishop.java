package Model.Pieces;


public class Bishop extends ChessPiece 
{
    public Bishop(boolean isWhite)
    {
        super(isWhite, "Bishop");
    }

    @Override
    public boolean checkMove(String currCoordinate, String destCoordinate) throws IllegalArgumentException
    {
        this.checkCoordinate(currCoordinate);
        this.checkCoordinate(destCoordinate);
        
        int colDiff = Math.abs(currCoordinate.charAt(0) - destCoordinate.charAt(0));
        int rowDiff = Math.abs(currCoordinate.charAt(1) - destCoordinate.charAt(1));
        
        
        
        return colDiff == rowDiff; 
        
        
    }
}