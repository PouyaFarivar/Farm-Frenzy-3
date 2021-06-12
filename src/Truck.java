import java.util.LinkedList;

public class Truck extends GamePlay
{
    private LinkedList<Predator> predators;
    private LinkedList<Product> products;
    final int capacity=15;
    final int operation_time=10;
    private int counter ;
    private int time ;

    Truck(){
        predators = new LinkedList<>() ;
        products = new LinkedList<>() ;
        counter = 0 ;
        time = -1 ;
    }

    public int getLeftSpace(){
        return capacity - counter ;
    }

    public Product getProduct (String name){
        for (Product product : products){
            if (product.getName().equals(name)){
                return product ;
            }
        }
        return null ;
    }

    public Predator getPredator (String name){
        for (Predator predator : predators){
            if (predator.getName().equals(name)){
                return predator ;
            }
        }
        return null ;
    }

    public LinkedList<Predator> getPredators() {
        return predators;
    }

    public LinkedList<Product> getProducts() {
        return products;
    }

    public int getCounter() {
        return counter;
    }

    public int getTime() {
        return time;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public void setTime(int time) {
        this.time = time;
    }
}

