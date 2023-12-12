package Model.Pieces;

public abstract class ChessPiece 
{
    /**
     * A boolean that holds the color information of the piece.
     * Note that it is the color of the piece, not its tile
     */
    public final boolean isWhite;
    
    /**
     * A String that holds the name of the piece.
     * E.g.: "Knight"
     */
    public final String name;
    
    /**
     * Path of the piece's image relative to '/resources'
     */
    public final String imagePath;
    
    protected ChessPiece(boolean isWhite, String name)
    {
        this.isWhite = isWhite;
        this.name = name;
        
        String color = isWhite ? "White" : "Black";
        
        this.imagePath = String.format("%s/%s", color, name);
    }
    
    
    /**
     * Checks whether a move is valid.
     * The column-coordinate of its arguments must be capitalized.
     * E.g.: "A1", "B2"
     * Passing invalid arguments such as "a1" or non-existent coordinates will
     * result in an IllegalArgumentException
     * @param currCoordinate
     * @param destCoordinate
     * @return 
     */
    public abstract boolean checkMove(String currCoordinate, String destCoordinate) throws IllegalArgumentException;  
    
    public void move(String currCoordinate, String destCoordinate)
    {
        //code
    }
            
}
