import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;

public class Animal extends GamePlay
{
    private int id ;
    private String name ;
    private int type  ;//1 = DOMESTIC 2 = DEFENDER 3 = PREDATOR
    private int sizeInWarehouse;
    private int price_for_purchase;
    private int price_for_sale ;
    private int speed ;
    private Image image;
    private ImageView imageView;

    public Animal(String name , int type , int pr_purchase , int price_for_sale , int x , int y , int speed , int sizeInWarehouse) throws FileNotFoundException
    {
        super(x , y);
        this.name = name ;
        this.type = type ;
        this.price_for_purchase=pr_purchase;
        this.price_for_sale = price_for_sale ;
        this.speed = speed ;
        id = 0 ;
        this.sizeInWarehouse = sizeInWarehouse ;
        this.image= new Image(new FileInputStream("D:/project files/" + this.name + ".png"));
        this.imageView = new ImageView(this.image);

    }

    public Animal(){}


    public int getPrice_for_purchase() {
        return price_for_purchase;
    }

    public int getType() {
        return type;
    }

    public int getSizeInWarehouse() {
        return sizeInWarehouse;
    }

    public int getPrice_for_sale() {
        return price_for_sale;
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
class Domestic extends Animal{
    String productName;
    int lives;
    int timeProduce ;
    int productTimer ;

    public Domestic(String name , String productName , int pr_purchase , int x , int y , int timeProduce) throws FileNotFoundException
    {
        super(name, 1, pr_purchase, 0 , x , y , 1 , 0);
        this.productName = productName;
        lives = 10;
        productTimer = 0 ;
        this.timeProduce = timeProduce ;

    }

    Domestic(){}

    public Product produce() throws FileNotFoundException
    {
        Product product = new Product() ;
        if (getName().equals("hen")){
            product = new Product.Egg(getX() , getY()) ;
        }else if (getName().equals("ostrich")){
            product = new Product.Feather(getX()  , getY()) ;
        }else if (getName().equals("buffalo")){
            product = new Product.Milk(getX() , getY()) ;
        }
        return product ;
    }

    public int getLives() {
        return lives;
    }

    public int getProductTimer() {
        return productTimer;
    }

    public int getTimeProduce() {
        return timeProduce;
    }

    public void setProductTimer(int productTimer) {
        this.productTimer = productTimer;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    static class Hen extends Domestic{

        public  Hen(int x , int y) throws FileNotFoundException
        {
            super("hen" , "egg" , 100 , x , y , 2);
        }
    }
   static class Ostrich extends Domestic{

        public Ostrich(int x , int y) throws FileNotFoundException
        {
            super("ostrich" , "feather" , 200 , x , y , 3);
        }
    }

    static class Buffalo extends Domestic{

        public Buffalo(int x ,int y) throws FileNotFoundException
        {
            super("buffalo" , "milk" , 400 ,x ,y , 5);
        }
    }

}
class Predator extends Animal{
    private int cageToStop ;
    private int time_captured ;
    private Cage cage ;
    private boolean captured ;
    final int timeToRun = 5 ;

    public Predator(String name , int pr_sell ,int x , int y , int speed , int cageToStop , int size) throws FileNotFoundException
    {
        super(name , 3, 0 , pr_sell , x , y , speed , size);
        this.cageToStop = cageToStop ;
        time_captured = -1 ;
        cage = new Cage() ;
        captured = false ;
    }

    Predator(){}

    void escape(){};
    void destroyProduct(){};

    public boolean isCaptured() {
        return captured;
    }

    public Cage getCage() {
        return cage;
    }

    public int getCageToStop() {
        return cageToStop;
    }

    public int getTime_captured() {
        return time_captured;
    }

    public void setCaptured(boolean captured) {
        this.captured = captured;
    }

    public void setTime_captured(int time_captured) {
        this.time_captured = time_captured;
    }

    static class Lion extends Predator{

        public Lion(int x , int y) throws FileNotFoundException
        {
            super("lion" , 150 , x ,y ,1 , 3 , 5);
        }
    }
    static class Bear extends Predator{
        public Bear(int x ,int y) throws FileNotFoundException
        {
            super("bear" , 200 , x , y , 1 , 4 , 6);
        }
    }
    static class Tiger extends Predator{

        public Tiger(int x , int y) throws FileNotFoundException
        {
            super("tiger" , 250, x, y , 2 , 4 , 6);

        }
    }
    public void ruin_product(){
    }

}
class Defender extends Animal{
    private LinkedList<Product> products ;
    public Defender(String name , int pr_purchase , int x , int y) throws FileNotFoundException
    {
        super(name, 2 , pr_purchase , 0 ,x, y ,1 , 0 );
        products = new LinkedList<>() ;
    }

    public LinkedList<Product> getProducts() {
        return products;
    }

    static class Cat extends Defender{

        public Cat(int x , int y) throws FileNotFoundException
        {
            super("cat" , 150 , x , y);
        }

    };
    static class Dog extends Defender{
        public Dog(int x , int y) throws FileNotFoundException
        {
            super("dog", 100 , x, y);
        }

    };

}

