import java.util.HashMap;
import java.util.LinkedList;

public class Level {
    private int levelNum ;
    private int startingCoins ;
    private HashMap <String , Integer> animalAchievement ;//name of domestic animal , number of it
    private HashMap <String , Integer> productAchievement ;//name of the product , number of it
    private HashMap <String , Integer> predators ;// name of the predator , turn when he comes in
    private int goalCoins ;
    private LinkedList <Integer> rewardTimes ;// 0 = gold 1= silver 3= bronze
    private LinkedList <String> startingAnimals ;
    private LinkedList <String> workshops ;

    Level (int startingCoins , int goalCoins , int levelNum , HashMap<String , Integer> animalAchievement , HashMap<String , Integer>productAchievement , HashMap<String , Integer>predators , LinkedList<String>startingAnimals , LinkedList<String>workshops , LinkedList<Integer>rewardTimes){
        this.levelNum = levelNum ;
        this.startingCoins = startingCoins ;
        this.goalCoins = goalCoins ;
        this.rewardTimes = rewardTimes ;
        this.animalAchievement =  animalAchievement;
        this.productAchievement = productAchievement;
        this.predators = predators;
        this.startingAnimals =startingAnimals ;
        this.workshops = workshops ;
    }

    Level(){}

    public int getLevelNum() {
        return levelNum;
    }

    public LinkedList<String> getWorkshops() {
        return workshops;
    }

    public LinkedList<String> getStartingAnimals() {
        return startingAnimals;
    }

    public int getGoalCoins() {
        return goalCoins;
    }

    public HashMap<String, Integer> getAnimalAchievement() {
        return animalAchievement;
    }

    public HashMap<String, Integer> getProductAchievement() {
        return productAchievement;
    }

    public HashMap<String, Integer> getPredators() {
        return predators;
    }

    public int getStartingCoins() {
        return startingCoins;
    }

    public LinkedList<Integer> getRewardTimes() {
        return rewardTimes;
    }
}
