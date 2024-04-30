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
        boolean canPromote = false;
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
        
        if (!isValid)
        {
            isValid = checkCapture(currCoordinate, destCoordinate);
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

        canPromote = checkPromotion(destCoordinate);
        
        if (isValid)
        {
            isValid = checkObstruction(currCoordinate, destCoordinate);
        }
        
        return isValid;
    }
    
    @Override
    protected boolean checkObstruction(String currCoordinate, String destCoordinate)
    {
        int rowDiff = destCoordinate.charAt(1) - currCoordinate.charAt(1);
        
        if (Math.abs(rowDiff) == 1)
        {
            return true;
        }
        
        
        StringBuilder coordinateToCheck = new StringBuilder(currCoordinate);
        
        char rowCoordinate = (char) (currCoordinate.charAt(1) + rowDiff / Math.abs(rowDiff));
        
        coordinateToCheck.setCharAt(1, rowCoordinate);
        
        //returns true if tile is not occupied
        return ! (ChessBoard.getInstance().getTile(coordinateToCheck.toString()).getIsOccupied());
    }
    
    private boolean checkCapture(String currCoordinate, String destCoordinate)
    {
        int colDiff = currCoordinate.charAt(0) - destCoordinate.charAt(0);
        int rowDiff = destCoordinate.charAt(1) - currCoordinate.charAt(1);
        
        boolean canCapture = false;
        if (Math.abs(colDiff) == 1 && Math.abs(rowDiff) == 1)
        {
            ChessBoard chessBoard = ChessBoard.getInstance();
            if (chessBoard.getTile(destCoordinate).getIsOccupied())
            {
                canCapture = true;
            }
        }
        
        return canCapture;
    }

    public boolean checkPromotion(String destCoordinate){
        if (destCoordinate.charAt(1) == '8' && this.isWhite) {
            return true;
        } else if (destCoordinate.charAt(1) == '1' && !this.isWhite) {
            return true;
        } else {
            return false;
        }
    }

}