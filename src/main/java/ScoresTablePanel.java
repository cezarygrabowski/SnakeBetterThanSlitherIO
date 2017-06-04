import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/**
 * Panel responsible for showing the actual score board.
 */
public class ScoresTablePanel extends JPanel{
    /**
     * Method paints this panel
     * @param g Graphics
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        showScores(g2);
    }

    /**
     * Method customizes this panel
     * @param g2 Graphics
     */
    private void showScores(Graphics2D g2){
        int y = 100;
        int i=1;

        g2.setColor(Color.decode("#050608"));
        g2.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        g2.setColor(Color.WHITE);
        g2.setFont(StartMenu.getFontHeader());
        setLayout(null);

        ArrayList<Map.Entry<String, Integer>> scores;
        scores = CsvModule.readScores("scores.csv");
        //sort
        Collections.sort(scores, new Comparator<Map.Entry<String,Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if(o1.getValue() < o2.getValue()){
                    return 1;
                }else if(o1.getValue() > o2.getValue()){
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        g2.drawString("Top 10", 20, 50);
        g2.setFont(StartMenu.getFontMenu());
        g2.drawString("Nick", 200, 50);
        g2.drawString("Ilość punktów", 450, 50);
        g2.drawString("Kliknij ESC aby powrócić do głównego menu", 50, 650);
        for (Map.Entry entry: scores ) {
            g2.drawString(Integer.toString(i) + ". " + entry.getKey().toString(), 200, y);
            g2.drawString(entry.getValue().toString(), 450, y);
            y+=30;
            i++;
        }
    }

    /**
     * Method that reacts if "ESC" was clicked
     * @param e KeyEvent
     */
    public static void reactToESC(KeyEvent e) {
        if(e.getKeyCode() == 27){
            Window.frame.remove(Window.scoresTablePanel);
            Window.frame.add(Window.startMenu);
            Window.frame.repaint();
            Game.setInScoreScreen(false);
            Game.setStartMenuDisplayed(true);
        }
    }
}