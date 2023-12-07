
import java.util.ArrayList;


public abstract class ChessPiece {
    protected String currTileIndex;
    protected ArrayList<Tile> initialTiles;
    protected String color;

    public ChessPiece(String currTileIndex, ArrayList<Tile> initialTiles, String color) {
        this.currTileIndex = currTileIndex;
        this.initialTiles = initialTiles;
        this.color = color;
    }

    abstract boolean checkMove(Tile destTile);
    public void doMove(Tile destTile){
        
    }
}
