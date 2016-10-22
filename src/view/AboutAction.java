/*
 * The Tetris Program for the Assignment 6 PART B Tetris.  
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Description that explains how the tetris game works.
 * 
 * @author Louis Yang (jeho1994)
 * @version 1.0
 */
public class AboutAction extends AbstractAction {

    /**
     * Serial number for the abstract action.
     */
    private static final long serialVersionUID = -8557003812984553991L;
    /**
     * The tetris game message that shows how the game works.
     */
    private String myMessage;
    /**
     * The matching icon used in the frame.
     */
    private final ImageIcon myFrameIconMatch;
    
    
    /**
     * Constructor for the About that creates the about instructions for the tetris program.
     * 
     * @param theImage the image icon.
     */
    public AboutAction(final ImageIcon theImage) {
        super("About...");
        myFrameIconMatch = theImage;
        putValue(Action.MNEMONIC_KEY,
                 KeyEvent.getExtendedKeyCodeForChar('A'));
        putValue(Action.SELECTED_KEY, true);
        setMessage();
    }
    
    /**
     * Sets the informative message about the tetris program.
     */
    private void setMessage() {
        myMessage = "WELCOME TO THE TETRIS GAME HOSTED BY LOUIS YANG\n\n" 
                        +
                    "SCORING ALGORITHM\n\n" 
                        +
                    "                   1 line     2 lines     3 lines     4 lines\n" 
                        +
                    "Level 1:     40          100          300           400\n" 
                        +
                    "Level 2:     80          200          600           2400\n" 
                        +
                    "Level 3:     120        300          900           3600\n" 
                        +
                    "Level 10:   400       1000         3000        12000\n" 
                        +
                    "Level n:     40 * (n) 100 * (n)  300 * (n)  1200 * (n)\n\n" 
                        +
                    "SPEED DOES NOT INCREASE AFTER LVL 5\n";
    }

    /**
     * Shows information about the PowerPaint Program.
     */
    @Override
    public void actionPerformed(final ActionEvent theAction) {
        JOptionPane.showMessageDialog(
                                      null, 
                                      myMessage, 
                                      "About", JOptionPane.INFORMATION_MESSAGE,
                                      myFrameIconMatch);
    }
}
