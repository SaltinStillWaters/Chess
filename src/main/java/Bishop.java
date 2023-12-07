
import java.util.ArrayList;

public class Bishop extends ChessPiece {
    public Bishop(String currTileIndex, ArrayList<Tile> initialTiles, String color) {
        super(currTileIndex, initialTiles, color);
    }

    @Override
    boolean checkMove(Tile destTile) {
        int colDiff = this.currTileIndex.charAt(0) - destTile.coordinate.charAt(0);
        int rowDiff = this.currTileIndex.charAt(1) - destTile.coordinate.charAt(1);
        return colDiff == rowDiff;
    }
}