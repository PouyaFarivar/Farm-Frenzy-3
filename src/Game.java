import javax.xml.namespace.QName;
import java.util.LinkedList;
import java.util.Random;

public class Game {
    private Level level ;
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
    private Product [][] productMap ;
    private Grass [][] grassMap ;

    Game (Level level){
        this.level = level ;
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
        productMap = new Product[6][6] ;
        grassMap = new Grass[6][6] ;
        for (Workshop workshop : this.level.getWorkshops()){
            this.workshops.add(workshop);
        }
        for (Domestic domestic : this.level.getStartingAnimals()){
            this.domestics.add(domestic);
        }
    }

    public int buyAnimal (String name){//0 = done 1= wrong name 2 = not enough coins
        Animal animal = new Animal() ;
        Random random = new Random() ;
        int x = random.nextInt(6);
        int y = random.nextInt(6);
        if (name.equals("cat")){
            animal = new Defender.Cat(x, y) ;
        }else if (name.equals("dog")){
            animal = new Defender.Dog(x , y) ;
        }else if (name.equals("hen")){
            animal = new Domestic.Hen(x ,y) ;
        }else if (name.equals("ostrich")){
            animal = new Domestic.Ostrich(x , y) ;
        }else if (name.equals("buffalo")){
            animal = new Domestic.Buffalo(x , y) ;
        }else {
            return 1 ;
        }
        if (animal.getPrice_for_purchase() <= coins){
            coins = coins - animal.getPrice_for_purchase() ;
            if (animal.getType() == 1){
                domestics.add((Domestic)animal);
            }else if (animal.getType() == 2){
                defenders.add((Defender) animal);
            }
            return 0 ;
        }else {
            return 2 ;
        }
    }

    public int pickup (int x , int y){// 0 = done 1 = not enough space 2 = no product in given coordinates ;
        boolean done = false ;
        for (Product product : products){
            if (product.getX() == x && product.getY() == y){
                if (product.getSize()<=warehouse.remainingSpace()) {
                    warehouse.getProducts().add(product);
                    warehouse.setCounter(warehouse.getCounter() + product.getSize());
                    products.remove(product);
                    productMap[x][y] = null;
                }else {
                    return 1;
                }
                done = true ;
                break ;
            }
        }
        if (done == false){
            for (Predator predator : predators){
                if (predator.getX() == x && predator.getY() == y){
                    if (predator.getSizeInWarehouse()<=warehouse.remainingSpace()) {
                        warehouse.getPredators().add(predator);
                        warehouse.setCounter(warehouse.getCounter() + predator.getSizeInWarehouse());
                        predators.remove(predator);
                    }else {
                        return 1 ;
                    }
                    done = true ;
                    break;
                }
            }
        }
        if (done == false){
            return 2 ;
        }else {
            return 0;
        }
    }

    public int fillWell(){//0 = done 1 = not empty
        if (well.getAmount_left() == 0){
            well.fill();
            return 0 ;
        }else {
            return 1 ;
        }
    }

    public int plantGrass(int x , int y){// 0 = done 1 = coordinate not free
        if (grassMap[x][y] == null || grassMap[x][y].getHealth() == 0){
            Grass grass = new Grass(x , y);
            grasses.add(grass) ;
            grassMap[x][y] = grass ;
            return 0 ;
        }else {
            return 1 ;
        }
    }

    public int buildWorkShop(String name){// 0 = done 1 = u cant build that in this level 2 = no coins 3 = already built
        Workshop workshop = getWorkShopByName(name);
        if (workshop == null){
            return 1 ;
        } else if (workshop.getLevel() == 0){
            if (workshop.getPrice() <= coins){
                coins = coins = workshop.getPrice() ;
                workshop.setLevel(1);
                return 0 ;
            }else {
                return 2 ;
            }
        }else{
            return 3 ;
        }
    }

    public int work(String name){
        Workshop workshop = getWorkShopByName(name);


    }

    public int moveToTruckProduct (String productName){
       Product product =  warehouse.getProduct(productName);
    }

    public Workshop getWorkShopByName(String name){
        for (Workshop workshop : workshops){
            if (workshop.getName().equals(name)){
                return workshop ;
            }
        }
        return null ;
    }

}
