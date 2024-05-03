package View;

import Model.Pieces.ChessPiece;
import Control.Promotion;
import Model.Pieces.Queen;
import Model.Pieces.Bishop;
import Model.Pieces.Rook;
import Model.Pieces.Knight;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class PromotionFrame extends JFrame {

    private ChessPiece selectedPiece;
    private TilePanel pawnDestTile;

    public PromotionFrame(boolean isWhite, TilePanel pawnDestTile) {
        this.setTitle("Promotion");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new GridLayout(1, 4));

        String[] pieceOptions = {"Queen", "Rook", "Bishop", "Knight"};
        for (String option : pieceOptions) {
            PiecePanel piecePanel = new PiecePanel(option, isWhite);
            piecePanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    // Determine the selected piece based on the option
                    selectedPiece = createChessPiece(option, isWhite);
                    // Close the promotion frame
                    dispose();
                    // Handle the promotion in the Promotion class
                    Promotion.promotePawn(isWhite, pawnDestTile, pawnDestTile, selectedPiece);
                }
            });
            this.add(piecePanel);
        }

        this.pack();
        this.setLocationRelativeTo(null);
    }

    private ChessPiece createChessPiece(String option, boolean isWhite) {
        // Create and return the corresponding chess piece based on the option
        switch (option) {
            case "Queen":
                return new Queen(isWhite);
            case "Rook":
                return new Rook(isWhite);
            case "Bishop":
                return new Bishop(isWhite);
            case "Knight":
                return new Knight(isWhite);
            default:
                return null; // Handle invalid option gracefully
        }
    }

    private class PiecePanel extends JPanel {
        private String pieceName;
        private ImageIcon image;

        public PiecePanel(String pieceName, boolean isWhite) {
            this.pieceName = pieceName;
            this.setPreferredSize(new Dimension(100, 100)); // Adjust size as needed

            // Load image based on piece name and color
            try {
               if(isWhite){
                URL imageURL = getClass().getResource("/Pieces/White/" + pieceName + ".png");
                image = new ImageIcon(imageURL);
               } else{
                URL imageURL = getClass().getResource("/Pieces/Black/" + pieceName + ".png");
                image = new ImageIcon(imageURL);
               }
            } catch (Exception e) {
                System.err.println("Error loading image: " + e.getMessage());
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Draw the piece image
            if (image != null) {
                g.drawImage(image.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
