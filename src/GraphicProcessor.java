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

import javafx.css.Match;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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
    private DataBase dataBase;
    private User realUser;
    private Level realLevel;

    GraphicProcessor()
    {
        dataBase = new DataBase();
        realLevel = new Level();
        realUser = new User();
    }

    public void run() throws IOException
    {
        load();
        goStart(new ActionEvent());
    }


    public void play(User user, Level level) throws FileNotFoundException
    {
        Game game = new Game(level);
        Stage stage = new Stage();
        stage.setTitle("Farm Frenzy 3");
        //Images
        Image image = new Image(new FileInputStream("D:/project files/background 2.jpg"));
        Image poultry = new Image(new FileInputStream("D:/project files/poultry.png"));
        Image icecream_fct = new Image(new FileInputStream("D:/project files/ice cream fct.png"));
        Image sewing_fct = new Image(new FileInputStream("D:/project files/sewing.png"));
        Image weaving_fct = new Image(new FileInputStream("D:/project files/weaving.png"));
        Image bakery = new Image(new FileInputStream("D:/project files/bakery.png"));
        Image milk_fct = new Image(new FileInputStream("D:/project files/milkfct.png"));
        Image warehouse = new Image(new FileInputStream("D:/project files/warehouse.png"));
        Image mill = new Image(new FileInputStream("D:/project files/mill.png"));
        Image well = new Image(new FileInputStream("D:/project files/well.png"));

        Image dog = new Image(new FileInputStream("D:/project files/dog.png"));
        Image bear = new Image(new FileInputStream("D:/project files/bear.png"));
        Image cat = new Image(new FileInputStream("D:/project files/cat.png"));
        Image lion = new Image(new FileInputStream("D:/project files/lion.png"));
        Image turkey = new Image(new FileInputStream("D:/project files/ostrich.png"));
        Image buffalo = new Image(new FileInputStream("D:/project files/buffalo.png"));
        Image tiger = new Image(new FileInputStream("D:/project files/tiger.png"));
        Image hen = new Image(new FileInputStream("D:/project files/hen.png"));

        Image milk_bucket = new Image(new FileInputStream("D:/project files/milk.png"));
        Image milk_packet = new Image(new FileInputStream("D:/project files/packetMilk.png"));
        Image egg = new Image(new FileInputStream("D:/project files/egg.png"));
        Image icecream = new Image(new FileInputStream("D:/project files/iceCream.png"));
        Image flour = new Image(new FileInputStream("D:/project files/flour.png"));
        Image fabric = new Image(new FileInputStream("D:/project files/fabric.png"));
        Image feather = new Image(new FileInputStream("D:/project files/feather.png"));
        Image clothes = new Image(new FileInputStream("D:/project files/clothing.png"));

        Image grass = new Image(new FileInputStream("D:/project files/grass.png"));
        Image cage = new Image(new FileInputStream("D:/project files/cage.png"));
        Image henButton = new Image(new FileInputStream("D:/project files/henButton.jpg"));
        Image turkeyButton = new Image(new FileInputStream("D:/project files/turkeyButton.jpg"));
        Image dogButton = new Image(new FileInputStream("D:/project files/dogButton.jpg"));
        Image catButton = new Image(new FileInputStream("D:/project files/catButton.jpg"));
        Image buffaloButton = new Image(new FileInputStream("D:/project files/buffaloButton.jpg"));


        //Setting the image view
        ImageView imageView = new ImageView(image);
        ImageView imageView_mill = new ImageView(mill);
        ImageView imageView_icecreamfct = new ImageView(icecream_fct);
        ImageView imageView_sewingfct = new ImageView(sewing_fct);
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
        ImageView imageView_cage = new ImageView(cage);
        ImageView imageView_hb = new ImageView(henButton);
        ImageView imageView_tb = new ImageView(turkeyButton);
        ImageView imageView_bb = new ImageView(buffaloButton);
        ImageView imageView_cb = new ImageView(catButton);
        ImageView imageView_db = new ImageView(dogButton);


        ArrayList<ImageView> grass_views = new ArrayList<ImageView>();
        //Setting the position of the image
        imageView.setX(0);
        imageView.setY(0);
        imageView_icecreamfct.setX(10);
        imageView_icecreamfct.setY(60);
        imageView_milkfct.setX(10);
        imageView_milkfct.setY(300);
        imageView_mill.setX(10);
        imageView_mill.setY(490);
        imageView_sewingfct.setX(1110);
        imageView_sewingfct.setY(20);
        imageView_weavingfct.setX(1110);
        imageView_weavingfct.setY(250);
        imageView_poultry.setX(900);
        imageView_poultry.setY(8);
        imageView_bakery.setX(1140);
        imageView_bakery.setY(450);
        imageView_well.setX(550);
        imageView_well.setY(30);

        //setting the fit height and width of the image view
        imageView.setFitHeight(700);
        imageView.setFitWidth(1400);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(false);

        //Group
        Group root = new Group(imageView);

        //Buttons
        Image turn_im = new Image(new FileInputStream("resources/Graphic/download.jpg"));
        ImageView imageView_turn = new ImageView(turn_im);
        Button btnturn = new Button();
        btnturn.setGraphic(imageView_turn);
        btnturn.setLayoutX(440);
        btnturn.setLayoutY(0);
        Button button_hen = new Button();
        button_hen.setGraphic(imageView_hb);
        button_hen.setLayoutX(0);
        button_hen.setLayoutY(0);
        button_hen.setPrefSize(70, 70);
        Button button_turkey = new Button();
        button_turkey.setGraphic(imageView_tb);
        button_turkey.setLayoutX(80);
        button_turkey.setLayoutY(0);
        button_turkey.setPrefSize(70, 70);
        Button button_buffalo = new Button();
        button_buffalo.setGraphic(imageView_bb);
        button_buffalo.setLayoutX(150);
        button_buffalo.setLayoutY(0);
        button_buffalo.setPrefSize(70, 70);
        Button button_dog = new Button();
        button_dog.setGraphic(imageView_db);
        button_dog.setLayoutX(220);
        button_dog.setLayoutY(0);
        button_dog.setPrefSize(70, 70);
        Button button_cat = new Button();
        button_cat.setGraphic(imageView_cb);
        button_cat.setLayoutX(290);
        button_cat.setLayoutY(0);
        button_cat.setPrefSize(70, 70);
        Button button_warehouse = new Button();
        button_warehouse.setGraphic(imageView_warehouse);
        button_warehouse.setLayoutX(600);
        button_warehouse.setLayoutY(650);
        button_warehouse.setPrefSize(100, 50);
        Button build = new Button("Build");
        build.setLayoutX(370);
        build.setLayoutY(0);
        build.setPrefSize(50, 35);
        Button upgrade = new Button("Upgrade");
        upgrade.setLayoutX(370);
        upgrade.setLayoutY(35);
        upgrade.setPrefSize(50, 35);
        Button exit = new Button("Exit");
        exit.setLayoutX(1200);
        exit.setLayoutY(0);
        exit.setPrefSize(80, 30);
        Image coin = new Image(new FileInputStream("D:/project files/coin.png"));
        ImageView imageView_coin = new ImageView(coin);
        Button btn_coin = new Button();
        btn_coin.setGraphic(imageView_coin);
        btn_coin.setLayoutX(1280);
        btn_coin.setLayoutY(0);

        int turn = 0;

        LocalDateTime time = LocalDateTime.now();
        String log = "";
        log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + "Level " + level.getLevelNum() + " started";
        writeLogger(log, user.getUserName());
        while (true) {
        if (turn == 0)
        {
            time = LocalDateTime.now();
            //WalkAnimation walkAnimation= new WalkAnimation(game);
            //walkAnimation.play();
            EventHandler<MouseEvent> handler_coin = new EventHandler<MouseEvent>()
            {

                @Override
                public void handle(MouseEvent mouseEvent)
                {
                    if(mouseEvent.getSource() == btn_coin)
                    {
                        Alert a = new Alert(AlertType.NONE);
                        a.setAlertType(AlertType.INFORMATION);
                        a.setContentText("You have " + String.valueOf(game.getCoins()) + "coins.");
                        a.show();
                    }
                }
            };
            btn_coin.setOnMouseClicked(handler_coin);
            EventHandler<MouseEvent> handler_buy = new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent mouseEvent)
                {
                    String name = "";
                    if (mouseEvent.getSource() == button_cat)
                        name = "cat";
                    if (mouseEvent.getSource() == button_dog)
                        name = "dog";
                    if (mouseEvent.getSource() == button_hen)
                        name = "hen";
                    if (mouseEvent.getSource() == button_buffalo)
                        name = "buffalo";
                    if (mouseEvent.getSource() == button_turkey)
                        name = "ostrich";
                    int possible = 0;
                    try
                    {
                        possible = game.buyAnimal(name);
                    } catch (FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                    Alert a = new Alert(AlertType.NONE);
                    String log = "";
                    LocalDateTime time = LocalDateTime.now();
                    switch (possible)
                    {

                        case 0:
                            log = "[Info] " + LocalDateTime.now().toLocalDate().toString() + " _ " + LocalDateTime.now().toLocalTime().toString() + " Bought " + name + " successfully.";
                            writeLogger(log, user.getUserName());
                            break;
                        case 2:
                            // set alert type
                            a.setAlertType(AlertType.ERROR);

                            // set content text
                            a.setContentText("You don't have enough coins!");

                            // show the dialog
                            a.show();
                            log = "[Error] " + LocalDateTime.now().toLocalDate().toString() + " _ " + LocalDateTime.now().toLocalTime().toString() + " Not enough coins to buy the animal";
                            writeLogger(log, user.getUserName());
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
            time = LocalDateTime.now();

            EventHandler<MouseEvent> handler_well = new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent mouseEvent)
                {
                    Alert a = new Alert(AlertType.NONE);
                    String log = "";
                    LocalDateTime time = LocalDateTime.now();

                    if (mouseEvent.getSource() == imageView_well)
                    {
                        int possible = game.fillWell();
                        switch (possible)
                        {
                            case 0:
                                a.setAlertType(AlertType.CONFIRMATION);
                                a.setContentText("Well filled successfully");
                                log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Well filled successfully";
                                writeLogger(log, user.getUserName());
                                break;
                            case 1:
                                // set alert type
                                a.setAlertType(AlertType.ERROR);
                                a.setContentText("Well is not empty yet.");
                                log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Given coordinates is empty.";
                                writeLogger(log, user.getUserName());
                                break;
                            case 2:
                                a.setAlertType(AlertType.ERROR);
                                a.setContentText("Well is being filled.");
                                log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Well is being filled.";
                                writeLogger(log, user.getUserName());
                                break;
                            default:
                                break;
                        }
                        a.show();
                    }
                }
            };
            imageView_well.setOnMouseClicked(handler_well);

            EventHandler<MouseEvent> handler_plant = new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent mouseEvent)
                {
                    String log = "";
                    LocalDateTime time = LocalDateTime.now();
                    if (mouseEvent.getSource() == imageView)
                    {
                        double x = mouseEvent.getX();
                        double y = mouseEvent.getY();
                        if (x > 300 && x < 1110 && y > 210 && y < 650)
                        {
                            int grass_x = (int) ((x - 300) / 133 + 1);
                            int grass_y = (int) ((y - 210) / 73 + 1);
                            int possible = game.plantGrass(grass_x, grass_y);
                            Alert a = new Alert(AlertType.NONE);
                            switch (possible)
                            {
                                case 0:
                                    a.setAlertType(AlertType.CONFIRMATION);
                                    a.setContentText("Operation successful.");

                                    log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Grass Planted successfully";
                                    writeLogger(log, user.getUserName());
                                    break;
                                case 1:
                                    a.setAlertType(AlertType.ERROR);
                                    a.setContentText("Not enough water.");
                                    log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Not enough water.";
                                    writeLogger(log, user.getUserName());
                                    break;
                                case 2:
                                    a.setAlertType(AlertType.ERROR);
                                    a.setContentText("Given coordinate is not on the map.");
                                    log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Given coordinate is not on the map.";
                                    writeLogger(log, user.getUserName());
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }
            };
            imageView.setOnMouseClicked(handler_plant);

            EventHandler<MouseEvent> handler_build = new EventHandler<MouseEvent>()
            {

                @Override
                public void handle(MouseEvent mouseEvent)
                {
                    if (mouseEvent.getSource() == build)
                    {
                        GridPane pane = new GridPane();
                        final String[] name = new String[1];
                        Button b = new Button("done");
                        Label workshop = new Label("Workshop name:");
                        TextField tf1 = new TextField();
                        tf1.setLayoutX(350);
                        tf1.setLayoutY(70);
                        pane.addRow(0, workshop, tf1);
                        pane.addRow(1, b);

                        root.getChildren().addAll(pane);
                        EventHandler<MouseEvent> handler_build2 = new EventHandler<MouseEvent>()
                        {
                            @Override
                            public void handle(MouseEvent mouseEvent)
                            {
                                name[0] = tf1.getText();
                                root.getChildren().remove(pane);
                            }
                        };
                        b.setOnMouseClicked(handler_build2);
                        Alert a = new Alert(AlertType.NONE);
                        int possible = game.buildWorkShop(name[0]);
                        String log = "";
                        LocalDateTime time = LocalDateTime.now();
                        switch (possible)
                        {
                            case 0:
                                a.setAlertType(AlertType.CONFIRMATION);
                                a.setContentText("Operation successful.");
                                log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Built Workshop successfully";
                                writeLogger(log, user.getUserName());
                                break;
                            case 1:
                                a.setAlertType(AlertType.ERROR);
                                a.setContentText("You cant build this workShop in this level.");
                                log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Workshop not available.";
                                writeLogger(log, user.getUserName());
                                break;
                            case 2:
                                a.setAlertType(AlertType.ERROR);
                                a.setContentText("You don't have enough coins");
                                log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Not enough coins.";
                                writeLogger(log, user.getUserName());
                                break;
                            case 3:
                                a.setAlertType(AlertType.ERROR);
                                a.setContentText("WorkShop already built.");
                                log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " WorkShop already built.";
                                writeLogger(log, user.getUserName());
                                break;
                            default:
                                break;
                        }
                        a.show();
                    }
                    if (mouseEvent.getSource() == upgrade)
                    {
                        GridPane pane = new GridPane();
                        final String[] name = new String[1];
                        Button b = new Button("done");
                        Label workshop = new Label("Workshop name:");
                        TextField tf1 = new TextField();
                        tf1.setLayoutX(350);
                        tf1.setLayoutY(70);
                        pane.addRow(0, workshop, tf1);
                        pane.addRow(1, b);
                        root.getChildren().addAll(pane);
                        EventHandler<MouseEvent> handler_upgrade = new EventHandler<MouseEvent>()
                        {
                            @Override
                            public void handle(MouseEvent mouseEvent)
                            {
                                name[0] = tf1.getText();
                                root.getChildren().remove(pane);
                            }
                        };
                        b.setOnMouseClicked(handler_upgrade);
                        Alert a = new Alert(AlertType.NONE);
                        int possible = game.upgradeWorkShop(name[0]);
                        String log = "";
                        LocalDateTime time = LocalDateTime.now();
                        switch (possible)
                        {
                            case 0:
                                a.setAlertType(AlertType.CONFIRMATION);
                                a.setContentText("Operation successful.");
                                log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Upgraded workshop successfully.";
                                writeLogger(log, user.getUserName());
                                break;
                            case 1:
                                a.setAlertType(AlertType.ERROR);
                                a.setContentText("You can't build this workShop in this level.");
                                log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Can't build workshop in this level.";
                                writeLogger(log, user.getUserName());
                                break;
                            case 2:
                                a.setAlertType(AlertType.ERROR);
                                a.setContentText("You don't have enough coins");
                                log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Not enough coins.";
                                writeLogger(log, user.getUserName());
                                break;
                            case 3:
                                a.setAlertType(AlertType.ERROR);
                                a.setContentText("WorkShop already upgraded to level 2.");
                                log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " WorkShop already upgraded.";
                                writeLogger(log, user.getUserName());
                                break;
                            case 4:
                                a.setAlertType(AlertType.ERROR);
                                a.setContentText("WorkShop should at least be level 1 to upgrade.");
                                log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " WorkShop should at least be level 1 to upgrade.";
                                writeLogger(log, user.getUserName());
                            default:
                                break;
                        }
                        a.show();
                    }
                }
            };
            build.setOnMouseClicked(handler_build);
            upgrade.setOnMouseClicked(handler_build);


            EventHandler<MouseEvent> handler_work = new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent mouseEvent)
                {
                    String name = "";
                    String log = "";
                    LocalDateTime time = LocalDateTime.now();
                    Alert a = new Alert(AlertType.NONE);
                    if (mouseEvent.getSource() == imageView_bakery)
                        name = "bakery";
                    if (mouseEvent.getSource() == imageView_mill)
                        name = "flourmill";
                    if (mouseEvent.getSource() == imageView_milkfct)
                        name = "milkPacking";
                    if (mouseEvent.getSource() == imageView_sewingfct)
                        name = "sewingFactory";
                    if (mouseEvent.getSource() == imageView_weavingfct)
                        name = "weavingFactory";
                    if (mouseEvent.getSource() == imageView_icecreamfct)
                        name = "iceCreamShop";
                    if (mouseEvent.getSource() == imageView_poultry)
                        name = "henMaker";
                    int possible = game.work(name);
                    switch (possible)
                    {
                        case 0:
                            a.setAlertType(AlertType.CONFIRMATION);
                            a.setContentText("Operation successful.");
                            log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Workshop working successfully";
                            writeLogger(log, user.getUserName());
                            break;
                        case 2:
                            a.setAlertType(AlertType.ERROR);
                            a.setContentText("WorkShop not built.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " WorkShop not built.";
                            writeLogger(log, user.getUserName());
                            break;
                        case 3:
                            a.setAlertType(AlertType.ERROR);
                            a.setContentText("WorkShop is working already.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " WorkShop is working already.";
                            writeLogger(log, user.getUserName());
                            break;
                        case 4:
                            a.setAlertType(AlertType.ERROR);
                            a.setContentText("not enough entry product.");
                            log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Not enough entry product for the workshop.";
                            writeLogger(log, user.getUserName());
                        default:
                            break;
                    }
                    a.show();
                }
            };
            imageView_bakery.setOnMouseClicked(handler_work);
            imageView_mill.setOnMouseClicked(handler_work);
            imageView_sewingfct.setOnMouseClicked(handler_work);
            imageView_weavingfct.setOnMouseClicked(handler_work);
            imageView_poultry.setOnMouseClicked(handler_work);
            imageView_icecreamfct.setOnMouseClicked(handler_work);
            imageView_milkfct.setOnMouseClicked(handler_work);

            EventHandler<MouseEvent> handler_pickup = new EventHandler<MouseEvent>()
            {

                @Override
                public void handle(MouseEvent mouseEvent)
                {
                    String log = "";
                    LocalDateTime time = LocalDateTime.now();
                    for (Product p : game.getProducts())
                    {
                        if (mouseEvent.getSource() == p.getImageView())
                        {
                            Alert a = new Alert(AlertType.NONE);
                            int possible = game.pickup(p.getX(), p.getY());
                            switch (possible)
                            {
                                case 0:
                                    log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Picked up successfully";
                                    writeLogger(log, user.getUserName());
                                    root.getChildren().remove(p.getImageView());
                                    break;
                                case 1:
                                    a.setAlertType(AlertType.ERROR);
                                    a.setContentText("Not enough space in ware House.");
                                    log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Not enough space in ware House.";
                                    writeLogger(log, user.getUserName());
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

                }
            };
            for (Product p : game.getProducts())
            {
                p.getImageView().setOnMouseClicked(handler_pickup);
            }
            for (Predator p : game.getPredators())
            {
                p.getImageView().setOnMouseClicked(new EventHandler<MouseEvent>()
                {
                    @Override
                    public void handle(MouseEvent mouseEvent)
                    {
                        if (mouseEvent.getSource() == p.getImageView())
                        {
                            int possible = game.buildCage(p.getX(), p.getY());
                            if (p.isCaptured())
                            {
                                imageView_cage.setX(p.getImageViewX());
                                imageView_cage.setY(p.getImageViewY());
                                root.getChildren().add(imageView_cage);
                            }
                        }

                    }
                });
            }


            EventHandler<MouseEvent> handler_warehouse = new EventHandler<MouseEvent>()
            {
                @Override
                public void handle(MouseEvent mouseEvent)
                {
                    if (mouseEvent.getSource() == imageView_warehouse)
                    {
                        Alert a = new Alert(AlertType.NONE);
                        GridPane pane1 = new GridPane();
                        final String[] action = {""};
                        Button b = new Button("done");
                        Label command = new Label("Command:");
                        TextField tf1 = new TextField();
                        tf1.setLayoutX(350); tf1.setLayoutY(70);
                        pane1.addRow(0, command, tf1);
                        pane1.addRow(1, b);
                        root.getChildren().addAll(pane1);
                        EventHandler<MouseEvent> handler_truck = new EventHandler<MouseEvent>()
                        {
                            @Override
                            public void handle(MouseEvent mouseEvent)
                            {
                                action[0] = tf1.getText();
                                root.getChildren().remove(pane1);
                            }
                        };
                        b.setOnMouseClicked(handler_truck);
                        Matcher matcher;
                        if ((matcher = getCommandMatcher(action[0], "truck load ([A-Za-z0-9_]+[A-Za-z0-9_]*)")).find())
                        {

                            String name = matcher.group(1);
                            int possible = -1;
                            if (name.equals("lion") || name.equals("bear") || name.equals("tiger"))
                            {
                                possible = game.moveToTruckPredator(name);
                            } else
                            {
                                possible = game.moveToTruckProduct(name);
                            }
                            String log = "";
                            LocalDateTime time = LocalDateTime.now();
                            switch (possible)
                            {
                                case 0:
                                    a.setAlertType(AlertType.CONFIRMATION);
                                    a.setContentText("Operation successful.");
                                    log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Truck loaded successfully.";
                                    writeLogger(log, user.getUserName());
                                    break;
                                case 1:
                                    a.setAlertType(AlertType.ERROR);
                                    a.setContentText("No such product in ware house.");
                                    log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " No such product available.";
                                    writeLogger(log, user.getUserName());
                                    break;
                                case 2:
                                    a.setAlertType(AlertType.ERROR);
                                    a.setContentText("Not enough space in Truck inventory.");
                                    log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Not enough space in Truck.";
                                    writeLogger(log, user.getUserName());
                                    break;
                                case 3:
                                    a.setAlertType(AlertType.ERROR);
                                    a.setContentText("Truck is moving.");
                                    log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Truck is moving";
                                    writeLogger(log, user.getUserName());
                                    break;
                                default:
                                    break;
                            }
                        } else if ((matcher = getCommandMatcher(action[0], "truck unload ([A-Za-z0-9_]+[A-Za-z0-9_]*)")).find())
                        {
                            String name = matcher.group(1);
                            int possible = -1;
                            if (name.equals("lion") || name.equals("bear") || name.equals("tiger"))
                            {
                                possible = game.unloadFromTruckPredator(name);
                            } else
                            {
                                possible = game.unloadFromTruckProduct(name);
                            }
                            String log = "";
                            LocalDateTime time = LocalDateTime.now();
                            switch (possible)
                            {
                                case 0:
                                    a.setAlertType(AlertType.CONFIRMATION);
                                    a.setContentText("Operation successful.");
                                    log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Truck unloaded successfully.";
                                    writeLogger(log, user.getUserName());
                                    break;
                                case 1:
                                    a.setAlertType(AlertType.ERROR);
                                    a.setContentText("No such product in Truck.");
                                    log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " No such product in Truck.";
                                    writeLogger(log, user.getUserName());
                                    break;
                                case 2:
                                    a.setAlertType(AlertType.ERROR);
                                    a.setContentText("Not enough space in Warehouse.");
                                    log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Not enough space in Ware house.";
                                    writeLogger(log, user.getUserName());
                                    break;
                                case 3:
                                    a.setAlertType(AlertType.ERROR);
                                    a.setContentText("Truck is moving.");
                                    log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Moving Truck.";
                                    writeLogger(log, user.getUserName());
                                    break;
                                default:
                                    break;
                            }
                        } else if (action[0].equals("truck go"))
                        {
                            String log = "";
                            LocalDateTime time = LocalDateTime.now();
                            int possible = game.truckGo();
                            switch (possible)
                            {
                                case 0:
                                    a.setAlertType(AlertType.CONFIRMATION);
                                    a.setContentText("Operation successful.");
                                    log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Truck is moving successfully.";
                                    writeLogger(log, user.getUserName());
                                    break;
                                case 1:
                                    a.setAlertType(AlertType.ERROR);
                                    a.setContentText("Truck is empty.");
                                    log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Truck is empty.";
                                    writeLogger(log, user.getUserName());
                                    break;
                                case 2:
                                    a.setAlertType(AlertType.ERROR);
                                    a.setContentText("Truck is already in move.");
                                    log = "[Error] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Moving Truck.";
                                    writeLogger(log, user.getUserName());
                                    break;
                                default:
                                    break;
                            }
                        }
                        a.show();
                    }
                }
            };
            imageView_warehouse.setOnMouseClicked(handler_warehouse);
            EventHandler<MouseEvent> handler_exit = new EventHandler<MouseEvent>()
            {

                @Override
                public void handle(MouseEvent mouseEvent)
                {
                    String log = "";
                    LocalDateTime time = LocalDateTime.now();
                    if (mouseEvent.getSource() == exit)
                    {
                        try
                        {
                            changeLevel(mouseEvent);
                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        }
                        log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " exited level " + level.getLevelNum();
                        writeLogger(log, user.getUserName());
                    }

                }
            };
            exit.setOnMouseClicked(handler_exit);

        } else
        {
            EventHandler<MouseEvent> handler_turn = new EventHandler<MouseEvent>()
            {

                @Override
                public void handle(MouseEvent mouseEvent)
                {
                    if (mouseEvent.getSource() == btnturn)
                    {
                        int possible = 0;
                        try
                        {
                            possible = game.goTurn();
                        } catch (FileNotFoundException e)
                        {
                            e.printStackTrace();
                        }
                        if (possible == 0)
                        {
                            String log = "";
                            LocalDateTime time = LocalDateTime.now();
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(AlertType.INFORMATION);
                            a.setContentText("Time Passed");
                            a.show();
                            log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " turns passed ";
                            writeLogger(log, user.getUserName());
                        } else
                        {
                            int medal = 0;
                            if (user.getLevelTime().containsKey(game.getLevel().getLevelNum()))
                            {
                                if (user.getLevelTime().get(game.getLevel().getLevelNum()) > game.getTurn())
                                {
                                    user.getLevelTime().put(game.getLevel().getLevelNum(), game.getTurn());
                                    user.setCoin(user.getCoin() + game.getCoins() - game.getLevel().getGoalCoins());
                                    medal = game.giveMedal();
                                    if (medal != user.getLevelMedal().get(game.getLevel().getLevelNum()))
                                    {
                                        user.getLevelMedal().put(game.getLevel().getLevelNum(), medal);
                                        switch (medal)
                                        {
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
                            } else
                            {
                                user.getLevelTime().put(game.getLevel().getLevelNum(), game.getTurn());
                                user.setCoin(user.getCoin() + game.getCoins() - game.getLevel().getGoalCoins());
                                user.setMaximumLevel(game.getLevel().getLevelNum());
                                medal = game.giveMedal();
                                user.getLevelMedal().put(game.getLevel().getLevelNum(), medal);
                                switch (medal)
                                {
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
                            Alert a = new Alert(Alert.AlertType.NONE);
                            a.setAlertType(AlertType.INFORMATION);
                            String str = "Victory : ";
                            String log = "";
                            LocalDateTime time = LocalDateTime.now();
                            switch (medal)
                            {
                                case 1:
                                    str += "You got a Gold medal on level " + String.valueOf(game.getLevel().getLevelNum());
                                    log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Finished level " + level.getLevelNum() + " with Gold Medal ";
                                    writeLogger(log, user.getUserName());
                                    break;
                                case 2:
                                    str += "You got a Silver medal on level " + game.getLevel().getLevelNum();
                                    log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Finished level " + level.getLevelNum() + " Silver Medal ";
                                    writeLogger(log, user.getUserName());
                                    break;
                                case 3:
                                    str += "You got a Bronze medal on level " + game.getLevel().getLevelNum();
                                    log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Finished level " + level.getLevelNum() + " Bronze Medal ";
                                    writeLogger(log, user.getUserName());
                                    break;
                                case 4:
                                    str += "No medal.";
                                    log = "[Info] " + time.toLocalDate().toString() + " _ " + time.toLocalTime().toString() + " Finished level " + level.getLevelNum() + " No Medal ";
                                    writeLogger(log, user.getUserName());
                                    break;
                                default:
                                    break;
                            }
                            a.setContentText(str);
                            a.show();
                        }

                    }
                }
            };
            btnturn.setOnMouseClicked(handler_turn);

        }
        for (int i = 0; i < 6; i++)
            for (int j = 0; j < 6; j++)
            {
                if (game.getGrassMap()[i][j] > 0)
                {
                    ImageView one = new ImageView(grass);
                    one.setX(i * 133 + 300);
                    one.setY(j * 73 + 210);
                    grass_views.add(one);
                }
            }
        for (ImageView x : grass_views)
            root.getChildren().add(x);
        for (Product p : game.getProducts())
        {
            root.getChildren().add(p.getImageView());
        }
        for (Predator p : game.getPredators())
        {
            root.getChildren().add(p.getImageView());
        }
        for (Defender d : game.getDefenders())
        {
            root.getChildren().add(d.getImageView());
        }
        for (Domestic d : game.getDomestics())
        {
            root.getChildren().add(d.getImageView());
        }
        root.getChildren().addAll(button_cat, button_dog, button_hen, button_turkey, button_buffalo, btnturn
                , imageView_sewingfct, imageView_well, imageView_icecreamfct, imageView_mill,
                imageView_weavingfct, imageView_bakery, imageView_poultry, imageView_milkfct, button_warehouse,
                build, upgrade, exit, btn_coin);
        Scene scene = new Scene(root, 1400, 700);
        stage.setScene(scene);
        stage.show();
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



    @FXML
    TextField loginUsername ;
    @FXML
    TextField loginPassword ;
    @FXML
    Label loginState ;
    @FXML
    TextField signupUsername ;
    @FXML
    TextField signupPassword ;
    @FXML
    Label signupState ;
    @FXML
    Label getLevelState ;
    @FXML
    TextField levelNumText ;



    public void goStart(javafx.event.ActionEvent e) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        //stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goLogin(javafx.event.ActionEvent e) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void login (javafx.event.ActionEvent e) throws IOException {
        Stage stage = new Stage();

        String username = loginUsername.getText() ;
        String password = loginPassword.getText() ;
        User user = dataBase.getUserByUserName(username);
        if (user != null){
            if (user.getPassword().equals(password)) {
                loginState.setText("You are Logged in.");
                saveUser(username);
                realUser = dataBase.getUserByUserName(username);
                Parent root = FXMLLoader.load(getClass().getResource("GetLevel.fxml"));
                stage = (Stage)((Node)e.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }else {
                loginState.setText("WRONG PASSWORD");
            }
        }else {
            loginState.setText("No Account with such username");
        }

    }

    public void changeLevel(MouseEvent e) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("GetLevel.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void goSignup(javafx.event.ActionEvent e) throws IOException {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void signup (javafx.event.ActionEvent e) throws IOException {
        Stage stage = new Stage();

        String username = signupUsername.getText() ;
        String password = signupPassword.getText() ;
        User user = dataBase.getUserByUserName(username);
        if (user == null){
            User addUser = new User(username , password);
            dataBase.getUsers().add(addUser);
            dataBase.getUserByUserName(username);
            save();
            saveUser(username);
            signupState.setText("You are signed up");
            Parent root = FXMLLoader.load(getClass().getResource("GetLevel.fxml"));
            stage = (Stage)((Node)e.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else {
            signupState.setText("Username already in use");
        }
    }

    public void close (javafx.event.ActionEvent e){
        save();
        Stage stage = new Stage() ;
        stage.close();
    }

    public void play (javafx.event.ActionEvent e) throws FileNotFoundException {
        User user = dataBase.getUserByUserName(loadUser());
        int level = 0 ;
        level = Integer.parseInt(levelNumText.getText());
        if (level > 0) {
            if (level <= user.getMaximumLevel() + 1) {
                saveLevel(String.valueOf(level));
                getLevelState.setText("Launching level " + level);
                saveLevel(String.valueOf(level));
                realLevel = dataBase.getLevelByNum(level);
                System.out.println("BIH");
                play(realUser , realLevel);
                System.out.println("trash");
            }else {
                getLevelState.setText("Level not unlocked yet : your at level " + (user.getMaximumLevel()+1));
            }
        }else {
            getLevelState.setText("Choose a valid level number");
        }

    }


    public DataBase load() {
        DataBase dataBase1 = new DataBase() ;
        Gson gson = new Gson() ;
        try {
            dataBase1 = gson.fromJson(new FileReader("resources\\dataBase.json") , dataBase1.getClass());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        return dataBase1 ;
    }

    public void save() {
        dataBase.save();
    }

    private String loadUser() {
        File file = new File("resources\\UserNow");
        String output = "";
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                output = output = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return output;
    }

    private void saveUser(String nemune) {
        try {
            File file = new File("resources\\UserNow" );
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

    private String loadLevel() {
        File file = new File("resources\\LevelNow");
        String output = "";
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                output = output = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return output;
    }

    private void saveLevel(String nemune) {
        try {
            File file = new File("resources\\LevelNow" );
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

    /*public User getUser (){
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
    }*/

    public Level menu (User user){
        LocalDateTime  time  = LocalDateTime.now();
        String log = "" ;
        Scanner scanner = new Scanner(System.in);
        Matcher matcher ;
        Level level = new Level() ;

        return level ;
    }

}


