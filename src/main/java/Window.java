import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Class specifies the main window that will be displayed
 */
public class Window extends Canvas {
    static JFrame frame;
    static StartMenu startMenu;

    static Game game;
    static GameOverPanel gameOverPanel;
    static ScoresTablePanel scoresTablePanel;
    static EnterNickNamePanel enterNickNamePanel;

    /**
     * Constructor that sets height, width, title of the window.
     * Creates JFrame object that will be used throughout the
     * life of the game.
     * @param width int width of the window
     * @param height int height of the window
     * @param title String title of the window
     */
    public Window(int width, int height, String title) {
        startMenu = new StartMenu();
        scoresTablePanel = new ScoresTablePanel();
        enterNickNamePanel = new EnterNickNamePanel();
        scoresTablePanel.setBounds(0,0,Game.WIDTH,Game.HEIGHT); //bez tego nic nie widać
        frame = new JFrame(title);

        gameOverPanel = new GameOverPanel();
        gameOverPanel.setBounds(0,0,Game.WIDTH,Game.HEIGHT);

        //gameOverPanel.add(new JTextArea("dasdas", 6, 10));
        //gameOverPanel.add(new JTextArea("dasdas", 3, 8));
        frame.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                
            }
                
            @Override
            public void keyPressed(KeyEvent e) {
                if(!Game.isRunning()) {
                    if(Game.isStartMenuDisplayed()){
                        StartMenu.checkStartMenuSelection(e);
                    }
                    if(Game.isInScoreScreen()){
                        ScoresTablePanel.reactToESC(e);
                    }
                }
            }
//                    //Game Over Menu
//                }else if(Game.isFinished()==true) {
//
//                    checkGameOverInputs(e);
//
//                    //Start Menu
//                }else if(Game.isStartmenu()==true) {
//
//                    checkStartMenuInputs(e);
//
//                }
//            }
                
            @Override
            public void keyReleased(KeyEvent e) {
                
            }
        });
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        startMenu.setLayout(new BorderLayout());
        startMenu.setBounds(0,0,Game.WIDTH,Game.HEIGHT);

        JLabel logo = new JLabel(new ImageIcon("images/Snake.jpg"));
        startMenu.add(logo, BorderLayout.NORTH);

        frame.add(startMenu);
        frame.setVisible(true);

    }
}
