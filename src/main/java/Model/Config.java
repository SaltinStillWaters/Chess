package Model;


public class Config 
{
    public final static int CHESSBOARD_SIZE;   //chess board dimension will be 'x by x'
    public final static int PIECE_SIZE;
    
    static 
    {
        //configurable
        CHESSBOARD_SIZE = 640;
        
        
        //not-configurable
        PIECE_SIZE = CHESSBOARD_SIZE / 9;
    }
    
}
