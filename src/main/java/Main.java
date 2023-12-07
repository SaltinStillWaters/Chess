
/**
 *
 * @author Salti
 */
public class Main 
{ 
    public static void main(String[] args) 
    {
        boolean tileColor_IsWhite = false;
        for (int x = 0; x < 8; ++x)
        {
            String col = Character.toString((char) ('A' + x));
            
            for (int y = 0; y < 8; ++y)
            {
                boolean isOccupied = true;
                
                if (y < 2 || y > 5) isOccupied = true;
                else                isOccupied = false;
                
                String coordinate = col + Character.toString((char) ('1' + y));
                
                if (tileColor_IsWhite)  tileColor_IsWhite = false;
                else                    tileColor_IsWhite = true;
                    
            }
            
            if (tileColor_IsWhite)  tileColor_IsWhite = false;
            else                    tileColor_IsWhite = true;
        }
    }
}   
