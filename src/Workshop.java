public class Workshop extends GamePlay
{
    private String name ;
    private String entryProduct;
    private String outputProduct;
    private int level ;// 0 = not build
    private int operation_time;
    private int price;

    public Workshop (String name , String entryProduct , String outputProduct , int operation_time , int price){
        super(0 , 0);
        level = 0 ;
        this.name = name ;
        this.entryProduct = entryProduct ;
        this.outputProduct = outputProduct ;
        this.operation_time = operation_time ;
        this.price = price ;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getPrice() {
        return price;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    static class FlourMill extends Workshop{
        public FlourMill(){
            super("flourMill", "egg" , "flour" , 4 , 150);
        }
    }
    static class Bakery extends Workshop{
        public Bakery(){
            super("bakery" , "flour" , "bread" , 5 , 250);
        }
    }
    static class MilkPackaging extends Workshop{
        public MilkPackaging(){
            super("milkPacking" , "milk" , "packetMilk" , 6 , 400);
        }
    }
    static class IceCreamShop extends Workshop{
        public IceCreamShop(){
            super("iceCreamShop" , "packetMilk" , "iceCream" , 7 , 550);
        }
    }
    static class WeavingFactory extends Workshop{
        public WeavingFactory(){
            super("weavingFactory" , "feather" , "fabric" , 5 , 250);
        }
    }
    static class SewingFactory extends Workshop{
        public SewingFactory(){
            super("sewingFactory" , "fabric" , "clothing" , 6 , 400);
        }
    }

}

