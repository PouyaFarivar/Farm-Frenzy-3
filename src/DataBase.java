import java.io.*;
import java.security.PublicKey;
import java.util.LinkedList;

public class DataBase {
    private LinkedList <User> users ;
    private LinkedList <Level> levels ;

    DataBase(){}

    public void save() {
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();
        String databaseString = gson.toJson(this) ;
        wirte("dataBase.json" , databaseString);
    }

    public void load() {
        Gson gson = new Gson() ;
        try {
            dataBase = gson.fromJson(new FileReader("resource\\dataBase.json") , dataBase.getClass());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }


    private void wirte(String fileName, String nemune) {
        try {
            File file = new File("resource\\" + fileName);
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
