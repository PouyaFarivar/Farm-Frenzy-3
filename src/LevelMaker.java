import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LevelMaker {
    static class Leveler {
        DataBase dataBase ;

        Leveler(){
            dataBase = new DataBase() ;
        }

        public void run (){
            load();
            int startingCoins = 0 ;
            int goalCoins = 0 ;
            Matcher matcher  ;
            int levelNum = dataBase.getLevels().size() + 1 ;
            HashMap<String , Integer> animalAchievement = new HashMap<>() ;
            HashMap<String , Integer> productAchievement = new HashMap<>() ;
            HashMap<String , Integer> predators = new HashMap<>() ;
            LinkedList<String> startingAnimals = new LinkedList<>() ;
            LinkedList<String> workshops = new LinkedList<>() ;
            LinkedList<Integer> rewardTimes = new LinkedList<>() ;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Level num : " +levelNum);
            System.out.print("Starting coins : ");
            startingCoins = scanner.nextInt() ;
            System.out.print("Goal coins : ");
            goalCoins = scanner.nextInt() ;
            System.out.print("Gold : ");
            rewardTimes.add(scanner.nextInt()) ;
            System.out.print("Silver : ");
            rewardTimes.add(scanner.nextInt()) ;
            System.out.print("Bronze : ");
            rewardTimes.add(scanner.nextInt());
            System.out.println("Animal achievements : ");
            while (true){
                String action = scanner.nextLine() ;
                if ((matcher = getCommandMatcher(action , "([A-Za-z0-9_]+[A-Za-z0-9_]*) (\\d+)")).find()){
                    animalAchievement.put(matcher.group(1) , Integer.parseInt(matcher.group(2)));
                    System.out.println("added!");
                }else if (action.equals("done")){
                    break;
                }
            }
            System.out.println("Product achievement : ");
            while (true){
                String action = scanner.nextLine() ;
                if ((matcher = getCommandMatcher(action , "([A-Za-z0-9_]+[A-Za-z0-9_]*) (\\d+)")).find()){
                    productAchievement.put(matcher.group(1) , Integer.parseInt(matcher.group(2)));
                    System.out.println("added!");
                }else if (action.equals("done")){
                    break;
                }
            }
            System.out.println("Predators : ");
            while (true){
                String action = scanner.nextLine() ;
                if ((matcher = getCommandMatcher(action , "([A-Za-z0-9_]+[A-Za-z0-9_]*) (\\d+)")).find()){
                    predators.put(matcher.group(1) , Integer.parseInt(matcher.group(2)));
                    System.out.println("added!");
                }else if (action.equals("done")){
                    break;
                }
            }
            System.out.println("Starting animals : ");
            while (true){
                String action = scanner.nextLine() ;
                if ((matcher = getCommandMatcher(action , "([A-Za-z0-9_]+[A-Za-z0-9_]*)")).find()){
                    if (matcher.group(1).equals("hen")){
                        startingAnimals.add(matcher.group(1));
                        System.out.println("added!");
                    }else if (matcher.group(1).equals("ostrich")){
                        startingAnimals.add(matcher.group(1));
                        System.out.println("added!");
                    }else if (matcher.group(1).equals("buffalo")){
                        startingAnimals.add(matcher.group(1));
                        System.out.println("added!");
                    }else if (action.equals("done")){
                        break;
                    }else {
                        System.out.println("Wrong domestic name.");
                    }
                }
            }
            System.out.println("WorkShops : ");
            while (true){
                String action = scanner.nextLine() ;
                if ((matcher = getCommandMatcher(action , "([A-Za-z0-9_]+[A-Za-z0-9_]*)")).find()){
                    if (matcher.group(1).equals("flourMill")){
                        workshops.add(matcher.group(1));
                        System.out.println("added!");
                    }else if (matcher.group(1).equals("bakery")){
                        workshops.add(matcher.group(1));
                        System.out.println("added!");
                    }else if (matcher.group(1).equals("iceCreamShop")){
                        workshops.add(matcher.group(1));
                        System.out.println("added!");
                    }else if (matcher.group(1).equals("milkPackaging")){
                        workshops.add(matcher.group(1));
                        System.out.println("added!");
                    }else if (matcher.group(1).equals("sewingFactory")){
                        workshops.add(matcher.group(1));
                        System.out.println("added!");
                    }else if (matcher.group(1).equals("weavingFactory")){
                        workshops.add(matcher.group(1));
                        System.out.println("added!");
                    }else if (action.equals("done")){
                        break;
                    }
                }
            }
            Level level = new Level(startingCoins ,  goalCoins ,  levelNum , animalAchievement , productAchievement , predators , startingAnimals , workshops , rewardTimes);
            dataBase.getLevels().add(level);
            dataBase.save();
            System.out.println("level " + dataBase.getLevels().size() + " added!");
        }

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
    public static void main(String[] args) {
        Leveler leveler = new Leveler() ;
        leveler.run();
    }
}
