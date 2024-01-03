package Model.Pieces;

import Model.ChessBoard.ChessBoard;


public class Queen extends ChessPiece{

    public Queen(boolean isWhite)
    {
        super(isWhite, "Queen");
    }
    
    @Override
    public boolean checkMove(String currCoordinate, String destCoordinate) throws IllegalArgumentException
    {
        this.checkCoordinate(currCoordinate);
        this.checkCoordinate(destCoordinate);
        
        int colDiff = Math.abs(currCoordinate.charAt(0) - destCoordinate.charAt(0));
        int rowDiff = Math.abs(currCoordinate.charAt(1) - destCoordinate.charAt(1));
        
        boolean isValid;
        if(rowDiff == 0 && colDiff != 0)
        {
            isValid = true;
        } 
        else if (colDiff == 0 && rowDiff != 0)
        {
            isValid = true;
        }
        else 
        {
            isValid = colDiff == rowDiff;
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
        
        
        //possible early return if move has a radius of 1 tile
        //TilePanelClickEvent will handle it, in such a case
        if (Math.abs(rowDiff) == 1 || Math.abs(colDiff) == 1)
        {
            return true;
        }
        
        
        int loopCounter;
        int colSign;
        int rowSign;
                    
        if (Math.abs(rowDiff) == Math.abs(colDiff)) //Bishop move
        {
            loopCounter = Math.abs(colDiff);
            colSign = colDiff / Math.abs(colDiff);
            rowSign = rowDiff / Math.abs(rowDiff);
        }
        else    //Rook move
        {
            loopCounter = (colDiff == 0 ? rowDiff : colDiff);
            loopCounter = Math.abs(loopCounter);

            colSign = (colDiff == 0 ? 0 : colDiff / Math.abs(colDiff));
            rowSign = (rowDiff == 0 ? 0 : rowDiff / Math.abs(rowDiff));
        }
        
        
        //tile checks
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
