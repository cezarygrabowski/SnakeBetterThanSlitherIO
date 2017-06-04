import javax.swing.*;
import java.awt.*;

/**
 * Class displays panel to enter a nickname and launch the game
 */
public class EnterNickNamePanel extends JPanel {

    /**
     * Method paints the component
     * @param g Graphics
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawEnterNicknamePanel(g2);

    }

    /**
     * Method customizes the panel
     * @param g2 Graphics2D
     */
    private void drawEnterNicknamePanel(Graphics2D g2) {
        g2.setColor(Color.decode("#050608"));
        g2.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        g2.setColor(Color.WHITE);
        setLayout(null);
        g2.setFont(StartMenu.getFontHeader());
        g2.drawString("Podaj swój nick:", 20, 100);
        g2.setFont(StartMenu.getFontMenu());
    }

}
