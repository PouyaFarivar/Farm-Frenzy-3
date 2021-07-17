import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

public class Controller {
    private DataBase dataBase = load() ;
    Stage stage ;
    GraphicProcessor gp = new GraphicProcessor() ;

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
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void goLogin(javafx.event.ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void login (javafx.event.ActionEvent e) throws IOException {
        String username = loginUsername.getText() ;
        String password = loginPassword.getText() ;
        User user = dataBase.getUserByUserName(username);
        if (user != null){
            if (user.getPassword().equals(password)) {
                loginState.setText("You are Logged in.");
                saveUser(username);
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

    public void goSignup(javafx.event.ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Signup.fxml"));
        stage = (Stage)((Node)e.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void signup (javafx.event.ActionEvent e) throws IOException {
        String username = signupUsername.getText() ;
        String password = signupPassword.getText() ;
        User user = dataBase.getUserByUserName(username);
        if (user == null){
            User realUser = new User(username , password);
            dataBase.getUsers().add(realUser);
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
        stage = new Stage() ;
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
                gp.play(user , dataBase.getLevelByNum(level)) ;
                stage.close();
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

}
