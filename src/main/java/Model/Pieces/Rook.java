package Model.Pieces;

import Model.ChessBoard.ChessBoard;


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
        
        int colDiff = Math.abs(currCoordinate.charAt(0) - destCoordinate.charAt(0));
        int rowDiff = Math.abs(currCoordinate.charAt(1) - destCoordinate.charAt(1));
        
        boolean isValid = true;
        if (rowDiff == 0 && colDiff != 0)
        {
            isValid = true;
        } 
        else 
        {
            isValid = colDiff == 0 && rowDiff != 0;
        }  
        
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
        
        int loopCounter = colDiff == 0 ? rowDiff : colDiff;
        loopCounter = Math.abs(loopCounter);
        
        int colSign = colDiff == 0 ? 0 : colDiff / Math.abs(colDiff);
        int rowSign = rowDiff == 0 ? 0 : rowDiff / Math.abs(rowDiff);
        
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
    
    /**
     * Returns a boolean whether or not the King has moved.
     * @return 
     */
    public boolean getHasMoved()
    {
        return this.hasMoved;
    }
    
    /**
     * Marks that the Rook has moved.
     */
    public void setHasMovedToTrue()
    {
        this.hasMoved = true;
    }
}