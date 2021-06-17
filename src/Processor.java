import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Processor {
    private DataBase dataBase ;

    Processor (){
        dataBase = new DataBase() ;
    }

    public void run (){
        load();
        while (true) {
            User user = null;
            if (user == null) {
                user = getUser();
            }
            if (user == null){
                break;
            }
            dataBase.save();
            while (true) {
                Level level = menu(user);
                if (level != null) {
                    play(user, level);
                }else {
                    break;
                }
                dataBase.save();
            }
        }

    }

    public User getUser (){
        Scanner scanner = new Scanner(System.in);
        Matcher matcher ;
        String action = "";
        User user = new User() ;
        boolean done = false;
        boolean name = false ;
        int sign = 0;// 0 = not set 1 = sign in 2 = sign up
        String password = "" ;
        String userName = "" ;
        System.out.println("HI \nWelcome to Farm Frenzy 3");
        while (done == false) {
            action = scanner.nextLine();
            if (action.toLowerCase().contains("log in")) {
                sign = 1 ;
            } else if (action.toLowerCase().contains("sign up")) {
                sign = 2 ;
            } else if ((matcher = getCommandMatcher(action , "username ([A-Za-z0-9_]+[A-Za-z0-9_]*)")).find()) {
                userName = matcher.group(1);
                if (sign == 0){
                    System.out.println("First choose if you want to sign in or sign up .");
                }else if (sign == 1){
                    if (dataBase.getUserByUserName(userName) != null){
                        System.out.println("Enter your password : ");
                        name = true ;
                    }else {
                        System.out.println("No user with such username .");
                    }
                }else if (sign == 2){
                    if (dataBase.getUserByUserName(userName) == null){
                        System.out.println("Enter your password :");
                        name = true ;
                    }else {
                        System.out.println("the chosen username already exists .");
                    }
                }
            }else if ((matcher = getCommandMatcher(action , "password ([A-Za-z0-9_]+[A-Za-z0-9_]*)")).find()){
                password = matcher.group(1) ;
                if (sign == 0){
                    System.out.println("First choose if you want to sign in or sign up .");
                }else if (sign == 1){
                    if (dataBase.getUserByUserName(userName).getPassword().equals(password)){
                        done = true ;
                    }else {
                        System.out.println("WRONG PASSWORD.");
                    }
                }else if (sign == 2){
                    done = true ;
                }
            }else if (action.equals("exit")){
                return null ;
            }else{
                System.out.println("Invalid Input.");
            }
            if (done == true){
                if (sign == 1){
                    user = dataBase.getUserByUserName(userName);
                }else if (sign == 2){
                    user = new User(userName , password);
                    dataBase.getUsers().add(user);
                }
            }
        }
        return user ;
    }

    public Level menu (User user){
        Scanner scanner = new Scanner(System.in);
        Matcher matcher ;
        Level level = new Level() ;
        int levelNum = 0 ;
        String action = "" ;
        boolean done = false ;
        System.out.println("Menu :");
        while (done == false){
            action = scanner.nextLine();
            if ((matcher = getCommandMatcher(action , "start (\\d+)")).find()) {
                levelNum = Integer.parseInt(matcher.group(1));
                if (levelNum  <= user.getMaximumLevel()+1) {
                    level = dataBase.getLevelByNum(levelNum);
                    if (level != null) {
                        done = true;
                    } else {
                        System.out.println("No level with such level Number.");
                    }
                } else {
                    System.out.println("level not unlocked.\ncomplete the level before this level in order to unlock this one.");
                }
            }else if (action.toLowerCase().contains("log out")){
                level = null ;
                done = true ;
            }else if (action.toLowerCase().contains("settings")){
                settings();
            }else {
                System.out.println("Invalid Input.");
            }
        }
        return level ;
    }

    public void play (User user , Level level){
        Game game = new Game(level) ;
        Scanner scanner = new Scanner(System.in);
        Matcher matcher ;
        int turn = 0 ;
        String action = "" ;
        while (true){
            if (turn == 0) {
                action = scanner.nextLine();
                if ((matcher = getCommandMatcher(action, "buy ([A-Za-z0-9_]+[A-Za-z0-9_]*)")).find()) {
                    String name = matcher.group(1);
                    int possible = game.buyAnimal(name);
                    switch (possible) {
                        case 0:
                            System.out.println("Operation successful.");
                            break;
                        case 1:
                            System.out.println("Wrong animal name.");
                            break;
                        case 2:
                            System.out.println("Not enough coins.");
                            break;
                        default:
                            break;
                    }
                } else if ((matcher = getCommandMatcher(action, "pickup (\\d+) (\\d+)")).find()) {
                    int x = Integer.parseInt(matcher.group(1));
                    int y = Integer.parseInt(matcher.group(2));
                    int possible = game.pickup(x, y);
                    switch (possible) {
                        case 0:
                            System.out.println("Operation successful.");
                            break;
                        case 1:
                            System.out.println("Not enough space in ware House.");
                            break;
                        case 2:
                            System.out.println("Given coordinates is empty.");
                            break;
                        case 3 :
                            System.out.println("Predator is not captured.");
                            break;
                        default:
                            break;
                    }
                } else if (action.equals("well")) {
                    int possible = game.fillWell();
                    switch (possible) {
                        case 0:
                            System.out.println("Operation successful.");
                            break;
                        case 1:
                            System.out.println("Well is not empty yet.");
                            break;
                        case 2 :
                            System.out.println("Well is being filled.");
                            break;
                        default:
                            break;
                    }
                } else if ((matcher = getCommandMatcher(action, "plant (\\d+) (\\d+)")).find()) {
                    int x = Integer.parseInt(matcher.group(1));
                    int y = Integer.parseInt(matcher.group(2));
                    int possible = game.plantGrass(x, y);
                    switch (possible) {
                        case 0:
                            System.out.println("Operation successful.");
                            break;
                        case 1:
                            System.out.println("Not enough water.");
                            break;
                        case 2 :
                            System.out.println("Given coordinate is not on the map.");
                            break;
                        default:
                            break;
                    }
                } else if ((matcher = getCommandMatcher(action, "build ([A-Za-z0-9_]+[A-Za-z0-9_]*)")).find()) {
                    String name = matcher.group(1);
                    int possible = game.buildWorkShop(name);
                    switch (possible) {
                        case 0:
                            System.out.println("Operation successful.");
                            break;
                        case 1:
                            System.out.println("You cant build this workShop in this level.");
                            break;
                        case 2:
                            System.out.println("You dont have enough coins");
                            break;
                        case 3:
                            System.out.println("WorkShop already built.");
                            break;
                        default:
                            break;
                    }
                } else if ((matcher = getCommandMatcher(action, "work ([A-Za-z0-9_]+[A-Za-z0-9_]*)")).find()) {
                    String name = matcher.group(1);
                    int possible = game.work(name);
                    switch (possible) {
                        case 0:
                            System.out.println("Operation successful.");
                            break;
                        case 1:
                            System.out.println("Wrong workShop name.");
                            break;
                        case 2:
                            System.out.println("WorkShop not built.");
                            break;
                        case 3:
                            System.out.println("WorkShop is working already.");
                            break;
                        case 4:
                            System.out.println("not enough entry product.");
                        default:
                            break;
                    }
                } else if ((matcher = getCommandMatcher(action, "cage (\\d+) (\\d+)")).find()) {
                    int x = Integer.parseInt(matcher.group(1));
                    int y = Integer.parseInt(matcher.group(2));
                    int possible = game.buildCage(x, y);
                    switch (possible) {
                        case 0:
                            System.out.println("Operation successful.");
                            break;
                        case 1:
                            System.out.println("No predator at the given coordinates.");
                            break;
                        case 2:
                            System.out.println("The Predator at the given coordinates is already captured.");
                            break;
                        default:
                            break;
                    }
                } else if ((matcher = getCommandMatcher(action, "truck load ([A-Za-z0-9_]+[A-Za-z0-9_]*)")).find()) {
                    String name = matcher.group(1);
                    int possible = -1;
                    if (name.equals("lion") || name.equals("bear") || name.equals("tiger")) {
                        possible = game.moveToTruckPredator(name);
                    } else {
                        possible = game.moveToTruckProduct(name);
                    }
                    switch (possible) {
                        case 0:
                            System.out.println("Operation successful.");
                            break;
                        case 1:
                            System.out.println("No such product in ware house.");
                            break;
                        case 2:
                            System.out.println("Not enough space in Truck inventory.");
                            break;
                        case 3 :
                            System.out.println("Truck is moving.");
                            break;
                        default:
                            break;
                    }
                } else if ((matcher = getCommandMatcher(action, "truck unload ([A-Za-z0-9_]+[A-Za-z0-9_]*)")).find()) {
                    String name = matcher.group(1);
                    int possible = -1;
                    if (name.equals("lion") || name.equals("bear") || name.equals("tiger")) {
                        possible = game.unloadFromTruckPredator(name);
                    } else {
                        possible = game.unloadFromTruckProduct(name);
                    }
                    switch (possible) {
                        case 0:
                            System.out.println("Operation successful.");
                            break;
                        case 1:
                            System.out.println("No such product in Truck.");
                            break;
                        case 2:
                            System.out.println("Not enough space in Ware house.");
                            break;
                        case 3 :
                            System.out.println("Truck is moving.");
                            break;
                        default:
                            break;
                    }
                }else if (action.contains("truck go")){
                    int possible = game.truckGo() ;
                    switch (possible){
                        case 0 :
                            System.out.println("Operation successful.");
                            break;
                        case 1 :
                            System.out.println("Truck is empty.");
                            break;
                        case 2 :
                            System.out.println("Truck is already in move.");
                            break;
                        default:
                            break;
                    }
                }else if ((matcher = getCommandMatcher(action , "upgrade ([A-Za-z0-9_]+[A-Za-z0-9_]*)")).find()){
                    String name = matcher.group(1);
                    int possible = game.upgradeWorkShop(name);
                    switch (possible) {
                        case 0:
                            System.out.println("Operation successful.");
                            break;
                        case 1:
                            System.out.println("You cant build this workShop in this level.");
                            break;
                        case 2:
                            System.out.println("You dont have enough coins");
                            break;
                        case 3:
                            System.out.println("WorkShop already upgraded to level 2.");
                            break;
                        case 4 :
                            System.out.println("WorkShop should at least be level 1 to upgrade.");
                        default:
                            break;
                    }
                }
                else if ((matcher= getCommandMatcher(action, "turn (\\d+)")).find()) {
                    turn = Integer.parseInt(matcher.group(1)) ;
                }else if (action.contains("exit")){
                    break;
                }else if (action.contains("inquiry")){
                    game.plot();
                }else{
                    System.out.println("INVALID COMMAND.");
                }
            }else {
                int possible = game.goTurn();
                if (possible == 0) {
                    turn = turn - 1;
                    game.plot();
                    System.out.println();
                    if (turn == 0) {
                        System.out.println("Time passed.");
                    }
                }else {
                    int medal = 0;
                    if (user.getLevelTime().containsKey(game.getLevel().getLevelNum())) {
                        if (user.getLevelTime().get(game.getLevel().getLevelNum()) > game.getTurn()) {
                            user.getLevelTime().put(game.getLevel().getLevelNum(), game.getTurn());
                            user.setCoin(user.getCoin() + game.getCoins() - game.getLevel().getGoalCoins());
                            medal = game.giveMedal() ;
                            if (medal != user.getLevelMedal().get(game.getLevel().getLevelNum())) {
                                user.getLevelMedal().put(game.getLevel().getLevelNum(), medal);
                                switch (medal) {
                                    case 1:
                                        user.setCoin(user.getCoin() + 300);
                                        break;
                                    case 2:
                                        user.setCoin(user.getCoin() + 200);
                                        break;
                                    case 3:
                                        user.setCoin(user.getCoin() + 100);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                    }else {
                        user.getLevelTime().put(game.getLevel().getLevelNum() , game.getTurn()) ;
                        user.setCoin(user.getCoin() + game.getCoins() - game.getLevel().getGoalCoins());
                        user.setMaximumLevel(game.getLevel().getLevelNum());
                        medal = game.giveMedal() ;
                        user.getLevelMedal().put(game.getLevel().getLevelNum() , medal);
                        switch (medal) {
                            case 1:
                                user.setCoin(user.getCoin() + 300);
                                break;
                            case 2:
                                user.setCoin(user.getCoin() + 200);
                                break;
                            case 3:
                                user.setCoin(user.getCoin() + 100);
                                break;
                            default:
                                break;
                        }
                    }
                    System.out.print("Victory : ");
                    switch (medal){
                        case 1 :
                            System.out.println("You got a Gold medal on level " + game.getLevel().getLevelNum());
                            break;
                        case 2 :
                            System.out.println("You got a Silver medal on level " + game.getLevel().getLevelNum());
                            break;
                        case 3 :
                            System.out.println("You got a Bronze medal on level " + game.getLevel().getLevelNum());
                            break;
                        case 4 :
                            System.out.println("No medal.");
                            break;
                        default:
                            break;
                    }

                    break;
                }
            }
        }
    }

    public void settings (){}

    public void load() {
        Gson gson = new Gson() ;
        try {
            dataBase = gson.fromJson(new FileReader("resources\\dataBase.json") , dataBase.getClass());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input.trim());
    }



}
