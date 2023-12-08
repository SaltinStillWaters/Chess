package Model.Pieces;


public class Pawn extends ChessPiece
{

    @Override
    public boolean checkMove(String currCoordinate, String destCoordinate)
    {
        int colDiff = currCoordinate.charAt(0) - destCoordinate.charAt(0);
        
        int rowDiff = currCoordinate.charAt(1) - destCoordinate.charAt(1);
        
        
        boolean isValid = true;
        if (colDiff != 0)
        {
            isValid = false;
        }
        else if (rowDiff < 0)
        {
            System.out.println("backward");
            isValid = false;
        }
        else if (rowDiff > 2)
        {
            isValid = false;
        }
        else if (rowDiff == 2)
        {
            if (currCoordinate.charAt(1) != '2' && currCoordinate.charAt(1) != '7')
            {
                isValid = false;
            }
        }
        
        return isValid;
    }
    
    
}
