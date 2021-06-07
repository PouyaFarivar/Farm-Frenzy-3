import java.io.*;
import java.util.LinkedList;
import com.google.gson.* ;

public class DataBase {
    private LinkedList <User> users ;
    private LinkedList <Level> levels ;

    DataBase(){
        users = new LinkedList<>() ;
        levels = new LinkedList<>() ;
    }

    public void save() {
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();
        String databaseString = gson.toJson(this) ;
        wirte("dataBase.json" , databaseString);
    }


    private void wirte(String fileName, String nemune) {
        try {
            File file = new File("resources\\" + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file) ;
            fileWriter.write(nemune);
            fileWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

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
