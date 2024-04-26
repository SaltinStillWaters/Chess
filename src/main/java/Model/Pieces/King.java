package Model.Pieces;

import Model.ChessBoard.ChessBoard;


public class King extends ChessPiece
{
    private boolean hasMoved;
    
    public King(boolean isWhite)
    {
        super(isWhite, "King");
        hasMoved = false;
    }
    
    @Override
    public boolean checkMove(String currCoordinate, String destCoordinate) throws IllegalArgumentException
    {
        this.checkCoordinate(currCoordinate);
        this.checkCoordinate(destCoordinate);
        
        int colDiff = Math.abs(currCoordinate.charAt(0) - destCoordinate.charAt(0));
        int rowDiff = Math.abs(currCoordinate.charAt(1) - destCoordinate.charAt(1));
        
        boolean isValid;
        
        if(rowDiff == 0 && colDiff == 1)
        {
            isValid = true;
        } 
        else if (colDiff == 0 && rowDiff == 1)
        {
            isValid = true;
        } 
        else    
        {
            isValid = (colDiff == 1 && rowDiff == 1);
        }
        
        if (!this.hasMoved && isValid)
        {
            this.hasMoved = true;
        }
        return isValid;
    }

    @Override
    protected boolean checkObstruction(String currCoordinate, String destCoordinate)
    {
        //King does not need to check obstruction
        return true;
    }

    public boolean getHasMoved()
    {
        return hasMoved;
    }

    public void setHasMoved()
    {
        this.hasMoved = true;
    }
}