/*
 * The Tetris Program for the Assignment 6 PART B Tetris. 
 */
package view;


import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;


import model.Board;

/**
 * The Timer Action for the Tetris Game.
 * 
 * @author Louis Yang (jeho1994)
 * @version 1.0
 */
public class TimerAction extends AbstractAction {

    /**
     * Serial id for the Timer Action.
     */
    private static final long serialVersionUID = -680434042933701670L;
    /**
     * The board of the tetris game.
     */
    private final Board myBoard;
    
    /**
     * Constructor for the Timer Action Class.
     * 
     * @param theBoard the board of the game.
     */
    public TimerAction(final Board theBoard) {
        super();
        myBoard = theBoard;
    }
    
    /**
     * Advances the tetris game by one step. 
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myBoard.step();
    }
}
