/*
 * The Tetris Program for the Assignment 6 PART A Tetris. 
 */
package runapplication;

import java.awt.EventQueue;

import view.TetrisGUI;

/**
 * Starts the Tetris Game.
 * 
 * @author jeho1994
 * @version 1.0
 */
public final class TetrisMain {

    /**
     * Private constructor to inhibit external instantiation.
     */
    private TetrisMain() {
        throw new IllegalStateException();
    }

    /**
     * The start point for the program; creates the GUI.
     * 
     * @param theArgs command line arguments - ignored in this program
     */
    public static void main(final String... theArgs) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TetrisGUI();
            }
        });
    }
}
