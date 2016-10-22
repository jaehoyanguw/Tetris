/*
 * The Tetris Program for the Assignment 6 PART B Tetris. 
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import model.Point;
import model.TetrisPiece;

/**
 * Panel that prints the next tetris piece. 
 * 
 * @author Louis Yang (jeho1994)
 * @version 1.0
 */
public class NextPiecePanel extends JPanel {

    /** The dimension of the panel. */
    public static final Dimension DEFAULT_DIMENSION = new Dimension(300, 200);
    /** The width adjustment to make the next piece in the center. */
    public static final int WIDTH_ADJUSTMENT = 50;
    /** Default number of blocks in a tetris piece. */
    public static final int TETRIS_BLOCK_NUMBER = 4;
    /** Default font size for the panel title. */
    public static final int FONT_SIZE = 20;
    
    /**
     * The Serial Bar Code.
     */
    private static final long serialVersionUID = -4219526823371069572L;
    
    /** Next tetris piece of the game. */
    private TetrisPiece myNextPiece;
    /** Each block width of the tetris piece. */
    private final double myBlockWidth;
    /** Each block height of the tetris piece. */
    private final double myBlockHeight;
    
    /**
     * Constructor for the NextPiecePanel.
     */
    public NextPiecePanel() {
        super();
        myBlockWidth = DEFAULT_DIMENSION.getHeight() / TETRIS_BLOCK_NUMBER;
        myBlockHeight = DEFAULT_DIMENSION.getHeight() / TETRIS_BLOCK_NUMBER;           
        setup();
    }
    
    /**
     * Sets up the panel.
     */
    private void setup() {
        this.setSize(DEFAULT_DIMENSION);
        this.setMinimumSize(DEFAULT_DIMENSION);
        this.setPreferredSize(DEFAULT_DIMENSION);
        this.setBackground(Color.LIGHT_GRAY);
        final LineBorder border = new LineBorder(Color.WHITE, 2, true);
        final TitledBorder title = new TitledBorder(border, "NEXTPIECE");
        title.setTitleJustification(TitledBorder.CENTER);
        title.setTitleColor(Color.WHITE);
        title.setTitleFont(new Font("Arial Black", Font.BOLD, FONT_SIZE));
        this.setBorder(title);
    }
    
    /**
     * Changes the next piece of the tetris game.
     * 
     * @param theNextPiece the next tetris piece of the current tetris game.
     */
    public void setNextPiece(final TetrisPiece theNextPiece) {
        myNextPiece = theNextPiece;
    }
    
    /**
     * Paints the next tetris piece of the game.
     * 
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(Color.WHITE);
        final Point[] nextPiecePoints = myNextPiece.getPoints();
        for (int i = 0; i < nextPiecePoints.length; i++) {
            final Point a = (Point) nextPiecePoints[i];
            final int x = a.x();
            final int y = a.y() + 1;
            final Shape rectangle = new Rectangle2D.Double(WIDTH_ADJUSTMENT 
                                                           + x * myBlockWidth, 
                                                        (4 -  y) * myBlockHeight, 
                                                           myBlockWidth - 2, 
                                                           myBlockHeight - 2);
            g2d.fill(rectangle);
            
        }
    }
}
