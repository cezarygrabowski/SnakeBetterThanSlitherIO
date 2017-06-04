import javax.swing.*;
import java.awt.*;

/**
 * Panel extends JPanel. Displays your score and
 * reason of your death at the end of the game.
 */
public class GameOverPanel extends JPanel {
    /**
     * Paints this panel
     * @param g Graphics
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGameOverPanel(g2);
    }

    /**
     * Method customizes this panel
     * @param g2 Graphics2D
     */
    private void drawGameOverPanel(Graphics2D g2) {
        g2.setColor(Color.decode("#050608"));
        g2.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        g2.setColor(Color.WHITE);
        setLayout(null);
        g2.setFont(StartMenu.getFontHeader());
        g2.drawString("Twój wynik to:", 20, 100);
        g2.drawString(Integer.toString(Snake.hud.getScore()), 350, 100);

    }

}
