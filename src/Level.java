import java.util.HashMap;
import java.util.LinkedList;

public class Level {
    private int levelNum ;
    private int startingCoins ;
    private HashMap <String , Integer> animalAchievement ;//name of domestic animal , number of it
    private HashMap <String , Integer> productAchievement ;//name of the product , number of it
    private HashMap <String , Integer> predators ;// name of the predator , turn when he comes in
    private int goalCoins ;
    private LinkedList <Integer> rewardTimes ;
    private LinkedList <Domestic> startingAnimals ;
    private LinkedList <Workshop> workshops ;

    Level ( int startingCoins , int goalCoins , int levelNum){
        this.levelNum = levelNum ;
        this.startingCoins = startingCoins ;
        this.goalCoins = goalCoins ;
        rewardTimes = new LinkedList<>() ;
        animalAchievement = new HashMap<>() ;
        productAchievement = new HashMap<>() ;
        predators = new HashMap<>() ;
        startingAnimals = new LinkedList<>() ;
        workshops = new LinkedList<>() ;
    }

    Level(){}

    public int getLevelNum() {
        return levelNum;
    }

    public LinkedList<Workshop> getWorkshops() {
        return workshops;
    }

    public LinkedList<Domestic> getStartingAnimals() {
        return startingAnimals;
    }
}
