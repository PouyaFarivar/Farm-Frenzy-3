import java.util.regex.Matcher;

public class Product extends GamePlay
{
    String name  ;
    int price;
    int size;
    int time_present;
    boolean available;
    final int givenTime = 10 ;

    Product(String name , int price ){
        this.name = name ;
        this.price = price ;
        time_present = 0 ;
        available = true ;
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

    public void setTime_present(int time_present) {
        this.time_present = time_present;
    }

    static class Egg extends Product{
        Egg(){
            super("egg" , 15);
        }
    }
    static class Feather extends Product{
        Feather(){
            super("feather" , 20);
        }
    }
    static class Milk extends Product{
        Milk(){
            super("milk" , 25);
        }
    }
    static class Flour extends Product{
        Flour(){
            super("flour" , 40);
        }
    }
    static class Fabric extends Product{
        Fabric(){
            super("fabric" , 50);
        }
    }
    static class Bread extends Product{
        Bread(){
            super("bread" , 80);
        }
    }
    static class PacketMilk extends Product{
        PacketMilk(){
            super("packetMilk" , 60);
        }
    }
    static class Clothing extends Product{
        Clothing(){
            super("clothing" , 100);
        }
    }
    static class IceCream extends Product{
        IceCream(){
            super("iceCream" , 120);
        }
    }
}


