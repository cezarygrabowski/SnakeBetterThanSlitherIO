import java.awt.*;

/**
 * Abstract class being the basis of all objects within the game
 */
public abstract class GameObject {
    protected float x, y;
    protected ID id;
    protected float velX, velY;

    /**
     * Constructor of GameObject
     * @param x X coordinate of location
     * @param y Y coordinate of location
     * @param id type of object
     */
    public GameObject(float x, float y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    /**
     * Method invoked every time the game refreshes.
     */
    public abstract void tick();

    /**
     * Method responsible for rendering object on the screen
     * @param g Object of class Graphics
     */
    public abstract void render(Graphics g);

    /**
     * Method returns bounds of the object which are used to check collisions
     * @return A object of Rectangle class which specifies the bounds of object
     */
    public abstract Rectangle getBounds();

    /**
     * Setter of attribute x
     * @param x New value of attribute x
     */
    public void setX(float x){
        this.x = x;
    }

    /**
     * Setter of attribute x
     * @param y New value of attribute x
     */
    public void setY(float y){
        this.y = y;
    }

    /**
     * Getter of x coordinate
     * @return current value of attribute x
     */
    public float getX(){
        return x;
    }

    /**
     * Getter of y coordinate
     * @return current value of attribute y
     */
    public float getY(){
        return y;
    }

    /**
     * Setter of attribute id
     * @param id New value of attribute x
     */
    public void setId(ID id){
        this.id = id;
    }

    /**
     * Getter of x attribute
     * @return current value of attribute x
     */

    public ID getId(){
        return id;
    }
    /**
     * Setter of X velocity
     * @param velX New value of attribute velX
     */

    public void setVelX(float velX) {
        this.velX = velX;
    }
    /**
     * Setter of Y velocity
     * @param velY New value of attribute velY
     */
    public void setVelY(float velY) {
        this.velY = velY;
    }
    /**
     * Getter of velocity X
     * @return current value X velocity
     */
    public float getVelX(){
        return velX;
    }

    /**
     * Getter of velocity Y
     * @return current value Y velocity
     */
    public float getVelY(){
        return velY;
    }
}
