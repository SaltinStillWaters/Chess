package Model.Pieces;

public abstract class ChessPiece 
{
    public final boolean isWhite;
    public final String name;
    public final String imagePath;
    
    public ChessPiece(boolean isWhite, String name)
    {
        this.isWhite = isWhite;
        this.name = name;
        
        String color;
        if (isWhite)    color = "White";
        else            color = "Black";
        
        StringBuilder imagePathTemp = new StringBuilder();
        imagePathTemp.append(color);
        imagePathTemp.append("/");
        imagePathTemp.append(name);
        
        this.imagePath = imagePathTemp.toString();
    }
    
    
    public abstract boolean checkMove(String currCoordinate, String destCoordinate);  
    
    public void move(String currCoordinate, String destCoordinate)
    {
        //code
    }
            
}
