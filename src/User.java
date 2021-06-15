import java.util.HashMap;
import java.util.LinkedList;

public class User {
    private String userName ;
    private String password ;
    private int coin ;
    private int maximumLevel ;
    private HashMap <Integer , Integer> levelTime ;// level num , turn
    private HashMap <Integer , Integer> levelMedal ;// 0 = no medal 1 = gold 2 = silver 3 = bronze || prize bronze = 100 coin silver = 200 coin bronze = 300 coin

    User (String userName , String password ){
        this.userName = userName ;
        this.password = password ;
        coin = 0 ;
        maximumLevel = 0 ;
        levelTime = new HashMap<>() ;
        levelMedal = new HashMap<>() ;
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

    public HashMap<Integer, Integer> getLevelMedal() {
        return levelMedal;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public void setMaximumLevel(int maximumLevel) {
        this.maximumLevel = maximumLevel;
    }
}
