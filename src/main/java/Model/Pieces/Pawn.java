package Model.Pieces;


public class Pawn extends ChessPiece
{

    public Pawn(boolean isWhite)
    {
        super(isWhite, "Pawn");
    }
    
    @Override
    public boolean checkMove(String currCoordinate, String destCoordinate)
    {
        int colDiff = currCoordinate.charAt(0) - destCoordinate.charAt(0);
        
        int rowDiff = destCoordinate.charAt(1) - currCoordinate.charAt(1);
        
        
        boolean isValid = true;
        if (colDiff != 0)
        {
            isValid = false;
        }
        else if (Math.abs(rowDiff) > 2)
        {
            isValid = false;
        }
        else if (Math.abs(rowDiff) == 2)
        {
            if (currCoordinate.charAt(1) != '2' && currCoordinate.charAt(1) != '7')
            {
                isValid = false;
            }
        }
        
        //backward move
        if (this.isWhite)
        {
            if (rowDiff < 0)
            {
                isValid = false;
            }
        }
        else
        {
            if (rowDiff > 0)
            {
                isValid = false;
            }
        }
        
        return isValid;
    }
    
    
}
