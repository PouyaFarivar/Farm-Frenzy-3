import java.security.PublicKey;
import java.util.LinkedList;

public class DataBase {
    private LinkedList <User> users ;
    private LinkedList <Level> levels ;

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

    public int checkUser(String userName) { //0 = dont exist 1 = user
        for (User user : users) {
            if (user.getUserName().equals(userName)) {
                return 1;
            }
        }
        return 0;
    }




}
