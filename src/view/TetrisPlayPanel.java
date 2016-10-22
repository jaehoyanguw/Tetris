/*
 * The Tetris Program for the Assignment 6 PART B Tetris. 
 */
package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import model.Block;

/**
 * Prints the current tetris game in progress.
 * 
 * @author Louis Yang (jeho1994)
 * @version 1.0
 */
public class TetrisPlayPanel extends JPanel {
    
    /** The default image of the program. */
    public static final ImageIcon FRAME_IMAGE = new ImageIcon("./images/uwseal.gif");
    /** The dimension of the panel. */
    public static final Dimension DEFAULT_DIMENSION = new Dimension(400, 875);
    /** The columns of the panel. */
    public static final int INITIAL_WIDTH = 10;
    /** The rows of the panel. */
    public static final int INITIAL_HEIGHT = 20;
    /** A minimal difference indicator. */
    public static final double DIFFERENCE = .0000001;
    
    /**
     * Serial number for the panel.
     */
    private static final long serialVersionUID = 1L;
    
    /** Current board setting of the game. */
    private List<Block[]> myBoard;
    /** The width of the panel. */
    private int myWidth;
    /** The height of the panel. */
    private int myHeight;
    /** My game over.*/
    private boolean myGameOver;
    
    /**
     * Constructor for the TetrisPlayPanel.
     */
    public TetrisPlayPanel() {
        super();
        myBoard = new ArrayList<Block[]>();
        myWidth = INITIAL_WIDTH;
        myHeight = INITIAL_HEIGHT;
        myGameOver = false;
        setup();
    }

    
    /** Sets up the tetris program. */
    private void setup() {
        this.setSize(DEFAULT_DIMENSION);
        this.setMinimumSize(DEFAULT_DIMENSION);
        this.setPreferredSize(DEFAULT_DIMENSION);
        this.setBackground(Color.DARK_GRAY);
    }
    
    /**
     * Sets the width and the height of the panel.
     * 
     * @param theWidth the width of the panel.
     * @param theHeight the height of the panel.
     */
    public void setWidthHeight(final int theWidth, final int theHeight) {
        myWidth = theWidth;
        myHeight = theHeight;
    }
    
    /**
     * Clears the current board.
     */
    public void clearBoard() {
        myBoard.clear();
    }
    
    /**
     * Sets the board of the tetris game.
     * 
     * @param theBoard the board data of the current tetris game.
     */
    public void setBoard(final List<Block[]> theBoard) {
        myBoard = theBoard;
    }
    
    /**
     * Assigns whether the game is over.
     * 
     * @param theGameOver the game over.
     */ 
    public void gameIsOver(final boolean theGameOver) {
        myGameOver = theGameOver;
    }
    
    /**
     * Paints all the drawing including the current one drawn.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(Color.WHITE);
        double rows = 0;
        double column = 0;
        final double width = DEFAULT_DIMENSION.getWidth() / myWidth;
        final double height = DEFAULT_DIMENSION.getHeight() / myHeight;
        g2d.drawRect(0, 0, (int) DEFAULT_DIMENSION.getWidth(),
                     (int) DEFAULT_DIMENSION.getHeight());
//        final int x = (this.getWidth() - FRAME_IMAGE.getImage().getWidth(null)) / 2;
//        final int y = (this.getHeight() - FRAME_IMAGE.getImage().getHeight(null)) / 2;
//        g2d.drawImage(FRAME_IMAGE.getImage(), x, y, null);
        final int extraSpace = 5; 
        for (int i = myBoard.size() - extraSpace; i >= 0; i--) {
            final Block[] row = myBoard.get(i);
            for (final Block c : row) {
                if (Math.abs(column - myWidth) < DIFFERENCE) {
                    column = 0;
                }
                if (c != null) {
                    final Shape rectangle = new Rectangle2D.Double(column * width + -1, 
                                                                   rows * height - 1, 
                                                                   width - 1, 
                                                                   height - 1); 
                    g2d.fill(rectangle);
                }
                column++;
            }
            rows++;
        }
        
        if (myGameOver) {
            g2d.drawString("Game Over!", (int) (DEFAULT_DIMENSION.getWidth() / 2), 
                           (int) (DEFAULT_DIMENSION.getHeight() / 2));
        }
    }
}
