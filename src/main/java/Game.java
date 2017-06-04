import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Timer;

/**
 * Main game class
 */
public class Game extends Canvas implements Runnable{
    
    static final int WIDTH = 1024;
    static final int HEIGHT = WIDTH / 12 * 9;
    private static Timer scheduler;
    private final Spawner spawner;
    private HUD hud;
    private static boolean inScoreScreen = false;

    private static String nick;

    private static Thread thread;

    private static boolean inEnterNicknamePanel = false;

    private static boolean running = false;
    private static boolean gameEnded = false;
    private static boolean startMenuDisplayed= true;
    private static boolean paused = false;
    private static String selectedMenuOption = "Rozpocznij grę";
    private static JTextField textField;
    private Handler handler;

    /**
     * Constructor of Game
     */
    public Game(){
        handler = new Handler();
        hud = new HUD();
        spawner = new Spawner(handler);
        Snake snake = new Snake(300,300,ID.Snake, 2, handler, hud);
        handler.addObject(snake);
        handler.addObject(new BasicFood(100,100,ID.BasicFood));
        handler.addObject(new MovingFood(300,300,ID.MovingFood));
        handler.addObject(new PoisonousFood(800,400,ID.PoisonousFood));
        this.addKeyListener(new KeyInput(snake));

    }

    /**
     * Method starts Thread
     */
    synchronized void start() {
        try {
            thread = new Thread(this);
            thread.start();
            running = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Method that ends the game and displays gameOverPanel
     * @param msg consist cause of death
     */
    static void gameOver(String msg){
        Window.frame.remove(Window.game);
        JLabel label = new JLabel(msg);

        label.setForeground(Color.WHITE);
        label.setFont(StartMenu.getFontHeader());

        Window.gameOverPanel.add(label);

        Window.frame.add(Window.gameOverPanel);
        Window.frame.setVisible(true);
        Window.frame.repaint();

        CsvModule.writeToCsv(Game.getNick(), Snake.hud.getScore());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.exit(0);

    }

    /**
     * Method used to keep variable in range of min and max which are provided as 2nd and 3rd parameters
     * @param var int , variable to be checked
     * @param min int , minimal allowed value of given variable
     * @param max int , maximal allowed value of given variable
     * @return int , if value of var is higher/lower than max/min returns max/min otherwise returns var
     */
    public static int clamp(int var, int min, int max){
        if(var >= max){
            return max;
        }
        if(var <= min){
            return  min;
        }
        return var;
    }

    /**
     * Main game loop. Runs game with set amount of ticks per second and fps
     */
    public void run() {
        this.requestFocus(); scheduler = new Timer();
        scheduler.schedule(spawner, 0, 1000);
        long lastTick = System.nanoTime();
        long lastFPS = lastTick;
        double amountOfTicks = 60.0;
        double amountOfFPS = 1000.0;
        double nsTick = 1000000000 / amountOfTicks;
        double nsFPS = 1000000000 / amountOfFPS;
        double deltaTick = 0;
        double deltaFPS = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            deltaTick += (now - lastTick) / nsTick;
            lastTick = now;
            while (deltaTick >= 1) {
                tick();
                deltaTick--;
            }
            now = System.nanoTime();
            deltaFPS += (now - lastFPS) / nsFPS;
            lastFPS = now;
            if (running && deltaFPS >= 1) {
                render();
                frames++;
                deltaFPS--;
            } if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    }

    /**
     * Method responsible for rendering game window
     */
    public void render(){
        BufferStrategy bufferStrat = this.getBufferStrategy();
        if(bufferStrat == null){
            this.createBufferStrategy(2);
            return;
        }
        Graphics g = bufferStrat.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0,0,WIDTH,HEIGHT);
        handler.render(g);
        hud.render(g);
        g.dispose();
        bufferStrat.show();
    }
    /**
     * Method invoked every time the game refreshes.
     * Invokes tick() methods of handler and hud objects
     */
    public void tick(){
        handler.tick();
        hud.tick();
    }

    public static void main(String args[]){ new Window(WIDTH, HEIGHT, "Wunsz"); }

    /**
     * Getter of attribute thread
     * @return attribute thread
     */
    public static Thread getThread() {
        return thread;
    }

    /**
     * Setter of attribute thread
     * @param thread Thread object
     */
    public static void setThread(Thread thread) {
        Game.thread = thread;
    }

    /**
     * Getter of attribute inScoreScreen
     * @return inScoreScreen boolean
     */
    public static boolean isInScoreScreen() {
        return inScoreScreen;
    }

    /**
     * Setter of attribute inScoreScreen
     * @param inScoreScreen boolean
     */
    public static void setInScoreScreen(boolean inScoreScreen) {
        Game.inScoreScreen = inScoreScreen;
    }

    /**
     * Getter of attribute startMenuDisplayed
     * @return startMenuDisplayed
     */
    public static boolean isStartMenuDisplayed() {
        return startMenuDisplayed;
    }

    /**
     * Setter of attribute startMenuDisplayed
     * @param isStartMenuDisplayed boolean
     */
    public static void setStartMenuDisplayed(boolean isStartMenuDisplayed) {
        Game.startMenuDisplayed= isStartMenuDisplayed;
    }

    /**
     * Getter of attribute paused
     * @return paused paused
     */
    public static boolean isPaused() { return paused; }

    /**
     * Setter of attribute paused
     * @param paused boolean
     */
    public static void setPaused(boolean paused) { Game.paused = paused; }

    /**
     * Getter of attribute running
     * @return running boolean
     */
    static boolean isRunning() { return running; }

    /**
     * Setter of attribute running
     * @param running boolean
     */
    public static void setRunning(boolean running) { Game.running = running; }

    /**
     * Setter of attribute selectedMenuOption
     * @param menuSelection String
     */
    static void setMenuSelection(String menuSelection) {
        selectedMenuOption = menuSelection;
    }

    /**
     * Getter of attribute gameEnded
     * @return gameEnded boolean
     */
    public static boolean isGameEnded() {
        return gameEnded;
    }

    /**
     * Setter of attribute gameEnded
     * @param gameEnded boolean
     */
    public static void setGameEnded(boolean gameEnded) {
        Game.gameEnded = gameEnded;
    }

    /**
     * Getter of attribute textField
     * @return textField
     */
    public static JTextField gettextField() {
        return textField;
    }

    /**
     * Setter of attribute textField
     * @param textField JTextField
     */
    public static void settextField(JTextField textField) {
        Game.textField = textField;
    }

    /**
     * Getter of attribute nick
     * @return nick
     */
    public static String getNick() {
        return nick;
    }

    /**
     * Setter of attribute nick
     * @param nick String
     */
    public static void setNick(String nick) {
        Game.nick = nick;
    }

    /**
     * Getter of attribute inEnterNicknamePanel
     * @return inEnterNicknamePanel
     */
    public static boolean isInEnterNicknamePanel() {
        return inEnterNicknamePanel;
    }

    /**
     * Setter of attribute inEnterNicknamePanel
     * @param inEnterNicknamePanel boolean
     */
    public static void setInEnterNicknamePanel(boolean inEnterNicknamePanel) {
        Game.inEnterNicknamePanel = inEnterNicknamePanel;
    }

    /**
     * Getter of attribute selectedMenuOption
     * @return selectedMenuOption
     */
    static String getMenuSelection() { return selectedMenuOption; }
}