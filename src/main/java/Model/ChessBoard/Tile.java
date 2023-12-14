package Model.ChessBoard;

import Model.Pieces.ChessPiece;

/**
 * Represents a tile in a Chessboard.
 * Holds information such as: 
 *  - whether or not it is occupied
 *  - a reference to the ChessPiece that occupies it
 *  - its coordinate
 *  - its color
 * @author Salti
 */
public class Tile 
{
    private ChessPiece chessPiece;
    private boolean isOccupied;
    
    /**
     * Coordinate of the Tile.
     * E.g.: "A1" Which is located at the South-West corner of the Chessboard from white's perspective
     */
    public final String coordinate;
    
    /**
     * Color of the tile.
     */
    public final boolean isWhite;

    
    public Tile(ChessPiece chessPiece, boolean isOccupied, String coordinate, boolean isWhite)
    {
        this.chessPiece = chessPiece;
        this.isOccupied = isOccupied;
        this.coordinate = coordinate;
        this.isWhite = isWhite;
    }
    
    /**
     * Sets the ChessPiece for the tile.
     * Calling this method will also mark the tile as occupied.
     * Throws IllegalArgumentException if the argument passed to it is null
     * @param chessPiece 
     */
    public void setChessPiece(ChessPiece chessPiece)
    {
        try
        {
            if (chessPiece == null)
            {
                throw new IllegalArgumentException("ChessPiece cannot be null");
            }
            
            this.chessPiece = chessPiece;
            this.isOccupied = true;
        } 
        catch (IllegalArgumentException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }
    
    /**
     * Removes the ChessPiece from the tile,
     * and marks it as not occupied
     */
    public void removeChessPiece()
    {
        this.chessPiece = null;
        this.isOccupied = false;
    }
    
    /**
     * Returns a reference to the ChessPiece that occupies the tile.
     * Returns null if Tile is not occupied
     * @return 
     */
    public ChessPiece getChessPiece()
    {
        return chessPiece;
    }

    /**
     * Returns a boolean if the Tile is occupied
     * @return 
     */
    public boolean getIsOccupied()
    {
        return isOccupied;
    }
    
    public String getCoordinate(){
        return this.coordinate;
    }
   
}
