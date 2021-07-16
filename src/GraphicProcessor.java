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
import javafx.scene.control.Alert.AlertType;
import java.util.regex.Pattern;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.Scene;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.FileInputStream;


public class GraphicProcessor
{
    private DataBase dataBase ;

    GraphicProcessor (){
        dataBase = new DataBase() ;
    }

    public void run (){
        //load();
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
    public Group play (User user , Level level) throws FileNotFoundException
    {
        Game game = new Game(level) ;
        Group root = new Group();
        //Images
        Image image= new Image(new FileInputStream("D:/project files/background 2.jpg"));
        Image truck= new Image(new FileInputStream("D:/project files/truck.png"));
        Image icecream_fct= new Image(new FileInputStream("D:/project files/ice cream fct.png"));
        Image sewing_fct= new Image(new FileInputStream("D:/project files/sewing.png"));
        Image weaving_fct= new Image(new FileInputStream("D:/project files/weaving.png"));
        Image bakery= new Image(new FileInputStream("D:/project files/bakery.png"));
        Image milk_fct= new Image(new FileInputStream("D:/project files/milkfct.png"));
        Image warehouse= new Image(new FileInputStream("D:/project files/warehouse.png"));
        Image poultry= new Image(new FileInputStream("D:/project files/poultry.png"));
        Image mill= new Image(new FileInputStream("D:/project files/mill.png"));
        Image well= new Image(new FileInputStream("D:/project files/well.png"));

        Image dog= new Image(new FileInputStream("D:/project files/dog.png"));
        Image bear= new Image(new FileInputStream("D:/project files/bear.png"));
        Image cat= new Image(new FileInputStream("D:/project files/cat.png"));
        Image lion= new Image(new FileInputStream("D:/project files/lion.png"));
        Image turkey= new Image(new FileInputStream("D:/project files/turkey.png"));
        Image buffalo= new Image(new FileInputStream("D:/project files/buffalo.png"));
        Image tiger= new Image(new FileInputStream("D:/project files/tiger.png"));
        Image hen= new Image(new FileInputStream("D:/project files/hen.png"));
        Image milk_bucket= new Image(new FileInputStream("D:/project files/bucket of milk.png"));
        Image milk_packet= new Image(new FileInputStream("D:/project files/packetmilk.png"));
        Image egg= new Image(new FileInputStream("D:/project files/egg.png"));
        Image icecream= new Image(new FileInputStream("D:/project files/icecream.png"));
        Image flour= new Image(new FileInputStream("D:/project files/flour.png"));
        Image fabric= new Image(new FileInputStream("D:/project files/fabric.png"));
        Image feather= new Image(new FileInputStream("D:/project files/feather.png"));
        Image clothes= new Image(new FileInputStream("D:/project files/clothes.png"));
        Image grass= new Image(new FileInputStream("D:/project files/grass.png"));



        //Setting the image view
        ImageView imageView = new ImageView(image);
        ImageView imageView_mill = new ImageView(mill);
        ImageView imageView_icecreamfct = new ImageView(icecream_fct);
        ImageView imageView_sewingfct  = new ImageView(sewing_fct);
        ImageView imageView_weavingfct = new ImageView(weaving_fct);
        ImageView imageView_milkfct = new ImageView(milk_fct);
        ImageView imageView_bakery = new ImageView(bakery);
        ImageView imageView_poultry = new ImageView(poultry);
        ImageView imageView_warehouse = new ImageView(warehouse);
        ImageView imageView_well = new ImageView(well);

        ImageView imageView_dog = new ImageView(dog);
        ImageView imageView_cat = new ImageView(cat);
        ImageView imageView_bear = new ImageView(bear);
        ImageView imageView_buffalo = new ImageView(buffalo);
        ImageView imageView_hen = new ImageView(hen);
        ImageView imageView_tiger = new ImageView(tiger);
        ImageView imageView_lion = new ImageView(lion);
        ImageView imageView_turkey = new ImageView(turkey);
        ImageView imageView_feather = new ImageView(feather);
        ImageView imageView_fabric = new ImageView(fabric);
        ImageView imageView_clothes = new ImageView(clothes);
        ImageView imageView_packetmilk = new ImageView(milk_packet);
        ImageView imageView_bucketmilk = new ImageView(milk_bucket);
        ImageView imageView_icecream = new ImageView(icecream);
        ImageView imageView_egg = new ImageView(egg);
        ImageView imageView_flour = new ImageView(flour);
        ImageView imageView_grass = new ImageView(grass);

        //Setting the position of the image
        imageView.setX(0); imageView.setY(0);
        imageView_icecreamfct.setX(10); imageView_icecreamfct.setY(60);
        imageView_milkfct.setX(10); imageView_milkfct.setY(300);
        imageView_mill.setX(10); imageView_mill.setY(490);
        imageView_sewingfct.setX(1110); imageView_sewingfct.setY(20);
        imageView_weavingfct.setX(1110); imageView_weavingfct.setY(250);
        imageView_poultry.setX(900); imageView_poultry.setY(8);
        imageView_bakery.setX(1140); imageView_bakery.setY(450);
        imageView_well.setX(550); imageView_well.setY(30);

        //setting the fit height and width of the image view
        imageView.setFitHeight(700); imageView.setFitWidth(1400);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(false);

        Image turn_im = new Image(new FileInputStream("D:/project files/download.jpg"));
        ImageView imageView_turn = new ImageView(turn_im);
        Button btnturn = new Button();
        btnturn.setGraphic(imageView_turn);
        btnturn.setLayoutX(420);btnturn.setLayoutY(0);
        Button button_hen= new Button ("Hen");
        button_hen.setLayoutX(0);button_hen.setLayoutY(0); button_hen.setPrefSize(70,70);
        Button button_turkey= new Button ("Turkey");
        button_turkey.setLayoutX(70);button_turkey.setLayoutY(0); button_turkey.setPrefSize(70,70);
        Button button_buffalo= new Button ("Buffalo");
        button_buffalo.setLayoutX(140);button_buffalo.setLayoutY(0); button_buffalo.setPrefSize(70,70);
        Button button_dog= new Button ("Dog");
        button_dog.setLayoutX(210);button_dog.setLayoutY(0); button_dog.setPrefSize(70,70);
        Button button_cat= new Button ("Cat");
        button_cat.setLayoutX(280);button_cat.setLayoutY(0); button_cat.setPrefSize(70,70);
        Button button_warehouse= new Button ();
        button_warehouse.setGraphic(imageView_warehouse);
        button_warehouse.setLayoutX(600);button_warehouse.setLayoutY(650); button_warehouse.setPrefSize(100,50);
        Button build = new Button ("Build");
        build.setLayoutX(350);build.setLayoutY(0); build.setPrefSize(70,70);
        GridPane pane = new GridPane();

        Scanner scanner = new Scanner(System.in);
        Matcher matcher ;
        int turn = 0 ;
        String action = "" ;
        LocalDateTime time = LocalDateTime.now();
        String log = "";
        log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + "Level " + level.getLevelNum() + " started" ;
        writeLogger(log , user.getUserName());
        while (true) {
            if (turn == 0) {
                time = LocalDateTime.now() ;
                EventHandler<MouseEvent> handler_buy = new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent mouseEvent)
                    {
                        String name="";
                        if(mouseEvent.getSource()==button_cat)
                            name="cat";
                        if(mouseEvent.getSource()==button_dog)
                            name="dog";
                        if(mouseEvent.getSource()==button_hen)
                            name="hen";
                        if(mouseEvent.getSource()==button_buffalo)
                            name="buffalo";
                        if(mouseEvent.getSource()==button_turkey)
                            name="ostrich";
                        int possible = game.buyAnimal(name);
                        Alert a = new Alert(AlertType.NONE);
                        switch (possible) {
                            case 0:
                                log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Bought " + name + " successfully.";
                                writeLogger(log , user.getUserName());
                                break;
                            case 2:
                                // set alert type
                                a.setAlertType(AlertType.ERROR);

                                // set content text
                                a.setContentText("You don't have enough coins!");

                                // show the dialog
                                a.show();
                                log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Not enough coins to buy the animal";
                                writeLogger(log , user.getUserName());
                                break;
                            default:
                                break;
                        }
                    }
                };
                button_turkey.setOnMouseClicked(handler_buy);
                button_dog.setOnMouseClicked(handler_buy);
                button_cat.setOnMouseClicked(handler_buy);
                button_hen.setOnMouseClicked(handler_buy);
                button_buffalo.setOnMouseClicked(handler_buy);
                time = LocalDateTime.now() ;
                action = scanner.nextLine();

                EventHandler<MouseEvent> handler_well = new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent mouseEvent)
                    {
                        Alert a = new Alert(AlertType.NONE);

                        if(mouseEvent.getSource() == imageView_well)
                        {
                            int possible = game.fillWell();
                            switch (possible) {
                                case 0:
                                    a.setAlertType(AlertType.CONFIRMATION);
                                    a.setContentText("Well filled successfully");
                                    log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Well filled successfully";
                                    writeLogger(log , user.getUserName());
                                    break;
                                case 1:
                                    // set alert type
                                    a.setAlertType(AlertType.ERROR);
                                    a.setContentText("Well is not empty yet.");
                                    log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Given coordinates is empty.";
                                    writeLogger(log , user.getUserName());
                                    break;
                                case 2:
                                    a.setAlertType(AlertType.ERROR);
                                    a.setContentText("Well is being filled.");
                                    log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Well is being filled.";
                                    writeLogger(log , user.getUserName());
                                    break;
                                default:
                                    break;
                            }
                            a.show();
                        }
                    }
                };
                imageView_well.setOnMouseClicked(handler_well);

                EventHandler<MouseEvent> handler_build = new EventHandler<MouseEvent>(){

                    @Override
                    public void handle(MouseEvent mouseEvent)
                    {
                        if(mouseEvent.getSource()==build)
                        {
                            String name;
                            Button b= new Button("done");
                            Label workshop=new Label("Workshop name:");
                            TextField tf1=new TextField();
                            tf1.setLayoutX(350); tf1.setLayoutY(70);
                            pane.addRow(0, workshop, tf1);
                            pane.addRow(1,b);

                            root.getChildren().addAll(pane);
                            EventHandler<MouseEvent> handler_build2= new EventHandler<MouseEvent>()
                            {
                                @Override
                                public void handle(MouseEvent mouseEvent)
                                {
                                    name = tf1.getText();
                                    root.getChildren().remove(pane);
                                }
                            };
                            b.setOnMouseClicked(handler_build2);
                            Alert a = new Alert(AlertType.NONE);
                            int possible = game.buildWorkShop(name);
                            switch (possible) {
                                case 0:
                                    a.setAlertType(AlertType.ERROR);
                                    a.setContentText("Operation successful.");
                                    log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Built Workshop successfully";
                                    writeLogger(log , user.getUserName());
                                    break;
                                case 1:
                                    a.setAlertType(AlertType.ERROR);
                                    a.setContentText("You cant build this workShop in this level.");
                                    log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Workshop not available.";
                                    writeLogger(log , user.getUserName());
                                    break;
                                case 2:
                                    a.setAlertType(AlertType.ERROR);
                                    a.setContentText("You don't have enough coins");
                                    log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Not enough coins.";
                                    writeLogger(log , user.getUserName());
                                    break;
                                case 3:
                                    a.setAlertType(AlertType.ERROR);
                                    a.setContentText("WorkShop already built.");
                                    log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " WorkShop already built.";
                                    writeLogger(log , user.getUserName());
                                    break;
                                default:
                                    break;
                            }
                            a.show();
                        }
                    }
                };
                build.setOnMouseClicked(handler_build);


                EventHandler<MouseEvent> handler_work = new EventHandler<MouseEvent>(){
                    @Override
                    public void handle(MouseEvent mouseEvent)
                    {
                        String name = "";
                        Alert a = new Alert(AlertType.NONE);
                        if(mouseEvent.getSource() == imageView_bakery)
                            name="bakery";
                        if(mouseEvent.getSource() == imageView_mill)
                            name="flourmill";
                        if(mouseEvent.getSource() == imageView_milkfct)
                            name="milkPacking";
                        if(mouseEvent.getSource() == imageView_sewingfct)
                            name="sewingFactory";
                        if(mouseEvent.getSource() == imageView_weavingfct)
                            name="weavingFactory";
                        if(mouseEvent.getSource() == imageView_icecreamfct)
                            name="iceCreamShop";
                        if(mouseEvent.getSource() == imageView_poultry)
                            name="henMaker";
                        int possible = game.work(name);
                        switch (possible) {
                            case 0:
                                a.setAlertType(AlertType.CONFIRMATION);
                                a.setContentText("Operation successful.");
                                log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Workshop working successfully";
                                writeLogger(log , user.getUserName());
                                break;
                            case 2:
                                a.setAlertType(AlertType.ERROR);
                                a.setContentText("WorkShop not built.");
                                log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " WorkShop not built.";
                                writeLogger(log , user.getUserName());
                                break;
                            case 3:
                                a.setAlertType(AlertType.ERROR);
                                a.setContentText("WorkShop is working already.");
                                log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " WorkShop is working already.";
                                writeLogger(log , user.getUserName());
                                break;
                            case 4:
                                a.setAlertType(AlertType.ERROR);
                                a.setContentText("not enough entry product.");
                                log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Not enough entry product for the workshop.";
                                writeLogger(log , user.getUserName());
                            default:
                                break;
                        }
                    }
                };

                 else if ((matcher = getCommandMatcher(action, "cage (\\d+) (\\d+)")).find()) {
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
                            System.out.println("You don't have enough coins");
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


