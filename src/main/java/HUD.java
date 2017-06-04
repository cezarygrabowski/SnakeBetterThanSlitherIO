import java.awt.*;

/**
 * Class implementing Heads-Up Display including score and hunger calculation
 */
public class HUD {
    private int score;
    private int hunger;

    /**
     * Constructor of HUD
     */
    public HUD(){
        score = 0;
        hunger = 0;
    }

    /**
     * Method invoked every time the game refreshes.
     * Implements increase of hunger over time.
     */
    public void tick() {
        hunger += 1;
        hunger = Game.clamp(hunger, 0, 1000);
    }

    /**
     * Method responsible for rendering object on the screen
     * @param g Object of class Graphics
     */
    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(16, 16, 200, 32);
        g.setColor(Color.red);
        g.fillRect(16, 16, hunger/10 * 2, 32);
        g.setColor(Color.white);
        g.drawRect(16, 16, 200, 32);
        g.setColor(Color.white);
        g.drawString("Score: " + score, 16 , 16);
        g.drawString("Hunger: " + getHunger() + "%", 16 , 32);

    }


    /**
     * Setter of score attribute
     * @param score new value of score attribute
     */
    public void setScore(int score){
        this.score = score;
    }

    /**
     * Getter of score attribute
     * @return current value of score attribute
     */
    public int getScore(){
        return score;
    }

    /**
     * Setter of hunger attribute
     * @param hunger new value of hunger attribute
     */
    public void setHunger(int hunger){
        this.hunger = hunger*10;
    }

    /**
     * Getter of hunger attribute
     * @return current value of hunger attribute
     */
    public int getHunger(){
        return hunger/10;
    }
}
