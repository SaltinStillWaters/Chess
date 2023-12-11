package Model.Pieces;


public class Bishop extends ChessPiece 
{
    public Bishop(boolean isWhite)
    {
        super(isWhite, "Bishop");
    }

    @Override
    public boolean checkMove(String currCoordinate, String destCoordinate) 
    {
        int colDiff = currCoordinate.charAt(0) - destCoordinate.charAt(0);
        int rowDiff = currCoordinate.charAt(1) - destCoordinate.charAt(1);
        
        return colDiff == rowDiff; 
    }
}