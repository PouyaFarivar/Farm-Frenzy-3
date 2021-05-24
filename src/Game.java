import java.util.LinkedList;

public class Game {
    private int turn ;
    private int coins ;
    private LinkedList<Product> products ;
    private LinkedList<Grass> grasses ;
    private LinkedList<Workshop> workshops ;
    private LinkedList<Defender> defenders ;
    private LinkedList<Domestic> domestics ;
    private LinkedList<Predator> predators ;
    private Well well ;
    private Warehouse warehouse ;
    private Truck truck ;
    Game (Level level){
        products = new LinkedList<>() ;
        grasses = new LinkedList<>() ;
        workshops = new LinkedList<>() ;
        defenders = new LinkedList<>() ;
        domestics = new LinkedList<>() ;
        predators = new LinkedList<>() ;
        well = new Well() ;
        warehouse = new Warehouse() ;
        truck = new Truck() ;
        turn = 0 ;
        coins = 0 ;

    }
}
