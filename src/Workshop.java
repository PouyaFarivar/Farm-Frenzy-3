import java.io.FileNotFoundException;
import java.util.SplittableRandom;

public class Workshop extends GamePlay
{
    private int id ;
    private String name ;
    private String entryProduct;
    private String outputProduct;
    private int level ;// 0 = not build
    private int working  ; // -1 = not working // 1 = one product inside 2 = 2 products inside
    private int operation_time;
    private int price;
    private int numInside ;

    public Workshop (int id , String name , String entryProduct , String outputProduct , int operation_time , int price , int x , int y){
        super(x , y);
        this.name = name ;
        this.entryProduct = entryProduct ;
        this.outputProduct = outputProduct ;
        this.id = id ;
        level = 0 ;
        working = -1;
        this.operation_time = operation_time ;
        this.price = price ;
        numInside = 0 ;
    }

    Workshop(){}

    public Product work () throws FileNotFoundException
    {
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

    public int getId() {
        return id;
    }

    static class FlourMill extends Workshop{
        public FlourMill(int x , int y , int id){
            super(id , "flourMill", "egg" , "flour" , 4 , 150 , x , y);
        }
    }
    static class Bakery extends Workshop{
        public Bakery(int x , int y, int id){
            super(id , "bakery" , "flour" , "bread" , 5 , 250 , x , y);
        }
    }
    static class MilkPacking extends Workshop{
        public MilkPacking(int x , int y, int id){
            super(id , "milkPacking" , "milk" , "packetMilk" , 6 , 400 , x , y);
        }
    }
    static class IceCreamShop extends Workshop{
        public IceCreamShop(int x , int y, int id){
            super(id , "iceCreamShop" , "packetMilk" , "iceCream" , 7 , 550 , x , y);
        }
    }
    static class WeavingFactory extends Workshop{
        public WeavingFactory(int x , int y, int id){
            super(id , "weavingFactory" , "feather" , "fabric" , 5 , 250 , x , y);
        }
    }
    static class SewingFactory extends Workshop{
        public SewingFactory(int x , int y, int id){
            super(id , "sewingFactory" , "fabric" , "clothing" , 6 , 400 , x , y);
        }
    }
    static class HenMaker extends Workshop{
        public HenMaker(int x , int y, int id){
            super(id , "henMaker" , "egg" , "hen" , 5 , 300 , x , y);
        }
    }

}

