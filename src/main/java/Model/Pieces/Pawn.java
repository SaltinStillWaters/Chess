package Model.Pieces;

import Model.ChessBoard.ChessBoard;


public class Pawn extends ChessPiece
{

    public Pawn(boolean isWhite)
    {
        super(isWhite, "Pawn");
    }
    
    @Override
    public boolean checkMove(String currCoordinate, String destCoordinate) throws IllegalArgumentException
    {
        this.checkCoordinate(currCoordinate);
        this.checkCoordinate(destCoordinate);
        
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
        
        System.out.println(isValid);
        if (isValid)
        {
            isValid = checkObstruction(currCoordinate, destCoordinate);
            System.out.println(isValid);
        }
        
        return isValid;
    }
    
    @Override
    protected boolean checkObstruction(String currCoordinate, String destCoordinate)
    {
        int rowDiff = destCoordinate.charAt(1) - currCoordinate.charAt(1);
        
        //possible early return
        if (Math.abs(rowDiff) == 1)
        {
            return true;
        }
        
        StringBuilder coordinateToCheck = new StringBuilder(currCoordinate);
        char newRow;
        if (rowDiff == 2)
        {
            newRow = (char) (currCoordinate.charAt(1) + 1);
        }
        else
        {
            newRow = (char) (currCoordinate.charAt(1) - 1);
        }
        
        coordinateToCheck.setCharAt(1, newRow);
        
        //returns true if tile is not occupied
        return ! (ChessBoard.getInstance().getTile(coordinateToCheck.toString()).getIsOccupied());
    }
}