import java.util.SplittableRandom;

public class Workshop extends GamePlay
{
    private String name ;
    private String entryProduct;
    private String outputProduct;
    private int level ;// 0 = not build
    private int working  ; // -1 = not working // 1 = one product inside 2 = 2 products inside
    private int operation_time;
    private int price;
    private int numInside ;

    public Workshop (String name , String entryProduct , String outputProduct , int operation_time , int price , int x , int y){
        super(x , y);
        this.name = name ;
        this.entryProduct = entryProduct ;
        this.outputProduct = outputProduct ;
        level = 0 ;
        working = -1;
        this.operation_time = operation_time ;
        this.price = price ;
        numInside = 0 ;
    }

    Workshop(){}

    public Product work (){
        if (working < operation_time){
            working = working + 1 ;
        }
        if (working == operation_time){
            Product product = new Product() ;
            working = -1 ;
            if (outputProduct.equals("flour")){
                product = new Product.Flour(0 , 0) ;
            }else if (outputProduct.equals("fabric")){
                product = new Product.Fabric(0 , 0) ;
            }else if (outputProduct.equals("packetMilk")){
                product = new Product.PacketMilk(0 , 0) ;
            }else if (outputProduct.equals("bread")){
                product = new Product.Bread( 0 , 0 ) ;
            }else if (outputProduct.equals("clothing")){
                product = new Product.Clothing(0 , 0) ;
            }else if (outputProduct.equals("iceCream")){
                product = new Product.IceCream(0  ,0) ;
            }
            return product ;
        }else {
            return null ;
        }
    }

    public int getWorking() {
        return working;
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

    public int getOperation_time() {
        return operation_time;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getEntryProduct() {
        return entryProduct;
    }

    public String getOutputProduct() {
        return outputProduct;
    }

    public int getNumInside() {
        return numInside;
    }

    public void setWorking(int working) {
        this.working = working;
    }

    public void setNumInside(int numInside) {
        this.numInside = numInside;
    }

    static class FlourMill extends Workshop{
        public FlourMill(int x , int y){
            super("flourMill", "egg" , "flour" , 4 , 150 , x , y);
        }
    }
    static class Bakery extends Workshop{
        public Bakery(int x , int y){
            super("bakery" , "flour" , "bread" , 5 , 250 , x , y);
        }
    }
    static class MilkPackaging extends Workshop{
        public MilkPackaging(int x , int y){
            super("milkPacking" , "milk" , "packetMilk" , 6 , 400 , x , y);
        }
    }
    static class IceCreamShop extends Workshop{
        public IceCreamShop(int x , int y){
            super("iceCreamShop" , "packetMilk" , "iceCream" , 7 , 550 , x , y);
        }
    }
    static class WeavingFactory extends Workshop{
        public WeavingFactory(int x , int y){
            super("weavingFactory" , "feather" , "fabric" , 5 , 250 , x , y);
        }
    }
    static class SewingFactory extends Workshop{
        public SewingFactory(int x , int y){
            super("sewingFactory" , "fabric" , "clothing" , 6 , 400 , x , y);
        }
    }

}

