/*
 * The Tetris Program for the Assignment 6 PART A Tetris.  
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 * The UserKeyPanel class that shows the keys used by the user to play the tetris game.
 * 
 * @author Louis Yang (jeho1994)
 * @version 1.0
 */
public class UserKeyPanel extends JPanel {
    
    /** The dimension of the panel. */
    public static final Dimension DEFAULT_DIMENSION = new Dimension(300, 375);
    /** Default font size for the panel title. */
    public static final int TITLE_FONT_SIZE = 20;
    /** Default font size for the panel title. */
    public static final int CONTENT_FONT_SIZE = 13;
    /** Default font size for the panel title. */
    public static final String DEFAULT_FONT = "Arial Black";
    
    /**
     * The serial number for the panel.
     */
    private static final long serialVersionUID = -4157022831997039462L;
    
    /**
     * The constructor for the UserKeyPanel Class.
     */
    public UserKeyPanel() {
        super();
        setup();
    }
    
    /**
     * Sets up the UserKeyPanel Class with its components.
     */
    private void setup() {
        this.setSize(DEFAULT_DIMENSION);
        this.setMinimumSize(DEFAULT_DIMENSION);
        this.setPreferredSize(DEFAULT_DIMENSION);
        this.setBackground(Color.LIGHT_GRAY);
        final LineBorder border = new LineBorder(Color.WHITE, 2, true);
        final TitledBorder title = new TitledBorder(border, "USER KEYBUTTONS");
        title.setTitleJustification(TitledBorder.CENTER);
        title.setTitleColor(Color.WHITE);
        title.setTitleFont(new Font(DEFAULT_FONT, Font.BOLD, TITLE_FONT_SIZE));
        this.setBorder(title);
        keyDisplay();
    }
    
    /**
     * Creates the user key displays of the buttons used for tetris game.
     */
    private void keyDisplay() {
        final List<JButton> buttons = new ArrayList<JButton>();
        final JButton a = new JButton("MOVE LEFT: A");
        final JButton d = new JButton("MOVE RIGHT: D");
        final JButton x = new JButton("ROTATE LEFT: X");
        final JButton w = new JButton("ROTATE RIGHT: W");
        final JButton s = new JButton("MOVE DOWN: S");
        final JButton e = new JButton("DROP DOWN: E");
        final JButton n = new JButton("NEW GAME: N");
        final JButton p = new JButton("PAUSE/RESUME: P");
        final JButton q = new JButton("QUIT GAME: Q");  
        buttons.add(a);
        buttons.add(d);
        buttons.add(x);
        buttons.add(w);
        buttons.add(s);
        buttons.add(e);
        buttons.add(n);
        buttons.add(p);
        buttons.add(q);
        this.setLayout(new GridLayout(buttons.size(), 1));
        addButtonSettings(buttons);
    }
    
    /**
     * Helper method to add the keys to the UserKeyPanel.
     * 
     * @param theButton the button or the keys of the game.
     */
    private void addButtonSettings(final List<JButton> theButton) {
        for (int i = 0; i < theButton.size(); i++) {
            final JButton button  = theButton.get(i);
            button.setBackground(Color.LIGHT_GRAY);
            button.setForeground(Color.WHITE);
            button.setBorderPainted(false);
            button.setFocusPainted(false);
            button.setFont(new Font(DEFAULT_FONT, Font.BOLD, CONTENT_FONT_SIZE));
            button.setFocusable(false);
            this.add(button);
        }
    }

    /**
     * Paints all the drawing including the current one drawn.
     * 
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                             RenderingHints.VALUE_ANTIALIAS_ON);
    }
}
