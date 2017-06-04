import java.awt.*;
import java.util.Iterator;

/**
 * Class implementing snake controlled by player.
 * Extends GameObject
 */
public class Snake extends GameObject {
    private float size;
    private Handler handler;
    static HUD hud;


    /**
     * Constructor of Snake
     * @param x X coordinate of location
     * @param y Y coordinate of location
     * @param id type of object
     * @param size length of snake
     * @param handler instance of Handler class in game
     * @param hud instance of HUD class in game
     */
    public Snake(float x, float y, ID id, float size, Handler handler, HUD hud) {
        super(x, y, id);
        this.size = size;
        this.handler = handler;
        this.hud = hud;
    }

    /**
     * Method checks and responds to collision of snake with other objects within a game
     */
    private void collision() {
        GameObject tempObject;
        for (int i = 0; i < handler.objects.size(); i++) {
            tempObject = handler.objects.get(i);
            //basic food collision
            if (tempObject.getId() == ID.BasicFood) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //System.out.println("Collision with " + tempObject.getId());
                    handler.removeObject(tempObject);
                    hud.setScore(hud.getScore() + 1);
                    hud.setHunger(hud.getHunger() - 10);
                }
            }
            // poisonous food collision
            if (tempObject.getId() == ID.PoisonousFood) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //System.out.println("Collision with " + tempObject.getId());
                    handler.removeObject(tempObject);
                    hud.setScore(hud.getScore() - 1);
                    hud.setHunger(hud.getHunger() + 30);
                }
            }
            // moving food collision
            if (tempObject.getId() == ID.MovingFood) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //System.out.println("Collision with " + tempObject.getId());
                    handler.removeObject(tempObject);
                    hud.setScore(hud.getScore() + 1);
                    hud.setHunger(hud.getHunger() - 10);
                }
            }
            // cleanse food collision
            if (tempObject.getId() == ID.CleanseFood) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    //System.out.println("Collision with " + tempObject.getId());
                    handler.removeObject(tempObject);
                    for (Iterator<GameObject> iterator = handler.objects.iterator(); iterator.hasNext(); ) {
                        tempObject = iterator.next();
                        if (tempObject.getId() == ID.PoisonousFood) {
                            iterator.remove();
                        }
                        hud.setScore(hud.getScore() + 1);
                        hud.setHunger(hud.getHunger() - 10);
                    }
                }
            }
        }
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
     * It is responsible for movement, generating tail and checking collisions every game tick.
     */
    public void tick() {
        x += velX;
        y += velY;
        x = Game.clamp((int)x, 0, Game.WIDTH - 32);
        y = Game.clamp((int)y, 0, Game.HEIGHT - 32);

        if(hud.getHunger() >= 100){
            Game.gameOver("Umarłeś z głodu.");
        }
        collision();
        handler.addObject(new Tail(x, y, ID.Tail, size, handler));
    }

    /**
     * Method responsible for rendering object on the screen
     * @param g Object of class Graphics
     */
    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect((int)x, (int)y, 32, 32);
    }
}
