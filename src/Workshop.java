public class Workshop extends GamePlay
{
    Product entry;
    Product output;
    int number_of_entries;
    int operation_time;
    int price;
    public Product return_goods(Product e)
    {
        this.entry=e;
        return this.output;
    }

}
class FlourMill extends Workshop{}
class Bakery extends Workshop{}
class MilkPackaging extends Workshop{}
class IceCreamShop extends Workshop{}
class WeavingFactory extends Workshop{}
class SewingFactory extends Workshop{}

