import java.awt.*;
import java.util.LinkedList;

/**
 * Class that implements cumulative handling for all elements in the game
 */
public class Handler {

    LinkedList<GameObject> objects = new LinkedList<GameObject>();

    /**
     * Method invokes tick() method of every exiting object in game
     */
    public void tick(){
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i);
            tempObject.tick();
        }
    }

    /**
     * Method invokes render() method of every exiting object in game
     * @param g Object of class Graphics
     */
    public void render(Graphics g){
        for(int i = 1; i < objects.size(); i++){
            GameObject tempObject = objects.get(i);
            tempObject.render(g);
        }
        GameObject tempObject = objects.get(0);
        tempObject.render(g);
    }

    /**
     * Method implementing adding objects to list of all objects
     * @param object GameObject which is added to list of all objects in game
     */
    public void addObject(GameObject object){
        this.objects.add(object);
    }
    /**
     * Method implementing removing objects from list of all objects
     * @param object GameObject which is removed from list of all objects in game
     */
    public void removeObject(GameObject object){
        this.objects.remove(object);
    }
}
