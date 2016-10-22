/*
 * The Tetris Program for the Assignment 6 PART B Tetris.  
 */
package view;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

/**
 * MenuBar for the Tetris Game.
 * 
 * @author Louis Yang (jeho1994)
 * @version 1.0
 */
public class MenuBar extends JMenuBar {

    /** Default tick space. */
    public static final int TICK_SPACE = 5;
    /**
     * The Serial Bar Code.
     */
    private static final long serialVersionUID = -1420520171997104358L;
    /** The file menu. */
    private final JMenu myMenuMenu;
    /** The option menu. */
    private final JMenu myGameSizeMenu;
    /** The tool menu. */
    private final JMenu myOptionMenu;
    /** The help menu. */
    private final JMenu myHelpMenu;
    /** A button group for radio buttons. */
    private final ButtonGroup myGroup;
    
    /**
     * The Menu bar for the PowerPaint.
     */
    public MenuBar() {
        super();
        myMenuMenu = new JMenu("Menu");
        myGameSizeMenu = new JMenu("GameSize");
        myOptionMenu = new JMenu("Option");
        myHelpMenu = new JMenu("Help");
        myGroup = new ButtonGroup();
        setup();
    }
    
    /**
     * The setup of the menubar.
     * 
     */
    private void setup() {
        //1st option menu
        myMenuMenu.setMnemonic('M');     
        //2nd option menu
        myGameSizeMenu.setMnemonic('G');
        //3rd tool menu 
        myOptionMenu.setMnemonic('O');
        //4th help menu
        myHelpMenu.setMnemonic('H');
        this.add(myMenuMenu);
        this.add(myGameSizeMenu);
        this.add(myOptionMenu);
        this.add(myHelpMenu);
    }
    
    /**
     * Adds to the existing menu menu.
     * 
     * @param theAction the action.
     */
    public void addToMenuMenu(final Action theAction) {
        myMenuMenu.add(theAction);
    }
    
    /**
     * Adds to the existing game size menu.
     * 
     * @param theAction the action.
     */
    public void addToGameSizeMenu(final Action theAction) {
        final JRadioButtonMenuItem createdButton = new JRadioButtonMenuItem(theAction);       
        myGroup.add(createdButton);
        myGameSizeMenu.add(createdButton);
    }
    
    /**
     * Adds to the existing option menu.
     * 
     * @param theOption the new option.
     */
    public void addToOptionMenu(final JCheckBoxMenuItem theOption) {
        myOptionMenu.add(theOption);
    }
    
    /**
     * Adds to the existing help menu.
     * 
     * @param theAction the action.
     */
    public void addToHelpMenu(final Action theAction) {
        myHelpMenu.add(theAction);
    }
}
