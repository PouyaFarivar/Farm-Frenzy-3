import java.util.HashMap;
import java.util.LinkedList;

public class User {
    private String userName ;
    private String password ;
    private int coin ;
    private int maximumLevel ;
    private HashMap <Integer , Integer> levelTime ;// level num , turn

    User (String userName , String password ){
        this.userName = userName ;
        this.password = password ;
        coin = 0 ;
        maximumLevel = 0 ;
        levelTime = new HashMap<>() ;
    }

    User (){}

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getMaximumLevel() {
        return maximumLevel;
    }

    public HashMap<Integer, Integer> getLevelTime() {
        return levelTime;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public void setMaximumLevel(int maximumLevel) {
        this.maximumLevel = maximumLevel;
    }
}
