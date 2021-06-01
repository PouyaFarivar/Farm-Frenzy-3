public class Product extends GamePlay
{
    String name  ;
    int price;
    int size;
    int time_present;
    boolean available;

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }
}
class Egg extends Product{}
class Feather extends Product{}
class Milk extends Product{}
class Flour extends Product{}
class Fabric extends Product{}
class Bread extends Product{}
class PacketMilk extends Product{}
class Clothing extends Product{}
class IceCream extends Product{}

