/*
 * The Tetris Program for the Assignment 6 PART A Tetris.  
 */
package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

import model.Board;

/**
 *  User input of the tetris game.
 * 
 * @author Louis Yang (jeho1994)
 * @version 1.0
 */
public class UserInputListener extends KeyAdapter {

    /** The default time change of the tetris program. */
    public static final int INITIAL_DELAY = 1000;
    /** The board used for the tetris game. */
    private Board myBoard;
    /** the Timer of the tetris game. */
    private final ScoreBoardPanel myScorePanel;
    /** the Timer of the tetris game. */
    private Timer myTimer;
    /** My game ended. */
    private boolean myGameStarted;
    /** Game has been paused. */
    private boolean myGamePaused;
    /** board size has been changed. */
    private boolean myBoardSizeChanged;
    
    /**
     * Constructor for the user input listener class.
     * 
     * @param theBoard the board of the game.
     * @param theTimer the timer of the game.
     * @param theScorePanel the score panel of the game.
     */
    public UserInputListener(final Board theBoard, final Timer theTimer, 
                             final ScoreBoardPanel theScorePanel) {
        super();
        myBoard = theBoard;
        myTimer = theTimer;
        myScorePanel = theScorePanel;
        myGameStarted = false;
        myGamePaused = false;
        myBoardSizeChanged = false;
    }
    
    /**
     * Sets the timer to a new timer.
     * 
     * @param theTimer the timer of the game.
     */
    public void setTimer(final Timer theTimer) {
        myTimer = theTimer;
    }
    
    /**
     * Changes the board with a new dimension.
     * 
     * @param theBoard the board of the game.
     */
    public void setBoard(final Board theBoard) {
        myBoard = theBoard;
    }
    
    
    /**
     * Sets the game to done playing.
     */
    public void setGameEnded() {
        myGameStarted = false;
    }
    
    /**
     * Moves the current moving tetris piece. 
     * 
     * {@inheritDoc}
     */
    @Override
    public void keyTyped(final KeyEvent theEvent) {
        if (myGameStarted && !myGamePaused) {
            keyTypedHelper(theEvent);
        }
        if (theEvent.getKeyChar() == 'n') {
            myScorePanel.playNewGameSound();
            myScorePanel.clearEverything();
            myGameStarted = true;
            myGamePaused = false;
            myBoard.newGame();
            myBoardStarted();
            myTimer.setDelay(INITIAL_DELAY);
            myTimer.start();
        } else if (theEvent.getKeyChar() == 'p' && myGameStarted) {
            if (myGamePaused) {
                myGamePaused = false;
                myTimer.start();
            } else {
                myGamePaused = true;
                myTimer.stop();
            }
        } else if (theEvent.getKeyChar() == 'q' && myGameStarted) {
            myScorePanel.playGameOverSound();
            myScorePanel.clearEverything();
            myBoard.newGame();
            myTimer.stop();
            myTimer.setDelay(INITIAL_DELAY);
            myGameStarted = false;
            myGamePaused = false;
        }
    }
    
    /**
     * Helper method for key events. 
     *
     * @param theEvent the key board event.
     */
    private void keyTypedHelper(final KeyEvent theEvent) {
        if (theEvent.getKeyChar() == 'w') {
            myScorePanel.playPieceMovedSound();
            myBoard.rotateCW();
        } else if (theEvent.getKeyChar() == 'a') {
            myScorePanel.playPieceMovedSound();
            myBoard.left();
        } else if (theEvent.getKeyChar() == 's') {
            myScorePanel.playPieceMovedSound();
            myBoard.down();
        } else if (theEvent.getKeyChar() == 'd') {
            myScorePanel.playPieceMovedSound();
            myBoard.right();
        } else if (theEvent.getKeyChar() == 'x') {
            myScorePanel.playPieceMovedSound();
            myBoard.rotateCCW();
        } else if (theEvent.getKeyChar() == 'e') {
            myScorePanel.playPieceMovedSound();
            myBoard.drop();
        } 
    }
    
    /**
     * Helper method to check if the board has been resized recently.
     */
    private void myBoardStarted() {
        if (myBoardSizeChanged) {
            myScorePanel.addFreezeBlockScore();
            myBoardSizeChanged = false;
        }
    }
    
    /**
     * Changes the state of the board size changed into true.
     */
    public void setBoardChanged() {
        myBoardSizeChanged = true;
    }
}
