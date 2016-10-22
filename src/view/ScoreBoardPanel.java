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
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import runapplication.SoundPlayer;

/**
 * Prints the current scoreboard and level of the tetris Game.
 * 
 * @author Louis Yang (jeho1994)
 * @version 1.0
 */
public class ScoreBoardPanel extends JPanel {
    
    /** The new game sound effect. */
    public static final String NEW_GAME = "./sounds/wolf.wav";
    /** The moving piece sound effect. */
    public static final String PIECE_MOVE = "./sounds/click.wav";
    /** The game over sound effect. */
    public static final String GAME_OVER = "./sounds/gameover.wav";
    /** The level up sound effect. */
    public static final String LEVEL_UP = "./sounds/levelup.wav";
    /** The dimension of the panel. */
    public static final Dimension DEFAULT_DIMENSION = new Dimension(300, 300);
    /** The default increase of one line cleared. */
    public static final int DEFAULT_ONE_LINE_SCORE = 40;
    /** The default increase of two line cleared. */
    public static final int DEFAULT_TWO_LINE_SCORE = 100;
    /** The default increase of three lines cleared. */
    public static final int DEFAULT_THREE_LINE_SCORE = 300;
    /** The default increase of four lines cleared. */
    public static final int DEFAULT_FOUR_LINE_SCORE = 1200;
    /** The default timer deduction for each level. */
    public static final int DEFAULT_TIMER_DEDUCTION = 200;
    /** One line cleared. */
    public static final int ONE_LINE = 1;
    /** Two lines cleared. */
    public static final int TWO_LINE = 2;
    /** Three lines cleared. */
    public static final int THREE_LINE = 3;
    /** Number of lines needed to move to the next level. */
    public static final int LINE_TO_NEXT_LVL = 5;
    /** Point increase for each freeze block. */
    public static final int FREEZE_BLOCK_POINT = 4;
    /** Default font size for the panel title. */
    public static final int FONT_SIZE = 20;
    /** Default initial point for the tetris score. */
    public static final int INITIAL_POINT = -8;
    /** Level printout to show the current level. */
    public static final String GAME_LEVEL = "LEVEL ";
    /** Score printout to show the current score. */
    public static final String GAME_SCORE = "SCORE ";
    /** Line cleared printout to show the current lines cleared. */
    public static final String GAME_LINE_CLEARED = "CLEARED LINES ";
    /** Lines for level up printout to show the current lines required for level up. */
    public static final String GAME_LINE_NEXT_LVL = "LINE FOR NEXT LVL ";
    /** Font for the display. */
    public static final String STRING_FONT = "Arial Black";
    /**
     * Serial id for the panel.
     */
    private static final long serialVersionUID = -678745043454079742L;  
    /** The current score. */
    private int myScore; // will be used in part b in paint component;
    /** The current level. */
    private int myLevel;
    /** The number of lines cleared. */
    private int myLineCleared; // will be used in part b in paint component;
    /** The number of lines left to the next level. */
    private int myLinesForNextLvl;
    /** Number of lines cleared in current level. */
    private int myLevelLineCount;
    /** Timer used for for advancing the tetris program. */
    private Timer myTimer;
    /** The sound Player that will be playing the song. */
    private final SoundPlayer mySoundPlayer;
    /** Sound for the tetris game is muted. */
    private boolean mySoundMuted;
    
    /**
     * The constructor for the ScoreBoard Panel.
     * 
     * @param theTimer the timer the timer.
     * @param theSoundPlayer the sound player of the game.
     */
    public ScoreBoardPanel(final Timer theTimer, final SoundPlayer theSoundPlayer) {
        super();
        myLevel = 1;
        myScore = INITIAL_POINT;
        myLineCleared = 0;
        myLevelLineCount = 0;
        myLinesForNextLvl = LINE_TO_NEXT_LVL;
        myTimer = theTimer;
        mySoundPlayer = theSoundPlayer;
        mySoundMuted = false;
        setup();
    }
    
    /**
     * Sets up the ScoreBoard Panel.
     */
    private void setup() {
        this.setSize(DEFAULT_DIMENSION);
        this.setMinimumSize(DEFAULT_DIMENSION);
        this.setPreferredSize(DEFAULT_DIMENSION);
        this.setBackground(Color.LIGHT_GRAY);
        final LineBorder border = new LineBorder(Color.WHITE, 2, true);
        final TitledBorder title = new TitledBorder(border, "SCOREBOARD");
        title.setTitleJustification(TitledBorder.CENTER);
        title.setTitleColor(Color.WHITE);
        title.setTitleFont(new Font(STRING_FONT, Font.BOLD, FONT_SIZE));
        this.setBorder(title);
    }
    
    /** Play the new game sound. */
    public void playNewGameSound() {
        if (!mySoundMuted) {
            mySoundPlayer.play(NEW_GAME);
        }
    }
    
    /** Play the game over sound. */
    public void playGameOverSound() {
        if (!mySoundMuted) {
            mySoundPlayer.play(GAME_OVER);
        }
    }
    
    /** Play the piece moved sound. */
    public void playPieceMovedSound() {
        if (!mySoundMuted) {
            mySoundPlayer.play(PIECE_MOVE);
        }
    }
    
    /** Sets wether or not the sound should be muted.
     * 
     *  @param theSoundMute the sound is muted.
     */
    public void setSoundMuted(final boolean theSoundMute) {
        mySoundMuted = theSoundMute;
    }
    
    /**
     * Sets the level.
     * 
     * @param theLevel the current level.
     */
    public void setMyLevel(final int theLevel) {
        myLevel = theLevel; 
    }
    
    /**
     * Adds to the total amount of lines cleared.
     * 
     * @param theLineCleared the lines cleared.
     */
    public void myLinesCleared(final int theLineCleared) {
        myLineCleared += theLineCleared;
        final int nextLevelLineTotal = myLevelLineCount + theLineCleared;
        addToScore(theLineCleared);
        if (nextLevelLineTotal / LINE_TO_NEXT_LVL == 1) {
            if (!mySoundMuted) {
                mySoundPlayer.play(LEVEL_UP);
                System.out.println(myTimer.getDelay());
            }
            myLevel++;
            myLevelLineCount = nextLevelLineTotal % LINE_TO_NEXT_LVL;
            myLinesForNextLvl = LINE_TO_NEXT_LVL - myLevelLineCount;
            if (myLevel <= LINE_TO_NEXT_LVL) {
                myTimer.setDelay(myTimer.getDelay() - DEFAULT_TIMER_DEDUCTION);
            }
        } else {
            myLevelLineCount += theLineCleared;
            myLinesForNextLvl = LINE_TO_NEXT_LVL - myLevelLineCount;
        }
    }
    
    /**
     * adds to the score.
     * 
     * @param theLineCleared the lines cleared.
     */
    private void addToScore(final int theLineCleared) {
        if (theLineCleared == ONE_LINE) {
            myScore = myScore + DEFAULT_ONE_LINE_SCORE * myLevel; 
        } else if (theLineCleared == TWO_LINE) {
            myScore = myScore + DEFAULT_TWO_LINE_SCORE * myLevel; 
        } else if (theLineCleared == THREE_LINE) {
            myScore = myScore + DEFAULT_THREE_LINE_SCORE * myLevel; 
        } else {
            myScore = myScore + DEFAULT_FOUR_LINE_SCORE * myLevel; 
        }
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
        g2d.setColor(Color.WHITE);
        final Font font = new Font(STRING_FONT, Font.BOLD, 15);
        g2d.setFont(font);
        
        final String level = GAME_LEVEL + myLevel;    
        String score = GAME_SCORE + myScore;
        if (myScore < 0) {
            score = GAME_SCORE + 0;
        }
        final String lineCleared = GAME_LINE_CLEARED + myLineCleared;
        final String lineForLvl = GAME_LINE_NEXT_LVL + myLinesForNextLvl;
        final ArrayList<String> drawTexts = new ArrayList<String>();
        drawTexts.add(level);
        drawTexts.add(score);
        drawTexts.add(lineCleared);
        drawTexts.add(lineForLvl);
        for (int i = 0; i < drawTexts.size(); i++) {
            // get the visual bounds of the text using a GlyphVector.
            final FontRenderContext renderContext = g2d.getFontRenderContext();
            final GlyphVector glyphVector = font.createGlyphVector(renderContext, 
                                                                   drawTexts.get(i));
            final Rectangle2D visualBounds = glyphVector.getVisualBounds().getBounds();

            final int x = (int) ((getWidth() - visualBounds.getWidth()) 
                            / 2 - visualBounds.getX());
            final int y = (int) ((((getHeight() / drawTexts.size())
                            - visualBounds.getHeight()) 
                            / 2 - visualBounds.getY())) * (i + 2);
            // display the text visually centered in the JPanel
            g2d.drawString(drawTexts.get(i), x, y);
        }
    }
    
    /**
     * Sets the current timer to the selected timer.
     * 
     * @param theTimer the timer of the game.
     */
    public void setNewTimer(final Timer theTimer) {
        myTimer = theTimer;
    }
    
    /**
     * Used to increment the freeze block point to the score.
     */
    public void addFreezeBlockScore() {
        myScore += FREEZE_BLOCK_POINT;
    }
    
    /**
     * Used for restarting, quitting the game; resetting all the score, level and etc.
     */
    public void clearEverything() {
        myScore = INITIAL_POINT;
        myLevel = 1;
        myLineCleared = 0;
        myLevelLineCount = 0;
        myLinesForNextLvl = LINE_TO_NEXT_LVL;
    }
}
