/*public class Main {
    public static void main(String[] args) {
        Processor processor = new Processor() ;
        processor.run();
    }
}*/
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Start.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Farm Frenzy 3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

/*import javafx.application.Application;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Group;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.event.EventHandler;
        import javafx.scene.input.MouseEvent;
        import javafx.scene.paint.Color;
        import javafx.stage.Stage;

        import java.io.FileInputStream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GraphicProcessor gp=new GraphicProcessor();
        gp.run();

        //Configuring Group and Scene
       /* Group root = new Group();
        root.getChildren().addAll();
        Scene scene = new Scene(root,1400,700, Color.WHEAT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Farm Frenzy");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}*/

