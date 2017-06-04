import java.util.Random;
import java.util.TimerTask;

/**
 * Class implementing spawning of food every x seconds.
 * Extends TimerTask class
 */
public class Spawner extends TimerTask{
    private static Handler handler;
    private static Random random = new Random();
    public static int counter = 0;

    /**
     * Constructor of Spawner
     * @param handler instance of Handler class in game
     */
    public Spawner(Handler handler) {
        Spawner.handler = handler;
    }

    public void run(){
        int basic = 3;
        int poisonous = 5;
        int moving = 4;
        int cleanse = 20;
        counter++;
        if(counter % basic == 0){
            handler.addObject(new BasicFood(random.nextInt(Game.WIDTH-32), random.nextInt(Game.HEIGHT-32), ID.BasicFood));
        }
        if(counter % poisonous == 0){
            handler.addObject(new PoisonousFood(random.nextInt(Game.WIDTH-32), random.nextInt(Game.HEIGHT-32), ID.PoisonousFood));
        }
        if(counter % moving == 0){
            handler.addObject(new MovingFood(random.nextInt(Game.WIDTH-32), random.nextInt(Game.HEIGHT-32), ID.MovingFood));
        }
        if(counter % cleanse == 0){
            handler.addObject(new CleanseFood(random.nextInt(Game.WIDTH-32), random.nextInt(Game.HEIGHT-32), ID.CleanseFood));
        }
    }
}

