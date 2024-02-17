package Model.Pieces;

import Model.ChessBoard.ChessBoard;


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
        
        boolean isValid = (colDiff == rowDiff); 
        
        if (isValid)
        {
            isValid = checkObstruction(currCoordinate, destCoordinate);
        }
        
        return isValid;
    }
    
    @Override
    protected boolean checkObstruction(String currCoordinate, String destCoordinate)
    {
        int colDiff = destCoordinate.charAt(0) - currCoordinate.charAt(0);
        int rowDiff = destCoordinate.charAt(1) - currCoordinate.charAt(1);
        
        int loopCounter = Math.abs(colDiff);
        int colSign = colDiff / Math.abs(colDiff);
        int rowSign = rowDiff / Math.abs(rowDiff);
        
        ChessBoard chessBoard = ChessBoard.getInstance();
        
        boolean isObstructed = false;
        for (int x = 1; x < loopCounter; ++x)
        {
            //Increment Coordinate
                StringBuilder coordinateToCheck = new StringBuilder(2);

                char col = (char) (currCoordinate.charAt(0) + colSign * x);
                char row = (char) (currCoordinate.charAt(1) + rowSign * x);

                coordinateToCheck.append(col);
                coordinateToCheck.append(row);
            
            if (chessBoard.getTile(coordinateToCheck.toString()).getIsOccupied())
            {
                isObstructed = true;
                break;
            }
        }
        
        return !isObstructed;
    }
}