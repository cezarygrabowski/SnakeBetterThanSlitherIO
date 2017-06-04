import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Custom ActionListener that implements ActionListener
 */
public class startGameButtonAction implements ActionListener {
    /**
     * Method specifies behaviour after the button is clicked
     * @param e ActionEvent
     */
    public void actionPerformed(ActionEvent e) {
        Game.setNick(Game.gettextField().getText());
        Window.frame.remove(Window.enterNickNamePanel);
        Window.game = new Game();
        Window.game.setBounds(0,0,Game.WIDTH,Game.HEIGHT);
        Window.frame.add(Window.game);
        Window.game.start();
        Window.frame.repaint();
    }
}
