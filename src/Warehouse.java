import java.util.ArrayList;

public class Warehouse extends GamePlay
{
    private int counter;
    private final int capacity=30;
    private ArrayList<Product> products;
    private ArrayList<Predator>  predators ;

    public Warehouse(Product p,Predator a)
    {
        this.products=new ArrayList<Product>();
        this.predators=new ArrayList<Predator>();
        this.predators.add(a);
        this.products.add(p);
    }
    public Warehouse(){
        counter = 0 ;
        this.products=new ArrayList<Product>();
        this.predators=new ArrayList<Predator>();
    }
    public void SellGoods(){}

    public Product getProduct (String name){
        for (Product product : products){
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    public Predator getPredator(String name){
        for (Predator predator : predators){
            if (predator.getName().equals(name)) {
                return predator;
            }
        }
        return null;
    }

    public int remainingSpace (){
        return capacity - counter ;
    }

    public ArrayList<Predator> getPredators() {
        return predators;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public int getCounter() {
        return counter;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
