import java.awt.*;

/**
 * Class implementing moving food for snake in game
 * extending GameObject class
 */
public class MovingFood extends GameObject {
    /**
     * Constructor of MovingFood
     * @param x X coordinate of location
     * @param y Y coordinate of location
     * @param id type of object
     */
    public MovingFood(int x, int y, ID id) {
        super(x, y, id);
        velX = 2;
        velY = 2;
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
     * Implements movement of MovingFood object during game.
     */
    public void tick() {
        x += velX;
        y += velY;
        if(x <= 0 || x >= Game.WIDTH - 32) velX *= -1;
        if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
    }

    /**
     * Method responsible for rendering object on the screen
     * @param g Object of class Graphics
     */
    public void render(Graphics g) {
        g.setColor(Color.pink);
        g.fillRect((int)x, (int)y, 32, 32);
    }
}
