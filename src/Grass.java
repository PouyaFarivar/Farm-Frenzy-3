import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Grass extends GamePlay
{
    private int health ;
    Image image;
    ImageView imageView;

    Grass(int x  , int y ) throws FileNotFoundException
    {
        super(x , y);
        health = 5 ;
        this.image = new Image(new FileInputStream("D:/project files/grass.png"));
        this.imageView = new ImageView(this.image);

    }

    void getEaten(){
        health = health - 5 ;
    }

    public int getHealth() {
        return health;
    }
}
