import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Processor {
    private DataBase dataBase ;

    Processor (){
        dataBase = new DataBase() ;
    }

    public void run (){
        dataBase.load();
        while (true){
            User user = getUser() ;
            if (user == null){
                break;
            }
            Level level = getLevel(user);
            if (level == null){
                break;
            }
            play(user , level);
            dataBase.save();
        }

    }

    public User getUser (){
        Scanner scanner = new Scanner(System.in);
        String action = "";
        User user = new User() ;
        boolean done = false;
        boolean haveAcc = false;
        System.out.println("HI \nWelcome to Farm Frenzy 3\nIf you want to Sign in , type in 'sign in' , If you want to Sign up type in 'sign up' :");
        while (true) {
            action = scanner.nextLine();
            if (action.toLowerCase().contains("sign in")) {
                haveAcc = true;
                break;
            } else if (action.toLowerCase().contains("sign up")) {
                break;
            } else {
                System.out.println("Please Enter a valid option . \n'sign in' or 'sign up' : ");
            }
        }
        while (done = false){
            Matcher matcher ;
            action = scanner.nextLine();
            String userName = "" ;
            String password = "" ;
            if (haveAcc == true){
                System.out.println("Enter your username : ");
                if ((matcher = getCommandMatcher(action , "username ([A-Za-z0-9_]+[A-Za-z0-9_]*)")).find()){
                    userName = matcher.group(1);
                    if (dataBase.checkUser(userName) == 1){
                        System.out.println("Dear " + userName + " please enter your password : ");
                        while (true) {
                            action = scanner.nextLine();
                            if ((matcher = getCommandMatcher(action, "password ([A-Za-z0-9_]+[A-Za-z0-9_]*)")).find()) {
                                password = matcher.group(1);
                                if (dataBase.getUserByUserName(userName).getPassword().equals(password)) {
                                    done = true;
                                    break;
                                } else {
                                    System.out.println("WRONG PASSWORD\n");
                                }
                            }
                        }

                    }

                }

            }
        }

    }

    public Level getLevel (User user){

    }

    public void play (User user , Level level){
        Game game = new Game(level) ;
    }

    private Matcher getCommandMatcher(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(input.trim());
    }

}
