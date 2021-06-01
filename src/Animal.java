public class Animal extends GamePlay
{
    private String name ;
    private int type  ;//1 = DOMESTIC 2 = DEFENDER 3 = PREDATOR
    private int SizeInWarehouse;
    private int price_for_purchase;
    private int price_for_sale ;

    public Animal(String name , int type , int pr_purchase , int price_for_sale , int x , int y) {
        super(x , y);
        this.name = name ;
        this.type = type ;
        this.price_for_purchase=pr_purchase;
        this.price_for_sale = price_for_sale ;
    }

    public Animal(){}


    public int getPrice_for_purchase() {
        return price_for_purchase;
    }

    public int getType() {
        return type;
    }

    public int getSizeInWarehouse() {
        return SizeInWarehouse;
    }
}
class Domestic extends Animal{
    String productName;
    int lives;

    public Domestic(String name , String productName , int pr_purchase , int x , int y) {
        super(name, 1, pr_purchase, 0 , x , y);
        this.productName = productName;
        lives = 10;

    }

    static class Hen extends Domestic{

        public  Hen(int x , int y){
            super("hen" , "egg" , 100 , x , y);
        }
    }
   static class Ostrich extends Domestic{

        public Ostrich(int x , int y){
            super("ostrich" , "feather" , 200 , x , y);
        }


    }

    static class Buffalo extends Domestic{

        public Buffalo(int x ,int y){
            super("buffalo" , "milk" , 400 ,x ,y);
        }
    }

}
class Predator extends Animal{
    int speed ;
    int cageToStop ;
    boolean in_cage ;
    int time_captured ;

    public Predator(String name , int pr_sell ,int x , int y , int speed , int cageToStop)
    {
        super(name , 3, 0 , pr_sell , x , y );
        this.speed = speed ;
        this.cageToStop = cageToStop ;
        in_cage = false ;
        time_captured = 0 ;
    }

    void escape(){};
    void destroyProduct(){};

    static class Lion extends Predator{

        public Lion(int x , int y)
        {
            super("lion" , 150 , x ,y ,1 , 3);
        }
    }
    static class Bear extends Predator{
        public Bear(int x ,int y)
        {
            super("bear" , 200 , x , y , 1 , 4);
        }
    }
    static class Tiger extends Predator{

        public Tiger(int x , int y)
        {
            super("tiger" , 250, x, y , 2 , 4);

        }
    }
    public void ruin_product(){
    }

}
class Defender extends Animal{
    public Defender(String name , int pr_purchase , int x , int y)
    {
        super(name, 2 , pr_purchase , 0 ,x, y);
    }


    static class Cat extends Defender{
        public Cat(int x , int y)
        {
            super("cat" , 150 , x , y);
        }

    };
    static class Dog extends Defender{
        public Dog(int x , int y)
        {
            super("dog", 100 , x, y);
        }

    };

}
