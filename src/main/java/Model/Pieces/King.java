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

    public boolean checkMoveCastle(String currCoordinate, String destCoordinate) throws IllegalArgumentException
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

    public boolean checkCastleAttempt(String currCoordinate, String destCoordinate)
    {
        if (hasMoved)
        {
            System.out.println("king has moved");
            return false;
        }

        int rowDiff = Math.abs(currCoordinate.charAt(1) - destCoordinate.charAt(1));        
        if (rowDiff > 0)    
        {
            System.out.println("invalid row");
            return false;
        }

        int[] validCols = {1, 2, 6};
        boolean validCol = false;

        for (int x = 0; x < validCols.length; ++x)
        {
            if (destCoordinate.charAt(0) - 'A' == validCols[x])
            {
                validCol = true;
                break;
            }
        }
        if (!validCol)  
        {
            System.out.println("invalid column");
            return false;
        }
        //get corresponding rook
        int colDiff = destCoordinate.charAt(0) - currCoordinate.charAt(0);
        int rookCol = colDiff > 0 ? 7 : 0;
        
        int rookRow = '8' - currCoordinate.charAt(1);

            //check if rook is placed at initial spot
        ChessPiece chessPiece = ChessBoard.getInstance().getTile(rookRow, rookCol).getChessPiece();

        if (!chessPiece.name.equals("Rook"))
        {
            System.out.println("rook is missing");
            return false;
        }

        Rook rook = (Rook) chessPiece;
        return !rook.getHasMoved();
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