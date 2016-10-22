/*
 * The Tetris Program for the Assignment 6 PART B Tetris. 
 */
package view;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Block;
import model.Board;
import model.TetrisPiece;
import runapplication.SoundPlayer;

/**
 * Tetris game.
 * 
 * @author Louis Yang (jeho1994)
 * @version 1.0
 */
public class TetrisGUI implements Observer {

    /** The default image of the program. */
    public static final ImageIcon FRAME_IMAGE = new ImageIcon("./images/uwseal.gif");
    /** The default tetris game song. */
    public static final String GAME_SONG = "./sounds/uwsong.wav";
    /** The default game over sound effect. */
    public static final String GAME_OVER = "./sounds/gameover.wav";
    /** The default line cleared sound effect. */
    public static final String LINE_CLEARED = "./sounds/explosion.wav";
    /** The dimension of the program. */
    public static final Dimension DEFAULT_DIMENSION = new Dimension(1000, 800);
    /** The default time change of the tetris program. */
    public static final int INITIAL_DELAY = 1000;
    /** The board used for the tetris game.*/
    private Board myBoard;
    /** The top level Window for this GUI. */
    private final JFrame myFrame;
    /** The panel used for displaying the game. */
    private final TetrisPlayPanel myTetrisPlayPanel;
    /** The panel used for displaying the next tetris piece of the game. */
    private final NextPiecePanel myNextPiecePanel;
    /** The panel used for displaying the current scoring board of the game. */
    private ScoreBoardPanel myScoreBoardPanel;
    /** The panel that displays the user interaction for playing this game. */
    private final UserKeyPanel myUserKeyPanel;
    /** Timer used for for advancing the tetris program. */
    private Timer myTimer;
    /** Timer action for the timer. */
    private final TimerAction myTimerAction;
    /** User interaction listener. */
    private UserInputListener myUserInputListener;
    /** SoundPlayer. */
    private SoundPlayer mySoundPlayer; 
    /** Sound for the tetris game is muted. */
    private boolean mySoundMuted;
    
    /**
     * TetrisGui Constructor.
     */
    public TetrisGUI() {
        myBoard = new Board();
        myFrame =  new JFrame("Tetris");
        myTetrisPlayPanel = new TetrisPlayPanel();
        myNextPiecePanel = new NextPiecePanel();
        myUserKeyPanel = new UserKeyPanel();
        myTimerAction = new TimerAction(myBoard);
        myTimer = new Timer(INITIAL_DELAY, myTimerAction);
        mySoundMuted = false;
        setup();
    }
    
    /** Sets up the tetris program. */
    private void setup() {
        mySoundPlayer = new SoundPlayer();
        myUserInputListener = new UserInputListener(myBoard, myTimer, myScoreBoardPanel);
        myScoreBoardPanel = new ScoreBoardPanel(myTimer, mySoundPlayer);
        mySoundPlayer.loop(GAME_SONG);
        myFrame.setIconImage(FRAME_IMAGE.getImage());
        addKeyListener();
        final MenuBar menuBar = new MenuBar();
        final QuitAction quitAction = new QuitAction(myFrame);
        final Dimension regularGame = new Dimension(10, 20);
        final Dimension midGame = new Dimension(15, 30);
        final Dimension largeGame = new Dimension(30, 40);
        final JCheckBoxMenuItem muteOption = newMuteFunction();
        menuBar.addToGameSizeMenu(new TetrisSizeAction(regularGame));
        menuBar.addToGameSizeMenu(new TetrisSizeAction(midGame));
        menuBar.addToGameSizeMenu(new TetrisSizeAction(largeGame));
        menuBar.addToOptionMenu(muteOption);
        menuBar.addToMenuMenu(quitAction);
        menuBar.addToHelpMenu(new AboutAction(FRAME_IMAGE));
        myFrame.setJMenuBar(menuBar);
        myFrame.add(myTetrisPlayPanel, BorderLayout.CENTER);
        final JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.setAlignmentX(BoxLayout.LINE_AXIS);
        rightPanel.add(myNextPiecePanel);
        rightPanel.add(myUserKeyPanel);
        rightPanel.add(myScoreBoardPanel);
        myFrame.add(rightPanel, BorderLayout.EAST);
        myFrame.pack();
        myFrame.setResizable(false);
        myBoard.addObserver(this);
        myBoard.newGame();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        /** A ToolKit. */
        final Toolkit kit = Toolkit.getDefaultToolkit();    
        //centers the program in the center
        myFrame.setLocation(
            (int) (kit.getScreenSize().getWidth() / 2 - myFrame.getWidth() / 2),
            (int) (kit.getScreenSize().getHeight() / 2 - myFrame.getHeight() / 2));
        myFrame.pack();
        myFrame.setVisible(true);
    }
    
    /** Sound mute or not mute effect. 
     * 
     *  @return wether or not if the sound is muted for the tetris game.
     */
    private JCheckBoxMenuItem newMuteFunction() {
        final JCheckBoxMenuItem soundMuted = new JCheckBoxMenuItem("SOUND MUTE",
                                                                       false);
        soundMuted.setMnemonic('S');
        soundMuted.addItemListener(new ItemListener() {
            public void itemStateChanged(final ItemEvent theItem) {
                mySoundMuted = soundMuted.isSelected();
                myScoreBoardPanel.setSoundMuted(mySoundMuted);
                if (mySoundMuted) {
                    mySoundPlayer.stopAll();
                } else {
                    mySoundPlayer.play(GAME_SONG);
                }
            }
        });
        return soundMuted;
    }
    
    /**
     * Sets the new dimension of the tetris game.
     * 
     * @param theWidth the width of the new tetris dimension.
     * @param theHeight the height of the new tetris dimension.
     */
    private void setNewDimensionGame(final int theWidth, final int theHeight) {
        myTimer.removeActionListener(myTimerAction);
        myBoard.deleteObserver(this);
        myBoard = new Board(theWidth, theHeight);
        myBoard.addObserver(this);
        myTimer = new Timer(INITIAL_DELAY, new TimerAction(myBoard));
        myFrame.removeKeyListener(myUserInputListener);
        myUserInputListener = new UserInputListener(myBoard, myTimer, myScoreBoardPanel);
        myFrame.addKeyListener(myUserInputListener);
    }
    
    /**
     * Adds the key listener to the frame.
     */
    private void addKeyListener() {
        myFrame.addKeyListener(new UserInputListener(myBoard, myTimer, myScoreBoardPanel));

    }
    
    // This class implements Observer
    /**
     * filters which update and repaint to perform.
     * 
     * {@inheritDoc}
     */
    @Override
    public void update(final Observable arg0, final Object arg1) {
        if (arg1 instanceof ArrayList<?>) {
            final ArrayList<Block[]> blockArray = (ArrayList<Block[]>) arg1; // unavoidable.
            myTetrisPlayPanel.setBoard(blockArray);
            myTetrisPlayPanel.repaint();
        } else if (arg1 instanceof Integer[]) {
            if (!mySoundMuted) {
                mySoundPlayer.play(LINE_CLEARED);
            }
            final Integer[] linesClearedList = (Integer[]) arg1;
            final int linesCleared = linesClearedList.length;
            myScoreBoardPanel.myLinesCleared(linesCleared);
            myScoreBoardPanel.repaint();
        } else if (arg1 instanceof Boolean) {
            if (!mySoundMuted) {
                mySoundPlayer.play(GAME_OVER);
                myTimer.stop();
            }
            JOptionPane.showMessageDialog(
                                         null, 
                                         "PRESS N TO REPLAY", 
                                         "GAME OVER", JOptionPane.INFORMATION_MESSAGE);
        } else {
            final TetrisPiece nextPiece = (TetrisPiece) arg1;
            myNextPiecePanel.setNextPiece(nextPiece);
            myNextPiecePanel.repaint();
            myScoreBoardPanel.addFreezeBlockScore();
            myScoreBoardPanel.repaint();
        }
    }    
    
    /**
     * Tetris size adjustment action.
     * 
     * @author Louis Yang (jeho1994)
     * @version 1.0
     */
    public final class TetrisSizeAction extends AbstractAction {

        /**
         * Serial number for the class.
         */
        private static final long serialVersionUID = 191381439467642488L;
        /** the width of the new game.*/
        private final int myWidth;
        /** the height of the new game.*/
        private final int myHeight;
        
        /**
         * The constructor for the TetrisSizeAction.
         * 
         * @param theDimension the dimension of the game.
         */
        public TetrisSizeAction(final Dimension theDimension) {
            super("SIZE " + (int) theDimension.getWidth() + " X " 
                            + (int) theDimension.getHeight());
            myWidth = (int) theDimension.getWidth();
            myHeight = (int) theDimension.getHeight();
            putValue(Action.SELECTED_KEY, true);
        }

        /**
         * Sets the tetris game to a certain size.
         * 
         * {@inheritDoc}
         */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myTimer.stop();
            myUserInputListener.setGameEnded();
            myTetrisPlayPanel.clearBoard();
            myTetrisPlayPanel.repaint();
            setNewDimensionGame(myWidth, myHeight);
            myTetrisPlayPanel.setWidthHeight(myWidth, myHeight);
            myScoreBoardPanel.clearEverything();
            myUserInputListener.setBoardChanged();
            JOptionPane.showMessageDialog(
                                          null, 
                                          "PRESS N TO PLAY", 
                                          "GAME RESET", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
