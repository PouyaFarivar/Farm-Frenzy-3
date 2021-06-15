import java.util.regex.Matcher;

public class Product extends GamePlay
{
    private String name  ;
    private int price;
    private int size;
    private int time_present;
    private int available;// 0 = warehouse 1 = ground 2 = cat
    private int givenTime ;

    Product(String name , int price , int x , int y , int givenTime , int size){
        super(x , y);
        this.name = name ;
        this.price = price ;
        time_present = 0 ;
        available = 1 ;
        this.givenTime = givenTime ;
        this.size = size ;
    }

    Product(){}

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

    public int getPrice() {
        return price;
    }

    public void setTime_present(int time_present) {
        this.time_present = time_present;
    }

    public void setAvailable(int available) {
        this.available = available;
    }


    static class Egg extends Product{
        Egg(int x , int y){
            super("egg" , 15 , x , y , 4 ,1);
        }
    }
    static class Feather extends Product{
        Feather(int x , int y){
            super("feather" , 20 , x , y , 4 ,1);
        }
    }
    static class Milk extends Product{
        Milk(int x , int y){
            super("milk" , 25 , x , y , 4 , 1);
        }
    }
    static class Flour extends Product{
        Flour(int x , int y){
            super("flour" , 40 , x , y , 5 , 2);
        }
    }
    static class Fabric extends Product{
        Fabric(int x , int y){
            super("fabric" , 50 , x , y , 5 , 2);
        }
    }
    static class PacketMilk extends Product{
    PacketMilk(int x , int y){
        super("packetMilk" , 60 , x, y , 5 , 2);
    }
}
    static class Bread extends Product{
        Bread(int x , int y){
            super("bread" , 80 , x ,y , 6 , 4);
        }
    }

    static class Clothing extends Product{
        Clothing(int x , int y ){
            super("clothing" , 100 , x , y , 6 , 4);
        }
    }
    static class IceCream extends Product{
        IceCream(int x ,  int y){
            super("iceCream" , 120 , x , y , 6 , 4);
        }
    }
}


