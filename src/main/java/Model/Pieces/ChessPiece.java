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
    
    
    /**
     * Checks whether a coordinate is valid.
     * Throws an IllegalArgumentException if it is not.
     * @param coordinate
     * @throws IllegalArgumentException 
     */
    protected void checkCoordinate(String coordinate) throws IllegalArgumentException
    {
        boolean row = coordinate.charAt(1) >= '1' && coordinate.charAt(1) <= '8';
      
        boolean col = coordinate.charAt(0) >= 'A' && coordinate.charAt(0) <= 'H';
        
        if (! (row&& col))
        {
            throw new IllegalArgumentException("Argument: '" + coordinate + "' is not a valid coordinate\n");
        }
    }
    
    public void move(String currCoordinate, String destCoordinate)
    {
        //code
    }
            
}
