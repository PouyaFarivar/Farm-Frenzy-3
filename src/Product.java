import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;

public class Product extends GamePlay
{
    private int id ;
    private String name  ;
    private int price;
    private int size;
    private int time_present;
    private int available;// 0 = warehouse 1 = ground 2 = cat
    private int givenTime ;
    private Image image;
    private ImageView imageView;

    Product(String name , int price , int x , int y , int givenTime , int size) throws FileNotFoundException
    {
        super(x , y);
        this.name = name ;
        this.price = price ;
        time_present = 0 ;
        available = 1 ;
        this.givenTime = givenTime ;
        this.size = size ;
        id = 0 ;
        this.image= new Image(new FileInputStream("D:/project files/" + this.name + ".png"));
        this.imageView = new ImageView(this.image);
    }

    Product(){}

    public void setImageViewXY(int x , int y)
    {
        double imageviewx = (x-1)*133 + 300;
        double imageviewy = (y-1)*73 +210;
        this.imageView.setX(imageviewx);
        this.imageView.setY(imageviewy);
    }
    public double getImageViewX(){return this.imageView.getX();}
    public double getImageViewY(){return this.imageView.getY();}
    public ImageView getImageView(){return this.imageView;}
    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getTime_present() {
        return time_present;
    }

    public int getGivenTime() {
        return givenTime;
    }

    public int getAvailable() {
        return available;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setTime_present(int time_present) {
        this.time_present = time_present;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public void setId(int id) {
        this.id = id;
    }

    static class Egg extends Product{
        Egg(int x , int y) throws FileNotFoundException
        {
            super("egg" , 15 , x , y , 4 ,1);
        }
    }
    static class Feather extends Product{
        Feather(int x , int y) throws FileNotFoundException
        {
            super("feather" , 20 , x , y , 4 ,1);
        }
    }
    static class Milk extends Product{
        Milk(int x , int y) throws FileNotFoundException
        {
            super("milk" , 25 , x , y , 4 , 1);
        }
    }
    static class Flour extends Product{
        Flour(int x , int y) throws FileNotFoundException
        {
            super("flour" , 40 , x , y , 5 , 2);
        }
    }
    static class Fabric extends Product{
        Fabric(int x , int y) throws FileNotFoundException
        {
            super("fabric" , 50 , x , y , 5 , 2);
        }
    }
    static class PacketMilk extends Product{
    PacketMilk(int x , int y) throws FileNotFoundException
    {
        super("packetMilk" , 60 , x, y , 5 , 2);
    }
}
    static class Bread extends Product{
        Bread(int x , int y) throws FileNotFoundException
        {
            super("bread" , 80 , x ,y , 6 , 4);
        }
    }

    static class Clothing extends Product{
        Clothing(int x , int y ) throws FileNotFoundException
        {
            super("clothing" , 100 , x , y , 6 , 4);
        }
    }
    static class IceCream extends Product{
        IceCream(int x ,  int y) throws FileNotFoundException
        {
            super("iceCream" , 120 , x , y , 6 , 4);
        }
    }
}


