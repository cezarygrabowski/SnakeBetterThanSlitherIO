import java.awt.*;

/**
 * Class implementing basic food for snake in game
 * extending GameObject class
 */
public class BasicFood extends GameObject {
    /**
     * Standard constructor of GameObject
     * @param x X coordinate of location
     * @param y Y coordinate of location
     * @param id type of object
     */
    public BasicFood(int x, int y, ID id) {
        super(x, y, id);
    }

    /**
     * Method returns bounds of the object which are used to check collisions
     * @return A object of Rectangle class which specifies the bounds of object
     */
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32 , 32);
    }

    /**
     * Method invoked every time the game refreshes.
     * Does absolutely nothing in this class
     */
    public void tick() {
    }

    /**
     * Method responsible for rendering object on the screen
     * @param g Object of class Graphics
     */
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect((int)x, (int)y, 32, 32);
    }
}
