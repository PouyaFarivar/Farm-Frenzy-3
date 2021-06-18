import com.google.gson.Gson;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
        LocalDateTime time = LocalDateTime.now() ;
        String log = "" ;
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
                    System.out.println("First choose if you want to log in or sign up .");
                }else if (sign == 1){
                    if (dataBase.getUserByUserName(userName).getPassword().equals(password)){
                        done = true ;
                    }else {
                        System.out.println("WRONG PASSWORD.");
                        log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " WRONG PASSWORD" ;
                        writeLogger(log , userName);
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
                    log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Logged in" ;
                    writeLogger(log , userName);
                }else if (sign == 2){
                    user = new User(userName , password);
                    dataBase.getUsers().add(user);
                    log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Account created" ;
                    writeLogger(log , userName);

                }
            }
        }
        return user ;
    }

    public Level menu (User user){
        LocalDateTime  time  = LocalDateTime.now();
        String log = "" ;
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
                        log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + "No level with such level Number " ;
                        writeLogger(log , user.getUserName());
                    }
                } else {
                    System.out.println("level not unlocked.\ncomplete the level before this level in order to unlock this one.");
                    log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + "level not unlocked" ;
                    writeLogger(log , user.getUserName());

                }
            }else if (action.toLowerCase().contains("log out")){
                level = null ;
                done = true ;
                log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + "logged out" ;
                writeLogger(log , user.getUserName());
            }else if (action.toLowerCase().contains("settings")){
                settings();

            }else {
                System.out.println("Invalid Input.");
                log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Invalid Input" ;
                writeLogger(log , user.getUserName());
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
        LocalDateTime time = LocalDateTime.now();
        String log = "";
        System.out.println("Level " + level.getLevelNum() + " started : ");
        log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + "Level " + level.getLevelNum() + " started" ;
        writeLogger(log , user.getUserName());
        while (true) {
            if (turn == 0) {
                time = LocalDateTime.now() ;
                action = scanner.nextLine();
                if ((matcher = getCommandMatcher(action, "buy ([A-Za-z0-9_]+[A-Za-z0-9_]*)")).find()) {
                    String name = matcher.group(1);
                    int possible = game.buyAnimal(name);
                    switch (possible) {
                        case 0:
                            System.out.println("Operation successful.");
                            log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Bought " + name + " successfully.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 1:
                            System.out.println("Wrong animal name.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Wrong animal name.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 2:
                            System.out.println("Not enough coins.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Not enough coins to buy the animal";
                            writeLogger(log , user.getUserName());
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
                            log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Picked up successfully";
                            writeLogger(log , user.getUserName());
                            break;
                        case 1:
                            System.out.println("Not enough space in ware House.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Not enough space in ware House.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 2:
                            System.out.println("Given coordinates is empty.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Given coordinates is empty.";
                            writeLogger(log , user.getUserName());
                            break;
                        default:
                            break;
                    }
                } else if (action.equals("well")) {
                    int possible = game.fillWell();
                    switch (possible) {
                        case 0:
                            System.out.println("Operation successful.");
                            log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Well filled successfully";
                            writeLogger(log , user.getUserName());
                            break;
                        case 1:
                            System.out.println("Well is not empty yet.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Given coordinates is empty.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 2:
                            System.out.println("Well is being filled.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Well is being filled.";
                            writeLogger(log , user.getUserName());
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
                            log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Grass Planted successfully";
                            writeLogger(log , user.getUserName());
                            break;
                        case 1:
                            System.out.println("Not enough water.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Not enough water.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 2:
                            System.out.println("Given coordinate is not on the map.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Given coordinate is not on the map.";
                            writeLogger(log , user.getUserName());
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
                            log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Built Workshop successfully";
                            writeLogger(log , user.getUserName());
                            break;
                        case 1:
                            System.out.println("You cant build this workShop in this level.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Workshop not available.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 2:
                            System.out.println("You don't have enough coins");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Not enough coins.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 3:
                            System.out.println("WorkShop already built.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " WorkShop already built.";
                            writeLogger(log , user.getUserName());
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
                            log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Workshop working successfully";
                            writeLogger(log , user.getUserName());
                            break;
                        case 1:
                            System.out.println("Wrong workShop name.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Wrong workShop name.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 2:
                            System.out.println("WorkShop not built.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " WorkShop not built.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 3:
                            System.out.println("WorkShop is working already.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " WorkShop is working already.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 4:
                            System.out.println("not enough entry product.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Not enough entry product for the workshop.";
                            writeLogger(log , user.getUserName());
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
                            log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Cage built successfully";
                            writeLogger(log , user.getUserName());
                            break;
                        case 1:
                            System.out.println("No predator at the given coordinates.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " No predator at the given coordinates.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 2:
                            System.out.println("The Predator at the given coordinates is already captured.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Predator already captured.";
                            writeLogger(log , user.getUserName());
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
                            log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Truck loaded successfully.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 1:
                            System.out.println("No such product in ware house.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " No such product available.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 2:
                            System.out.println("Not enough space in Truck inventory.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Not enough space in Truck.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 3:
                            System.out.println("Truck is moving.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Truck is moving";
                            writeLogger(log , user.getUserName());
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
                            log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Truck unloaded successfully.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 1:
                            System.out.println("No such product in Truck.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " No such product in Truck.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 2:
                            System.out.println("Not enough space in Ware house.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Not enough space in Ware house.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 3:
                            System.out.println("Truck is moving.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Moving Truck.";
                            writeLogger(log , user.getUserName());
                            break;
                        default:
                            break;
                    }
                } else if (action.contains("truck go")) {
                    int possible = game.truckGo();
                    switch (possible) {
                        case 0:
                            System.out.println("Operation successful.");
                            log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Truck is moving successfully.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 1:
                            System.out.println("Truck is empty.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Truck is empty.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 2:
                            System.out.println("Truck is already in move.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Moving Truck.";
                            writeLogger(log , user.getUserName());
                            break;
                        default:
                            break;
                    }
                } else if ((matcher = getCommandMatcher(action, "upgrade ([A-Za-z0-9_]+[A-Za-z0-9_]*)")).find()) {
                    String name = matcher.group(1);
                    int possible = game.upgradeWorkShop(name);
                    switch (possible) {
                        case 0:
                            System.out.println("Operation successful.");
                            log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Upgraded workshop successfully.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 1:
                            System.out.println("You can't build this workShop in this level.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Can't build workshop in this level.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 2:
                            System.out.println("You dont have enough coins");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Not enough coins.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 3:
                            System.out.println("WorkShop already upgraded to level 2.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " WorkShop already upgraded.";
                            writeLogger(log , user.getUserName());
                            break;
                        case 4:
                            System.out.println("WorkShop should at least be level 1 to upgrade.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " WorkShop should at least be level 1 to upgrade.";
                            writeLogger(log , user.getUserName());
                        default:
                            break;
                    }
                } else if ((matcher = getCommandMatcher(action, "turn (\\d+)")).find()) {
                    turn = Integer.parseInt(matcher.group(1));
                    log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " turn " + turn ;
                    writeLogger(log , user.getUserName());
                } else if (action.contains("exit")) {
                    log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " exited level " + level.getLevelNum() ;
                    writeLogger(log , user.getUserName());
                    break;
                } else if (action.contains("inquiry")) {
                    game.plot();
                    log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Requested inquiry " ;
                    writeLogger(log , user.getUserName());
                } else {
                    System.out.println("INVALID COMMAND.");
                    log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Invalid Command " ;
                    writeLogger(log , user.getUserName());
                }
            } else {
                int possible = game.goTurn();
                if (possible == 0) {
                    turn = turn - 1;
                    game.plot();
                    System.out.println();
                    if (turn == 0) {
                        System.out.println("Time passed.");
                        log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " turns passed " ;
                        writeLogger(log , user.getUserName());
                    }
                } else {
                    int medal = 0;
                    if (user.getLevelTime().containsKey(game.getLevel().getLevelNum())) {
                        if (user.getLevelTime().get(game.getLevel().getLevelNum()) > game.getTurn()) {
                            user.getLevelTime().put(game.getLevel().getLevelNum(), game.getTurn());
                            user.setCoin(user.getCoin() + game.getCoins() - game.getLevel().getGoalCoins());
                            medal = game.giveMedal();
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
                    } else {
                        user.getLevelTime().put(game.getLevel().getLevelNum(), game.getTurn());
                        user.setCoin(user.getCoin() + game.getCoins() - game.getLevel().getGoalCoins());
                        user.setMaximumLevel(game.getLevel().getLevelNum());
                        medal = game.giveMedal();
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
                    System.out.print("Victory : ");
                    switch (medal) {
                        case 1:
                            System.out.println("You got a Gold medal on level " + game.getLevel().getLevelNum());
                            log = "[Info] " +time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Finished level " + level.getLevelNum() + " with Gold Medal ";
                            writeLogger(log , user.getUserName());
                            break;
                        case 2:
                            System.out.println("You got a Silver medal on level " + game.getLevel().getLevelNum());
                            log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Finished level " + level.getLevelNum() + " Silver Medal ";
                            writeLogger(log , user.getUserName());
                            break;
                        case 3:
                            System.out.println("You got a Bronze medal on level " + game.getLevel().getLevelNum());
                            log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() +  " Finished level " + level.getLevelNum() + " Bronze Medal ";
                            writeLogger(log , user.getUserName());
                            break;
                        case 4:
                            System.out.println("No medal.");
                            log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() +  " Finished level " + level.getLevelNum() + " No Medal ";
                            writeLogger(log , user.getUserName());
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

    public void writeLogger(String str , String userName){
        try {
            LocalDateTime time = LocalDateTime.now() ;
            File f1 = new File("resources\\loggers\\Logger" + userName +".txt");
            if (!f1.exists()) {
                f1.createNewFile() ;
                FileWriter myWriter = new FileWriter(f1);
                myWriter.write("Date created : " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + System.lineSeparator());
                myWriter.write("User name : " + userName + System.lineSeparator());
                myWriter.write("Date of last modification : " + time.toString() + System.lineSeparator());
                myWriter.write(str);
                myWriter.write(System.lineSeparator());
                myWriter.close();
            }else {
                Scanner scanner = new Scanner(f1);
                StringBuffer bf = new StringBuffer() ;
                String oldLine = "";
                while (scanner.hasNextLine()){
                    String trash = scanner.nextLine() ;
                    bf.append(trash + System.lineSeparator());
                    if (trash.contains("Date of last modification :")){
                        oldLine = trash ;
                    }
                }
                String newLine = "Date of last modification : " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() ;
                String fileContent = bf.toString();
                fileContent = fileContent.replace(oldLine , newLine);
                FileWriter writer = new FileWriter(f1);
                writer.append(fileContent);
                writer.flush();
                writer.close();
                FileWriter fileWriter = new FileWriter(f1, true);
                BufferedWriter bw = new BufferedWriter(fileWriter);
                bw.write(str);
                bw.write(System.lineSeparator());
                bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input.trim());
    }



}
