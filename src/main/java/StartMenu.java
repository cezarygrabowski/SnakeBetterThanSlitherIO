import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Panel that displays start menu
 */
public class StartMenu extends JPanel {
    /**
     * Getter of attribute fontMenu
     * @return fontMenu Font
     */
    public static Font getFontMenu() {
        return fontMenu;
    }

    /**
     * Setter of attribute fontMenu
     * @param fontMenu Font, font to set
     */
    public static void setFontMenu(Font fontMenu) {
        StartMenu.fontMenu = fontMenu;
    }

    /**
     * Getter of attribute fontHeader
     * @return fontHeader Font
     */
    public static Font getFontHeader() {
        return fontHeader;
    }

    /**
     * Setter of attribute setFontHeader
     * @param fontHeader Font to set
     */
    public static void setFontHeader(Font fontHeader) {
        StartMenu.fontHeader = fontHeader;
    }


    private static Font fontMenu;
    private static Font fontHeader;

    /**
     * Method paints this panel
     * @param g Graphics
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawStartMenu(g2);
    }

    /**
     * Method customizes this panel
     * @param g2 Graphics2D
     */
    private void drawStartMenu(Graphics2D g2) {
        fontMenu = new Font(Font.SANS_SERIF, Font.PLAIN, 15);
        fontHeader = new Font(Font.SANS_SERIF, Font.BOLD, 30);
        g2.setColor(Color.decode("#050608"));
        g2.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        g2.setColor(Color.WHITE);
        setLayout(null);
        g2.setFont(fontHeader);
        g2.drawString("Snake", 20, 100);
        g2.setFont(fontMenu);
        g2.drawString("Rozpocznij grę", 20, 200);
        g2.drawString("Tablica wyników", 20, 250);
        g2.drawString("Wyjdź z gry", 20, 300);
        g2.drawString("Autorzy: Marcin Dubiel oraz Cezary Grabowski", 20, 450);

        switch (Game.getMenuSelection()) {
            case "Rozpocznij grę":
                g2.setColor(Color.ORANGE);
                g2.drawString("Rozpocznij grę", 20, 200);
                break;
            case "Tablica wyników":
                g2.setColor(Color.ORANGE);
                g2.drawString("Tablica wyników", 20, 250);
                break;
            case "Wyjdź z gry":
                g2.setColor(Color.ORANGE);
                g2.drawString("Wyjdź z gry", 20, 300);
                break;
        }
    }

    /**
     * Method checks which key is clicked and act accordingly
     * If "up" or "down" key is clicked it changes color of given menu option.
     * "Enter" key, depending on highlighted menu option, closes the game, displays
     * scores Table or launches the game.
     * @param e KeyEvent
     */
    static void checkStartMenuSelection(KeyEvent e) {
        //ESC
        if(e.getKeyCode()==27) {
            System.exit(0);
            //Up
        }else if(e.getKeyCode()==38) {
            switch (Game.getMenuSelection()){
                case "Wyjdź z gry":
                    Game.setMenuSelection("Tablica wyników");
                    Window.frame.repaint();
                    break;
                case "Tablica wyników":
                    Game.setMenuSelection("Rozpocznij grę");
                    Window.frame.repaint();
                    break;
            }

            //Down
        }else if(e.getKeyCode()==40) {
            switch (Game.getMenuSelection()){
                case "Rozpocznij grę":
                    Game.setMenuSelection("Tablica wyników");
                    Window.frame.repaint();
                    break;
                case "Tablica wyników":
                    Game.setMenuSelection("Wyjdź z gry");
                    Window.frame.repaint();
                    break;
            }
            //Enter
        }else if(e.getKeyCode()==10) {
            switch(Game.getMenuSelection()) {
                case "Rozpocznij grę":
                    Game.setInEnterNicknamePanel(true);
                    Game.setStartMenuDisplayed(false);
                    Window.frame.remove(Window.startMenu);
                    Window.enterNickNamePanel.setBounds(0,0,Game.WIDTH,Game.HEIGHT);
                    Game.settextField(new JTextField("Wpisz swój nick"));
                    Game.gettextField().setSize(30,30);
                    Window.enterNickNamePanel.setLayout(new BoxLayout(Window.enterNickNamePanel, BoxLayout.PAGE_AXIS));
                    Window.enterNickNamePanel.add(Game.gettextField());
                    JButton startGameButton = new JButton("Rozpocznij grę");
                    startGameButton.addActionListener(new startGameButtonAction());
                    Window.enterNickNamePanel.add(startGameButton);
                    Window.enterNickNamePanel.setBorder(BorderFactory.createEmptyBorder(150,20,550,800));
                    Window.frame.add(Window.enterNickNamePanel);
                    Window.frame.setVisible(true);
                    Window.frame.repaint();

                    break;
                case "Tablica wyników":
                    //System.exit(0);
                    Window.frame.remove(Window.startMenu);
                    Window.frame.add(Window.scoresTablePanel);
                    Game.setInScoreScreen(true);
                    Game.setStartMenuDisplayed(false);
                    Window.frame.repaint();
                    break;
                case "Wyjdź z gry":
                    System.exit(0);
                    break;
            }
        }
    }
}