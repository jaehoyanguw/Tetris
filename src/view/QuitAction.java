/*
 * The Tetris Program for the Assignment 6 PART B Tetris.  
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;

/**
 * The quit action for the tetris program.
 * 
 * @author Louis Yang (jeho1994)
 * @version 1.0
 */
public class QuitAction extends AbstractAction {

    /**
     * The Serial Bar Code.
     */
    private static final long serialVersionUID = 3404348933680502744L;
    /**
     * The frame of the tetris program.
     */
    private final JFrame myFrame;
    
    /**
     * The constructor for the QuitAction that exits the program.
     * 
     * @param theFrame the frame of the program.
     */
    public QuitAction(final JFrame theFrame) {
        super("Exit");
        myFrame = theFrame;
        putValue(Action.MNEMONIC_KEY,
                 KeyEvent.getExtendedKeyCodeForChar('x'));
        putValue(Action.SELECTED_KEY, true);
    }

    /**
     * Exits the program.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myFrame.dispatchEvent(new WindowEvent(myFrame,
                                              WindowEvent.WINDOW_CLOSING));
    }
}