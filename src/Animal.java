public class Animal extends GamePlay
{
    int SizeInWarehouse;
    int price_for_purchase;
    int price_for_sale;

    public Animal(int pr_purchase, int pr_sale)
    {
        this.price_for_purchase=pr_purchase;
        this.price_for_sale=pr_sale;
    }


}
class Domestic extends Animal{
    Product product;
    int lives;

    public Domestic(int pr_purchase, int pr_sale)
    {
        super(pr_purchase, pr_sale);
    }

    class Hen extends Domestic{
        Egg egg=new Egg();

        public Hen(int pr_purchase, int pr_sale)
        {
            super(pr_purchase, pr_sale);
        }
    }
    class Ostrich extends Domestic{
        Feather feather= new Feather();

        public Ostrich(int pr_purchase, int pr_sale)
        {
            super(pr_purchase, pr_sale);
        }
    }

    class Buffalo extends Domestic{
        Milk milk=new Milk();
        public Buffalo(int pr_purchase, int pr_sale)
        {
            super(pr_purchase, pr_sale);
        }
    }

}
class Predator extends Animal{
    Cage cage;
    double speed;
    boolean in_cage=false;
    int time_captured;

    public Predator(int pr_purchase, int pr_sale)
    {
        super(pr_purchase, pr_sale);
    }

    void escape(){};
    void destroyProduct(){};

    class Lion extends Predator{

        public Lion(int pr_purchase, int pr_sale)
        {
            super(pr_purchase, pr_sale);
        }
    }
    class Bear extends Predator{
        public Bear(int pr_purchase, int pr_sale)
        {
            super(pr_purchase, pr_sale);
        }
    }
    class Tiger extends Predator{

        public Tiger(int pr_purchase, int pr_sale)
        {
            super(pr_purchase, pr_sale);
        }
    }
    public void ruin_product(){
    }

}
class Defender extends Animal{
    public Defender(int pr_purchase, int pr_sale)
    {
        super(pr_purchase, pr_sale);
    }

    class Cat extends Defender{
        public Cat(int pr_purchase, int pr_sale)
        {
            super(pr_purchase, pr_sale);
        }
    };
    class Dog extends Defender{
        public Dog(int pr_purchase, int pr_sale)
        {
            super(pr_purchase, pr_sale);
        }
    };

}
