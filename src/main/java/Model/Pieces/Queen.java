
package Model.Pieces;

public class Queen extends ChessPiece{

    @Override
    public boolean checkMove(String currCoordinate, String destCoordinate) {
        int colDiff = currCoordinate.charAt(0) - destCoordinate.charAt(0);
        int rowDiff = currCoordinate.charAt(1) - destCoordinate.charAt(1);
        
        if(rowDiff == 0 && colDiff !=0){
            return true;
        } else if (colDiff == 0 && rowDiff !=0){
            return true;
        } else if (colDiff == rowDiff){
            return true;
        } else{
            return false;
        }
    }
    
}
