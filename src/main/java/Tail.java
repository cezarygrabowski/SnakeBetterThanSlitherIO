import java.awt.*;

/**
 * Class implementing tail of snake.
 * Extends GameObject
 */
public class Tail extends GameObject{

    private Handler handler;
    private float size;

    /**
     * Constructor of Tail
     * @param x X coordinate of location
     * @param y Y coordinate of location
     * @param id type of object
     * @param size length of snake
     * @param handler instance of Handler class in game
     */
    public Tail(float x, float y, ID id, float size, Handler handler){
        super(x, y, id);
        this.size = size;
        this.handler = handler;
    }

    /**
     * Getter of size attribute
     * @return current value of size attribute
     */
    public float getSize(){
        return size;
    }

    /**
     * Setter of size attribute
     * @param life new value of size attribute
     */
    public void setSize(float life){
        this.size = life;
    }

    /**
     * Method invoked every time the game refreshes.
     * It is responsible for removing unnecessary parts of tail
     */
    public void tick() {
        if(size > 0){
            size -= 0.05f;
        }
        else {
            handler.removeObject(this);
        }
    }

    /**
     * Method responsible for rendering object on the screen
     * @param g Object of class Graphics
     */
    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect((int)x, (int)y, 32, 32);
    }

    /**
     * Method returns bounds of the object which are used to check collisions
     * @return A object of Rectangle class which specifies the bounds of object
     */
    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, 32 , 32);
    }
}
