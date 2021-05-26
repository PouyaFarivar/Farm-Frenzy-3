import java.security.PublicKey;
import java.util.LinkedList;

public class DataBase {
    private LinkedList <User> users ;
    private LinkedList <Level> levels ;
    private LinkedList <Domestic> domestics ;
    private LinkedList <Defender> defenders ;
    private LinkedList <Predator> predators ;

    DataBase(){}

    public void save(){}
    public void load(){}

    public User getUserByUserName (String userName){
        for (User user : users){
            if (user.getUserName().equals(userName)){
                return user ;
            }
        }
        return null ;
    }

    public Level getLevelByNum (int num){
        for (Level level : levels){
            if (level.getLevelNum() == num){
                return level ;
            }
        }
        return null ;
    }

    public LinkedList<Level> getLevels() {
        return levels;
    }

    public LinkedList<User> getUsers() {
        return users;
    }
}
