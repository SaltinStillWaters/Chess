package Model;

/**
 * A set of constants that defines the particularities of the program
 */
public class Config 
{
    /**
     * Length and Width of the ChessBoard Frame
     */
    public final static int CHESSBOARD_SIZE;
    
    /**
     * Length and width of the pieces
     */
    public final static int PIECE_SIZE;
    public final static boolean AUTO_FLIP_BOARD;

    static 
    {
        //configurable
        CHESSBOARD_SIZE = 640;
        AUTO_FLIP_BOARD = true;
        
        //not-configurable
        PIECE_SIZE = CHESSBOARD_SIZE / 9;
    }
    
}
